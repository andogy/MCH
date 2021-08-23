package test.MinecraftQuery;

import project.Community.lib.json.JSONArray;
import project.Community.lib.json.JSONObject;
import test.MinecraftQuery.api.Protocol;
import test.MinecraftQuery.api.QueryException;
import test.MinecraftQuery.api.QueryStatus;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class getServers {
    public static int count_static = 10;
    public static int count = count_static;

    public static boolean pingFinished = false;

    public static long latencyAvg = 0;

    public static int finished = 0;
    public static int min = 0;
    public static int max = 0;
    public static long minPoint = 0;
    public static long maxPoint = 0;

    public static String hostName = "125.64.103.212";
    public static int port = 18724;

    public static int latency = 0;

    public static void main(String[] args) {

        try {
            //getSource
            JSONObject jsonSource = new JSONObject(new QueryStatus.Builder(hostName, port, Protocol.TCP).build().getStatus().toJson());
            jsonSource.remove("favicon");
            System.out.println("原始数据:\n" + jsonSource);
            System.out.println("---------------------服务器信息----------------------");
            JSONObject server = new JSONObject(jsonSource.get("server").toString());
            System.out.println("主机名:" + server.get("hostname"));
            System.out.println("端口:" + server.get("port"));
            System.out.println("IP:" + server.remove("ipaddress") + ":" + server.get("port"));
            System.out.println("查询端口:" + server.get("queryport"));

            System.out.println("延迟:");

            pingFinished = false;

            //            try {
            //                new Thread(() -> {
            //                    try {
            //                        ping(0);
            //                    } catch (QueryException e) {
            //                        pingError(e, null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(1);
            //                    } catch (QueryException e) {
            //                        pingError(e, null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(2);
            //                    } catch (QueryException e) {
            //                        pingError(e, null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(3);
            //                    } catch (QueryException e) {
            //                        pingError(e, null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(4);
            //                    } catch (QueryException e) {
            //                        pingError(e, null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(5);
            //                    } catch (QueryException e) {
            //                        pingError(e,null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                    new Thread(() -> {
            //                        try {
            //                            ping(6);
            //                        } catch (QueryException e) {
            //                            pingError(e, null);
            //                        } finally {
            //                            pingFinished(server);
            //                        }
            //                    }).start();
            //
            //                    new Thread(() -> {
            //                        try {
            //                            ping(7);
            //                        } catch (QueryException e) {
            //                            pingError(e, null);
            //                        } finally {
            //                            pingFinished(server);
            //                        }
            //                    }).start();
            //
            //                    new Thread(() -> {
            //                        try {
            //                            ping(8);
            //                        } catch (QueryException e) {
            //                            pingError(e, null);
            //                        } finally {
            //                            pingFinished(server);
            //                        }
            //                    }).start();
            //
            //                    new Thread(() -> {
            //                        try {
            //                            ping(9);
            //                        } catch (QueryException e) {
            //                            pingError(e, null);
            //                        } finally {
            //                            pingFinished(server);
            //                        }
            //                    }).start();
            //
            //                    new Thread(() -> {
            //                        try {
            //                            ping(10);
            //                        } catch (QueryException e) {
            //                            pingError(e, null);
            //                        } finally {
            //                            pingFinished(server);
            //                        }
            //                    }).start();
            //
            //                    new Thread(() -> {
            //                        try {
            //                            ping(11);
            //                        } catch (QueryException e) {
            //                            pingError(e,null);
            //                        } finally {
            //                            pingFinished(server);
            //                        }
            //                    }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(12);
            //                    } catch (QueryException e) {
            //                        pingError(e, null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(13);
            //                    } catch (QueryException e) {
            //                        pingError(e, null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(14);
            //                    } catch (QueryException e) {
            //                        pingError(e, null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(15);
            //                    } catch (QueryException e) {
            //                        pingError(e, null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(16);
            //                    } catch (QueryException e) {
            //                        pingError(e, null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(17);
            //                    } catch (QueryException e) {
            //                        pingError(e,null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(18);
            //                    } catch (QueryException e) {
            //                        pingError(e, null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(19);
            //                    } catch (QueryException e) {
            //                        pingError(e, null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(20);
            //                    } catch (QueryException e) {
            //                        pingError(e, null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(21);
            //                    } catch (QueryException e) {
            //                        pingError(e, null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(22);
            //                    } catch (QueryException e) {
            //                        pingError(e, null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();
            //
            //                new Thread(() -> {
            //                    try {
            //                        ping(23);
            //                    } catch (QueryException e) {
            //                        pingError(e,null);
            //                    } finally {
            //                        pingFinished(server);
            //                    }
            //                }).start();

            try {
                int i = count;
                while(count > 0 & ! pingFinished) {
                    JSONObject latency = new JSONObject(new QueryStatus.Builder(hostName, 19132, Protocol.TCP).reSetPort(port).reSetProtocol(Protocol.TCP).build().getStatus().toJson());
                    latency = new JSONObject(latency.get("server").toString());
                    System.out.println("(Thread0)=" + latency.get("latency") + "ms");
                    if(i == count) {
                        min = Integer.parseInt(latency.get("latency").toString());
                        max = Integer.parseInt(latency.get("latency").toString());
                    }
                    if(Integer.parseInt(latency.get("latency").toString()) > max) {
                        max = Integer.parseInt(latency.get("latency").toString());
                        maxPoint = count - i + 1;
                    }
                    if(Integer.parseInt(latency.get("latency").toString()) < min) {
                        min = Integer.parseInt(latency.get("latency").toString());
                        minPoint = count - i + 1;
                    }
                    latencyAvg += Long.parseLong(latency.get("latency").toString());
                    finished += 1;
                    count--;
                }
            } catch (Exception e) {
                System.out.println("延迟测试目标" + count + "Cts, 最终以" + finished + "Cts结束");
                System.out.println("原因:" + e);

                pingFinished = true;
            } finally {
                System.out.println("-------------" + server.get("hostname") + ":" + server.get("port") + "-----------------------------");
                System.out.println("测试延迟总和(sum):\n   =" + latencyAvg + "ms(" + finished + "Cts)");
                System.out.println("平均延迟(Avg):\n   =" + latencyAvg / finished + "ms(" + count_static + "/" + finished + "Cts)");
                System.out.println("最高(max):\n   =" + max + "ms(" + maxPoint + "Ct)");
                System.out.println("最低(min):\n   =" + min + "ms(" + minPoint + "Ct)");

                pingFinished = true;
            }

            System.out.println("描述:" + jsonSource.get("description"));

            System.out.println("----------------------版本信息----------------------");
            JSONObject version = new JSONObject(jsonSource.get("version").toString());
            System.out.println("协议版本:" + version.get("protocol"));
            System.out.println("游戏版本:" + version.get("name"));

            //players
            JSONObject players = new JSONObject(jsonSource.get("players").toString());
            System.out.println("----------------------玩家信息----------------------");
            System.out.println("最大玩家:" + players.get("max"));
            System.out.println("在线玩家:(" + players.get("online") + ")");
            JSONArray playersList;
            playersList = new JSONArray(players.getJSONArray("sample"));
            for(int i = playersList.length(); i > 0; i--) {
                JSONObject jo2 = new JSONObject(playersList.get(i - 1).toString());
                System.out.println(jo2.remove("name") + "\033[32;1m(" + jo2.remove("id") + ")\033[0m");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ping(int threadID) throws QueryException {
        int i = count;
        while(count > 0 & ! pingFinished) {
            JSONObject latency = new JSONObject(new QueryStatus.Builder(hostName, 19132, Protocol.TCP).reSetPort(port).reSetProtocol(Protocol.TCP).build().getStatus().toJson());
            latency = new JSONObject(latency.get("server").toString());
            System.out.println("(Thread)" + threadID + "=" + latency.get("latency") + "ms");
            if(i == count) {
                min = Integer.parseInt(latency.get("latency").toString());
                max = Integer.parseInt(latency.get("latency").toString());
            }
            if(Integer.parseInt(latency.get("latency").toString()) > max) {
                max = Integer.parseInt(latency.get("latency").toString());
                maxPoint = count - i + 1;
            }
            if(Integer.parseInt(latency.get("latency").toString()) < min) {
                min = Integer.parseInt(latency.get("latency").toString());
                minPoint = count - i + 1;
            }
            latencyAvg += Long.parseLong(latency.get("latency").toString());
            finished += 1;
            count--;
        }
    }

    public static void pingError(Exception e, Error er) {
        System.out.println("延迟测试目标" + count + "Cts, 最终以" + finished + "Cts结束");
        if(e == null)
            System.out.println("原因:" + er);
        else
            System.out.println("原因:" + e);

        pingFinished = true;
    }

    public static void pingFinished(JSONObject server) {
        System.out.println("-------------" + server.get("hostname") + ":" + server.get("port") + "-----------------------------");
        System.out.println("测试延迟总和(sum):\n   =" + latencyAvg + "ms(" + finished + "Cts)");
        System.out.println("平均延迟(Avg):\n   =" + latencyAvg / finished + "ms(" + count_static + "/" + finished + "Cts)");
        System.out.println("最高(max):\n   =" + max + "ms(" + maxPoint + "Ct)");
        System.out.println("最低(min):\n   =" + min + "ms(" + minPoint + "Ct)");

        pingFinished = true;
    }
}
