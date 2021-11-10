package com.github.zhuaidadaya.MCH.Events;

import com.github.zhuaidadaya.MCH.Command.Command;
import com.github.zhuaidadaya.MCH.Command.Test;
import com.github.zhuaidadaya.MCH.Command.limitedTypes;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Config.Config;
import com.github.zhuaidadaya.MCH.Config.ConfigMain;
import com.github.zhuaidadaya.MCH.Events.KeyListener.listener;
import com.github.zhuaidadaya.MCH.Events.UPD.URLs;
import com.github.zhuaidadaya.MCH.Events.UPD.countTime;
import com.github.zhuaidadaya.MCH.UI.ExtraUI;
import com.github.zhuaidadaya.MCH.UI.Lang.languageSet;
import com.github.zhuaidadaya.MCH.UI.MinecraftLauncher;
import com.github.zhuaidadaya.MCH.UI.inputUI;
import com.github.zhuaidadaya.MCH.lib.LoadToolTips;
import com.github.zhuaidadaya.MCH.lib.Resources;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Events {
    public static void Copy(String copy) {
        StringSelection selection = new StringSelection(copy);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }

    public static void Copy() {
        Copy(inputUI.inputArea.getText().replace("\r", "").replace("\n", ""));

        if(Community.historySaveID != 2) {
            try {
                FileWriter fileWriter = new FileWriter(historyReader.file, true);
                String write = inputUI.inputArea.getText().replace("/", "").replace("\r", "").replace("\n", "");
                //                String write = MchUI.input_Command.getText().replace("/", "").replace("\r", "").replace("\n", "");
                if(! write.equals("")) {
                    write += "\n";

                    if(! Community.functionEditing) {
                        LoadAssembly.loadAssembly("[Events Thread/Copy] Copy Bytes = " + Arrays.toString(write.getBytes(StandardCharsets.UTF_8)), "", false);
                        LoadAssembly.loadAssembly("[Events Thread/Copy] copy result = " + write.replace("\n", ""), "", false);
                        fileWriter.write(write);
                    } else {
                        ExtraUI.functionEdit.setText(ExtraUI.functionEdit.getText() + write);
                    }

                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (OutOfMemoryError e) {
                Errors.errors(e, null, true, "Copy", "", 700, 520, true, false);
            }
        }
    }

    @Config
    public static void switchColor(int ColorID) {
        if(! Community.isDaemons) {
            switch(ColorID) {
                case 0 -> {
                    Community.ColorID = 0;
                    ConfigMain.colorSet = "Color@White";
                }
                case 1 -> {
                    Community.ColorID = 1;
                    ConfigMain.colorSet = "Color@Black";
                }
                case 2 -> {
                    Community.ColorID = 2;
                    ConfigMain.colorSet = "Color@Hades";
                }
            }

            try {
                ConfigMain.WriteConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }

            listener.switchTip.setLight("", true);
        }
    }

    @Config
    public static void switchLanguage(int LangID) {
        if(! Community.isDaemons) {

            int oldLangID = Community.LangID;

            switch(LangID) {
                case 0 -> {
                    Community.LangID = 0;
                    Community.LangSetID = 0;
                    ConfigMain.languageSet = "Language@Chinese";
                }
                case 1 -> {
                    Community.LangID = 1;
                    Community.LangSetID = 1;
                    ConfigMain.languageSet = "Language@English";
                }
                case 2 -> {
                    Community.LangSetID = 2;
                    ConfigMain.languageSet = "Language@Auto";
                }
                case 3 -> {
                    Community.LangID = 3;
                    Community.LangSetID = 3;
                    ConfigMain.languageSet = "Language@Chinese_TW";
                }
            }

            LoadToolTips.load("/com/github/zhuaidadaya/resources/resource_files/tooltip/tooltip.index");

            languageSet.Language();

            try {
                ConfigMain.WriteConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(LangID != oldLangID) {
                new Resources.initLanguage();
            }
        }
    }

    @Config
    public static void switchExButtonWillExit(boolean exButton) {
        if(! Community.isDaemons) {
            if(exButton) {
                Community.exitButtonWillExit = true;
                ConfigMain.exButtonSet = "Button@Ex.Exit";
            } else {
                Community.exitButtonWillExit = false;
                ConfigMain.exButtonSet = "Button@Ex.Smaller";
            }

            try {
                ConfigMain.WriteConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Config
    public static void switchFastLoad(boolean fastLoad) {
        if(! Community.isDaemons) {
            if(fastLoad) {
                Community.fastLoad = true;
                ConfigMain.fastLoadSet = "Load@Fast";
            } else {
                Community.fastLoad = false;
                ConfigMain.fastLoadSet = "Load@Safe";
            }

            try {
                ConfigMain.WriteConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Config
    public static void switchOnTop(boolean onTop) {
        if(! Community.isDaemons) {
            if(onTop) {
                Community.onTop = true;
                ConfigMain.onTopSet = "Display@OnTop";
            } else {
                Community.onTop = false;
                ConfigMain.onTopSet = "Display@Default";
            }

            try {
                ConfigMain.WriteConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Config
    public static void switchSaveUpdCache(boolean save) {
        if(! Community.isDaemons) {
            if(save) {
                Community.saveCache = true;
                ConfigMain.saveCache = "Cache@Save";
            } else {
                Community.saveCache = false;
                ConfigMain.saveCache = "Cache@Delete";
            }

            try {
                ConfigMain.WriteConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Config
    public static void switchSaveRunLog(boolean save) {
        if(! Community.isDaemons) {
            if(save) {
                Community.saveRunLog = true;
                ConfigMain.saveRunLog = "RunLog@Save";
            } else {
                Community.saveRunLog = false;
                ConfigMain.saveRunLog = "RunLog@Delete";
            }

            try {
                ConfigMain.WriteConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Config
    public static void switchSaveErrorLog(boolean save) {
        if(! Community.isDaemons) {
            if(save) {
                Community.saveErrorLog = true;
                ConfigMain.saveErrorLog = "ErrorLog@Save";
            } else {
                Community.saveErrorLog = false;
                ConfigMain.saveErrorLog = "ErrorLog@Delete";
            }

            try {
                ConfigMain.WriteConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Config
    public static void switchAutoUPD(boolean auto) {
        if(! Community.isDaemons) {
            if(auto) {
                Community.autoUPD = true;
                ConfigMain.autoPUDSet = "UPD@MCH";
            } else {
                Community.autoUPD = false;
                ConfigMain.autoPUDSet = "UPD@Self";
            }

            try {
                ConfigMain.WriteConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Config
    public static void switchHistorySaveID(int ID) {
        if(! Community.isDaemons) {
            if(ID == 0) {
                Community.historySaveID = 0;
                ConfigMain.saveHistorySet = "History@SaveAll";
            } else if(ID == 1) {
                Community.historySaveID = 1;
                ConfigMain.saveHistorySet = "History@SaveSome";
            } else if(ID == 2) {
                Community.historySaveID = 2;
                ConfigMain.saveHistorySet = "History@Delete";
            } else {
                Community.historySaveID = 1;
            }

            try {
                ConfigMain.WriteConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Config
    public static void switchMinecraftListenFlushSpeedLevel(int Levels) {
        if(! Community.isDaemons) {
            if(Levels == 0) {
                Community.minecraftListenFlushSpeedLevels = 0;
                ConfigMain.minecraftListenFlushSpeedLevel = "MinecraftListenFlushSpeedLevel@0";
            }

            if(Levels == 1) {
                Community.minecraftListenFlushSpeedLevels = 1;
                ConfigMain.minecraftListenFlushSpeedLevel = "MinecraftListenFlushSpeedLevel@1";
            }
        }

        try {
            ConfigMain.WriteConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Config
    @Command
    public static void switchInvalidCommandShow(boolean show) {
        if(! Community.isDaemons) {
            if(! show) {
                Community.showInvalidCommand = false;
                ConfigMain.showInvalidCommand = "InvalidCommand@Hide";
            } else {
                Community.showInvalidCommand = true;
                ConfigMain.showInvalidCommand = "InvalidCommand@Show";
            }
        }

        try {
            ConfigMain.WriteConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Config
    @Command
    public static void switchShowCommands(limitedTypes limitedType) {
        if(limitedType.equals(limitedTypes.BEDROCK)) {
            Community.showCommands = limitedTypes.BEDROCK;
            ConfigMain.showCommands = "Commands@Bedrock";
        }

        if(limitedType.equals(limitedTypes.JAVA)) {
            Community.showCommands = limitedTypes.JAVA;
            ConfigMain.showCommands = "Commands@Java";
        }

        if(limitedType.equals(limitedTypes.EDU)) {
            Community.showCommands = limitedTypes.EDU;
            ConfigMain.showCommands = "Commands@EDU";
        }

        if(limitedType.equals(limitedTypes.BDS)) {
            Community.showCommands = limitedTypes.BDS;
            ConfigMain.showCommands = "Commands@BDS";
        }

        if(limitedType.equals(limitedTypes.WS_SERVER)) {
            Community.showCommands = limitedTypes.WS_SERVER;
            ConfigMain.showCommands = "Commands@WSS";
        }

        try {
            ConfigMain.WriteConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Config
    @Command
    public static void switchCommandMethod(limitedTypes limitedType) {
        if(limitedType.equals(limitedTypes.BEDROCK)) {
            Community.showCommandMethod = limitedTypes.BEDROCK;
            ConfigMain.priorityDisplay = "PriorityDisplay@Bedrock";
        }

        if(limitedType.equals(limitedTypes.JAVA)) {
            Community.showCommandMethod = limitedTypes.JAVA;
            ConfigMain.priorityDisplay = "PriorityDisplay@java";
        }

        try {
            ConfigMain.WriteConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Config
    @Command
    public static void switchToWiki(boolean toWiki) {
        Community.toWiki = toWiki;

        if(toWiki) {
            ConfigMain.toWiki = "toWiki@on";
        } else {
            ConfigMain.toWiki = "toWiki@off";
        }
    }

    @Config
    public static void switchCheckResource(boolean check) {
        MinecraftLauncher.checkResource = check;

        if(check) {
            MinecraftLauncher.checkResource_config = "checkResource@check";
        } else {
            MinecraftLauncher.checkResource_config = "checkResource@ignored";
        }

        MinecraftLauncher.uploadConfig();

        try {
            ConfigMain.WriteConfig();
        } catch (Exception e) {

        }
    }

    @Command
    @Config
    public static void switchExcessProcess(boolean excess) {
        Community.excessProcess = excess;

        ConfigMain.excessProcess = excess ? "excessProcess@Enable" : "excessProcess@Disable";

        ConfigMain.uploadConfig();

        try {
            ConfigMain.WriteConfig();
        } catch(Exception ex) {

        }
    }

    @Config
    public static void checkUPD() {
        if(! Community.isDaemons) {
            URLs.checkUPD = true;
            countTime.startDUP_count = true;
        }
    }

    public static void closeStreamOfUPD() {
        //        关闭更新时的流
        try {
            URLs.out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public static void test() {
        System.out.println("test");
    }
}