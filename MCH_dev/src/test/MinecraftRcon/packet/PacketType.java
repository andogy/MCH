package test.MinecraftRcon.packet;

/**
 * The type of a packet.
 * 
 * <table border="1" summary="Packet IDs">
 * <tr>
 * <th>Packet ID</th>
 * <th>Name</th>
 * <th>Description</th>
 * </tr>
 * <tr>
 * <td>3</td>
 * <td>Login</td>
 * <td>Outgoing payload: password. If the server returns a packet with the same
 * request ID, auth was successful (note: packet type is 2, not 3). If you get a
 * request ID of -1, auth failed (wrong password).</td>
 * </tr>
 * <tr>
 * <td>2</td>
 * <td>Command</td>
 * <td>Outgoing payload should be the command to run, e.g.
 * 
 * <pre>
 * time set 0
 * </pre>
 * 
 * </td>
 * </tr>
 * <tr>
 * <td>0</td>
 * <td>Command response</td>
 * <td>Incoming payload is the output of the command, though many commands
 * return nothing, and there's no way of detecting unknown commands. The output
 * of the command may be split over multiple packets, each containing 4096 bytes
 * (less for the last packet). Each packet contains part of the payload (and the
 * two-byte padding). The last packet sent is the end of the output.</td>
 * </tr>
 * </table>
 * @see <a href="http://wiki.vg/RCON">http://wiki.vg/RCON</a>
 */
public enum PacketType {
	AUTH(3, true), AUTH_RESPONSE(2, false), COMMAND(2, true), COMMAND_RESPONSE(0, false);

	private final int id;
	private final boolean clientside;

	PacketType(int id, boolean clientside) {
		this.id = id;
		this.clientside = clientside;
	}

	/**
	 * The type id as used in the raw packet data.
	 * 
	 * @return the type id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns wether the packet was sent by the client or not
	 * 
	 * @return true if this packet was sent by the client
	 */
	public boolean isClientside() {
		return clientside;
	}

	/**
	 * Gets the PacketType using the type id from a raw packet
	 * 
	 * @param id
	 *            the type id as used in the raw packet data
	 * @param clientside
	 *            wether the packet was sent by the client
	 * @return the related PacketType or null if no PacketType belongs to this
	 *         id and is client-/serverside
	 * @see PacketType#getId()
	 */
	public static PacketType fromID(int id, boolean clientside) {
		for (PacketType type : values()) {
			if (type.getId() == id && type.clientside == clientside)
				return type;
		}
		return null;
	}
}