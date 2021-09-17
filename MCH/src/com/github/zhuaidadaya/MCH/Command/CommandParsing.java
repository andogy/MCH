package com.github.zhuaidadaya.MCH.Command;

import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.Events.KeyListener.listener;
import com.github.zhuaidadaya.MCH.Events.historyReader;
import com.github.zhuaidadaya.MCH.Help.Helps;
import com.github.zhuaidadaya.MCH.UI.Lang.languageSet;
import com.github.zhuaidadaya.MCH.UI.MchUI;
import com.github.zhuaidadaya.MCH.UI.loadingWindow;
import com.github.zhuaidadaya.MCH.UI.perf_UI;
import com.github.zhuaidadaya.MCH.lib.Resources;
import com.github.zhuaidadaya.MCH.lib.json.JSONArray;
import com.github.zhuaidadaya.MCH.lib.json.JSONException;
import com.github.zhuaidadaya.MCH.lib.json.JSONObject;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

public class CommandParsing extends Thread {
    public static int willOver = 150;
    public static int steps = 0;
    public static int commandSteps = 0;
    public static int execute = 0;
    public static int lastExecute = 0;
    public static int allSteps = 0;

    public static String command = "";

    public static String stepNow = "";

    public static int offset = 0;

    public static String buffered;

    public static String toResource = "";

    public static boolean canToWiki = false;

    public static boolean er;

    public static boolean showElementsIsOne = false;
    public static boolean hasWiki = false;

    public static String target_save = "";
    public static JSONObject displayJson_save = new JSONObject();
    public static String wikis = "";

    public static JSONObject commandsJson = new JSONObject();

    public static void perf() {
    }

    public static void command(String command_perf, boolean perf, File command_f, String c) {
        String comm = "";
        long start = System.currentTimeMillis();

        try {
            String completeCommand = c;
            command = completeCommand;

            if(completeCommand.indexOf("/") == 0) {
                completeCommand = completeCommand.replaceFirst("/", "");
            }

            String target;
            if(completeCommand.contains(" ")) {
                target = completeCommand.substring(0, completeCommand.indexOf(" "));
            } else {
                target = completeCommand;
            }

            completeCommand = completeCommand.toLowerCase();
            target = target.toLowerCase();

            JSONObject json = new JSONObject();
            JSONObject jsonResources = new JSONObject();
            try {
                json = new JSONObject(new JSONObject(new JSONObject(commandsJson.toString()).get("resources").toString()).get("commands").toString());
                jsonResources = new JSONObject(new JSONObject(new JSONObject(commandsJson.toString()).get("resources").toString()).get("resource").toString());
            } catch (Exception ignored) {

            }

            commandSteps = 0;

            willOver = 1500;

            allSteps = 0;
            offset = 0;

            lastExecute = 0;

            loadingWindow.percentage.setText(" " + completeCommand);

            if(command_perf.equals("")) {
                display(json, target, completeCommand, jsonResources, json, target, json, jsonResources);
                comm = completeCommand;
            } else {
                display(json, target, command_perf, jsonResources, json, target, json, jsonResources);
                comm = command_perf;
            }
        } catch (Exception e) {
            MchUI.command1.setText(languageSet.getCommandWord("commandsNotFound"));
        }

        if(perf)
            perf_UI.jt.setText("command: " + comm + "\n" + "length: " + comm.length() + "\n" + "steps: " + stepNow + "\n  " + (System.currentTimeMillis() - start) + " ms\n  " + 1000 / (System.currentTimeMillis() - start) + " TPS");

    }

