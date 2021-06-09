package project.Community.Command;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import project.Community.Community;
import project.Community.Events.historyReader;
import project.Community.UI.Lang.initLanguage;
import project.Community.UI.Lang.languageSet;
import project.Community.UI.MchUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;

public class CommandParsing extends Thread {
//    public static HashMap<Integer, String> displayNotOk = new HashMap<>();
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
            MchUI.command1.setText(languageSet.getCommandWord("commandsNotFound"));
        }

    }

    public static void display(JSONObject commandJson, String target, String targetSource, JSONObject resources, JSONObject displayJson, String commandTarget) {
        try {
            if (willOver > 0) {
                willOver -= 1;
            }

            try {
                //                分支解析引导，对于分支超多的命令非常重要
                steps = new JSONObject(displayJson.get(target).toString()).getInt("fork") - 1;
            } catch (Exception ignored) {

            }

            String lists = "";
            try {
                if (commandTarget.equals("?") | commandTarget.contains("help")) {
                 lists = containsTarget(commandJson,target);
                } else {
                    lists = containsTarget(displayJson, target);
                }
            } catch (Exception e) {
//                commandNotFound(target);
                commandNotFound();
            }

//            displayNotOk.put(displayNotOk.size(), target);

            try {
                if (steps >= new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                    steps = 0;
//                    System.out.println(displayNotOk.toString());
//                    commandNotFound(displayNotOk.get(0));
                    commandNotFound();
                }
            } catch (Exception ignored) {

            }

            JSONArray jsa = new JSONArray();
            try {
                jsa = new JSONArray(new JSONObject(new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).get(steps).toString()).get("tree").toString());
            } catch (Exception e) {
//                commandNotFound(target);
                commandNotFound();
            }
            if (lists.equals("")) {
                steps += 1;
            } else if (targetSource.contains(" ") & !lists.contains("\n\n")) {
                MchUI.command1.setText(languageSet.getCommandWord("noComplete")  + "\"" + target + "\"");
            } else if (targetSource.contains(" ") & lists.contains("\n\n")) {

                try {


                    if (!jsa.get(commandSteps).toString().equals("@end")) {

                        targetSource = targetSource.substring(targetSource.indexOf(" ") + 1);

                        if (targetSource.contains(" ")) {
                            target = targetSource.substring(0, targetSource.indexOf(" "));
                        } else {
                            target = targetSource;
                        }

//                        displayNotOk.clear();
                        displayJson = new JSONObject((resources.get(jsa.get(commandSteps).toString()).toString()));

                        commandSteps += 1;

                        boolean over = false;
                        if (targetSource.contains(" ")) {
                            try {
                                over = new JSONObject(displayJson.get(target).toString()).getBoolean("over");
                            } catch (Exception ignored) {

                            }
                            try {
                                willOver = new JSONObject(displayJson.get(target).toString()).getInt("will_over") + 1;
                            } catch (Exception ignored) {

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
                        MchUI.command1.setText(languageSet.getCommandWord("commandEnd"));
                    }
                } catch (JSONException e) {
                    try {
                        if (steps + 1 == new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                            commandNotFound();
                            steps = 0;
                        } else if (steps + 1 < new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                            steps += 1;
                            display(commandJson, target, targetSource, resources, displayJson, commandTarget);
                            System.gc();
                        }
                    } catch (JSONException ignored) {

                    }
                }
            } else {
                MchUI.command1.setText(lists);
            }
        } catch (CommandOverException e) {
            commandNotFound();
        }
    }

//    public static void commandNotFound(String errPoint) {
//        MchUI.command1.setText(languageSet.getCommandWord("commandEnd") + ": \"" + errPoint + "\"");
//    }

    public static void commandNotFound() {
        MchUI.command1.setText(languageSet.getCommandWord("commandEnd"));
    }

    public static String containsTarget(JSONObject displayJson, String target) {
        //        用于判断命令是否失效
        boolean invalid = false;
        //        用于获取命令使用限制列表
        String limited;
        //        用于返回显示的命令
        String result = "";
        //        用于保存所有已解析的命令
        String all = "";

        //        初始化语言文件
        initLanguage.initCommand();

        //        获得所有的命令
        Iterator<String> iterator = displayJson.keys();

        while (iterator.hasNext()) {
            String next = iterator.next();
            all += next + "\n";

            invalid = false;
            try {
                //            判断$invalid (是否失效)
                invalid = new JSONObject(displayJson.get(next).toString()).getBoolean("$invalid");
            } catch (Exception ignored) {

            }

            try {
                //                获得命令使用限制列表
                limited = getLimited(new JSONArray(new JSONObject(displayJson.get(next).toString()).get("limited").toString()));
            } catch (Exception e) {

            }

            if (invalid) {

                //判断是否显示失效的命令
                if (Community.displayInvalidCommand) {
                    if (noDisplayType().contains(next)) {

                    } else if (next.equals(target)) {
                        //                        如果完全匹配，则直接返回当前结果(加命令描述)
                        result = next + "    " + languageSet.getCommandWord(new JSONObject(displayJson.get(next).toString()).get("description").toString()) + "\n\n";
                    } else if (next.contains(target)) {
                        //                        如果不完全匹配，则保存当前结果(加命令描述)
                        result += next + "    " + languageSet.getCommandWord(new JSONObject(displayJson.get(next).toString()).get("description").toString()) + "\n";
                    }
                }

            } else {
                //如果不失效则直接显示

                if (noDisplayType().contains(next)) {

                } else if (next.equals(target)) {
                    //                        如果完全匹配，则直接返回当前结果(加命令描述)
                    result = next + "    " + languageSet.getCommandWord(new JSONObject(displayJson.get(next).toString()).get("description").toString()) + "\n\n";
                } else if (next.contains(target)) {
                    //                        如果不完全匹配，则保存当前结果(加命令描述)
                    result += next + "    " + languageSet.getCommandWord(new JSONObject(displayJson.get(next).toString()).get("description").toString()) + "\n";
                }
            }
        }

        //        如果没有任何符合的模板文本，则判断有没有可能是允许自定义的(如选择器中的选定名称或者help里的页码)
        if (result.equals("")) {
            if (all.contains("@String")) {
                result = target + "    " + languageSet.getCommandWord(new JSONObject(displayJson.get("@String").toString()).get("description").toString()) + "\n\n";
            }
            if (all.contains("@Number")) {
                try {
                    long targetValue = Long.parseLong(target);
                    if (new JSONObject(displayJson.get("@Number").toString()).getLong("limited") > targetValue) {
                        result = targetValue + "    " + languageSet.getCommandWord(new JSONObject(displayJson.get("@Number").toString()).get("description").toString());
                    } else {
                        result = "" + languageSet.getCommandWord(new JSONObject(displayJson.get("@Number").toString()).get("valueOutLimited").toString()) + "\n";
                    }
                } catch (NumberFormatException e) {
                    result = target + " " + languageSet.getCommandWord(new JSONObject(displayJson.get("@Number").toString()).get("valueErr").toString());
                }
            }
        }

        /*
        Don't Delete it!!!
        There have a bug, if not charAt, then the parse will not normal
        不要删除这行!!!
        这有一个小bug，如果不进行charAt，解析会变得不太正常
         */
        result.charAt(0);

        return result;
    }

    public static String getLimited(JSONArray limitedList) {
        String limited = "";
        for (int i = limitedList.length() - 1; i >= 0; i--) {
            try {
                limited += limitedList.get(i) + "\n";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return limited;
    }

    public static String noDisplayType() {
        return """
                @String
                @Number
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
