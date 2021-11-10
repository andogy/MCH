package com.github.zhuaidadaya.MCH;

import com.github.zhuaidadaya.MCH.Command.limitedTypes;
import com.github.zhuaidadaya.MCH.Config.ConfigMain;
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
import com.github.zhuaidadaya.MCH.lib.extras.ExtraLoader;
import com.github.zhuaidadaya.MCH.lib.extras.ExtraVersion;
import com.github.zhuaidadaya.MCH.lib.extras.ExtraVersions;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;

import static com.github.zhuaidadaya.MCH.lib.Resources.initLanguage.lang;

public class Community {
    Logger logger = new Logger("Main Thread");

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
    public static boolean dPerf = false;

    public static boolean excessProcess = false;

    public static LinkedHashMap<String, String> toolTips = new LinkedHashMap<>();

    public static limitedTypes showCommands = limitedTypes.ALL_EDITION;
    public static limitedTypes showCommandMethod = limitedTypes.BEDROCK;

    public static boolean toWiki = false;

    public static LinkedHashMap<Object, Object> conf = new LinkedHashMap<>();
    public static LinkedHashMap<Object, Object> extraConf = new LinkedHashMap<>();
    public static LinkedHashMap<Object, Object> launcherConf = new LinkedHashMap<>();

    public static String os = System.getProperty("os.name");

    public static UiSizeMap uiSizeMap = new UiSizeMap();

    public static String UPD_ID;
    public static String verID;
    public static String ver;

    public static ExtraVersions<String,ExtraVersion> extraVersions = new ExtraVersions<>();

    public Community() {
        ConfigMain.uploadConfig();
    }

    public static void main(String[] args) {
        new Community().startMch(args);
    }

    public void startMch(String... arg) {
        dPerf = true;

        ExtraVersion exv = Resources.getExtraVersion("/com/github/zhuaidadaya/resources/resource_files/version/version.json");
        extraVersions.put(exv);

        while(new File(ConfigMain.path + "res.cache").isFile()) {
            new File(ConfigMain.path + "res.cache").delete();
        }

        Exits.Exit_Button();

        displaySets.Color();

        //        keyboard监听线程
        new listener().start();

        HashSet<String> args = new HashSet<>(Arrays.asList(arg));

        saveErrorLog = true;
        saveRunLog = true;
        ConfigMain.path = System.getProperty("user.home").replace("\\", "/") + "/AppData/Roaming/" + ConfigMain.path;
        //        Config.path = "D:/normal/MCH/";

        if(args.contains("developing"))
            ConfigMain.path = "/MCH_testing_path/";
        ConfigMain.resPath = ConfigMain.path + ConfigMain.resPath;
        ConfigMain.runLogsPath = ConfigMain.path + ConfigMain.runLogsPath;
        ConfigMain.errLogsPath = ConfigMain.path + ConfigMain.errLogsPath;
        ConfigMain.logsPath = ConfigMain.path + ConfigMain.logsPath;
        ConfigMain.dPerfLogsPath = ConfigMain.path + ConfigMain.dPerfLogsPath;

        Log.defErrPath = new File(ConfigMain.errLogsPath);
        Log.defRunPath = new File(ConfigMain.runLogsPath);

        /*
        封装文件部分
         */
        File runTo = new File(ConfigMain.path + "logs/");
        try {
            Log.packetLog(runTo.getAbsoluteFile(), runTo.getAbsolutePath() + "/run/latest.log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        new File(ConfigMain.path + "extra/").mkdirs();

        LangID = 0;
        new Resources.initLanguage();

        launcher = args.contains("launcher");

        if(args.contains("ex-tes")) {
            new ExtraLoader().LoadExtra(false);

            for(Object exv_tes : extraVersions.keySet()) {
                ExtraVersion ex = (ExtraVersion) exv_tes;
                System.out.println(ex.getInfo());
            }
        } else {
            if(args.contains("perf-ui"))
                perf = true;

            if(args.contains("PTEST-rabbit")) {
                new Thread(PTEST :: rabbitTest).start();
            } else {
                displaySets.Color();

                LoadToolTips.load("/com/github/zhuaidadaya/resources/resource_files/tooltip/tooltip.index");

                new Resources.initLanguage();
                languageSet.Language();

                UPD_ID = extraVersions.get(exv).getUpdateID();
                verID = extraVersions.get(exv).getID();
                ver = extraVersions.get(exv).getVersion();

                loadingWindow.ui();

                LoadAssembly.loadAssembly("[Main Thread/INFO] Loading for Mch(" + verID + ") - " + UPD_ID, lang.get("loading_color"), false);

                //        加载颜色
                LoadAssembly.loadAssembly("[Main Thread/INFO] Reloading Color Assembly", lang.get("loading_color"), false);
                displaySets.Color();
                new displaySets().start();

                new Resources.initLanguage();
                languageSet.Language();

                new ConfigMain();
                try {
                    ConfigMain.uploadConfig();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(args.contains("launcher")) {
                    new Thread(() -> {
                        MinecraftLauncher.UI();
                        MinecraftLauncher.show();
                    }).start();
                }

                if(args.contains("console")) {
                    new Thread(console :: aCnsole).start();
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
                    ConfigMain.parsing(true);

                    new Resources.initLanguage();
                    languageSet.Language();

                    //        加载语言
                    LoadAssembly.loadAssembly("[Main Thread/INFO] Reloading Language Assembly", lang.get("loading_lang"), false);
                    new Resources.initLanguage();
                    new languageSet().start();

        /*
        预加载部分
         */

                    //        创建文件夹
                    File f = new File(ConfigMain.path);
                    f.mkdirs();

                    new exit();

                    //        处理URL请求的线程
                    new URLs().start();

                    if(! args.contains("launcher")) {
                        //        读取历史记录的线程
                        new historyReader().start();

                        //        计时线程,这是在UPD时用于计算连接服务器的时间的
                        new countTime().start();

                        //        开启minecraft监听线程,给启动器使用预留准备
                        new ExtraUI.minecraftListener().start();

                        //        开启websocket监听线程,给wsServer使用预留准备
                        //        new webSocket.webSocketListener().start();
                    }

                    //        这个线程用于计算MCH文件夹大小
                    new dirSize().start();

                    new daemons().start();

                    if(Community.LangID == 0) {
                        MenuUI2.mchDirSize.setText("MCH文件占用: 计算中      UPD缓存: 计算中");
                    } else if(Community.LangID == 1) {
                        MenuUI2.mchDirSize.setText("MCH File Size: counting      UPD cache: counting");
                    }

                    URLs.checkUPD = true;

                    new MenuUI();
                    new MenuUI2();
                    new ExtraUI();

                    //                    new ExtraLoader().LoadExtra();
                    new Thread(() -> new ExtraLoader().LoadExtra(true)).start();

                    if(! args.contains("noc")) {
                        new Thread(() -> {
                            //        显示UI
                            LoadAssembly.loadAssembly("[Main Thread/INFO] Loading UI", lang.get("loading_MchUI"), false);
                            if(ConfigMain.canStartUI) {
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
            MinecraftLauncher.mainFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        else
            MinecraftLauncher.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        if(args.contains("menu")) {
            MenuUI.jFrame.setVisible(true);
        }
    }
}