    public static int getNealyStepPosByCaret() {
        try {
            CommandParsing cp = new CommandParsing();
            int caret = MchUI.input_Command.getCaretPosition();
            Object[] poss = new Object[256];

            int err = allSteps < 1 ? commandSteps : allSteps;
            int errPos = 0;

            String cacheCommand = command;
            while(err != 0) {
                try {
                    errPos += cacheCommand.substring(0, cacheCommand.indexOf(" ") + 1).length();
                    cacheCommand = cacheCommand.substring(cacheCommand.indexOf(" ") + 1);
                    err--;
                    poss[cp.getNullInArray(poss)] = errPos;
                } catch (Exception e) {
                    break;
                }
            }

            if(Integer.parseInt(poss[cp.getNullInArray(poss) - 1].toString()) > caret)
                System.out.println(Integer.parseInt(poss[cp.getNullInArray(poss) - 2].toString()));

            //            System.out.println(Arrays.toString(Arrays.stream(poss).toArray()));

        } catch (Exception e) {

        }

        return - 1;
    }

    public static void display(JSONObject commandJson, String target, String targetSource, JSONObject resources, JSONObject displayJson, String commandTarget, JSONObject sourceJson, JSONObject sourceResource) {
        er = false;
        boolean full = MchUI.input_Command.getText().length() == MchUI.input_Command.getCaretPosition() || MchUI.input_Command.getCaretPosition() == 0;

        try {
            if(willOver > 0) {
                willOver -= 1;
            }

            limitedTypes limited = limitedTypes.NULL;

            try {
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
                    if(full)
                        lists = containsTarget(commandJson, target, commandJson, commandTarget);
                    else
                        lists = containsTarget(commandJson, target, commandJson, MchUI.input_Command.getText().substring(0, getNealyStepPosByCaret()));
                } else {
                    lists = containsTarget(displayJson, target, commandJson, commandTarget);
                }
            } catch (Exception e) {
                commandNotFound();
            }

            try {
                if(steps >= new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                    if(commandSteps != 0)
                        commandNotFound();
                    steps = 0;
                }
            } catch (Exception ignored) {

            }

            JSONArray jsa = new JSONArray();
            try {
                jsa = new JSONArray(new JSONObject(new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).get(steps).toString()).get("tree").toString());
            } catch (Exception e) {
                //                commandNotFound();
            }

