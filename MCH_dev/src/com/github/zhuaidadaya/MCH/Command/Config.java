package com.github.zhuaidadaya.MCH.Command;

import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.Events.LoadAssembly;
import com.github.zhuaidadaya.MCH.UI.MchUI;
import com.github.zhuaidadaya.MCH.UI.MenuUI;
import com.github.zhuaidadaya.MCH.UI.MenuUI2;
import com.github.zhuaidadaya.MCH.UI.loadingWindow;
import com.github.zhuaidadaya.MCH.lib.Resources;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Locale;

import static com.github.zhuaidadaya.MCH.lib.Resources.initLanguage.lang;

public class Config {
    public static JTextArea jt = new JTextArea();
    public static JFrame jFrame = new JFrame();
    public static JButton continues = new JButton();
    public static JButton autoSet = new JButton();

    public static boolean iniHas = false;
    public static boolean iniOneMOre = false;

    public static boolean settingIni = false;

    public static String colorSet = "Color@White";
    public static String languageSet = "Language@Auto";
    public static String exButtonSet = "Button@Ex.exit";
    public static String fastLoadSet = "Load@Fast";
    public static String onTopSet = "Display@OnTop";
    public static String saveCache = "Cache@Delete";
    public static String saveErrorLog = "ErrorLog@Delete";
    public static String saveRunLog = "RunLog@Delete";
    public static String autoPUDSet = "UPD@Self";
    public static String saveHistorySet = "History@SaveAll";
    public static String input_command = "Input@";
    public static String minecraftListenFlushSpeedLevel = "MinecraftListenFlushSpeedLevel@0";
    public static String showInvalidCommand = "InvalidCommand@hide";
    public static String showCommands = "Commands@Bedrock";
    public static String priorityDisplay = "PriorityDisplay@Bedrock";
    public static String toWiki = "toWiki@off";

    public static String unsupported = "";

    public static boolean canStartUI = true;

    public static String sets = "settings.ini";
    public static String path = "MinecraftCommandHelper/";
    public static String runLogsPath = "MinecraftCommandHelper/logs/run";
    public static String errLogsPath = "MinecraftCommandHelper/logs/run";
    public static String resPath = "resources/";

