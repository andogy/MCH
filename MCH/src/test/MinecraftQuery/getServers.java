package test.MinecraftQuery;

import org.json.JSONArray;
import org.json.JSONObject;
import test.MinecraftQuery.api.Protocol;
import test.MinecraftQuery.api.QueryStatus;

import java.util.Arrays;

public class getServers {
    public static void main(String[] args) {
        String hostName = "127.0.0.1";
        int port = 25565;

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
            long latencyAvg = 0;

            int count = 250;
            int finished = 0;
            int min = 0;
            int max = 0;
            long minPoint = 0;
            long maxPoint = 0;
            try {
                for (int i = count; i > 0; i--) {
                    JSONObject latency = new JSONObject(new QueryStatus.Builder(hostName, 19132, Protocol.TCP).reSetPort(port).reSetProtocol(Protocol.TCP).build().getStatus().toJson());
                    latency = new JSONObject(latency.get("server").toString());
                    System.out.println("   =" + latency.get("latency") + "ms");
                    if (i == count) {
                        min = Integer.parseInt(latency.get("latency").toString());
                        max = Integer.parseInt(latency.get("latency").toString());
                    }
                    if (Integer.parseInt(latency.get("latency").toString()) > max) {
                        max = Integer.parseInt(latency.get("latency").toString());
                        maxPoint = count - i + 1;
                    }
                    if (Integer.parseInt(latency.get("latency").toString()) < min) {
                        min = Integer.parseInt(latency.get("latency").toString());
                        minPoint = count - i + 1;
                    }
                    latencyAvg += Long.parseLong(latency.get("latency").toString());
                    finished += 1;
                }
            } catch (Exception e) {
                System.out.println("延迟测试目标" + count + "Cts, 最终以" + finished + "Cts结束");
                System.out.println("原因:" + e);
            } finally {
                System.out.println("--------------------------------------------------");
                System.out.println("测试延迟总和(sum):\n   =" + latencyAvg + "ms(" + finished + "Cts)");
                System.out.println("平均延迟(Avg):\n   =" + latencyAvg / finished + "ms(" + count + "/" + finished + "Cts)");
                System.out.println("最高(max):\n   =" + max + "ms(" + maxPoint + "Ct)");
                System.out.println("最低(min):\n   =" + min + "ms(" + minPoint + "Ct)");
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
            for (int i = playersList.length(); i > 0; i--) {
                JSONObject jo2 = new JSONObject(playersList.get(i - 1).toString());
                System.out.println(jo2.remove("name") + "\033[32;1m(" + jo2.remove("id") + ")\033[0m");
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}
