package test.MinecraftQuery.internal;

import test.MinecraftQuery.api.Protocol;
import test.MinecraftQuery.api.QueryException;
import test.MinecraftQuery.api.Status;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class QueryStatusTcpDepreciated extends QueryStatusTcpBase {

    public QueryStatusTcpDepreciated(final ServerDNS serverDNS, final int timeOut) {
        super(serverDNS, timeOut);
    }

    @Override
    public Status getStatus() throws QueryException {

        try {
            connect();

            sendHandShake();

            String response = receiveStatusResponse();

            status = new StatusBuilder().setProtocol(Protocol.TCP_DEPRECIATED).setLatency(calculateLatency()).setServerDNS(this.serverDNS).setData(response).build();
        } finally {
            disconnect();
        }

        return this.status;
    }

    @Override
    protected void sendHandShake() throws QueryException {

        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        final DataOutputStream handshake = new DataOutputStream(b);

        final String mcPingStr = "MC|PingHost";

        try {

            handshake.writeByte(254);
            handshake.writeByte(1);
            handshake.writeByte(250);
            handshake.writeShort(mcPingStr.length());
            handshake.writeBytes(new String(mcPingStr.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_16BE));
            handshake.writeShort(7 + (2 * this.serverDNS.getTargetHostName().length()));
            handshake.writeByte(Protocol.TCP_DEPRECIATED.getValue());
            handshake.writeShort(this.serverDNS.getTargetHostName().length());
            handshake.writeBytes(new String(this.serverDNS.getTargetHostName().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_16BE));
            handshake.writeInt(this.serverDNS.getPort());

            this.pingStart = System.currentTimeMillis();

            dataOutputStream.write(b.toByteArray());
            dataOutputStream.flush();
        } catch (final IOException e) {
            throw new QueryException(QueryException.ErrorType.SEND_FAILED, "Failed to write handshake to socket");
        }
    }

    @Override
    protected String receiveStatusResponse() throws QueryException {
        String response = "";

        try {
            int id = dataInputStream.readUnsignedByte();

            if (id == -1) {
                throw new QueryException(QueryException.ErrorType.NETWORK_PROBLEM, "Premature end of stream");
            } else if (id != 0xFF) {
                //we want a status response
                throw new QueryException(QueryException.ErrorType.INVALID_RESPONSE, String.format("Invalid packetID: %d", id));
            }

            // set time when the first message is received for latency calculation
            this.pingEnd = System.currentTimeMillis();

            int length = this.dataInputStream.readUnsignedShort();

            if (length == -1) {
                throw new QueryException(QueryException.ErrorType.NETWORK_PROBLEM, "Premature end of stream");
            } else if (length == 0) {
                throw new QueryException(QueryException.ErrorType.INVALID_RESPONSE, "Invalid length of response");
            }

            if (length != this.dataInputStream.available()) {
                // Server responded with smaller string length than what
                // is available on the socket, using this amount
                length = dataInputStream.available();
            }

            byte[] byteStream = new byte[length];
            if (this.dataInputStream.read(byteStream, 0, length) != length) {
                throw new QueryException(QueryException.ErrorType.NETWORK_PROBLEM, "Premature end of stream");
            }

            response = new String((new String(byteStream, StandardCharsets.UTF_16BE)).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new QueryException(QueryException.ErrorType.INVALID_RESPONSE,"invalid response");
        }

        return response;
    }


}