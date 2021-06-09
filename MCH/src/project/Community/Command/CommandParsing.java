package project.Community.Command;

import org.json.JSONArray;
import org.json.JSONObject;
import project.Community.Events.historyReader;
import project.Community.UI.Lang.languageSet;
import project.Community.UI.MchUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

public class CommandParsing extends Thread {
    public static int steps = 0;
    public static int commandSteps = 0;

    public static void commands() {

        String str;
        String jsonText = "";

        File f = new File("G:\\Code-Java\\MCH\\MCH\\src\\project\\resources\\Json\\test1.json");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            while ((str = br.readLine()) != null) {
                jsonText += str;
            }
        } catch (Exception e) {

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

        JSONObject json = new JSONObject(new JSONObject(new JSONObject(jsonText).get("resources").toString()).get("commands").toString());
        JSONObject jsonResources = new JSONObject(new JSONObject(new JSONObject(jsonText).get("resources").toString()).get("resource").toString());

        commandSteps = 0;

        display(json, target, targetSource, jsonResources, json, target);

    }

    public static void display(JSONObject commandJson, String target, String targetSource, JSONObject resources, JSONObject displayJson, String commandTarget) {
        String lists = containsTarget(displayJson, target);

        try {
            if (steps >= new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                steps = 0;
                commandNotFound();
            }
        } catch (Exception ignored) {

        }

        JSONArray jsa = new JSONArray();
        try {
            jsa = new JSONArray(new JSONObject(new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).get(steps).toString()).get("tree").toString());
        } catch (Exception e) {
            commandNotFound();
        }
        if (lists.equals("")) {
            steps += 1;
        } else if (targetSource.contains(" ") & !lists.contains("|")) {
            System.out.println("erring");
        } else if (targetSource.contains(" ") & lists.contains("|")) {

            try {


                if (!jsa.get(commandSteps).toString().equals("@end")) {

                    targetSource = targetSource.substring(targetSource.indexOf(" ") + 1);

                    if (targetSource.contains(" ")) {
                        target = targetSource.substring(0, targetSource.indexOf(" "));
                    } else {
                        target = targetSource;
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    displayJson = new JSONObject((resources.get(jsa.get(commandSteps).toString()).toString()));

                    commandSteps += 1;

                    display(commandJson, target, targetSource, resources, displayJson, commandTarget);
                } else {
                    MchUI.command1.setText(languageSet.getWord("commandEnd"));
                }
            } catch (Exception e) {
                if (steps == new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                    commandNotFound();
                } else if (steps < new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                    steps += 1;
                    display(commandJson, target, targetSource, resources, displayJson, commandTarget);
                }
            }
        } else {
            MchUI.command1.setText(lists);
        }

    }

    public static void commandNotFound() {
        MchUI.command1.setText(languageSet.getWord("commandEnd"));
    }

    public static String containsTarget(JSONObject jsonObject, String target) {

        Iterator<String> iterator = jsonObject.keys();

        String result = "";

        String all = "";
        while (iterator.hasNext()) {
            String next = iterator.next();
            all += next + "\n";
            if (noDisplayType().contains(next)) {

            } else if (next.equals(target)) {
                result = next + "|";
                break;
            } else if (next.contains(target)) {
                result += next + "    " + new JSONObject(jsonObject.get(next).toString()).get("description").toString() + "\n";
            }
        }

        if (result.equals("")) {
            if (all.contains("@String")) {
                result = target + "    " + new JSONObject(jsonObject.get("@String").toString()).get("description").toString() + "\n";
            }
        }

        return result;
    }

    public static String noDisplayType() {
        return """
                @String
                @Int
                @Float
                         
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
