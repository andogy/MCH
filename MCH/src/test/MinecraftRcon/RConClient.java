package test.MinecraftRcon;

import test.MinecraftRcon.packet.ClientPacket;
import test.MinecraftRcon.packet.Packet;
import test.MinecraftRcon.packet.PacketType;
import test.MinecraftRcon.packet.ServerPacket;

import java.io.*;
import java.net.Socket;

/**
 * A client that can connect to a RCON server, authenticate and then send
 * minecraft commands.
 */
public class RConClient implements Closeable {

    /**
     * The default port for RCON used by minecraft
     */
    public static final int DEFAULT_RCON_PORT = 25566;

    /**
     * The API version
     */
    public static final String API_VERSION = "1.1.1";

    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;

    private boolean authenticated;

    /**
     * Creates a connection to the rcon server and tries to authenticate using
     * the given password
     *
     * @param address
     *         the minecraft server address
     * @param port
     *         the rcon port
     * @param password
     *         the login password
     *
     * @throws IOException
     *         if the socket fails to connect or one of the streams fail to
     *         read/write
     * @throws AuthenticationException
     *         if the client fails to authenticate at the server
     */
    public RConClient(String address, int port, String password) throws IOException, AuthenticationException {
        createConnection(address, port);
        authenticate(password);
    }

    /**
     * Creates a connection to the rcon server using the default RCON port
     * (25575) and tries to authenticate using the given password
     *
     * @param address
     *         the minecraft server address
     * @param password
     *         the rcon port
     *
     * @throws IOException
     *         if the socket fails to connect or one of the streams fails to
     *         read/write
     * @throws AuthenticationException
     *         if the client fails to authenticate at the server
     */
    public RConClient(String address, String password) throws IOException, AuthenticationException {
        this(address, DEFAULT_RCON_PORT, password);
    }

    /**
     * Creates a connection to the rcon server
     *
     * @param address
     *         the minecraft server address
     * @param port
     *         the rcon port
     *
     * @throws IOException
     *         if the socket fails to connect or one of the streams fails to
     *         read/write
     */
    public RConClient(String address, int port) throws IOException {
        createConnection(address, port);
    }

    /**
     * Creates a connection to the rcon server using the default RCON port
     * (25575)
     *
     * @param address
     *         the minecraft server address
     *
     * @throws IOException
     *         if the socket fails to connect or one of the streams fails to
     *         read/write
     */
    public RConClient(String address) throws IOException {
        this(address, DEFAULT_RCON_PORT);
    }

    private void createConnection(String host, int port) throws IOException {
        socket = new Socket(host, port);
        outputStream = new DataOutputStream(socket.getOutputStream());
        inputStream = new DataInputStream(socket.getInputStream());
    }

    /**
     * @param password
     *         the password
     *
     * @throws IOException
     *         if one of the streams fails to read/write
     * @throws AuthenticationException
     *         if the client is already authenticated or
     */
    public void authenticate(String password) throws IOException, AuthenticationException {
        if (authenticated)
            throw new AuthenticationException("Already authenticated", AuthenticationException.ErrorType.ALREADY_AUTHENTICATED);
        Packet loginPacket = new ClientPacket(PacketType.AUTH, password);
        loginPacket.writeTo(outputStream);
        Packet loginResponse = new ServerPacket(inputStream);
        if (loginResponse.getType() != PacketType.AUTH_RESPONSE)
            throw new InvalidPacketException("Packet type should be AUTH_RESPONSE (" + PacketType.AUTH_RESPONSE.getId() + ")", loginResponse);
        if (loginResponse.getRequestID() == loginPacket.getRequestID())
            authenticated = true;
        else if (loginResponse.getRequestID() == Packet.REQUEST_ID_AUTH_FAIL)
            throw new AuthenticationException("Failed to authenticate at server" + socket.getInetAddress().getHostAddress() + ", port " + socket.getPort(), AuthenticationException.ErrorType.WRONG_PASSWORD);
    }

    /**
     * Returns if the client is already authenticated, either by using
     * {@link RConClient#authenticate(String)} or one of the constructors that
     * calls {@link RConClient#authenticate(String)}
     *
     * @return if the client is authenticated
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

    /**
     * Sends a command to the remote server, waits for it to complete and
     * returns the output of the command
     *
     * @param command
     *         the command to send
     *
     * @return the output of the command
     *
     * @throws AuthenticationException
     *         if you are not authenticated
     * @throws IOException
     *         if an I/O error occurs
     */
    public String sendCommand(String command) throws AuthenticationException, IOException {
        if (!authenticated)
            throw new AuthenticationException("Not yet authenticated", AuthenticationException.ErrorType.NOT_AUTHENTICATED);
        Packet commandPacket = new ClientPacket(PacketType.COMMAND, command);
        commandPacket.writeTo(outputStream);
        StringBuilder builder = new StringBuilder();
        Packet lastPacket;
        boolean repeated = false;
        while ((lastPacket = new ServerPacket(inputStream)).getLength() == 4096) {
            if (lastPacket.getType() != PacketType.COMMAND_RESPONSE)
                throw new InvalidPacketException("Received packet of invalid type " + lastPacket.getType(), lastPacket);
            builder.append(lastPacket.getPayloadAsString());
            repeated = true;
        }
        if (lastPacket.getLength() == 10)
            throw new InvalidPacketException("Packet payload of last packet empty " + (!repeated ? "(this could mean an invalid command)" : "(this could mean a server fault)"), lastPacket);
        else
            builder.append(lastPacket.getPayloadAsString());
        return builder.toString();
    }

    /**
     * Closes the connection to the server
     */
    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
    }

}
