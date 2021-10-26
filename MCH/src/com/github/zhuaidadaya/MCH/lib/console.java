package com.github.zhuaidadaya.MCH.lib;

import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.UI.MinecraftLauncher;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class console {
    public static void aCnsole() {
        Scanner sc = new Scanner(System.in);
        while(!Errors.CannotHandle) {
            boolean exit = false;
            String command = sc.nextLine();
            switch(command) {
                case "exit-mch" -> System.exit(- 1);

                case "exit" -> {
                    System.out.println("Console exit now");
                    exit = true;
                }
            }

            if(command.startsWith("instance")) {
                MinecraftLauncher.customInstance(command.substring(command.indexOf(" ") + 1));
            }

            if(command.equals("cd")) {
                outMessage(System.getProperty("user.dir"));
            } else if(command.startsWith("cd")) {
                command = command.replace("\\", "/");
                if(command.substring(command.indexOf(" ") + 1).equals("../")) {
                    System.setProperty("user.dir", new File(System.getProperty("user.dir")).getParentFile().getAbsolutePath());
                    outMessage(System.getProperty("user.dir"));
                } else {
                    File file;
                    File file1 = new File(command.substring(command.indexOf(" ") + 1));
                    if(file1.exists())
                        file = file1;
                    else
                        file = new File(System.getProperty("user.dir") + "/" + command.substring(command.indexOf(" ") + 1));
                    if(file.isFile() | file.isDirectory()) {
                        System.setProperty("user.dir", file.getAbsolutePath());
                        outMessage(System.getProperty("user.dir"));
                    } else {
                        outMessage("invalid url: " + file.getAbsolutePath());
                    }
                }
            }

            if(command.equals("ls")) {
                try {
                    LinkedList<String> directory = new LinkedList<>();
                    LinkedList<String> files = new LinkedList<>();
                    for(File f : new File(System.getProperty("user.dir")).listFiles()) {
                        if(f.isDirectory())
                            directory.add("dir -> " + f.getName());
                        else
                            files.add("file -> " + f.getName());
                    }

                    for(String s : directory)
                        outMessage(s);
                    for(String s : files)
                        outMessage(s);
                } catch (Exception e) {

                }
            }

            if(exit)
                break;
        }
    }

    public static void outMessage(String message) {
        String[] strings;
        try {
            strings = message.split(message.contains("\n") ? "\n" : message.contains("\r") ? "\r" : message + message);
        } catch (Exception e) {
            strings = new String[]{message};
        }
        for(String mess : strings)
            Log.writeLog("[Console Thread/INFO] " + mess);
    }
}
