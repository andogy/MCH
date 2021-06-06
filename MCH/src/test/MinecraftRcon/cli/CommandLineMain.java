package test.MinecraftRcon.cli;

import test.MinecraftRcon.AuthenticationException;
import test.MinecraftRcon.RConClient;

import java.io.Console;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandLineMain {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        boolean interactive = true;
        String password = "az";
        String command = "";

        try (Scanner sc = new Scanner(System.in)) {
            if (host == null) {
                if (interactive) {
                    System.out.println("Enter host address: ");
                    host = sc.nextLine();
                } else {
                    System.err.println("Need host address");
                    System.exit(1);
                }
            }
            if (password == null) {
                if (interactive) {
                    System.out.println("Enter password: ");
                    Console console = System.console();
                    if (console != null) {
                        password = new String(console.readPassword());
                    } else {
                        password = sc.nextLine();
                    }
                } else {
                    System.err.println("Need password");
                    System.exit(1);
                }
            }
        }

        String commands = """
                
                """;
        while (true) {
            //			if (command == null) {
            if (interactive) {
                try {

                    command = commands.substring(0, commands.indexOf("\n"));

                    Thread.sleep(2000);

                    if (command.contains("stopped")) {
                        Thread.sleep(5000);
                    }

                    System.out.println("sending: " + command);
                    commands = commands.substring(commands.indexOf("\n") + 1);
                } catch (Exception e) {
                    System.out.println("send finished");
                    break;
                }
                //				} else {
                //					System.err.println("Need command");
                //					System.exit(1);
                //				}
            }

            RConClient client;
            try {
                client = new RConClient(host);
            } catch (IOException e) {
                System.err.println("An exception occured while connecting to the server: ");
                e.printStackTrace(System.err);
                System.exit(1);
                return;
            }
            try {
                client.authenticate(password);
            } catch (IOException | AuthenticationException e) {
                System.err.println("An exception occured while authenticating: ");
                e.printStackTrace(System.err);
                try {
                    client.close();
                } catch (IOException ex) {
                    System.err.println("Additionaly, an exception occured while closing the client: ");
                    ex.printStackTrace(System.err);
                    System.exit(1);
                    return;
                }
                System.exit(1);
                return;
            }
            try {
                System.out.println(client.sendCommand(command));
            } catch (Exception ignored) {
                //                System.err.println("An exception occured while sending command: ");
                //                e.printStackTrace(System.err);
                //                try {
                //                    client.close();
                //                } catch (IOException ex) {
                //                    System.err.println("Additionaly, an exception occured while closing the client: ");
                //                    ex.printStackTrace(System.err);
                //                    return;
                //                }
                //                return;
            }
            try {
                client.close();
            } catch (IOException e) {
                System.err.println("An exception occured while closing the client: ");
                e.printStackTrace(System.err);
            }
        }
    }

    public static Map<String, String> parseArguments(String[] args) {
        final Map<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                if (args.length > i + 1 && !args[i + 1].startsWith("--")) {
                    map.put(args[i].substring(2), args[i + 1]);
                } else {
                    map.put(args[i].substring(2), null);
                }
            } else if (args[i].startsWith("-")) {
                if (args.length > i + 1 && !args[i + 1].startsWith("-")) {
                    String keyname = args[i].substring(1);
                    if (keyname.length() > 1) {
                        for (int j = 0; j < keyname.length() - 1; j++) {
                            map.put(keyname.substring(j, j + 1), null);
                        }
                        map.put(keyname.substring(keyname.length() - 1, keyname.length()), args[i + 1]);
                    } else {
                        map.put(keyname, args[i + 1]);
                    }
                } else {
                    String keyname = args[i].substring(1);
                    if (keyname.length() > 1) {
                        for (int j = 0; j < keyname.length(); j++) {
                            map.put(keyname.substring(j, j + 1), null);
                        }
                        map.put(keyname.substring(keyname.length() - 1, keyname.length()), null);
                    } else {
                        map.put(keyname, null);
                    }
                }
            }
        }
        return map;
    }
}
