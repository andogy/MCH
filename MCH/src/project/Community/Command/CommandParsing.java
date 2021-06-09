package project.Community.Command;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import project.Community.Community;
import project.Community.Events.historyReader;
import project.Community.UI.Lang.initLanguage;
import project.Community.UI.Lang.languageSet;
import project.Community.UI.MchUI;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

public class CommandParsing extends Thread {
    public static HashMap<Integer, String> displayNotOk = new HashMap<>();
    public static int willOver = 150;
    public static int steps = 0;
    public static int commandSteps = 0;

    public static void commands() {

        String str;
        String jsonText = "";

        File f = new File("C:\\.MCH\\commands.json");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            while ((str = br.readLine()) != null) {
                jsonText += str;
            }


            String targetSource = MchUI.input_Command.getText().replace("/", "");
            String target;
            if (targetSource.contains(" ")) {
                target = targetSource.substring(0, targetSource.indexOf(" "));
            } else {
                target = targetSource;
            }
            String out = "";
            int i = 0;

            JSONObject json = new JSONObject();
            JSONObject jsonResources = new JSONObject();
            try {
                json = new JSONObject(new JSONObject(new JSONObject(jsonText).get("resources").toString()).get("commands").toString());
                jsonResources = new JSONObject(new JSONObject(new JSONObject(jsonText).get("resources").toString()).get("resource").toString());
            } catch (Exception ignored) {

            }

            commandSteps = 0;

            willOver = 150;

            display(json, target, targetSource, jsonResources, json, target);
        } catch (IOException e) {
            MchUI.command1.setText(languageSet.getWord("commandsNotFound"));
        }

    }

    public static void display(JSONObject commandJson, String target, String targetSource, JSONObject resources, JSONObject displayJson, String commandTarget) {
        try {
            if (willOver > 0) {
                willOver -= 1;
            }

            String lists = "";
            try {
                lists = containsTarget(displayJson, target);
            } catch (Exception e) {
                commandNotFound(target);
            }

            displayNotOk.put(displayNotOk.size(), target);

            try {
                if (steps >= new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                    steps = 0;
                    System.out.println(displayNotOk.toString());
                    commandNotFound(displayNotOk.get(0));
                }
            } catch (Exception ignored) {

            }

            JSONArray jsa = new JSONArray();
            try {
                jsa = new JSONArray(new JSONObject(new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).get(steps).toString()).get("tree").toString());
            } catch (Exception e) {
                commandNotFound(target);
            }
            if (lists.equals("")) {
                steps += 1;
            } else if (targetSource.contains(" ") & !lists.contains("\n\n")) {
                System.out.println("erring");
                MchUI.command1.setText("error: \"" + target + "\"");
            } else if (targetSource.contains(" ") & lists.contains("\n\n")) {

                try {


                    if (!jsa.get(commandSteps).toString().equals("@end")) {

                        targetSource = targetSource.substring(targetSource.indexOf(" ") + 1);

                        if (targetSource.contains(" ")) {
                            target = targetSource.substring(0, targetSource.indexOf(" "));
                        } else {
                            target = targetSource;
                        }

                        displayNotOk.clear();
                        displayJson = new JSONObject((resources.get(jsa.get(commandSteps).toString()).toString()));

                        commandSteps += 1;

                        boolean over = false;
                        if (targetSource.contains(" ")) {
                            try {
                                over = new JSONObject(displayJson.get(target).toString()).getBoolean("over");
                            } catch (Exception e) {

                            }
                            try {
                                willOver = new JSONObject(displayJson.get(target).toString()).getInt("will_over") + 1;
                            } catch (Exception e) {

                            }
                        }

                        if (willOver == 0) {
                            over = true;
                        }

                        if (!over) {
                            display(commandJson, target, targetSource, resources, displayJson, commandTarget);
                        } else {
                            throw new CommandOverException("Overed");
                        }

                    } else {
                        MchUI.command1.setText(languageSet.getWord("commandEnd"));
                    }
                } catch (JSONException e) {
                    try {
                        if (steps + 1 == new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                            commandNotFound();
                        } else if (steps + 1 < new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                            steps += 1;
                            display(commandJson, target, targetSource, resources, displayJson, commandTarget);
                        }
                    } catch (JSONException ignored) {

                    }
                }
            } else {
                MchUI.command1.setText(lists);
            }
        } catch (CommandOverException e) {
            commandNotFound(target);
        }
    }

    public static void commandNotFound(String errPoint) {
        MchUI.command1.setText(languageSet.getWord("commandEnd") + ": \"" + errPoint + "\"");
    }

    public static void commandNotFound() {
        MchUI.command1.setText(languageSet.getWord("commandEnd"));
    }

    public static String containsTarget(JSONObject jsonObject, String target) {
        new initLanguage();

        Iterator<String> iterator = jsonObject.keys();

        String result = "";

        String all = "";
        while (iterator.hasNext()) {
            String next = iterator.next();
            all += next + "\n";

            boolean invalid = false;
            try {

                //            判断$invalid (是否失效)
                invalid = new JSONObject(jsonObject.get(next).toString()).getBoolean("$invalid");
            } catch (Exception e) {
                //                e.printStackTrace();
            }
            if (invalid) {

                //判断是否显示失效的命令
                if (Community.displayInvalidCommand) {
                    if (noDisplayType().contains(next)) {

                    } else if (next.equals(target)) {
                        result = next + "    " + languageSet.getWord(new JSONObject(jsonObject.get(next).toString()).get("description").toString()) + "\n\n";
                        break;
                    } else if (next.contains(target)) {
                        result += next + "    " + languageSet.getWord(new JSONObject(jsonObject.get(next).toString()).get("description").toString()) + "\n";
                    }
                }

            } else {
                //如果不失效则直接显示

                if (noDisplayType().contains(next)) {

                } else if (next.equals(target)) {
                    result = next + "    " + languageSet.getWord(new JSONObject(jsonObject.get(next).toString()).get("description").toString()) + "\n\n";
                    break;
                } else if (next.contains(target)) {
                    result += next + "    " + languageSet.getWord(new JSONObject(jsonObject.get(next).toString()).get("description").toString()) + "\n";
                }
            }
        }

        if (result.equals("")) {
            if (all.contains("@String")) {
                result = target + "    " + languageSet.getWord(new JSONObject(jsonObject.get("@String").toString()).get("description").toString()) + "\n\n";
            }
            if (all.contains("@Int")) {
                try {
                    int pages = Integer.parseInt(target);
                    if (new JSONObject(jsonObject.get("@Int").toString()).getInt("limited") < pages) {
                        result = languageSet.getWord("pageOut");
                    } else {
                        result = target + "    " + languageSet.getWord(new JSONObject(jsonObject.get("@Int").toString()).get("description").toString()) + "\n";
                    }
                } catch (NumberFormatException e) {
                    throw new NumberFormatException();
                }
            }
        }

        return result;
    }

    public static String noDisplayType() {
        return """
                @String
                @Int
                @Float
                $limited
                """;
    }

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!MchUI.input_Command.getText().equals("")) {

                if (MchUI.input_Command.getText().lastIndexOf("/") == 0 | !MchUI.input_Command.getText().contains("/")) {
                    if (!MchUI.switchTip.isFocusOwner()) {
                        CommandParsing.commands();
                    }

                    historyReader.BreakRead = true;
                    Parsing.flushHistory = true;

                }
                if (MchUI.input_Command.getText().contains("//")) {
                    MchUI.input_Command.setText(MchUI.input_Command.getText().replace("//", "/"));
                }
            } else {
                if (Parsing.flushHistory) {
                    historyReader.BreakRead = false;
                    Parsing.flushHistory = false;
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
