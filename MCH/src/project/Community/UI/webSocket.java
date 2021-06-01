/*
*   This
*   Part
*   is
*   Will
*   Be
*   Postponed
*
*   Because
*   This
*   Part
*   is
*   No
*   Ease
*
*   And
*   We
*   Team
*   Not
*   Good
*   At
*   The
*   Network
*
*   This
*   Part
*   Maybe
*   Resume
*   Development
*   in
*   Version
*   0.0.2
*   Or
*   Logger
*   Time
*
*
*
*
*/

package project.Community.UI;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import javax.swing.*;
import java.awt.*;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class webSocket {
    public static JFrame jFrame = new JFrame();
    public static JButton runSocket = new JButton();
    public static JButton stopSocket = new JButton();
    public static int port = 19133;
    public static JTextArea portSet = new JTextArea(String.valueOf(port));

    public webSocket() {

        jFrame.setLayout(new LayoutManager() {
            @Override
            public void addLayoutComponent(String name, Component comp) {
            }

            @Override
            public void removeLayoutComponent(Component comp) {
            }

            @Override
            public Dimension preferredLayoutSize(Container parent) {
                return null;
            }

            @Override
            public Dimension minimumLayoutSize(Container parent) {
                return null;
            }

            @Override
            public void layoutContainer(Container parent) {

            }
        });

        jFrame.add(runSocket);
        jFrame.add(stopSocket);
        jFrame.add(portSet);

        runSocket.addActionListener(e -> socket.runSocket(port, true));
        stopSocket.addActionListener(e -> socket.runSocket(port, false));

    }

    public static class webSocketListener extends Thread {
        public static String log = "";

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(15);

                    port = Integer.parseInt(webSocket.portSet.getText());

                } catch (Exception ignored) {

                }
            }
        }
    }

    public static class socket {

        public static void runSocket(int port, boolean runSocket) {

            ServerManager serverManager = new ServerManager();

            if (runSocket) {
                serverManager.Start(port);
            } else {
                serverManager.Stop();
            }
        }

    }

    public static class ServerManager {

        public static Map<WebSocket, String> userMap = new HashMap<>();
        private ServerSocket serverSocket = null;

        public ServerManager() {

        }

        public void UserLogin(String userName, WebSocket socket) {
            if (userName != null || socket != null) {
                userMap.put(socket, userName);
                System.out.println("LOGIN:" + userName);

                SendMessageToAll(userName + "...Login...");
            }
        }

        public void UserLeave(WebSocket socket) {
            if (userMap.containsKey(socket)) {
                String userName = userMap.get(socket);
                System.out.println("Leave:" + userName);
                userMap.remove(socket);
                SendMessageToAll(userName + "...Leave...");
            }
        }

        public void SendMessageToUser(WebSocket socket, String message) {
            if (socket != null) {
                socket.send(message);
            }
        }

        public void SendMessageToUser(String userName, String message) {
            Set<WebSocket> ketSet = userMap.keySet();
            for (WebSocket socket : ketSet) {
                String name = userMap.get(socket);
                if (name != null) {
                    if (name.equals(userName)) {
                        socket.send(message);
                        break;
                    }
                }
            }
        }

        public void SendMessageToAll(String message) {
            Set<WebSocket> ketSet = userMap.keySet();
            for (WebSocket socket : ketSet) {
                String name = userMap.get(socket);
                if (name != null) {
                    socket.send(message);
                }
            }
        }

        public boolean Start(int port) {

            if (port < 0) {
                System.out.println("Port error...");
                return false;
            }

            System.out.println("Start ServerSocket...");

            try {
                serverSocket = new ServerSocket(this, port);
                serverSocket.start();
                System.out.println("Start ServerSocket in Port " + port);
                return true;
            } catch (Exception e) {
                System.out.println("Start Failed...");
                e.printStackTrace();
                return false;
            }
        }

        public boolean Stop() {
            try {
                serverSocket.stop();
                System.out.println("停止了ws服务器");
                return true;
            } catch (Exception e) {
                System.out.println("无法停止ws服务器");
                e.printStackTrace();
                return false;
            }
        }

    }

    public static class ServerSocket extends WebSocketServer {

        private ServerManager _serverManager;

        public ServerSocket(ServerManager serverManager, int port) throws UnknownHostException {
            super(new InetSocketAddress(port));
            _serverManager = serverManager;
        }

        @Override
        public void onClose(WebSocket socket, int message,
                            String reason, boolean remote) {
            _serverManager.UserLeave(socket);
        }

        @Override
        public void onError(WebSocket socket, Exception message) {
            System.out.println("Exception:" + message.toString());
        }

        @Override
        public void onStart() {

        }

        @Override
        public void onMessage(WebSocket socket, String message) {
            System.out.println("接收信息:" + message);
        }

        @Override
        public void onOpen(WebSocket socket, ClientHandshake handshake) {
            ServerManager.userMap.put(socket, handshake.getResourceDescriptor());
            _serverManager.SendMessageToUser(socket, "草");
            System.out.println("有一个新连接:" + socket);
            System.out.println("所有连接: " + ServerManager.userMap);
        }
    }

}
