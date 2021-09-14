package test.MinecraftRcon.packet;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * A RCON packet, either sent by the server or the client.
 */
public abstract class Packet {

    public static final int REQUEST_ID_AUTH_FAIL = -1;

    private static final Charset PAYLOAD_CHARSET = StandardCharsets.US_ASCII;

    private static int requestIdCounter = 1;

    protected int length;
    protected int requestID;
    protected PacketType type;
    protected byte[] payload;

    public Packet(PacketType type, String payload) {
        this.type = type;
        this.requestID = requestIdCounter++;
        this.payload = payload.getBytes(PAYLOAD_CHARSET);
        this.length = Integer.BYTES * 2 + this.payload.length + Byte.BYTES * 2;
    }

    public Packet(InputStream stream) throws IOException {
        readFrom(stream);
    }

    protected static ByteBuffer getByteBuffer(InputStream stream, int length) throws IOException {
        byte[] lengthBytes = new byte[length];
        if (stream.read(lengthBytes) == -1)
            throw new EOFException();
        return ByteBuffer.wrap(lengthBytes).order(ByteOrder.LITTLE_ENDIAN);
    }

    protected abstract void readFrom(InputStream stream) throws IOException;

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public PacketType getType() {
        return type;
    }

    public void setType(PacketType type) {
        this.type = type;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
        this.length = Integer.BYTES * 2 + payload.length + Byte.BYTES * 2;
    }

    public void setPayload(String payload) {
        setPayload(payload.getBytes(PAYLOAD_CHARSET));
    }

    public String getPayloadAsString() {
        return new String(payload, PAYLOAD_CHARSET);
    }

    public int getLength() {
        return length;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(this.length + 4).order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(length);
        buffer.putInt(requestID);
        buffer.putInt(type.getId());
        buffer.put(payload);
        buffer.put((byte) 0);
        buffer.put((byte) 0);
        outputStream.write(buffer.array());
    }

    @Override
    public String toString() {
        return String.format("Packet[length=\"%s\",requestId=\"%s\",type=\"%s\" (id: %s), payload=\"%s\"]", length, requestID, type, type.getId(), getPayloadAsString());
    }

}
