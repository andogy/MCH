package project.Community.Command;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import project.Community.Community;
import project.Community.Events.Errors;
import project.Community.Events.historyReader;
import project.Community.UI.Lang.initLanguage;
import project.Community.UI.Lang.languageSet;
import project.Community.UI.MchUI;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.UUID;

public class CommandParsing extends Thread {
    public static int willOver = 150;
    public static int steps = 0;
    public static int commandSteps = 0;
    public static int execute = 0;
    public static int lastExecute = 0;
    public static int allSteps = 0;

    public static int commandPoint = 0;

    public static int offset = 0;

    public static String buffered;

    public static void commands() {
        String jsonText = "";

        File f = new File("C:\\.MCH\\commands.json");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            while((buffered = br.readLine()) != null) {
                jsonText += buffered;
            }

            String completeCommand = "";
            if(MchUI.input_Command.getText().indexOf("/") == 0) {
                completeCommand = MchUI.input_Command.getText().replaceFirst("/", "");
            } else {
                completeCommand = MchUI.input_Command.getText();
            }
            String target;
            if(completeCommand.contains(" ")) {
                target = completeCommand.substring(0, completeCommand.indexOf(" "));
            } else {
                target = completeCommand;
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

            willOver = 1500;

            allSteps = 0;
            offset = 0;

            lastExecute = 0;

            display(json, target, completeCommand, jsonResources, json, target);
        } catch (IOException e) {
            MchUI.command1.setText(languageSet.getCommandWord("commandsNotFound"));
        }
    }

