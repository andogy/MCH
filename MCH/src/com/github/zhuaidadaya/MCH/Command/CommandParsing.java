package com.github.zhuaidadaya.MCH.Command;

import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Config.ConfigMain;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.Events.KeyListener.listener;
import com.github.zhuaidadaya.MCH.Events.historyReader;
import com.github.zhuaidadaya.MCH.Help.Helps;
import com.github.zhuaidadaya.MCH.UI.Lang.languageSet;
import com.github.zhuaidadaya.MCH.UI.MchUI;
import com.github.zhuaidadaya.MCH.UI.inputUI;
import com.github.zhuaidadaya.MCH.UI.loadingWindow;
import com.github.zhuaidadaya.MCH.UI.perf_UI;
import com.github.zhuaidadaya.MCH.lib.Resources;
import com.github.zhuaidadaya.MCH.lib.json.JSONArray;
import com.github.zhuaidadaya.MCH.lib.json.JSONException;
import com.github.zhuaidadaya.MCH.lib.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Command
public class CommandParsing extends Thread {
    public static int willOver = 150;
    public static int steps = 0;
    public static int commandSteps = 0;
    public static int commandSteps_inside = 0;
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

    public static boolean changing = false;

    public static String target_save = "";
    public static JSONObject displayJson_save = new JSONObject();
    public static String wikis = "";

    public static JSONObject commandsJson = new JSONObject();

    @Test
    public static void main(String[] args) {
        new Resources.initLanguage();
        try {
            new CommandParsing().initCommandJSON(Resources.getResource("/com/github/zhuaidadaya/resources/resource_files/commands.json", CommandParsing.class));
        } catch (Exception e) {

        }

        new CommandParsing().commands("help 12", true, "");
    }

    public static void command(String command) {
        command("", false, command);
    }

    public static void command(String perf_command, boolean perf) {
        command(perf_command, true, "");
    }

    public static void command(String perf_command, String command, boolean perf) {
        command(perf_command, perf, command);
    }

    public static void command(String perf_command, boolean perf, String command_complete) {
        String comm = "";
        long start = System.currentTimeMillis();

        try {
            String completeCommand = command_complete;
            CommandParsing.command = completeCommand;

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
            commandSteps_inside = 0;

            willOver = 1500;

            allSteps = 0;
            offset = 0;

            lastExecute = 0;

            loadingWindow.percentage.setText(" " + completeCommand);

            MchUI.progressBar.setValue(0);
            MchUI.progressBar.setMaximum(completeCommand.length());

            CommandStats stats = checkCommand(json, target, completeCommand, jsonResources, json, target, json, jsonResources, new LinkedHashMap<>(), "", null);
            comm = completeCommand;

            if(stats.getStats().equals("error")) {
                CommandError<?, ?, ?> er = (CommandError<?, ?, ?>) stats;
                listener.switchTip.setLightForInput(er.getErrPos(), er.getCommandLength(), false);
                MchUI.commandArea.setText(er.getInfo());
            }
        } catch (Exception e) {
            e.printStackTrace();
            MchUI.commandArea.setText(languageSet.getCommandWord("commandsNotFound"));
        }

        if(perf)
            perf_UI.jt.setText("command: " + comm + "\n" + "length: " + comm.length() + "\n" + "steps: " + stepNow + "\n  " + (System.currentTimeMillis() - start) + " ms\n  " + 1000 / (System.currentTimeMillis() - start) + " TPS");

    }

    //    public static int getNealyStepPosByCaret() {
    //        try {
    //            CommandParsing cp = new CommandParsing();
    //            //            int caret = MchUI.input_Command.getCaretPosition();
    //            int caret = inputUI.inputArea.getCaretPosition();
    //            Object[] poss = new Object[256];
    //
    //            int err = allSteps < 1 ? commandSteps : allSteps;
    //            int errPos = 0;
    //
    //            String cacheCommand = command;
    //            while(err != 0) {
    //                try {
    //                    errPos += cacheCommand.substring(0, cacheCommand.indexOf(" ") + 1).length();
    //                    cacheCommand = cacheCommand.substring(cacheCommand.indexOf(" ") + 1);
    //                    err--;
    //                    poss[cp.getNullInArray(poss)] = errPos;
    //                } catch (Exception e) {
    //                    break;
    //                }
    //            }
    //
    //            if(Integer.parseInt(poss[cp.getNullInArray(poss) - 1].toString()) > caret)
    //                return (Integer.parseInt(poss[cp.getNullInArray(poss) - 2].toString()));
    //
    //            //            System.out.println(Arrays.toString(Arrays.stream(poss).toArray()));
    //
    //        } catch (Exception e) {
    //
    //        }
    //
    //        return 0;
    //    }