    public Config() {
        //获得屏幕大小
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        //设置窗口位置
        jFrame.setLocation(width / 2 - 500 / 2, height / 2 - 300 / 2);

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        iniHas = false;

        jFrame.setAlwaysOnTop(true);

        boolean hasIni = create();

        if (hasIni) {
            parsing();
        } else {
            LoadAssembly.badLoadAssembly("Main Thread/WARN] Cannot Load configs Assembly", lang.get("loading_ini_fail"));
        }

        if (iniOneMOre)
            if (!unsupported.equals("")) {
                Errors.tips(500, 150, lang.get("mayIsUnsupportedInfo") + "\n" + unsupported, lang.get("unSupport-conf"));
                while (Errors.jFrame.isVisible()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        unsupported = "";

        iniOneMOre = true;
    }

    public static void updateConf() {
        for (String s : Arrays.asList(Config.colorSet, Config.languageSet, Config.exButtonSet, Config.fastLoadSet, Config.onTopSet, Config.saveCache, Config.saveErrorLog, Config.saveRunLog, Config.autoPUDSet, Config.saveHistorySet, Config.input_command, Config.minecraftListenFlushSpeedLevel, Config.showInvalidCommand, Config.showCommands, Config.priorityDisplay, Config.toWiki)) {
            Community.conf.put(s.substring(0, s.indexOf("@")), s.substring(s.indexOf("@") + 1));
        }
    }

    public static void parsing() {
        try {

            String src = path + sets;
            File ini = new File(src);

            // 构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader(ini));
            String s;

            boolean exConf = false;

            while ((s = br.readLine()) != null) {

                if (s.equals("//$Extra_Conf")) {
                    exConf = true;
                }

                if (!s.contains("//")) {
                    if (!exConf)
                        Community.conf.put(s.substring(0, s.indexOf("@")), s.substring(s.indexOf("@") + 1));
                    else
                        Community.extraConf.put(s.substring(0, s.indexOf("@")), s.substring(s.indexOf("@") + 1));

                    LoadAssembly.loadAssembly("[Main Thread/INFO] Loading for config: " + s, lang.get("loading") + s, false);
                } else {
                    LoadAssembly.loadAssembly("[Main Thread/INFO] Skip for config notes: " + s, lang.get("loading") + s, false);
                }
            }

            System.out.println(Community.conf);

            Reads();

            br.close();
        } catch (Exception ignored) {

        }
    }

    public static boolean create() {
        try {
            FileReader fr = new FileReader(path + sets);
            try {
                fr.close();
                iniHas = true;
            } catch (IOException e) {
                fr.close();
                e.printStackTrace();
            }

            settingIni = false;
            return true;
        } catch (Exception e) {
            //            loadingWindow.jFrame.setVisible(false);

            File f = new File(path);
            f.mkdirs();

            jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

            canStartUI = false;

            jFrame.setSize(500, 320);

            jFrame.setResizable(false);

            jFrame.setTitle("Minecraft Commands Helper");

            jt.setText(lang.get("lost_ini"));
            continues.setText(lang.get("continue"));
            autoSet.setText(lang.get("auto_set"));


            continues.setVisible(true);
            autoSet.setVisible(true);

            jt.setEditable(false);

            LayoutManager layoutManager = new LayoutManager() {
                @Override
                public void addLayoutComponent(String name, Component comp) {
                }

                @Override
                public void removeLayoutComponent(Component comp) {
                }

                @Override
                public Dimension preferredLayoutSize(Container parent) {
                    return null;
                }

                @Override
                public Dimension minimumLayoutSize(Container parent) {
                    return null;
                }

                @Override
                public void layoutContainer(Container parent) {
                    jt.setBounds(0, 0, 500, 200);

                    continues.setBounds(50, 200, 150, 30);
                    autoSet.setBounds(50, 240, 150, 30);
                }
            };

            jFrame.setLayout(layoutManager);

            jFrame.setVisible(true);

            jFrame.add(jt);
            jFrame.add(continues);
            jFrame.add(autoSet);

            continues.addActionListener(e1 -> {
                settingIni = true;
                MenuUI2.jFrame.setVisible(true);
                MenuUI2.jFrame.setAlwaysOnTop(true);
                jFrame.setVisible(false);
            });
            autoSet.addActionListener(e1 -> {
                continues.setVisible(false);
                autoSet.setVisible(false);

                Resources.fixResource("/com/github/zhuaidadaya/resources/resource_files/settings_default.ini", path + "settings.ini", true);

                parsing();
                defaultIniSetOver();
            });
            return false;
        }
    }

    public static void defaultIniSetOver() {
        loadingWindow.jFrame.setVisible(false);

        LoadAssembly.loadAssembly("[Main Thread/INFO] Reloading UI", lang.get("reloading_MchUI"), false);
        if (! MchUI.jFrame.isVisible()) {
            new MchUI();
        }
        jFrame.setVisible(false);
        iniHas = true;
        if (MenuUI.OpenMenu) {
            new MenuUI();
        }
    }

    public static void WriteIni() throws Exception {
        updateConf();

        FileWriter fl = new FileWriter(path + sets, false);
        fl.write("//$MCH_Settings\n");

        for (String s : Community.conf.keySet())
            fl.write(s + "@" + Community.conf.get(s) + "\n");

        fl.write("//$Extra_settings\n");

        for (String s : Community.extraConf.keySet())
            fl.write(s + "@" + Community.extraConf.get(s) + "\n");

        fl.close();
    }

    public static void Reads() {
        for (String s : Community.conf.keySet())
            Reads(s + "@" + Community.conf.get(s));
    }

    public static void Reads(String s) {
        //            将大写字母全替换为小写
        s = s.toLowerCase();

        //        设定颜色
        {
            //            用户自定义颜色,暂时还不支持
            int User_color = s.indexOf("color@config");

            int Black = s.indexOf("color@black");
            int White = s.indexOf("color@white");
            int Hades = s.indexOf("color@hades");
            int auto = s.indexOf("color@auto");

            if (!(Black != -1 & White != -1 & Hades != -1 & auto != -1)) {
                if (Hades != -1) {
                    Community.ColorID = 2;
                    Community.ColorSetID = 2;
                    colorSet = "Color@Hades";
                    s = "";
                }

                if (Black != -1) {
                    Community.ColorID = 1;
                    Community.ColorSetID = 1;
                    colorSet = "Color@Black";
                    s = "";
                }

                if (White != -1) {
                    Community.ColorID = 0;
                    Community.ColorSetID = 0;
                    colorSet = "Color@White";
                    s = "";
                }

                if (auto != -1) {
                    Community.ColorSetID = 3;
                    colorSet = "Color@Auto";
                    s = "";
                }
            } else {
                Community.ColorID = 1;
                colorSet = "Color@White";
            }
        }

        //语言
        {
            int Chinese = s.indexOf("language@chinese");
            int English = s.indexOf("language@english");
            int auto = s.indexOf("language@auto");
            int Chinese_TW = s.indexOf("language@chinese_tw");

            if (!(Chinese != -1 & English != -1 & auto != -1 & Chinese_TW != -1)) {
                if (Chinese != -1) {
                    Community.LangID = 0;
                    Community.LangSetID = 0;
                    languageSet = "Language@Chinese";
                    s = "";
                }

                if (English != -1) {
                    Community.LangID = 1;
                    Community.LangSetID = 1;
                    languageSet = "Language@English";
                    s = "";
                }

                if (Chinese_TW != -1) {
                    Community.LangID = 3;
                    Community.LangSetID = 3;
                    languageSet = "Language@Chinese_TW";
                    s = "";
                }

                if (auto != -1) {
                    Community.LangSetID = 2;

                    Locale locale = Locale.getDefault();

                    if (locale.getLanguage().equals("zh")) {
                        Community.LangID = 0;
                        languageSet = "Language@Auto";
                    } else if (locale.getLanguage().equals("en")) {
                        Community.LangID = 1;
                        languageSet = "Language@Auto";
                    }

                    s = "";
                }
            } else {
                if (s.indexOf("language") == 0) {
                    Community.LangSetID = 2;

                    Locale locale = Locale.getDefault();

                    if (locale.getLanguage().equals("zh")) {
                        Community.LangID = 0;
                        languageSet = "Language@Auto";
                    } else if (locale.getLanguage().equals("en")) {
                        Community.LangID = 1;
                        languageSet = "Language@Auto";
                    }
                }
            }
        }

        //        退出按钮
        {
            int exit = s.indexOf("button@ex.exit");
            int exitNot = s.indexOf("button@ex.smaller");

            if (!(exit != -1 & exitNot != -1)) {
                if (exit != -1) {
                    Community.exitButtonWillExit = true;
                    exButtonSet = "Button@Ex.Exit";
                    s = "";
                }
                if (exitNot != -1) {
                    Community.exitButtonWillExit = false;
                    exButtonSet = "Button@Ex.Smaller";
                    s = "";
                }
            }
        }

        //        快速加载
        {
            int fast = s.indexOf("load@fast");
            int safe = s.indexOf("load@safe");

            if (!(fast != -1 & safe != -1)) {
                if (fast != -1) {
                    Community.fastLoad = true;
                    fastLoadSet = "Load@Fast";
                    s = "";
                }
                if (safe != -1) {
                    Community.fastLoad = false;
                    fastLoadSet = "Load@Safe";
                    s = "";
                }
            }
        }

        //        MCH置顶
        {
            int onTop = s.indexOf("display@ontop");
            int noOnTop = s.indexOf("display@default");

            if (!(onTop != -1 & noOnTop != -1)) {
                if (onTop != -1) {
                    Community.onTop = true;
                    onTopSet = "Display@OnTop";
                    s = "";
                }
                if (noOnTop != -1) {
                    Community.onTop = false;
                    onTopSet = "Display@Default";
                    s = "";
                }
            }
        }

        //        保存更新缓存
        {
            int saveCaches = s.indexOf("cache@save");
            int notSaveCaches = s.indexOf("cache@delete");

            if (!(saveCaches != -1 & notSaveCaches != -1)) {
                if (saveCaches != -1) {
                    Community.saveCache = true;
                    saveCache = "Cache@Save";
                    s = "";
                }

                if (notSaveCaches != -1) {
                    Community.saveCache = false;
                    saveCache = "Cache@Delete";
                    s = "";
                }
            }
        }

        //        保存错误日志
        {
            int saveErrorLogs = s.indexOf("errorlog@save");
            int notSaveErrorLogs = s.indexOf("errorlog@delete");

            if (!(saveErrorLogs != -1 & notSaveErrorLogs != -1)) {
                if (saveErrorLogs != -1) {
                    Community.saveErrorLog = true;
                    saveErrorLog = "ErrorLog@Save";
                    s = "";
                }

                if (notSaveErrorLogs != -1) {
                    Community.saveErrorLog = false;
                    saveErrorLog = "ErrorLog@Delete";
                    s = "";
                }
            }
        }

        //        保存运行日志
        {
            int saveRunLogs = s.indexOf("runlog@save");
            int notSaveRunLog = s.indexOf("runlog@delete");

            if (!(saveRunLogs != -1 & notSaveRunLog != -1)) {
                if (saveRunLogs != -1) {
                    Community.saveRunLog = true;
                    saveRunLog = "RunLog@Save";
                    s = "";
                }

                if (notSaveRunLog != -1) {
                    Community.saveRunLog = false;
                    saveRunLog = "RunLog@Delete";
                    s = "";
                }
            }
        }

        //        自动更新
        {
            int autoUPD = s.indexOf("upd@mch");
            int noAutoUPD = s.indexOf("upd@self");

            if (!(autoUPD != -1 & noAutoUPD != -1)) {
                if (autoUPD != -1) {
                    Community.autoUPD = true;
                    autoPUDSet = "UPD@MCH";
                    s = "";
                }

                if (noAutoUPD != -1) {
                    Community.autoUPD = false;
                    autoPUDSet = "UPD@Self";
                    s = "";
                }
            }
        }

        //        历史记录
        {
            int saveHistory = s.indexOf("history@savesome");
            int notSaveHistory = s.indexOf("history@delete");
            int saveAllHistory = s.indexOf("history@saveall");

            if (!(saveHistory != -1 & notSaveHistory != -1 & saveAllHistory != -1)) {
                if (saveAllHistory != -1) {
                    Community.historySaveID = 0;
                    saveHistorySet = "History@SaveAll";
                    s = "";
                }

                if (saveHistory != -1) {
                    Community.historySaveID = 1;
                    saveHistorySet = "History@SaveSome";
                    s = "";
                }

                if (notSaveHistory != -1) {
                    Community.historySaveID = 2;
                    saveHistorySet = "History@Delete";
                    s = "";
                }
            }
        }

        {
            if (!Community.started) {
                int input = s.indexOf("input@");

                if (input == 0) {
                    String setCommand = s.substring(s.indexOf("input@") + 6);
                    MchUI.input_Command.setText(setCommand);
                    s = "";
                }
            }
        }

        {
            int levels0 = s.indexOf("minecraftlistenflushspeedlevel@0");
            int levels1 = s.indexOf("minecraftlistenflushspeedlevel@1");

            if (!(levels0 != -1 & levels1 != -1)) {
                if (levels0 != -1) {
                    Community.minecraftListenFlushSpeedLevels = 0;
                    minecraftListenFlushSpeedLevel = "MinecraftListenFlushSpeedLevel@0";
                    s = "";
                }

                if (levels1 != -1) {
                    Community.minecraftListenFlushSpeedLevels = 1;
                    minecraftListenFlushSpeedLevel = "MinecraftListenFlushSpeedLevel@1";
                    s = "";
                }
            }
        }

        {
            int hide = s.indexOf("invalidcommand@hide");
            int show = s.indexOf("invalidcommand@show");

            if (!(hide != -1 & show != -1)) {
                if (show != -1) {
                    Community.showInvalidCommand = true;
                    showInvalidCommand = "InvalidCommand@Show";
                    s = "";
                }

                if (hide != -1) {
                    Community.showInvalidCommand = false;
                    showInvalidCommand = "InvalidCommand@Hide";
                    s = "";
                }
            }
        }

        {
            int bedrock = s.indexOf("commands@bedrock");
            int java = s.indexOf("commands@java");
            int edu = s.indexOf("commands@edu");
            int bds = s.indexOf("commands@bds");
            int wss = s.indexOf("commands@wss");

            if (!(bedrock != -1 & java != -1 & edu != -1 & bds != -1 & wss != -1)) {
                if (bedrock != -1) {
                    Community.showCommands = limitedTypes.BEDROCK;
                    showCommands = "Commands@Bedrock";
                    s = "";
                }

                if (java != -1) {
                    Community.showCommands = limitedTypes.JAVA;
                    showCommands = "Commands@Java";
                    s = "";
                }

                if (edu != -1) {
                    Community.showCommands = limitedTypes.EDU;
                    showCommands = "Commands@EDU";
                    s = "";
                }

                if (bds != -1) {
                    Community.showCommands = limitedTypes.BDS;
                    showCommands = "commands@BDS";
                    s = "";
                }

                if (wss != -1) {
                    Community.showCommands = limitedTypes.WS_SERVER;
                    showCommands = "commands@WSS";
                    s = "";
                }
            }
        }

        {
            int bedrock = s.indexOf("prioritydisplay@bedrock");
            int java = s.indexOf("prioritydisplay@java");

            if (!(bedrock != -1 & java != -1)) {
                if (bedrock != -1) {
                    Community.showCommandMethod = limitedTypes.BEDROCK;
                    priorityDisplay = "PriorityDisplay@Bedrock";
                    s = "";
                }

                if (java != -1) {
                    Community.showCommandMethod = limitedTypes.JAVA;
                    priorityDisplay = "PriorityDisplay@java";
                    s = "";
                }
            }
        }

        {
            int toWiki = s.indexOf("towiki@on");
            int toWikiNot = s.indexOf("towiki@off");

            if (!(toWiki != -1 & toWikiNot != -1)) {
                if (toWiki != -1) {
                    Community.toWiki = true;
                    s = "";
                } else if (toWikiNot != -1) {
                    Community.toWiki = false;
                    s = "";
                }
            }
        }

        try {
            if (Community.extraConf.get(s.substring(0, s.indexOf("@"))) != null)
                s = "";
        } catch (Exception e) {

        }

        if (!s.equals("")) {
            LoadAssembly.badLoadAssembly("[Main Thread/WARN] a config are did not supported in this MCH: " + s, "fail loading config");
            unsupported += s + "\n";
        }
    }
}