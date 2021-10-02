package com.github.zhuaidadaya.MCH;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.Command.limitedTypes;
import com.github.zhuaidadaya.MCH.Events.KeyListener.listener;
import com.github.zhuaidadaya.MCH.Events.LoadAssembly;
import com.github.zhuaidadaya.MCH.Events.UPD.URLs;
import com.github.zhuaidadaya.MCH.Events.UPD.countTime;
import com.github.zhuaidadaya.MCH.Events.daemons;
import com.github.zhuaidadaya.MCH.Events.historyReader;
import com.github.zhuaidadaya.MCH.Events.mchDir.dirSize;
import com.github.zhuaidadaya.MCH.Times.times;
import com.github.zhuaidadaya.MCH.UI.Color.displaySets;
import com.github.zhuaidadaya.MCH.UI.*;
import com.github.zhuaidadaya.MCH.UI.Lang.languageSet;
import com.github.zhuaidadaya.MCH.lib.*;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;

import static com.github.zhuaidadaya.MCH.lib.Resources.initLanguage.lang;

public class Community {
    public static boolean launcher = false;

    public static int ColorID = 1;
    public static int ColorSetID = 1;
    public static int LangID = 1;
    public static int LangSetID = 2;
    public static boolean exitButtonWillExit = true;
    public static boolean fastLoad = true;

    public static boolean onTop = false;

    public static boolean canUPD = false;

    public static int commandLength = 0;
    public static int nowPoint = 0;

    public static boolean functionEditing = false;

    public static boolean saveCache = false;
    public static boolean saveErrorLog = false;
    public static boolean saveRunLog = false;

    public static boolean isDaemons = true;

    public static int historySaveID = 1;

    public static int setsDisplayID = 0;
    public static int extraDisplayID = 0;

    public static boolean started = false;

    public static int minecraftListenFlushSpeedLevels = 0;

    public static boolean autoUPD = false;

    public static boolean showInvalidCommand = true;

    public static boolean perf = false;

    public static limitedTypes showCommands = limitedTypes.ALL_EDITION;
    public static limitedTypes showCommandMethod = limitedTypes.BEDROCK;

    public static boolean toWiki = false;

    public static LinkedHashMap<Object, Object> conf = new LinkedHashMap<>();
    public static LinkedHashMap<Object, Object> extraConf = new LinkedHashMap<>();
    public static LinkedHashMap<Object, Object> launcherConf = new LinkedHashMap<>();

    public static String os = System.getProperty("os.name");

    public static UiSizeMap uiSizeMap = new UiSizeMap();

    public static String UPD_ID = "157";
    public static String verID = "11445010154/0-0-1-54";
    public static String ver = "ah-54";

    public static extraLists lis = new extraLists();

    public Community() {
        Config.updateConf();
    }

    public static void main(String[] args) {
        new Community().startMch(args);
    }

