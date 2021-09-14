package test.MinecraftQuery.api;

import com.google.gson.Gson;

public class Status {
    private Server server;
    private Version version;
    private Players players;
    private String description;
    private String favicon;
    private Mods modinfo;
    private String gametype;
    private String map;

    public Server getServer() {
        return server;
    }

    public Version getVersion() {
        return version;
    }

    public Players getPlayers() {
        return players;
    }

    public String getDescription() {
        return description;
    }

    public String getFavIcon() {
        return favicon;
    }

    public String getFavIconStripped() {
        try {
            return favicon.replace("data:image/png;base64,", "");
        } catch(Exception ex) {
            return null;
        }
    }


    public Mods getMods() {
        return modinfo;
    }

    public static class Server {
        private String targethostname;
        private String hostname;
        private String ipaddress;
        private int port;
        private int queryport;
        private int latency;

        public String getTargetHostName() {
            return targethostname;
        }

        public String getHostName() {
            return hostname;
        }

        public int getPort() {
            return port;
        }

        public int getQueryPort() {
            return queryport;
        }

        public int getLatency() {
            return latency;
        }

        public String getIpAddress() {
            return ipaddress;
        }
    }

    public static class Version {
        private String name;
        private int protocol;

        public String getName() {
            return name;
        }

        public int getProtocol() {
            return protocol;
        }
    }

    public static class Players {
        private int max;
        private int online;
        private Player[] sample;

        public int getOnlinePlayers() {
            return online;
        }

        public int getMaxPlayers() {
            return max;
        }

        public Player[] getPlayer() {
            return sample;
        }

        public static class Player {
            private String name;
            private String id;

            public String getName() {
                return name;
            }

            public String getID() {
                return id;
            }
        }
    }

    public static class Mods {
        private String type;
        private Mod[] modList;

        public String getType() {
            return type;
        }

        public Mod[] getMod() {
            return modList;
        }

        public static class Mod {
            private String modid;
            private String version;

            public String getVersion() {
                return version;
            }

            public String getModID() {
                return modid;
            }
        }
    }

    public String getMap() {
        return map;
    }

    public String getGameType() {
        return gametype;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}