package test.MinecraftRcon.packet;

import test.MinecraftRcon.MalformedPacketException;

import java.io.IOException;
import java.io.InputStream;

/**
 * A RCON packet sent by the server.
 */
public class ServerPacket extends Packet {

	public ServerPacket(PacketType type, String payload) {
		super(type, payload);
	}

	public ServerPacket(InputStream inputStream) throws IOException {
		super(inputStream);
	}

	@Override
	protected void readFrom(InputStream stream) throws IOException {
		length = getByteBuffer(stream, 4).getInt();
		if (length < 10)
			throw new MalformedPacketException("Packet length lower than ten (minimum package size)");
		requestID = getByteBuffer(stream, 4).getInt();
		type = PacketType.fromID(getByteBuffer(stream, 4).getInt(), false);
		if (type == null)
			throw new MalformedPacketException("Packet type is none of known packet types");
		int payloadLength = length - (Integer.BYTES * 2 + Byte.BYTES * 2);
		payload = new byte[payloadLength];
		for (int i = 0; i < payloadLength; i++) {
			payload[i] = (byte) stream.read();
		}
		if (stream.read() != 0)
			throw new MalformedPacketException("Payload terminator byte not zero");
		if (stream.read() != 0)
			throw new MalformedPacketException("Packet terminator byte not zero");
	}

}
