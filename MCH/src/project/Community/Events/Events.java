package project.Community.Events;

import project.Community.Command.ini;
import project.Community.Command.limitedTypes;
import project.Community.Community;
import project.Community.Events.KeyListener.listener;
import project.Community.Events.UPD.URLs;
import project.Community.Events.UPD.countTime;
import project.Community.Times.times;
import project.Community.UI.ExtraUI;
import project.Community.UI.Lang.initLanguage;
import project.Community.UI.MchUI;
import project.Community.UI.MenuUI;
import project.Community.UI.MenuUI2;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Events {
    public static void menu() {
        new MenuUI();
        new MenuUI2();
        new ExtraUI();
    }

    public static void Copy() {
        StringSelection selection = new StringSelection(MchUI.input_Command.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);

        if (Community.historySaveID != 2) {
            try {
                FileWriter fileWriter = new FileWriter(ini.path + "history.txt", true);
                String write = MchUI.input_Command.getText().replace("\\", "").replace("\r", "").replace("\n", "");
                if (!write.equals("")) {
                    write += "\n";

                    if (!Community.functionEditing) {
                        System.out.println("[" + times.format + "]\n" + "Events/Copy: 复制字符 = " + Arrays.toString(write.getBytes(StandardCharsets.UTF_8)));
                        System.out.println("Events/Copy: 复制结果 = [" + write.replace("\n", "") + "]");
                        System.out.println("Events/Copy: 复制" + write.length() + "个字符");
                        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "Events/Copy: copy chars = " + Arrays.toString(write.getBytes(StandardCharsets.UTF_8)) + "\n");
                        LoadAssembly.loadAssembly("Events/Copy: copy result = [" + write.replace("\n", "") + "]\n");
                        LoadAssembly.loadAssembly("Events/Copy: copy " + write.length() + " chars");
                        fileWriter.write(write);
                    } else {
                        ExtraUI.functionEdit.setText(ExtraUI.functionEdit.getText() + write);
                    }

                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (OutOfMemoryError e) {
                Errors.errors(e, null, true, "Copy-DANGER");
            }
        }
    }

    public static void switchColor(int ColorID) {
        if (!Community.isDaemons) {
            switch (ColorID) {
                case 0 -> {
                    Community.ColorID = 0;
                    ini.colorSet = "Color@White";
                }
                case 1 -> {
                    Community.ColorID = 1;
                    ini.colorSet = "Color@Black";
                }
                case 2 -> {
                    Community.ColorID = 2;
                    ini.colorSet = "Color@Hades";
                }
            }

            try {
                ini.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }

            listener.switchTip.setLight("",true);
        }
    }

    public static void switchLanguage(int LangID) {
        if (!Community.isDaemons) {

            int oldLangID = Community.LangID;

            switch (LangID) {
                case 0 -> {
                    Community.LangID = 0;
                    ini.languageSet = "Language@Chinese";
                }
                case 1 -> {
                    Community.LangID = 1;
                    ini.languageSet = "Language@English";
                }
                case 2 -> {
                    Community.LangSetID = 2;
                    ini.languageSet = "Language@Auto";
                }
            }

            try {
                ini.WriteIni();
                ini.parsing();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (LangID != oldLangID) {
                new initLanguage();
            }
        }
    }

    public static void switchExButtonWillExit(boolean exButton) {
        if (!Community.isDaemons) {
            if (exButton) {
                Community.exitButtonWillExit = true;
                ini.exButtonSet = "Button@Ex.Exit";
            } else {
                Community.exitButtonWillExit = false;
                ini.exButtonSet = "Button@Ex.Smaller";
            }

            try {
                ini.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchFastLoad(boolean fastLoad) {
        if (!Community.isDaemons) {
            if (fastLoad) {
                Community.fastLoad = true;
                ini.fastLoadSet = "Load@Fast";
            } else {
                Community.fastLoad = false;
                ini.fastLoadSet = "Load@Safe";
            }

            try {
                ini.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchOnTop(boolean onTop) {
        if (!Community.isDaemons) {
            if (onTop) {
                Community.onTop = true;
                ini.onTopSet = "Display@OnTop";
            } else {
                Community.onTop = false;
                ini.onTopSet = "Display@Default";
            }

            try {
                ini.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchSaveUpdCache(boolean save) {
        if (!Community.isDaemons) {
            if (save) {
                Community.saveCache = true;
                ini.saveCache = "Cache@Save";
            } else {
                Community.saveCache = false;
                ini.saveCache = "Cache@Delete";
            }

            try {
                ini.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchSaveRunLog(boolean save) {
        if (!Community.isDaemons) {
            if (save) {
                Community.saveRunLog = true;
                ini.saveRunLog = "RunLog@Save";
            } else {
                Community.saveRunLog = false;
                ini.saveRunLog = "RunLog@Delete";
            }

            try {
                ini.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchSaveErrorLog(boolean save) {
        if (!Community.isDaemons) {
            if (save) {
                Community.saveErrorLog = true;
                ini.saveErrorLog = "ErrorLog@Save";
            } else {
                Community.saveErrorLog = false;
                ini.saveErrorLog = "ErrorLog@Delete";
            }

            try {
                ini.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchAutoUPD(boolean auto) {
        if (!Community.isDaemons) {
            if (auto) {
                Community.autoUPD = true;
                ini.autoPUDSet = "UPD@MCH";
            } else {
                Community.autoUPD = false;
                ini.autoPUDSet = "UPD@Self";
            }

            try {
                ini.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchHistorySaveID(int ID) {
        if (!Community.isDaemons) {
            if (ID == 0) {
                Community.historySaveID = 0;
                ini.saveHistorySet = "History@SaveAll";
            } else if (ID == 1) {
                Community.historySaveID = 1;
                ini.saveHistorySet = "History@SaveSome";
            } else if (ID == 2) {
                Community.historySaveID = 2;
                ini.saveHistorySet = "History@Delete";
            } else {
                Community.historySaveID = 1;
            }

            try {
                ini.WriteIni();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchMinecraftListenFlushSpeedLevel(int Levels) {
        if (!Community.isDaemons) {
            if (Levels == 0) {
                Community.minecraftListenFlushSpeedLevels = 0;
                ini.minecraftListenFlushSpeedLevel = "MinecraftListenFlushSpeedLevel@0";
            }

            if (Levels == 1) {
                Community.minecraftListenFlushSpeedLevels = 1;
                ini.minecraftListenFlushSpeedLevel = "MinecraftListenFlushSpeedLevel@1";
            }
        }

        try {
            ini.WriteIni();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchInvalidCommandShow(boolean show) {
        if (!Community.isDaemons) {
            if (!show) {
                Community.showInvalidCommand = false;
                ini.showInvalidCommand = "InvalidCommand@Hide";
            } else {
                Community.showInvalidCommand = true;
                ini.showInvalidCommand = "InvalidCommand@Show";
            }
        }

        try {
            ini.WriteIni();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchShowCommands(limitedTypes limitedType) {
        if (limitedType.equals(limitedTypes.BEDROCK)) {
            Community.showCommands = limitedTypes.BEDROCK;
            ini.showCommands = "Commands@Bedrock";
        }

        if (limitedType.equals(limitedTypes.JAVA)) {
            Community.showCommands = limitedTypes.JAVA;
            ini.showCommands = "Commands@Java";
        }

        if (limitedType.equals(limitedTypes.EDU)) {
            Community.showCommands = limitedTypes.EDU;
            ini.showCommands = "Commands@EDU";
        }

        if (limitedType.equals(limitedTypes.BDS)) {
            Community.showCommands = limitedTypes.BDS;
            ini.showCommands = "Commands@BDS";
        }

        if (limitedType.equals(limitedTypes.WS_SERVER)) {
            Community.showCommands = limitedTypes.WS_SERVER;
            ini.showCommands = "Commands@WSS";
        }

        try {
            ini.WriteIni();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchCommandMethod(limitedTypes limitedType) {
        if (limitedType.equals(limitedTypes.BEDROCK)) {
            Community.showCommandMethod = limitedTypes.BEDROCK;
            ini.priorityDisplay = "PriorityDisplay@Bedrock";
        }

        if (limitedType.equals(limitedTypes.JAVA)) {
            Community.showCommandMethod = limitedTypes.JAVA;
            ini.priorityDisplay = "PriorityDisplay@java";
        }

        try {
            ini.WriteIni();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkUPD() {
        if (!Community.isDaemons) {
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
}