            if(lists.equals("")) {
                steps += 1;
            } else if(targetSource.contains(" ") & ! lists.contains("\t\n")) {
                try {
                    if(! jsa.get(commandSteps).toString().equals("@end")) {
                        er = true;
                        noCompletedCommand();
                    } else {
                        throw new CommandOverException("Overed");
                    }
                } catch (Exception e) {

                }

            } else if(targetSource.contains(" ") & lists.contains("\t\n")) {

                try {
                    String STP = jsa.get(commandSteps).toString();
                    if(! STP.equals("@end")) {

                        boolean err = false;

                        stepNow = STP;

                        //        selector
                        if(STP.equals("@sel")) {
                            try {
                                String sel = targetSource.substring(targetSource.indexOf("[") + 1, targetSource.indexOf("]"));

                                if(! sel.equals("")) {
                                    if(sel.contains(",")) {
                                        containsTarget(displayJson, sel.substring(0, sel.indexOf(",")), commandJson, commandTarget);
                                    } else {
                                        System.out.println(sel);
                                        System.out.println(new JSONObject(resources.get("@sel").toString()));
                                        containsTarget(new JSONObject(resources.get("@sel").toString()), sel, commandJson, commandTarget);
                                    }
                                }
                            } catch (StringIndexOutOfBoundsException e) {

                            }
                        }

                        //        namespace
                        if(STP.equals("@namespace")) {
                            try {
                                String namespace = targetSource.substring(targetSource.indexOf(" ") + 1, targetSource.indexOf(" ", 2));
                                if(! namespace.equals("") & ! namespace.contains(":")) {
                                    MchUI.command1.setText("\"" + namespace + "\"" + languageSet.getCommandWord("mayErrNamespace"));
                                    err = true;
                                }
                            } catch (StringIndexOutOfBoundsException e) {

                            }
                        }

                        //        json
                        if(STP.equals("@json")) {
                            try {
                                String json = targetSource.substring(targetSource.lastIndexOf(" ") + 1);

                                if(! json.equals("")) {
                                    JSONObject jo = new JSONObject(json);
                                    MchUI.command1.setText(jo.toString());
                                } else {

                                }
                            } catch (JSONException e) {
                                throw new CommandErrException(String.format(languageSet.getCommandWord("jsonErr"), targetSource.substring(targetSource.lastIndexOf(" ") + 1)));
                            } catch (StringIndexOutOfBoundsException e) {

                            }
                        }

                        //        uuid

                        if(STP.equals("@uuid")) {
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

                            if(STP.equals("@commands")) {
                                allSteps += commandSteps;

                                String commandText = MchUI.input_Command.getText();

                                buffered = commandText;
                                int space = allSteps;
                                while(space > 0) {
                                    space -= 1;

                                    buffered = buffered.replaceFirst(" ", "");
                                }

                                try {
                                    buffered = buffered.substring(buffered.indexOf(" ") + 1);
                                } catch (Exception e) {
                                    buffered = buffered.substring(buffered.indexOf(" ") + 1);
                                }

                                commandText = buffered;

                                try {

                                    if(commandText.charAt(0) == ' ') {
                                        commandText = commandText.substring(1);

                                        allSteps += 1;
                                    }

                                } catch (Exception ignored) {

                                }

                                if(commandText.contains(" ")) {
                                    commandTarget = commandText.substring(0, commandText.indexOf(" "));
                                } else {
                                    commandTarget = commandText;
                                }

                                commandSteps = 0;
                                steps = 0;

                                allSteps += 1;

                                display(sourceJson, target, commandText, sourceResource, sourceJson, target, sourceJson, sourceResource);

                                throw new IllegalStateException();
                            }

                            try {
                                if(! toResource.equals("") & jsa.get(commandSteps).toString().equals("@RESOURCE_WRAP")) {
                                    displayJson = new JSONObject((resources.get(toResource)).toString());

                                    toResource = "";
                                } else {
                                    displayJson = new JSONObject((resources.get(jsa.get(commandSteps).toString()).toString()));
                                }
                            } catch (Exception e) {
                                displayJson = new JSONObject((resources.get(jsa.get(commandSteps).toString()).toString()));
                            }

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
                                display(commandJson, target, targetSource, resources, displayJson, commandTarget, sourceJson, sourceResource);
                            } catch (StackOverflowError ignored) {

                            }
                        } else {
                            throw new CommandOverException();
                        }

                    } else {
                        throw new CommandOverException();
                    }
                } catch (JSONException e) {
                    try {
                        if(steps + 1 == new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                            commandNotFound();
                            steps = 0;
                        } else if(steps + 1 < new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                            steps += 1;
                            try {
                                display(commandJson, target, targetSource, resources, displayJson, commandTarget, sourceJson, sourceResource);
                            } catch (StackOverflowError error) {

                            }
                        }
                    } catch (JSONException ignored) {

                    }
                }
            } else if(showElementsIsOne & ! wikis.equals("")) {
                if(Community.toWiki) {
                    target_save = target;
                    displayJson_save = displayJson;
                    try {
                        if(full)
                            MchUI.command1.setText(lists + "\n\n\n" + String.format(languageSet.getCommandWord("canToWiki"), languageSet.getCommandWord(new JSONObject(displayJson_save.get(wikis).toString()).get("wikiTips").toString())));
                        else
                            MchUI.tips.setText(lists + "\n\n\n" + String.format(languageSet.getCommandWord("canToWiki"), languageSet.getCommandWord(new JSONObject(displayJson_save.get(wikis).toString()).get("wikiTips").toString())));
                    } catch (Exception ex) {
                        if(full)
                            MchUI.command1.setText(lists + "\n\n\n" + String.format(languageSet.getCommandWord("canToWiki"), target));
                        else
                            MchUI.tips.setText(lists + "\n\n\n" + String.format(languageSet.getCommandWord("canToWiki"), target));
                    }
                } else {
                    if(full)
                        MchUI.command1.setText(lists);
                    else
                        MchUI.tips.setText(lists);
                }
            } else if(lists.contains("\n")) {
                if(full)
                    MchUI.command1.setText(lists);
                else
                    MchUI.tips.setText(lists);
            }
        } catch (CommandErrException e) {
            commandErr(e.getMessage());
        } catch (CommandOverException e) {
            commandOver();
        } catch (IllegalStateException e) {

        }

        if(! er)
            listener.switchTip.setLightForInput(0, command.length(), true);

    }

    public static void toWiki() {
        try {
            String wiki = new JSONObject(displayJson_save.get(target_save).toString()).get("wiki").toString();
            Helps.open(wiki);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void customCommandError(String message) {
        er = true;

        int err = allSteps < 1 ? commandSteps : allSteps;
        int errPos = 0;

        String cacheCommand = command;
        while(err != 0) {
            try {
                errPos += cacheCommand.substring(0, cacheCommand.indexOf(" ") + 1).length();
                cacheCommand = cacheCommand.substring(cacheCommand.indexOf(" ") + 1);
                err--;
            } catch (Exception e) {
                break;
            }
        }

        MchUI.command1.setText(String.format(message, command.substring(errPos)));

        listener.switchTip.setLightForInput(errPos, command.length(), false);
    }

    public static void invalidedValue(String message) {
        customCommandError(message);
    }

    public static void noCompletedCommand() {
        customCommandError(languageSet.getCommandWord("noComplete") + "\"%s\"");
    }

    public static void commandNotFound() {
        try {
            customCommandError(languageSet.getCommandWord("commandNotFound"));
        } catch (Exception e) {
            try {
                MchUI.command1.wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            commandNotFound();
        }
    }

    public static void commandOver() {
        MchUI.command1.setText(languageSet.getCommandWord("commandOver"));
    }

    public static void commandErr(String err) {
        MchUI.command1.setText(err);
    }

    //    public static void commandNotFound(String errPoint) {
    //        MchUI.command1.setText(languageSet.getCommandWord("commandEnd") + ": \"" + errPoint + "\"");
    //    }

    public static String containsTarget(JSONObject displayJson, String target, JSONObject commandJson, String commandTarget) {
        showElementsIsOne = false;
        hasWiki = false;
        wikis = "";

        //        用于判断命令是否失效
        boolean invalid;
        //        用于获取命令使用限制列表
        String limited;
        //        用于获取命令修饰分支限制
        String catchLimited;
        //        用于返回显示的命令
        StringBuilder result = new StringBuilder();
        //        用于保存所有已解析的命令
        StringBuilder all = new StringBuilder();

        //        获得所有的命令
        Iterator<String> iterator = displayJson.keys();

        StringBuilder allKey = new StringBuilder();
        while(iterator.hasNext()) {
            allKey.append(iterator.next()).append("\n");
        }

        String allKeys = sort(allKey.toString());

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

                all.append(next).append("\n");

                invalid = false;
                try {
                    //            判断$invalid (是否失效)
                    invalid = new JSONObject(displayJson.get(next).toString()).getBoolean("$invalid");
                } catch (Exception ignored) {

                }

                try {
                    String exe;
                    if(MchUI.input_Command.getText().contains(" ")) {
                        exe = MchUI.input_Command.getText().substring(lastExecute, execute);
                        exe = exe.substring(0, exe.indexOf(" ")).replace("/", "");
                    } else {
                        exe = MchUI.input_Command.getText();
                    }

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

                try {
                    if(toResource.equals("")) {
                        String resource = new JSONObject(displayJson.get(next).toString()).get("toResource").toString();

                        if(Objects.equals(next, target)) {
                            toResource = resource;
                        }
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

                        } else if(next.equals(target) | languageSet.getCommandWord(new JSONObject(displayJson.get(next).toString()).get("description").toString()).equals(target)) {
                            showElementsIsOne = true;

                            try {
                                new JSONObject(displayJson.get(next).toString()).get("wiki").toString();
                                wikis = next;
                                hasWiki = true;
                            } catch (Exception ex) {
                                // it did not have wiki
                            }

                            //                        如果完全匹配，则直接返回当前结果(加命令描述)
                            result = new StringBuilder(next + "    '" + languageSet.getCommandWord(new JSONObject(displayJson.get(next).toString()).get("description").toString()) + "\t\n");
                        } else if(next.contains(target) | languageSet.getCommandWord(new JSONObject(displayJson.get(next).toString()).get("description").toString()).contains(target)) {
                            //                        如果不完全匹配，则保存当前结果(加命令描述)
                            result.append(next).append("   '").append(languageSet.getCommandWord(new JSONObject(displayJson.get(next).toString()).get("description").toString())).append("\n");
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

                    } else if(next.equals(target) | languageSet.getCommandWord(new JSONObject(displayJson.get(next).toString()).get("description").toString()).equals(target)) {
                        showElementsIsOne = true;

                        try {
                            new JSONObject(displayJson.get(next).toString()).get("wiki").toString();
                            wikis = next;
                            hasWiki = true;
                        } catch (Exception ex) {
                            // it did not have wiki
                        }

                        //                        如果完全匹配，则直接返回当前结果(加命令描述)
                        result = new StringBuilder(next + "    '" + languageSet.getCommandWord(new JSONObject(displayJson.get(next).toString()).get("description").toString()) + "\t\n");
                    } else if(next.contains(target) | languageSet.getCommandWord(new JSONObject(displayJson.get(next).toString()).get("description").toString()).contains(target)) {
                        //                        如果不完全匹配，则保存当前结果(加命令描述)
                        result.append(next).append("    '").append(languageSet.getCommandWord(new JSONObject(displayJson.get(next).toString()).get("description").toString())).append("\n");
                    }
                }
            } catch (Exception e) {

            }
        }


        //        如果没有任何符合的模板文本，则判断有没有可能是允许自定义的(如选择器中的选定名称或者help里的页码)
        if(result.toString().equals("")) {
            if(all.toString().contains("@String")) {
                showElementsIsOne = true;

                try {
                    new JSONObject(displayJson.get("@String").toString()).get("wiki").toString();
                    wikis = "@String";
                    hasWiki = true;
                } catch (Exception ex) {
                    // it did not have wiki
                }

                result = new StringBuilder(target + "    " + languageSet.getCommandWord(new JSONObject(displayJson.get("@String").toString()).get("description").toString()) + "\t\n");
            }
            if(all.toString().contains("@Integer")) {
                try {
                    showElementsIsOne = true;

                    try {
                        new JSONObject(displayJson.get("@Integer").toString()).get("wiki").toString();
                        wikis = "@Integer";
                        hasWiki = true;
                    } catch (Exception ex) {
                        // it did not have wiki
                    }
                    int targetValue = Integer.parseInt(target);
                    if(new JSONObject(displayJson.get("@Integer").toString()).getLong("limited") > targetValue) {
                        result = new StringBuilder(targetValue + "    " + languageSet.getCommandWord(new JSONObject(displayJson.get("@Integer").toString()).get("description").toString()) + "\t\n");
                    } else {
                        invalidedValue(languageSet.getCommandWord(new JSONObject(displayJson.get("@Integer").toString()).get("valueOutLimited").toString()) + " - \"%s\"\n");
                        return "";
                    }
                } catch (NumberFormatException e) {
                    invalidedValue(languageSet.getCommandWord(new JSONObject(displayJson.get("@Integer").toString()).get("valueErr").toString()) + " - \"%s\"\n");
                    return "";
                }
            }
            if(all.toString().contains("@Double")) {
                try {
                    showElementsIsOne = true;

                    try {
                        new JSONObject(displayJson.get("@Double").toString()).get("wiki").toString();
                        wikis = "@Double";
                        hasWiki = true;
                    } catch (Exception ex) {
                        // it did not have wiki
                    }
                    double targetValue = Double.parseDouble(target);
                    if(new JSONObject(displayJson.get("@Double").toString()).getLong("limited") > targetValue) {
                        result = new StringBuilder(targetValue + "    " + languageSet.getCommandWord(new JSONObject(displayJson.get("@Double").toString()).get("description").toString()) + "\t\n");
                    } else {
                        invalidedValue(languageSet.getCommandWord(new JSONObject(displayJson.get("@Double").toString()).get("valueOutLimited").toString()) + " - \"%s\"\n");
                        return "";
                    }
                } catch (NumberFormatException e) {
                    invalidedValue(languageSet.getCommandWord(new JSONObject(displayJson.get("@Double").toString()).get("valueErr").toString()) + " - \"%s\"\n");
                    return "";
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

        return result.toString();
    }

    public static String sort(String all) {
        //        排序
        String[] st = all.split("\n");

        return Arrays.toString(Arrays.stream(st).sorted().toArray()).replace("[", "").replace("]", "").replace(", ", "\n");
    }

    public static String getLimited(JSONArray limitedList) {
        StringBuilder limited = new StringBuilder();
        for(int i = limitedList.length() - 1; i >= 0; i--) {
            try {
                limited.append(limitedList.get(i)).append("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return limited.toString();
    }

    public static String noDisplayType() {
        return """
                @String
                @Integer
                @Double
                @Float
                $limited""";
    }

    public void initCommandJSON(String initPath) {
        try {
            initCommandJSON(new File(Resources.getResourceByFile("/com/github/zhuaidadaya/resources/resource_files/commands.json", this.getClass()).toURI()));

            for(File f : new File(initPath).listFiles())
                initCommandJSON(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initCommandJSON(File f) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));

            StringBuilder command = new StringBuilder();
            String c;
            while((c = br.readLine()) != null)
                command.append(c);

            JSONObject JSONc = new JSONObject(command.toString());

            for(Object o : JSONc.keySet())
                commandsJson.putOnce(o.toString(), JSONc.get(o.toString()));
        } catch (Exception e) {
            //            e.printStackTrace();
        }
    }

    public int getNullInArray(Object[] array) {
        for(int i = 0; i < array.length - 1; i++) {
            if(array[i] == null) {
                return i;
            }
        }
        return - 1;
    }

    public void commands(String command_perf, boolean perf, String command) {
        try {
            command(command_perf, perf, new File(Resources.getResourceByFile("/com/github/zhuaidadaya/resources/resource_files/commands.json", this.getClass()).toURI()), command);
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {
        new Thread(() -> {
            while(! Errors.CannotHandle) {
                //                Resources.initLanguage.initLang("commands/commands.json", "/com/github/zhuaidadaya/resources/resource_files/", "");
                Resources.initLanguage.initFromSelf("commands/commands.json", "/com/github/zhuaidadaya/resources/resource_files/", "", this.getClass());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while(! Errors.CannotHandle) {
            long standTick = 50;
            long tick = System.currentTimeMillis();

            if(! Community.isDaemons) {
                var input = MchUI.input_Command.getText();
                if(! input.equals("") & ! input.contains("\n")) {
                    initCommandJSON(Config.resPath);
                    Resources.initLanguage.initFromSelf("commands.json", "/com/github/zhuaidadaya/resources/resource_files/", "", this.getClass());

                    //                    if (MchUI.input_Command.getText().lastIndexOf("/") == 0 | !MchUI.input_Command.getText().contains("/")) {
                    if(! MchUI.switchTip.isFocusOwner()) {
                        new CommandParsing().commands("", Community.perf, MchUI.input_Command.getText());
                    }
                    //                    }
                } else {
                    stepNow = "";
                    command = "";
                    if(Community.historySaveID == 2) {
                        MchUI.command1.setText("");
                    } else {
                        historyReader.flush();
                    }
                }
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
            }

            if(System.currentTimeMillis() - tick < standTick) {
                try {
                    Thread.sleep(standTick - (System.currentTimeMillis() - tick));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
