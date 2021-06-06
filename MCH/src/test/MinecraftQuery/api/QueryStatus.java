package test.MinecraftQuery.api;

import test.MinecraftQuery.internal.QueryStatusTcp;
import test.MinecraftQuery.internal.QueryStatusTcpDepreciated;
import test.MinecraftQuery.internal.QueryStatusUdp;
import test.MinecraftQuery.internal.ServerDNS;

public final class QueryStatus {

    private final ServerDNS serverDNS;
    private final Protocol protocol;
    private final int timeOut;

    private Status status;

    public QueryStatus(Builder builder) throws QueryException {
        this.serverDNS = new ServerDNS(builder.hostName, builder.port);
        this.timeOut = builder.timeOut;
        this.protocol = builder.protocol;
    }

    public Status getStatus() throws QueryException {

        if (this.status != null)
            return this.status;

        return refreshStatus();
    }

    public Status refreshStatus() throws QueryException {

        switch (this.protocol) {
            case TCP_DEPRECIATED -> this.status = new QueryStatusTcpDepreciated(serverDNS, timeOut).getStatus();
            case TCP -> this.status = new QueryStatusTcp(serverDNS, timeOut).getStatus();
            case UDP_BASIC, UDP_FULL -> this.status = new QueryStatusUdp(protocol, serverDNS, this.timeOut).getStatus();
        }
        return this.status;
    }

    public static final class Builder {
        private final String hostName;
        private int port;
        private int timeOut = 1000;
        private Protocol protocol;

        public Builder(String hostName, int port, Protocol protocol, int timeOut) {
            this.hostName = hostName;
            this.port = port;
            this.protocol = protocol;
            this.timeOut = timeOut;
        }

        public Builder(String hostName, int port, Protocol protocol) {
            this.hostName = hostName;
            this.port = port;
            this.protocol = protocol;
        }

        public Builder reSetPort(int port) {
            this.port = port;
            return this;
        }

        public Builder reSetProtocol(Protocol protocol) {
            this.protocol = protocol;
            return this;
        }

        public Builder reSetTimeOut(int timeOut) {
            this.timeOut = timeOut;
            return this;
        }

        public Builder setProtocolTcp() {
            this.protocol = Protocol.TCP;
            return this;
        }

        public Builder setProtocolTcpDepreciated() {
            this.protocol = Protocol.TCP_DEPRECIATED;
            return this;
        }

        public Builder setProtocolUdpBasic() {
            this.protocol = Protocol.UDP_BASIC;
            return this;
        }

        public Builder setProtocolUdpFull() {
            this.protocol = Protocol.UDP_FULL;
            return this;
        }

        public QueryStatus build() throws QueryException {
            return new QueryStatus(this);
        }
    }

}