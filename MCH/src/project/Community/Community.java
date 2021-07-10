package project.Community;

import project.Community.Command.ini;
import project.Community.Command.limitedTypes;
import project.Community.Events.*;
import project.Community.Events.KeyListener.listener;
import project.Community.Events.UPD.URLs;
import project.Community.Events.UPD.countTime;
import project.Community.Events.mchDir.dirSize;
import project.Community.Times.times;
import project.Community.UI.*;
import project.Community.UI.Color.displaySets;
import project.Community.UI.Lang.languageSet;
import project.Community.lib.Resources;
import project.Community.lib.filesOperator;

import static project.Community.lib.Resources.initLanguage.lang;

import java.io.File;
import java.util.Arrays;
import java.util.Locale;


public class Community {

    public static int ColorID = 0;
    public static int ColorSetID = 0;
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

    public static boolean autoUPD = true;

    public static boolean showInvalidCommand = true;

    public static limitedTypes showCommands = limitedTypes.ALL_EDITION;
    public static limitedTypes showCommandMethod = limitedTypes.BEDROCK;

    public static String UPD_ID = "154";
    public static String verID = "116980100152/0-0-1-52";
    public static String ver = "ah-52";

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));

        System.out.println(System.getenv("JAVA_HOME"));

        try {

            //        格式化时间
            times.Times();
            new times().start();

            Locale locale = Locale.getDefault();
            if(locale.getLanguage().equals("zh")) {
                Community.LangID = 0;
            } else if(locale.getLanguage().equals("en")) {
                Community.LangID = 1;
            }

            loadingWindow.ui();

            Resources.initLanguage.initFromSelf();

            displaySets.Color();

            new Resources.initLanguage();
            languageSet.Language();

            //        加载颜色
            LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadingAssemble: color\n", lang.get("loading_color"));
            displaySets.Color();
            new displaySets().start();


            //        加载语言
            LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadingAssemble: language\n", lang.get("loading_lang"));
            new Resources.initLanguage();
            new languageSet().start();

            //        引入退出类
            LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadingAssemble: Exits\n", lang.get("loading_exit"));
            new Exits();

            LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadingAssemble: ini\n", lang.get("loading_ini"));
            new ini();

            new File(ini.path + "res.cache").delete();

        /*
        预加载部分
         */

            //        创建文件夹
            File f = new File(ini.path);
            f.mkdirs();

            System.gc();

            LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadingAssemble: exit\n", lang.get("loading_exButtons"));
            new exit();

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

            new daemons().start();

            if(Community.LangID == 0) {
                MenuUI2.mchDirSize.setText("MCH文件占用: 计算中      UPD缓存: 计算中");
            } else if(Community.LangID == 1) {
                MenuUI2.mchDirSize.setText("MCH File Size: counting      UPD cache: counting");
            }

            URLs.checkUPD = true;

        /*
        封装文件部分
         */

            if(saveRunLog) {
                File run = new File(ini.path + "run.log");
                File runTo = new File(ini.path + "save\\run\\");
                filesOperator.saveCache(run, runTo, "run");
                run.delete();
            }
            if(saveErrorLog) {
                File err = new File(ini.path + "errors.log");
                File errTo = new File(ini.path + "save\\errors\\");
                filesOperator.saveCache(err, errTo, "error");
                err.delete();
            }

            Events.menu();

            System.gc();

            //        显示UI
            LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadingAssemble: MchUI\n", lang.get("loading_MchUI"));
            if(ini.canStartUI) {
                new MchUI();
                loadingWindow.jFrame.setVisible(false);
            } else {
                LoadAssembly.badLoadAssembly("[" + times.format + "]\n" + "LoadFail: MchUI\n", lang.get("loading_MchUI_fail") );
            }

            started = true;

            isDaemons = false;
        } catch (Exception e) {

        }
    }
}