    public static void display(JSONObject commandJson, String target, String targetSource, JSONObject resources, JSONObject displayJson, String commandTarget) {
        try {
            if(willOver > 0) {
                willOver -= 1;
            }

            limitedTypes limited = limitedTypes.NULL;

            try {
                //                分支解析引导，对于分支超多的命令非常重要
                steps = new JSONObject(displayJson.get(target).toString()).getInt("fork") - 1;
            } catch (Exception ignored) {

            }
            try {
                JSONArray forks = new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("forks").toString());
                int i = forks.length() - 1;
                while(i >= 0) {

                    //                    判断分支是否java版独有
                    if(new JSONObject(forks.get(i).toString()).get("fork").equals("java")) {
                        limited = limitedTypes.JAVA;
                    }

                    //                    判断分支是否基岩版独有
                    if(new JSONObject(forks.get(i).toString()).get("fork").equals("bedrock")) {
                        limited = limitedTypes.BEDROCK;
                    }

                    //                    判断分支是否通用
                    if(new JSONObject(forks.get(i).toString()).get("fork").equals("normal")) {
                        limited = limitedTypes.ALL_EDITION;
                    }

                    if(! (limited.equals(limitedTypes.ALL_EDITION))) {

                        if(limited.equals(limitedTypes.JAVA)) {
                            if(Community.showCommandMethod.equals(limitedTypes.JAVA)) {
                                if(! (i == steps)) {
                                    steps = i;
                                }
                            }
                        }

                        if(limited.equals(limitedTypes.BEDROCK)) {
                            if(Community.showCommandMethod.equals(limitedTypes.BEDROCK)) {
                                if(! (i == steps)) {
                                    steps = i;
                                }
                            }
                        }
                    }

                    i--;
                }
            } catch (Exception e) {

            }
            String lists = "";

            try {
                if(commandTarget.equals("?") | commandTarget.contains("help")) {
                    lists = containsTarget(commandJson, target);
                } else {
                    lists = containsTarget(displayJson, target);
                }
            } catch (Exception e) {
                //                commandNotFound(target);
                commandNotFound();
            }

            //            displayNotOk.put(displayNotOk.size(), target);

            try {
                if(steps >= new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
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


            if(lists.equals("")) {
                steps += 1;
            } else if(targetSource.contains(" ") & ! lists.contains("\t\n")) {
                try {
                    if(! jsa.get(commandSteps).toString().equals("@end")) {
                        MchUI.command1.setText(languageSet.getCommandWord("noComplete") + "\"" + target + "\"");
                    } else {
                        throw new CommandOverException("Overed");
                    }
                } catch (Exception e) {

                }
            } else if(targetSource.contains(" ") & lists.contains("\t\n")) {

                try {

                    if(! jsa.get(commandSteps).toString().equals("@end")) {

                        boolean err = false;

                        //        namespace
                        if(jsa.get(commandSteps).toString().equals("@namespace")) {
                            try {
                                String namespace = targetSource.substring(targetSource.lastIndexOf(" ") + 1);
                                if(! namespace.equals("") & ! namespace.contains(":")) {
                                    MchUI.command1.setText("\"" + namespace + "\"" + languageSet.getCommandWord("mayErrNamespace"));
                                    err = true;
                                }
                            } catch (StringIndexOutOfBoundsException e) {

                            }
                        }

                        //        json
                        if(jsa.get(commandSteps).toString().equals("@json")) {
                            try {
                                JSONObject test = new JSONObject(targetSource.substring(targetSource.lastIndexOf(" ")));
                                System.out.println(test + "  is correct json");
                            } catch (JSONException e) {
                                err = true;
                            } catch (StringIndexOutOfBoundsException e) {
                                //                                errProcess
                            }
                        }

                        //        uuid

                        if(jsa.get(commandSteps).toString().equals("@uuid")) {
                            try {
                                UUID uuid = UUID.fromString(targetSource.substring(targetSource.lastIndexOf(" ")));
                                System.out.println(uuid + "  is correct uuid");
                            } catch (IllegalArgumentException e) {
                                err = true;
                            } catch (StringIndexOutOfBoundsException e) {
                                //                                errProcess
                            }
                        }

                        boolean over = false;
                        if(! err) {

                            targetSource = targetSource.substring(targetSource.indexOf(" ") + 1);

                            if(targetSource.contains(" ")) {
                                target = targetSource.substring(0, targetSource.indexOf(" "));
                            } else {
                                target = targetSource;
                            }

                            if(jsa.get(commandSteps).toString().equals("@commands")) {

                            }

                            //                        displayNotOk.clear();
                            displayJson = new JSONObject((resources.get(jsa.get(commandSteps).toString()).toString()));

                            commandSteps += 1;

                            if(targetSource.contains(" ")) {
                                try {
                                    over = new JSONObject(displayJson.get(target).toString()).getBoolean("over");
                                } catch (Exception ignored) {

                                }
                                try {
                                    willOver = new JSONObject(displayJson.get(target).toString()).getInt("will_over") + 1;
                                } catch (Exception ignored) {

                                }
                            }

                            if(willOver == 0) {
                                over = true;
                            }
                        }


                        if(! over) {
                            try {
                                display(commandJson, target, targetSource, resources, displayJson, commandTarget);
                            } catch (StackOverflowError error) {

                            }
                        } else {
                            throw new CommandOverException("Overed");
                        }

                    } else {
                        MchUI.command1.setText(languageSet.getCommandWord("commandEnd"));
                    }
                } catch (JSONException e) {
                    try {
                        if(steps + 1 == new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                            commandNotFound();
                            steps = 0;
                        } else if(steps + 1 < new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                            steps += 1;
                            try {
                                display(commandJson, target, targetSource, resources, displayJson, commandTarget);
                            } catch (StackOverflowError error) {

                            }
                        }
                    } catch (JSONException ignored) {

                    }
                }
            } else {
                MchUI.command1.setText(lists);
            }
        } catch (CommandOverException e) {
            commandNotFound();
        } catch (IllegalStateException e) {

        }
    }

    //    public static void commandNotFound(String errPoint) {
    //        MchUI.command1.setText(languageSet.getCommandWord("commandEnd") + ": \"" + errPoint + "\"");
    //    }

    public static void commandNotFound() {
        try {
            MchUI.command1.setText(languageSet.getCommandWord("commandEnd"));
        } catch (Exception e) {
            try {
                MchUI.command1.wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            commandNotFound();
        }
    }

    public static String containsTarget(JSONObject displayJson, String target) {
        //        用于判断命令是否失效
        boolean invalid = false;
        //        用于获取命令使用限制列表
        String limited;
        //        用于获取命令修饰分支限制
        String catchLimited;
        //        用于返回显示的命令
        String result = "";
        //        用于保存所有已解析的命令
        String all = "";

        //        初始化语言文件
        initLanguage.initCommand();

        //        获得所有的命令
        Iterator<String> iterator = displayJson.keys();

        String allKey = "";
        while(iterator.hasNext()) {
            allKey += iterator.next() + "\n";
        }

        String allKeys = sort(allKey);

        BufferedReader br = new BufferedReader(new StringReader(allKeys));
        String next = null;

        while(true) {
            try {
                if((next = br.readLine()) == null)
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {

                all += next + "\n";

                invalid = false;
                try {
                    //            判断$invalid (是否失效)
                    invalid = new JSONObject(displayJson.get(next).toString()).getBoolean("$invalid");
                } catch (Exception ignored) {

                }

                try {
                    String exe = "";
                    if(MchUI.input_Command.getText().contains(" ")) {
                        exe = MchUI.input_Command.getText().substring(lastExecute, execute);
                        exe = exe.substring(0, exe.indexOf(" ")).replace("/", "");
                    } else {
                        exe = MchUI.input_Command.getText();
                    }
                    //                    System.out.println(exe);
                    if(! exe.equals("execute")) {
                        if(new JSONObject(displayJson.get(next).toString()).getBoolean("$execute")) {
                            next = "";
                        }
                    }
                } catch (Exception ignored) {

                }

                try {
                    //                获得命令使用限制列表
                    limited = getLimited(new JSONArray(new JSONObject(displayJson.get(next).toString()).get("limited").toString()));
                } catch (Exception e) {

                }

                try {
                    catchLimited = new JSONObject(displayJson.get(next).toString()).get("catchLimited").toString();
                    if(catchLimited.equals("bedrock") & Community.showCommandMethod.equals(limitedTypes.JAVA)) {
                        next = "";
                    }
                    if(catchLimited.equals("java") & Community.showCommandMethod.equals(limitedTypes.BEDROCK)) {
                        next = "";
                    }
                } catch (Exception e) {

                }

                if(invalid) {

                    //判断是否显示失效的命令
                    if(Community.showInvalidCommand) {

                        boolean Display = true;
                        try {
                            String noDisplayType = "";
                            BufferedReader noDisplay = new BufferedReader(new StringReader(noDisplayType()));
                            while((noDisplayType = noDisplay.readLine()) != null) {
                                if(noDisplayType.equals(next)) {
                                    Display = false;
                                }
                            }
                        } catch (Exception e) {

                        }

                        if(! Display) {

                        } else if(next.equals(target)) {
                            //                        如果完全匹配，则直接返回当前结果(加命令描述)
                            result = next + "    " + languageSet.getCommandWord(new JSONObject(displayJson.get(next).toString()).get("description").toString()) + "\t\n";
                        } else if(next.contains(target)) {
                            //                        如果不完全匹配，则保存当前结果(加命令描述)
                            result += next + "    " + languageSet.getCommandWord(new JSONObject(displayJson.get(next).toString()).get("description").toString()) + "\n";
                        }
                    }

                } else {
                    //如果不失效则直接显示

                    boolean Display = true;
                    try {
                        String noDisplayType = "";
                        BufferedReader noDisplay = new BufferedReader(new StringReader(noDisplayType()));
                        while((noDisplayType = noDisplay.readLine()) != null) {
                            if(noDisplayType.equals(next)) {
                                Display = false;
                            }
                        }
                    } catch (Exception e) {

                    }

                    if(! Display) {

                    } else if(next.equals(target)) {
                        //                        如果完全匹配，则直接返回当前结果(加命令描述)
                        result = next + "    " + languageSet.getCommandWord(new JSONObject(displayJson.get(next).toString()).get("description").toString()) + "\t\n";
                    } else if(next.contains(target)) {
                        //                        如果不完全匹配，则保存当前结果(加命令描述)
                        result += next + "    " + languageSet.getCommandWord(new JSONObject(displayJson.get(next).toString()).get("description").toString()) + "\n";
                    }
                }
            } catch (Exception e) {

            }
        }

        //        如果没有任何符合的模板文本，则判断有没有可能是允许自定义的(如选择器中的选定名称或者help里的页码)
        if(result.equals("")) {
            if(all.contains("@String")) {
                result = target + "    " + languageSet.getCommandWord(new JSONObject(displayJson.get("@String").toString()).get("description").toString()) + "\t\n";
            }
            if(all.contains("@Number")) {
                try {
                    long targetValue = Long.parseLong(target);
                    if(new JSONObject(displayJson.get("@Number").toString()).getLong("limited") > targetValue) {
                        result = targetValue + "    " + languageSet.getCommandWord(new JSONObject(displayJson.get("@Number").toString()).get("description").toString()) + "\t\n";
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

    public static String sort(String all) {
        //        排序
        String[] st = all.split("\n");

        return Arrays.toString(Arrays.stream(st).sorted().toArray()).replace("[", "").replace("]", "").replace(", ", "\n");
    }

    public static String getLimited(JSONArray limitedList) {
        String limited = "";
        for(int i = limitedList.length() - 1; i >= 0; i--) {
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
                $limited""";
    }

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(! Errors.CannotHandle) {
            if(! Community.isDaemons) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                var input = MchUI.input_Command.getText();
                if(! input.equals("") & ! input.contains("\n")) {

                    //                    if (MchUI.input_Command.getText().lastIndexOf("/") == 0 | !MchUI.input_Command.getText().contains("/")) {
                    if(! MchUI.switchTip.isFocusOwner()) {
                        CommandParsing.commands();
                    }
                    //                    }
                } else {
                    historyReader.flush();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
