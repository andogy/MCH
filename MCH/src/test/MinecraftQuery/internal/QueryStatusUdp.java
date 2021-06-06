package test.MinecraftQuery.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;

import test.MinecraftQuery.api.Protocol;
import test.MinecraftQuery.api.QueryException;
import test.MinecraftQuery.api.Status;

public class QueryStatusUdp extends QueryStatusBase {

    private static final byte[] MAGIC_BYTES = {(byte) 0xFE, (byte) 0xFD};
    private static final byte[] MAGIC_BYTES_FULLSTAT = {(byte) 0x73, (byte) 0x70, (byte) 0x6C,
            (byte) 0x69, (byte) 0x74, (byte) 0x6E, (byte) 0x75, (byte) 0x6D,
            (byte) 0x00, (byte) 0x80, (byte) 0x00 };

    private static final byte HANDSHAKE_BYTE = 0x9;
    private static final byte STAT_BYTE = 0x0;

    private Protocol protocol;
    private int challengeToken;
    private int sessionId;
    private DatagramSocket socket;

    public QueryStatusUdp(Protocol protocol, ServerDNS serverDNS, int timeOut) {
        super(serverDNS, timeOut);
        this.protocol = protocol;
        this.sessionId = (new Random().nextInt((0x7FFFFFFF - 0x1) + 1) + 0x1) & 0x0F0F0F0F;
    }

    @Override
    public Status getStatus()
            throws QueryException {

        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(this.timeOut);

            // send handshake to server to get token for further processing
            int latency = new BigInteger(requestData(true)).intValue();

            // request basic or full stats from the server
            byte[] response = requestData(false);

            // finally build the status object
            this.status = new StatusBuilder()
                                .setProtocol(protocol)
                                .setLatency(latency)
                                .setServerDNS(this.serverDNS)
                                .setData(response)
                                .build();
        } catch (IOException e) {
            throw new QueryException(QueryException.ErrorType.NETWORK_PROBLEM);
        } finally {
            if (socket != null) {
                socket.close();
            }
        }

        return this.status;
    }

    private byte getMessageByte(boolean isHandshake) {
        return (isHandshake ? HANDSHAKE_BYTE : STAT_BYTE);
    }

    private short getOptimalBufferSize(boolean isHandshake) {
        short bufferSize = 4096; // UDP FULL

        if (isHandshake) {
            bufferSize = 32;
        } else if (this.protocol == Protocol.UDP_BASIC) {
            bufferSize = 1024;
        }

        return bufferSize;
    }

    private byte[] requestData(boolean isHandshake)
            throws QueryException {

        ByteArrayOutputStream b = new ByteArrayOutputStream(128);
        DataOutputStream d = new DataOutputStream(b);

        try {
            d.write(MAGIC_BYTES);
            d.writeByte(getMessageByte(isHandshake));
            d.writeInt(this.sessionId);

            if (!isHandshake) {
                d.writeInt(this.challengeToken);
            }

            if (!isHandshake && protocol == Protocol.UDP_FULL) {
                d.write(new byte[] {0x00, 0x00, 0x00, 0x00});
            }

            byte[] sendData = b.toByteArray();

            DatagramPacket dataPacket = new DatagramPacket(sendData, sendData.length, this.inetSocketAddress);

            this.pingStart = System.currentTimeMillis();

            this.socket.send(dataPacket);
        } catch (IOException e) {
            throw new QueryException(QueryException.ErrorType.SEND_FAILED, "Failed to send packet to socket");
        }

        return receiveMessage(isHandshake);
    }

    private byte[] receiveMessage(boolean isHandshake)
            throws QueryException {

        byte[] dataBuffer;

        try {
            byte[] receiveData = new byte[getOptimalBufferSize(isHandshake)];
            DatagramPacket receiveDataPacket = new DatagramPacket(receiveData, receiveData.length);

            this.socket.receive(receiveDataPacket);

            // set time when the  message is received for latency calculation
            this.pingEnd = System.currentTimeMillis();

            ByteArrayInputStream b = new ByteArrayInputStream(Arrays.copyOf(receiveDataPacket.getData(), receiveDataPacket.getLength()));
            DataInputStream d = new DataInputStream(b);

            byte msgTypeRcv = d.readByte();
            int sessionIdRcv = d.readInt();

            if (sessionIdRcv != this.sessionId) {
                throw new QueryException(QueryException.ErrorType.INVALID_RESPONSE,
                        String.format("Session ID received '%d' is " +
                                "different from sent '%d'", sessionIdRcv, this.sessionId));
            }

            if (msgTypeRcv != getMessageByte(isHandshake)) {
                throw new QueryException(QueryException.ErrorType.INVALID_RESPONSE,
                        String.format("Message type received '%d' is " +
                                "different from sent '%d'", msgTypeRcv, getMessageByte(isHandshake)));
            }

            if (!isHandshake && protocol == Protocol.UDP_FULL) {
                byte[] fullStatCheck = new byte[MAGIC_BYTES_FULLSTAT.length];
                d.read(fullStatCheck);
                
                // According to https://wiki.vg/Query this is now meaningless and can be ignored
                // so in case this will cause an error later on this code can be removed
                if (!Arrays.equals(fullStatCheck, MAGIC_BYTES_FULLSTAT)) {
                    throw new QueryException(QueryException.ErrorType.INVALID_RESPONSE,
                            "Expected data stream is invalid, missing magic bytes for full stat!");
                }
            }

            dataBuffer = new byte[b.available()];
            d.read(dataBuffer);

            if (isHandshake) {
                try {
                    this.challengeToken = Integer.parseInt(new String(dataBuffer).trim());
                } catch (NumberFormatException e) {
                    throw new QueryException(QueryException.ErrorType.INVALID_RESPONSE,
                            "Invalid challenge token received");
                }

                // in case of handshake message calculate and return ping to server
                ByteBuffer bb = ByteBuffer.allocate(4);
                bb.putInt((int) (this.pingEnd - this.pingStart));
                dataBuffer = bb.array();
            }
        } catch (SocketTimeoutException e) {
            throw new QueryException(QueryException.ErrorType.TIMEOUT_REACHED,"time out");
        } catch (IOException e) {
            throw new QueryException(QueryException.ErrorType.INVALID_RESPONSE,
                    "Server returned invalid response!");
        }

        return dataBuffer;
    }

}