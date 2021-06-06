package test.MinecraftQuery.internal;

import test.MinecraftQuery.api.Protocol;
import test.MinecraftQuery.api.QueryException;
import test.MinecraftQuery.api.Status;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class QueryStatusTcp extends QueryStatusTcpBase {

    public QueryStatusTcp(ServerDNS serverDNS, int timeOut) {
        super(serverDNS, timeOut);
    }

    @Override
    public Status getStatus() {
        try {

            try {
                connect();

                sendHandShake();

                sendStatusRequest();

                String response = receiveStatusResponse();

                try {
                    long pingStart = System.currentTimeMillis();
                    sendPingRequest(pingStart);

                    long pingStartReceived = receivePingResponse();
                    long pingEnd = System.currentTimeMillis();

                    if (pingStart == pingStartReceived) {
                        this.pingStart = pingStart;
                        this.pingEnd = pingEnd;
                    }
                } catch (QueryException ignored) {

                }

                status = new StatusBuilder().setProtocol(Protocol.TCP).setLatency(calculateLatency()).setServerDNS(this.serverDNS).setData(response).build();
            } finally {
                disconnect();
            }
        } catch (QueryException e) {
            e.printStackTrace();
        }
        return this.status;
    }

    @Override
    protected void sendHandShake() throws QueryException {

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream handshake = new DataOutputStream(b);

        try {
            handshake.writeByte(0x00);
            writeVarInt(handshake, Protocol.TCP.getValue());
            writeVarInt(handshake, this.serverDNS.getTargetHostName().length());
            handshake.writeBytes(this.serverDNS.getTargetHostName());
            handshake.writeShort(this.serverDNS.getPort());
            writeVarInt(handshake, 1);
            writeVarInt(dataOutputStream, b.size());

            this.pingStart = System.currentTimeMillis();

            dataOutputStream.write(b.toByteArray());
            dataOutputStream.flush();
        } catch (IOException e) {
            throw new QueryException(QueryException.ErrorType.SEND_FAILED, "Failed to write handshake to socket");
        }
    }

    private void sendStatusRequest() throws QueryException {
        try {
            this.dataOutputStream.writeByte(1);
            this.dataOutputStream.writeByte(0);
            this.dataOutputStream.flush();
        } catch (IOException e) {
            throw new QueryException(QueryException.ErrorType.SEND_FAILED, "Failed to write status request to socket");
        }
    }

    @Override
    protected String receiveStatusResponse() throws QueryException {

        readVarInt(dataInputStream);
        int id = readVarInt(dataInputStream);

        if (id == -1) {
            throw new QueryException(QueryException.ErrorType.NETWORK_PROBLEM, "Premature end of stream");
        } else if (id != 0) {
            throw new QueryException(QueryException.ErrorType.INVALID_RESPONSE, "Invalid packetID: " + id);
        }

        this.pingEnd = System.currentTimeMillis();

        int jsonLength = readVarInt(dataInputStream);

        if (jsonLength == -1) {
            throw new QueryException(QueryException.ErrorType.NETWORK_PROBLEM, "Premature end of stream");
        }

        if (jsonLength == 0) {
            throw new QueryException(QueryException.ErrorType.INVALID_RESPONSE, "Invalid length of JSON response");
        }

        byte[] byteStream = new byte[jsonLength];
        try {
            dataInputStream.readFully(byteStream);  //read json string
        } catch (IOException e) {
            throw new QueryException(QueryException.ErrorType.INVALID_RESPONSE, "Server returned invalid response!");
        }

        return new String(byteStream);
    }

    private void sendPingRequest(long time) throws QueryException {
        try {
            this.dataOutputStream.writeByte(9);
            this.dataOutputStream.writeByte(1);
            this.dataOutputStream.writeLong(time);
            this.dataOutputStream.flush();
        } catch (IOException e) {
            throw new QueryException(QueryException.ErrorType.SEND_FAILED, "Failed to write ping request to socket");
        }
    }

    private long receivePingResponse() throws QueryException {

        long timeSent;

        readVarInt(dataInputStream);
        int id = readVarInt(dataInputStream);
        if (id == -1) {
            throw new QueryException(QueryException.ErrorType.NETWORK_PROBLEM, "Premature end of stream");
        } else if (id != 1) {
            throw new QueryException(QueryException.ErrorType.INVALID_RESPONSE, "Invalid packetID: " + id);
        }

        try {
            timeSent = dataInputStream.readLong();
        } catch (IOException e) {
            throw new QueryException(QueryException.ErrorType.INVALID_RESPONSE);
        }

        return timeSent;
    }


}