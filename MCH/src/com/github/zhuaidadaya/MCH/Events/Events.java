package com.github.zhuaidadaya.MCH.Events;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.Command.limitedTypes;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.KeyListener.listener;
import com.github.zhuaidadaya.MCH.Events.UPD.URLs;
import com.github.zhuaidadaya.MCH.Events.UPD.countTime;
import com.github.zhuaidadaya.MCH.UI.*;
import com.github.zhuaidadaya.MCH.UI.Lang.languageSet;
import com.github.zhuaidadaya.MCH.lib.Resources;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Events {
    public static void Copy() {
        StringSelection selection = new StringSelection(inputUI.inputArea.getText().replace("\r", "").replace("\n", ""));
//        StringSelection selection = new StringSelection(MchUI.input_Command.getText().replace("\r", "").replace("\n", ""));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);

        if(Community.historySaveID != 2) {
            try {
                FileWriter fileWriter = new FileWriter(historyReader.file, true);
                String write = inputUI.inputArea.getText().replace("/", "").replace("\r", "").replace("\n", "");
//                String write = MchUI.input_Command.getText().replace("/", "").replace("\r", "").replace("\n", "");
                if(! write.equals("")) {
                    write += "\n";

                    if(! Community.functionEditing) {
                        LoadAssembly.loadAssembly("[Events Thread/Copy] Copy Bytes = "+ Arrays.toString(write.getBytes(StandardCharsets.UTF_8)), "",false);
                        LoadAssembly.loadAssembly("[Events Thread/Copy] copy result = " + write.replace("\n", "") , "",false);
                        fileWriter.write(write);
                    } else {
                        ExtraUI.functionEdit.setText(ExtraUI.functionEdit.getText() + write);
                    }

                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (OutOfMemoryError e) {
                Errors.errors(e, null, true, "Copy","",700,520,true);
            }
        }
    }

    public static void switchColor(int ColorID) {
        if(! Community.isDaemons) {
            switch(ColorID) {
                case 0 -> {
                    Community.ColorID = 0;
                    Config.colorSet = "Color@White";
                }
                case 1 -> {
                    Community.ColorID = 1;
                    Config.colorSet = "Color@Black";
                }
                case 2 -> {
                    Community.ColorID = 2;
                    Config.colorSet = "Color@Hades";
                }
            }

            try {
                Config.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }

            listener.switchTip.setLight("", true);
        }
    }

    public static void switchLanguage(int LangID) {
        if(! Community.isDaemons) {

            int oldLangID = Community.LangID;

            switch(LangID) {
                case 0 -> {
                    Community.LangID = 0;
                    Community.LangSetID = 0;
                    Config.languageSet = "Language@Chinese";
                }
                case 1 -> {
                    Community.LangID = 1;
                    Community.LangSetID = 1;
                    Config.languageSet = "Language@English";
                }
                case 2 -> {
                    Community.LangSetID = 2;
                    Config.languageSet = "Language@Auto";
                }
                case 3 -> {
                    Community.LangID = 3;
                    Community.LangSetID = 3;
                    Config.languageSet = "Language@Chinese_TW";
                }
            }

            languageSet.Language();

            try {
                Config.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(LangID != oldLangID) {
                new Resources.initLanguage();
            }
        }
    }

    public static void switchExButtonWillExit(boolean exButton) {
        if(! Community.isDaemons) {
            if(exButton) {
                Community.exitButtonWillExit = true;
                Config.exButtonSet = "Button@Ex.Exit";
            } else {
                Community.exitButtonWillExit = false;
                Config.exButtonSet = "Button@Ex.Smaller";
            }

            try {
                Config.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchFastLoad(boolean fastLoad) {
        if(! Community.isDaemons) {
            if(fastLoad) {
                Community.fastLoad = true;
                Config.fastLoadSet = "Load@Fast";
            } else {
                Community.fastLoad = false;
                Config.fastLoadSet = "Load@Safe";
            }

            try {
                Config.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchOnTop(boolean onTop) {
        if(! Community.isDaemons) {
            if(onTop) {
                Community.onTop = true;
                Config.onTopSet = "Display@OnTop";
            } else {
                Community.onTop = false;
                Config.onTopSet = "Display@Default";
            }

            try {
                Config.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchSaveUpdCache(boolean save) {
        if(! Community.isDaemons) {
            if(save) {
                Community.saveCache = true;
                Config.saveCache = "Cache@Save";
            } else {
                Community.saveCache = false;
                Config.saveCache = "Cache@Delete";
            }

            try {
                Config.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchSaveRunLog(boolean save) {
        if(! Community.isDaemons) {
            if(save) {
                Community.saveRunLog = true;
                Config.saveRunLog = "RunLog@Save";
            } else {
                Community.saveRunLog = false;
                Config.saveRunLog = "RunLog@Delete";
            }

            try {
                Config.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchSaveErrorLog(boolean save) {
        if(! Community.isDaemons) {
            if(save) {
                Community.saveErrorLog = true;
                Config.saveErrorLog = "ErrorLog@Save";
            } else {
                Community.saveErrorLog = false;
                Config.saveErrorLog = "ErrorLog@Delete";
            }

            try {
                Config.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchAutoUPD(boolean auto) {
        if(! Community.isDaemons) {
            if(auto) {
                Community.autoUPD = true;
                Config.autoPUDSet = "UPD@MCH";
            } else {
                Community.autoUPD = false;
                Config.autoPUDSet = "UPD@Self";
            }

            try {
                Config.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchHistorySaveID(int ID) {
        if(! Community.isDaemons) {
            if(ID == 0) {
                Community.historySaveID = 0;
                Config.saveHistorySet = "History@SaveAll";
            } else if(ID == 1) {
                Community.historySaveID = 1;
                Config.saveHistorySet = "History@SaveSome";
            } else if(ID == 2) {
                Community.historySaveID = 2;
                Config.saveHistorySet = "History@Delete";
            } else {
                Community.historySaveID = 1;
            }

            try {
                Config.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchMinecraftListenFlushSpeedLevel(int Levels) {
        if(! Community.isDaemons) {
            if(Levels == 0) {
                Community.minecraftListenFlushSpeedLevels = 0;
                Config.minecraftListenFlushSpeedLevel = "MinecraftListenFlushSpeedLevel@0";
            }

            if(Levels == 1) {
                Community.minecraftListenFlushSpeedLevels = 1;
                Config.minecraftListenFlushSpeedLevel = "MinecraftListenFlushSpeedLevel@1";
            }
        }

        try {
            Config.WriteIni();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchInvalidCommandShow(boolean show) {
        if(! Community.isDaemons) {
            if(! show) {
                Community.showInvalidCommand = false;
                Config.showInvalidCommand = "InvalidCommand@Hide";
            } else {
                Community.showInvalidCommand = true;
                Config.showInvalidCommand = "InvalidCommand@Show";
            }
        }

        try {
            Config.WriteIni();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchShowCommands(limitedTypes limitedType) {
        if(limitedType.equals(limitedTypes.BEDROCK)) {
            Community.showCommands = limitedTypes.BEDROCK;
            Config.showCommands = "Commands@Bedrock";
        }

        if(limitedType.equals(limitedTypes.JAVA)) {
            Community.showCommands = limitedTypes.JAVA;
            Config.showCommands = "Commands@Java";
        }

        if(limitedType.equals(limitedTypes.EDU)) {
            Community.showCommands = limitedTypes.EDU;
            Config.showCommands = "Commands@EDU";
        }

        if(limitedType.equals(limitedTypes.BDS)) {
            Community.showCommands = limitedTypes.BDS;
            Config.showCommands = "Commands@BDS";
        }

        if(limitedType.equals(limitedTypes.WS_SERVER)) {
            Community.showCommands = limitedTypes.WS_SERVER;
            Config.showCommands = "Commands@WSS";
        }

        try {
            Config.WriteIni();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchCommandMethod(limitedTypes limitedType) {
        if(limitedType.equals(limitedTypes.BEDROCK)) {
            Community.showCommandMethod = limitedTypes.BEDROCK;
            Config.priorityDisplay = "PriorityDisplay@Bedrock";
        }

        if(limitedType.equals(limitedTypes.JAVA)) {
            Community.showCommandMethod = limitedTypes.JAVA;
            Config.priorityDisplay = "PriorityDisplay@java";
        }

        try {
            Config.WriteIni();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToWiki(boolean toWiki) {
        Community.toWiki = toWiki;

        if(toWiki) {
            Config.toWiki = "toWiki@on";
        } else {
            Config.toWiki = "toWiki@off";
        }
    }

    public static void switchCheckResource(boolean check) {
        MinecraftLauncher.checkResource = check;

        if(check) {
            MinecraftLauncher.checkResource_config = "checkResource@check";
        } else {
            MinecraftLauncher.checkResource_config = "checkResource@ignored";
        }

        MinecraftLauncher.uploadConfig();

        try {
            Config.WriteIni();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public static void test() {
        System.out.println("test");
    }
}