    public void startMch(String... arg) {
        HashSet<String> args = new HashSet<>(Arrays.asList(arg));

        saveErrorLog = true;
        saveRunLog = true;
        Config.path = System.getProperty("user.home").replace("\\", "/") + "/" + Config.path;

        if(args.contains("developing"))
            Config.path = "/MCH_testing_path/";
        Config.resPath = Config.path + Config.resPath;
        Config.runLogsPath = Config.path + Config.runLogsPath;
        Config.errLogsPath = Config.path + Config.errLogsPath;
        Config.logsPath = Config.path + Config.logsPath;

        Log.defErrPath = new File(Config.errLogsPath);
        Log.defRunPath = new File(Config.runLogsPath);


        /*
        封装文件部分
         */
        File runTo = new File(Config.path + "logs/");
        try {
            Log.packetLog(runTo.getAbsoluteFile(), runTo.getAbsolutePath() + "/run/latest.log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        new File(Config.path + "extra/").mkdirs();

        LangID = 0;
        new Resources.initLanguage();

        launcher = args.contains("launcher");

        if(args.contains("ex-tes")) {
            lis.showWindow();

            new ExtraLoader().LoadExtra();
        } else {
            if(args.contains("perf-ui"))
                perf = true;

            if(args.contains("PTEST-rabbit")) {
                new Thread(PTEST :: rabbitTest).start();
            } else {
                displaySets.Color();
                displaySets.Color();

                loadingWindow.ui();

                LoadAssembly.loadAssembly("[Main Thread/INFO] Loading for Mch(" + verID + ") - " + UPD_ID, lang.get("loading_color"), false);

                //        加载颜色
                LoadAssembly.loadAssembly("[Main Thread/INFO] Reloading Color Assembly", lang.get("loading_color"), false);
                displaySets.Color();
                new displaySets().start();

                new Resources.initLanguage();
                languageSet.Language();

                new Config();
                try {
                    Config.updateConf();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(args.contains("launcher")) {
                    new Thread(() -> {
                        MinecraftLauncher.UI();
                        MinecraftLauncher.show();
                    }).start();
                }


                try {
                    try {
                        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    } catch (Exception e) {

                    }

                    //        格式化时间
                    times.Times();
                    new times().start();

                    Locale locale = Locale.getDefault();
                    if(locale.getLanguage().equals("zh")) {
                        Community.LangID = 0;
                    } else if(locale.getLanguage().equals("en")) {
                        Community.LangID = 1;
                    }

                    Resources.initLanguage.initFromSelf("languages.json", "/com/github/zhuaidadaya/resources/resource_files/", "", this.getClass());
                    Resources.initLanguage.initFromSelf("commands.json", "/com/github/zhuaidadaya/resources/resource_files/", "", this.getClass());

                    displaySets.Color();

                    Log.writeLog("[Main Thread/INFO] reloading config");
                    Config.parsing(false);

                    new Resources.initLanguage();
                    languageSet.Language();

                    //        加载语言
                    LoadAssembly.loadAssembly("[Main Thread/INFO] Reloading Language Assembly", lang.get("loading_lang"), false);
                    new Resources.initLanguage();
                    new languageSet().start();

                    new File(Config.path + "res.cache").delete();

        /*
        预加载部分
         */

                    //        创建文件夹
                    File f = new File(Config.path);
                    f.mkdirs();

                    Exits.Exit_Button();

                    new exit();

                    if (!args.contains("launcher")) {
                        //        读取历史记录的线程
                        new historyReader().start();

                        //        处理URL请求的线程
                        new URLs().start();

                        //        计时线程,这是在UPD时用于计算连接服务器的时间的
                        new countTime().start();

                        //        keyboard监听线程
                        new listener().start();

                        //        开启minecraft监听线程,给启动器使用预留准备
                        new ExtraUI.minecraftListener().start();

                        //        开启websocket监听线程,给wsServer使用预留准备
                        //        new webSocket.webSocketListener().start();

                        //        这个线程用于计算MCH文件夹大小
                        new dirSize().start();
                    }

                    new daemons().start();

                    if (Community.LangID == 0) {
                        MenuUI2.mchDirSize.setText("MCH文件占用: 计算中      UPD缓存: 计算中");
                    } else if (Community.LangID == 1) {
                        MenuUI2.mchDirSize.setText("MCH File Size: counting      UPD cache: counting");
                    }

                    URLs.checkUPD = true;

                    new MenuUI();
                    new MenuUI2();
                    new ExtraUI();

                    //                    new ExtraLoader().LoadExtra();
                    new Thread(() -> new ExtraLoader().LoadExtra()).start();

                    if(! args.contains("noc")) {
                        new Thread(() -> {
                            //        显示UI
                            LoadAssembly.loadAssembly("[Main Thread/INFO] Loading UI", lang.get("loading_MchUI"), false);
                            if(Config.canStartUI) {
                                new MchUI();
                                loadingWindow.jFrame.setVisible(false);
                            } else {
                                LoadAssembly.badLoadAssembly("[Main Thread/WARN] Cannot Load UI", lang.get("loading_MchUI_fail"));
                            }

                        }).start();

                        started = true;
                    } else {
                        loadingWindow.jFrame.setVisible(false);
                    }

                    isDaemons = false;
                } catch (Exception e) {

                }
            }
        }

        if(perf)
            new perf_UI();

        if(Community.started)
            MinecraftLauncher.jFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        else
            MinecraftLauncher.jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        if(args.contains("menu")) {
            MenuUI.jFrame.setVisible(true);
        }
    }
}
