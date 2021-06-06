package test.MinecraftQuery.internal;

import test.MinecraftQuery.api.QueryException;

import java.io.*;
import java.net.Socket;

public abstract class QueryStatusTcpBase extends QueryStatusBase {

    protected Socket socket = null;
    protected OutputStream outputStream;
    protected DataOutputStream dataOutputStream;

    protected InputStream inputStream;
    protected DataInputStream dataInputStream;

    public QueryStatusTcpBase(ServerDNS serverDNS, int timeOut) {
        super(serverDNS, timeOut);
    }


    protected void connect()
            throws QueryException {
        try {
            this.socket = new Socket();

            this.socket.setSoTimeout(this.timeOut);
            this.socket.connect(this.inetSocketAddress, this.timeOut);

            this.outputStream = this.socket.getOutputStream();
            this.dataOutputStream = new DataOutputStream(this.outputStream);

            this.inputStream = this.socket.getInputStream();
            this.dataInputStream = new DataInputStream(this.inputStream);
        } catch (IOException e) {
            throw new QueryException(QueryException.ErrorType.NETWORK_PROBLEM, "TCP Connection to " + this.inetSocketAddress.getHostString() + ":" + this.inetSocketAddress.getPort() + "failed");
        }
    }

    protected void disconnect () {
        try {
            if (this.dataOutputStream != null)
                this.dataOutputStream.close();

            if (this.outputStream != null) 
                this.outputStream.close();

            if (this.dataInputStream != null)
                this.dataInputStream.close();

            if (this.inputStream != null)
                this.inputStream.close();

            if (this.socket != null)
                this.socket.close();
        } catch (IOException e) {
            // failed to disconnect?!, just keep going then
        } finally {
            this.dataOutputStream = null;
            this.outputStream = null;
            this.dataInputStream = null;
            this.inputStream = null;
            this.socket = null;
        }
    }

    protected abstract void sendHandShake() throws QueryException;
    
    protected abstract String receiveStatusResponse() throws QueryException;
    
}