    public static CommandStats checkCommand(JSONObject commandJson, String target, String targetSource, JSONObject resources, JSONObject displayJson, String commandTarget, JSONObject sourceJson, JSONObject sourceResource, LinkedHashMap<Object, Object> group, String groupType, JSONObject inCommandJson) {
        MchUI.progressBar.setValue(MchUI.progressBar.getMaximum() - targetSource.length());

        //        if(changing)
        //            targetSource = "";
        boolean inGroup = false;

        er = false;
        //        boolean full = MchUI.input_Command.getText().length() == MchUI.input_Command.getCaretPosition() || MchUI.input_Command.getCaretPosition() == 0;
        //
        //        boolean full = command.length() == inputUI.inputArea.getCaretPosition() || inputUI.inputArea.getCaretPosition() == 0;
        target_save = target;

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
            String lists;

            try {
                CommandStats stats;
                if(commandTarget.equals("?") | commandTarget.equals("help")) {
                    //                    if(full)
                    stats = containsTarget(commandJson, target, group, groupType, inCommandJson);
                    //                    else
                    //                        lists = containsTarget(commandJson, target, commandJson, inputUI.inputArea.getText().substring(0, getNealyStepPosByCaret()), "");
                    //                    lists = containsTarget(commandJson, target, commandJson, MchUI.input_Command.getText().substring(0, getNealyStepPosByCaret()));
                } else {
                    //                    if(full)
                    stats = containsTarget(displayJson, target, group, groupType, inCommandJson);
                    //                    else
                    //                        lists = containsTarget(displayJson, target, commandJson, inputUI.inputArea.getText().substring(0, getNealyStepPosByCaret()), "");
                }
                if(! stats.getStats().equals("error"))
                    lists = stats.getInfo();
                else
                    return stats;
            } catch (Exception e) {
                return commandNotFound();
            }

            try {
                if(steps >= new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                    if(commandSteps != 0)
                        return commandNotFound();
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

            try {
                JSONObject gp = new JSONObject(jsa.get(commandSteps).toString());
                inCommandJson = gp;
                commandSteps_inside++;
                groupType = gp.get("type").toString();
                if(gp.getJSONArray("group").length() <= commandSteps_inside - 1) {
                    commandSteps_inside = 0;
                    commandSteps++;
                    group.clear();
                    groupType = "";
                } else {
                    inGroup = true;
                }
            } catch (Exception ex) {
                groupType = "";
            }

            if(commandSteps_inside != 0) {
                jsa = new JSONObject(jsa.get(commandSteps).toString()).getJSONArray("group");
            }

            int getStep = commandSteps_inside != 0 ? Math.max(0, commandSteps_inside - 1) : commandSteps;

            if(lists.equals("")) {
                if(steps >= jsa.length())
                    return commandNotFound();

                steps++;
                return commandNotFound();
            } else if(targetSource.contains(" ") & ! lists.contains("\t\n")) {
                try {
                    if(! jsa.get(getStep).toString().equals("@end")) {
                        er = true;
                        return noCompletedCommand();
                    } else {
                        throw new CommandOverException("Overed");
                    }
                } catch (Exception e) {

                }
            } else if(targetSource.contains(" ") & lists.contains("\t\n")) {
                try {
                    String STP = jsa.get(getStep).toString();

                    if(inGroup & getStep > 0)
                        group.put(group.size(), target);

                    if(! STP.equals("@end")) {

                        boolean err = false;

                        stepNow = STP;

                        //        selector
                        //                        if(STP.equals("@sel")) {
                        //                            try {
                        //                                String sel = targetSource.substring(targetSource.indexOf("[") + 1, targetSource.indexOf("]"));
                        //
                        //                                if(! sel.equals("")) {
                        //                                    if(sel.contains(",")) {
                        //                                        containsTarget(displayJson, sel.substring(0, sel.indexOf(",")), commandJson, commandTarget, sel.substring(0, sel.indexOf(",")));
                        //                                    } else {
                        //                                        containsTarget(new JSONObject(resources.get("@sel").toString()), sel, commandJson, commandTarget, "@sel");
                        //                                    }
                        //                                }
                        //                            } catch (StringIndexOutOfBoundsException e) {
                        //
                        //                            }
                        //                        }

                        //        namespace
                        if(STP.equals("@namespace")) {
                            try {
                                String namespace = targetSource.substring(targetSource.indexOf(" ") + 1, targetSource.indexOf(" ", 2));
                                if(! namespace.equals("") & ! namespace.contains(":")) {
                                    MchUI.commandArea.setText("\"" + namespace + "\"" + languageSet.getCommandWord("mayErrNamespace"));
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
                                    MchUI.commandArea.setText(jo.toString());
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

                                String commandText = command;

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

                                return checkCommand(sourceJson, target, commandText, sourceResource, sourceJson, target, sourceJson, sourceResource, group, groupType, inCommandJson);
                            }

                            try {
                                if(! toResource.equals("") & jsa.get(getStep).toString().equals("@RESOURCE_WRAP")) {
                                    displayJson = new JSONObject((resources.get(toResource)).toString());

                                    toResource = "";
                                } else {
                                    displayJson = new JSONObject((resources.get(jsa.get(getStep).toString()).toString()));
                                }
                            } catch (Exception e) {
                                steps++;
                                //displayJson = new JSONObject((resources.get(jsa.get(commandSteps).toString()).toString()));
                            }

                            if(! inGroup) {
                                commandSteps++;
                                commandSteps_inside = 0;
                            }
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
                            return checkCommand(commandJson, target, targetSource, resources, displayJson, commandTarget, sourceJson, sourceResource, group, groupType, inCommandJson);
                        } else {
                            throw new CommandOverException();
                        }
                    } else {
                        throw new CommandOverException();
                    }
                } catch (JSONException e) {
                    try {
                        if(steps + 1 == new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                            steps = 0;
                        } else if(steps + 1 < new JSONArray(new JSONObject(commandJson.get(commandTarget).toString()).get("usage").toString()).length()) {
                            steps += 1;
                            return checkCommand(commandJson, target, targetSource, resources, displayJson, commandTarget, sourceJson, sourceResource, group, groupType, inCommandJson);
                        }
                    } catch (JSONException ignored) {

                    }
                }
            } else if(showElementsIsOne & ! wikis.equals("")) {
                if(Community.toWiki) {
                    displayJson_save = displayJson;
                    try {
                        MchUI.commandArea.setText(lists + "\n\n\n" + String.format(languageSet.getCommandWord("canToWiki"), languageSet.getCommandWord(new JSONObject(displayJson_save.get(wikis).toString()).get("wikiTips").toString())));
                    } catch (Exception ex) {
                        MchUI.commandArea.setText(lists + "\n\n\n" + String.format(languageSet.getCommandWord("canToWiki"), target));
                    }
                } else {
                    MchUI.commandArea.setText(lists);
                }
            } else if(lists.contains("\n")) {
                MchUI.commandArea.setText(lists);
            }
        } catch (CommandErrException e) {
            return customCommandError(e.getMessage());
        } catch (CommandOverException e) {
            return commandOver();
        }

        if(! er)
            listener.switchTip.setLightForInput(0, command.length(), true);

        return new CommandChecker<>("pass");
    }

    public static void toWiki() {
        try {
            if(canToWiki & Community.toWiki) {
                String wiki = new JSONObject(displayJson_save.get(target_save).toString()).get("wiki").toString();
                Helps.openInBrowse(wiki);
            }
        } catch (Exception ex) {

        }
    }

    public static CommandStats customCommandError(String message) {
        er = true;

        int err = allSteps < 1 ? (commandSteps_inside > 0 ? commandSteps_inside : commandSteps) : allSteps + 2;
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

        //        return (String.format(message, command.substring(errPos)));
        return new CommandError<>(String.format(message, command.substring(errPos)), errPos, command.length());
    }

    public static CommandStats invalidedValue(String message) {
        return customCommandError(message);
    }

    public static CommandStats noCompletedCommand() {
        return customCommandError(languageSet.getCommandWord("noComplete") + "\"%s\"");
    }

    public static CommandStats commandNotFound() {
        try {
            return customCommandError(languageSet.getCommandWord("commandNotFound"));
        } catch (Exception e) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {

            }
            return commandNotFound();
        }
    }

    public static CommandStats commandOver() {
        return customCommandError(languageSet.getCommandWord("commandOver"));
    }

    public static void commandErr(String err) {
        MchUI.commandArea.setText(err);
    }

    //    public static void commandNotFound(String errPoint) {
    //        MchUI.command1.setText(languageSet.getCommandWord("commandEnd") + ": \"" + errPoint + "\"");
    //    }

    public static CommandStats containsTarget(JSONObject displayJson, String target, LinkedHashMap<Object, Object> group, String groupType, JSONObject inCommandJson) {
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

        String groupFilter = "";

        StringBuilder allKey = new StringBuilder();
        while(iterator.hasNext()) {
            allKey.append(iterator.next()).append("\n");
        }

        if(group.size() > 0) {
            if(groupType.equals("xyz")) {
                try {
                    for(Object o : group.values()) {
                        if(o.toString().charAt(0) == '~')
                            groupFilter = "~";
                        else if(o.toString().charAt(0) == '^')
                            groupFilter = "^";
                    }
                } catch (Exception e) {

                }
            }
        }

        String allKeys = sort(allKey.toString());

        BufferedReader br = new BufferedReader(new StringReader(allKeys));
        String next = null;
        String saveNext = "";

        while(true) {
            try {
                if((next = br.readLine()) == null)
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(target.contains(next))
                saveNext = next;

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
                    //                    if(MchUI.input_Command.getText().contains(" ")) {
                    //                        exe = MchUI.input_Command.getText().substring(lastExecute, execute);
                    //                        exe = exe.substring(0, exe.indexOf(" ")).replace("/", "");
                    //                    } else {
                    //                        exe = MchUI.input_Command.getText();
                    //                    }
                    if(command.contains(" ")) {
                        exe = command.substring(lastExecute, execute);
                        exe = exe.substring(0, exe.indexOf(" ")).replace("/", "");
                    } else {
                        exe = command;
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

                        if(Objects.equals(next, target))
                            toResource = resource;
                    }
                } catch (Exception e) {

                }

                if(invalid) {
                    //判断是否显示失效的命令
                    if(Community.showInvalidCommand) {
                        boolean Display = true;
                        try {
                            String noDisplayType;
                            BufferedReader noDisplay = new BufferedReader(new StringReader(noDisplayType()));
                            while((noDisplayType = noDisplay.readLine()) != null) {
                                if(noDisplayType.equals(next)) {
                                    Display = false;
                                }
                            }

                            if(! groupFilter.equals("") & ! next.equals(""))
                                Display = next.equals(groupFilter);
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
                        String noDisplayType;
                        BufferedReader noDisplay = new BufferedReader(new StringReader(noDisplayType()));
                        while((noDisplayType = noDisplay.readLine()) != null) {
                            if(noDisplayType.equals(next)) {
                                Display = false;
                            }
                        }

                        if(! groupFilter.equals("") & ! next.equals(""))
                            Display = next.equals(groupFilter);
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
        CommandStats checker = noMatchs(result, all, target, displayJson, saveNext, inCommandJson, groupFilter);

        /*
        Don't Delete it!!!
        There have a bug, if not charAt, then the parse will not normal
        不要删除这行!!!
        这有一个小bug，如果不进行charAt，解析会变得不太正常
         */
        //        result.charAt(0);

        return checker;
    }

    public static CommandStats noMatchs(StringBuilder result, StringBuilder all, String target, JSONObject displayJson, String saveNext, JSONObject inCommandJson, String groupFilter) {
        String exceptType = "";
        try {
            exceptType = inCommandJson.get("except").toString();
        } catch (Exception e) {

        }

        if(! groupFilter.equals("") & ! target.equals("") & ! target.equals(groupFilter) & ! all.toString().contains(exceptType))
            return customCommandError(String.format(languageSet.getCommandWord(inCommandJson.get("valueErr").toString()), target));

        if(result.toString().equals("")) {
            try {
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
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if(all.toString().contains("@Integer")) {
                    JSONObject json = new JSONObject(displayJson.get("@Integer").toString());
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
                            result = new StringBuilder(targetValue + "    " + languageSet.getCommandWord(json.get("description").toString()) + "\t\n");
                        } else {
                            return invalidedValue(languageSet.getCommandWord(json.get("valueOutLimited").toString()) + " - \"%s\"\n");
                        }
                    } catch (NumberFormatException e) {
                        return invalidedValue(languageSet.getCommandWord(json.get("valueErr").toString()) + " - \"%s\"\n");
                    }
                }
            } catch (Exception e) {

            }

            try {
                if(all.toString().contains("@Double")) {
                    int ignoredSize = 0;
                    try {
                        ignoredSize = new JSONObject(displayJson.get("@Double").toString()).getInt("ignoredSize");
                    } catch (Exception e) {

                    }

                    if(ignoredSize > 0 & ignoredSize < target.length())
                        target = target.substring(ignoredSize);

                    JSONObject json = new JSONObject(displayJson.get("@Double").toString());

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
                            result = new StringBuilder(targetValue + "    " + String.format(languageSet.getCommandWord(json.get("description").toString()), targetValue) + "\t\n");
                        } else {
                            return invalidedValue(languageSet.getCommandWord(json.get("valueOutLimited").toString()) + " - \"%s\"\n");
                        }
                    } catch (NumberFormatException e) {
                        try {
                            if(new JSONObject(displayJson.get(saveNext).toString()).get("extends") != null) {
                                JSONArray extendJsons = new JSONArray(new JSONObject(displayJson.get(saveNext).toString()).get("extends").toString());
                                for(Object extendJson : extendJsons) {
                                    if(new JSONObject(extendJson.toString()).get("@Double") != null) {
                                        return noMatchs(result, all, target, new JSONObject(extendJson.toString()), saveNext, inCommandJson, groupFilter);
                                    }
                                }
                            }
                        } catch (Exception e1) {

                        }

                        return invalidedValue(languageSet.getCommandWord(json.get("valueErr").toString()) + " - \"%s\"\n");
                    }
                }
            } catch (Exception e) {

            }

            try {
                if(all.toString().contains("@sel_extends")) {
                    showElementsIsOne = true;

                    try {
                        new JSONObject(displayJson.get("@sel_extends").toString()).get("wiki").toString();
                        wikis = "@sel_extends";
                        hasWiki = true;
                    } catch (Exception ex) {
                        // it did not have wiki
                    }

                    result = new StringBuilder(target + "    " + languageSet.getCommandWord(new JSONObject(displayJson.get("@sel_extends").toString()).get("description").toString()) + "\t\n");
                }
            } catch (Exception e) {

            }
        }

        return new CommandChecker<>(result.toString());
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
                $limited
                @sel_extends""";
    }

    public void initCommandJSON(String initPath) {
        try {
            initCommandJSON(Resources.getResource("/com/github/zhuaidadaya/resources/resource_files/commands.json", Resources.class));

            for(File f : new File(initPath).listFiles()) {
                initCommandJSON(new FileInputStream(f));
            }
        } catch (Exception e) {

        }
    }

    public void initCommandJSON(InputStream inputStream) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            StringBuilder command = new StringBuilder();
            String c;
            while((c = br.readLine()) != null)
                command.append(c);

            JSONObject JSONc = new JSONObject(command.toString());

            for(Object o : JSONc.keySet())
                commandsJson.putOnce(o.toString(), JSONc.get(o.toString()));

        } catch (Exception e) {

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
            command(command_perf, perf, command);
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {
                new Thread(() -> {
                    while(! Errors.CannotHandle) {
                        if(! Community.isDaemons) {
                            //                Resources.initLanguage.initLang("commands/commands.json", "/com/github/zhuaidadaya/resources/resource_files/", "");
                            initCommandJSON(ConfigMain.resPath + "commands/");
                            Resources.initLanguage.initFromSelf("commands.json", "/com/github/zhuaidadaya/resources/resource_files/", "", this.getClass());

                            try {
                                Thread.sleep(250);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

        new Thread(() -> {
            String text = inputUI.inputArea.getText();
            while(! Errors.CannotHandle) {
                if(! Community.isDaemons) {
                    changing = ! text.equals(inputUI.inputArea.getText());
                    text = inputUI.inputArea.getText();

                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        while(! Errors.CannotHandle) {
            long standTick = inputUI.inputArea.getText().equals("") ? 200 : (Community.excessProcess ? 1 : 125);
            long tick = System.currentTimeMillis();

            if(! Community.isDaemons) {
                var input = inputUI.inputArea.getText();
                if(! input.equals("") & ! input.contains("\n")) {
                    //                    if (MchUI.input_Command.getText().lastIndexOf("/") == 0 | !MchUI.input_Command.getText().contains("/")) {
                    if(! MchUI.switchTip.isFocusOwner()) {
                        //                        new CommandParsing().commands("", Community.perf, MchUI.input_Command.getText());
                        new CommandParsing().commands("", Community.perf, inputUI.inputArea.getText());
                    }
                    //                    }
                } else {
                    stepNow = "";
                    command = "";
                    if(Community.historySaveID == 2) {
                        MchUI.commandArea.setText("");
                    } else {
                        historyReader.flush();
                    }
                }

                if(! MchUI.switchTip.isVisible())
                    MchUI.commandDisplay.setText(MchUI.commandArea.getText().replace("'", " "));
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
