package test.MinecraftQuery.internal;

import test.MinecraftQuery.api.QueryException;
import test.MinecraftQuery.api.Status;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;

public abstract class QueryStatusBase {
    
    protected final InetSocketAddress inetSocketAddress;
    protected final ServerDNS serverDNS;
    protected int timeOut = 0;

    protected long pingStart = 0;
    protected long pingEnd = 0;

    protected Status status;

    public QueryStatusBase(ServerDNS serverDNS, int timeOut) {
        this.serverDNS = serverDNS;
        inetSocketAddress = new InetSocketAddress(serverDNS.getTargetHostName(), serverDNS.getPort());
        this.timeOut = timeOut;
    }

    public abstract Status getStatus() throws QueryException;

    protected int calculateLatency() {
        return (int) (this.pingEnd - this.pingStart);
    }

    protected void writeVarInt(
            DataOutputStream out,
            int paramInt)
            throws QueryException {
        try {
            while (true) {
                if ((paramInt & -128) == 0) {
                    out.writeByte(paramInt);
                    return;
                }

                out.writeByte(paramInt & 127 | 128);
                paramInt >>>= 7;
            }
        } catch (IOException e) {
            throw new QueryException(QueryException.ErrorType.SEND_FAILED, "Failed to write VarInt to socket");
        }
    }

    protected int readVarInt(DataInputStream in) 
            throws QueryException {
        int i = 0;
        int j = 0;
        int k;
        do {
            try {
                k = in.readByte();
            } catch (IOException e) {
                throw new QueryException(QueryException.ErrorType.INVALID_RESPONSE, "Server returned invalid response!");
            }
            i |= (k & 127) << j++ * 7;
            if (j > 5)
                throw new QueryException(QueryException.ErrorType.INVALID_RESPONSE, "VarInt too big");
        } while ((k & 128) == 128);
        return i;
    }
}