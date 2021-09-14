package test.MinecraftRcon;

import test.MinecraftRcon.packet.Packet;

import java.io.IOException;


/**
 * Thrown when a packet is sent that shouldn't be there, e.g. the server sends a
 * packet that violates the protocol
 */
public class InvalidPacketException extends IOException {

	private static final long serialVersionUID = 7359011692721587596L;
	private final Packet invalidPacket;

	/**
	 * Constructs a new InvalidPacketException, given a message and the invalid
	 * packet
	 * 
	 * @param message
	 *            the message to show
	 * @param invalidPacket
	 *            the packet that was invalid
	 */
	public InvalidPacketException(String message, Packet invalidPacket) {
		super(message);
		this.invalidPacket = invalidPacket;
	}

	/**
	 * Returns the packet that was considered invalid
	 * 
	 * @return the invalid {@link Packet}
	 */
	public Packet getInvalidPacket() {
		return invalidPacket;
	}

}
