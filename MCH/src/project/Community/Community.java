package project.Community;

import project.Community.Command.CommandParsing;
import project.Community.Command.ini;
import project.Community.Events.*;
import project.Community.Events.KeyListener.listener;
import project.Community.Events.UPD.URLs;
import project.Community.Events.UPD.countTime;
import project.Community.Events.mchDir.dirSize;
import project.Community.Times.times;
import project.Community.UI.*;
import project.Community.UI.Color.displaySets;
import project.Community.lib.filesOperator;
import project.Community.UI.Lang.initLanguage;

import java.io.File;


public class Community {

    public static int ColorID = 0;
    public static int ColorSetID = 0;
    public static int LangID = 0;
    public static int LangSetID = 0;
    public static boolean exitButtonWillExit = true;
    public static boolean fastLoad = true;
    public static boolean onTop = false;

    public static boolean canUPD = false;

    public static int commandLength = 0;
    public static int nowPoint = 0;

    public static boolean functionEditing = false;

    public static boolean saveCache = false;

    public static boolean saveErrorLog = true;
    public static boolean saveRunLog = true;

    public static boolean isDaemons = false;

    public static int historySaveID = 1;

    public static int setsDisplayID = 0;
    public static int extraDisplayID = 0;

    public static boolean started = false;

    public static int minecraftListenFlushSpeedLevels = 0;

    public static boolean autoUPD = false;

    public static boolean iniHas = false;

    public static boolean showInvalidCommand = false;

    public static String UPD_ID = "152";
    public static String verID = "11166010150/0-0-1-50";
    public static String ver = "ah-50";

    public static void main(String[] args) {
        loadingWindow.ui();

        System.out.println("[" + times.format + "]\n" + "ini:配置文件就绪中");
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadingAssemble: ini\n");
        new ini();

        new initLanguage();

        new File(ini.path + "res.cache").delete();

        /*
        预加载部分
         */

        //        格式化时间
        times.Times();
        new times().start();

        //        创建文件夹
        File f = new File(ini.path);
        f.mkdirs();

        System.gc();

        System.out.println("[" + times.format + "]\n" + "exit:exit事件就绪中");
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadingAssemble: exit\n");
        new exit();

        //        引入退出类
        System.out.println("[" + times.format + "]\n" + "Exits:退出按钮事件就绪中");
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadingAssemble: Exits\n");
        new Exits();

        //        加载颜色
        System.out.println("[" + times.format + "]\n" + "colors:色彩就绪中");
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadingAssemble: color\n");
        new displaySets().start();

        //        判断一些文件是否存在的线程
        new filesHas().start();

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

        System.gc();

        if (Community.LangID == 0) {
            MenuUI2.mchDirSize.setText("MCH文件占用: 计算中      UPD缓存: 计算中");
        } else if (Community.LangID == 1) {
            MenuUI2.mchDirSize.setText("MCH File Size: counting      UPD cache: counting");
        }

        URLs.checkUPD = true;

        /*
        封装文件部分
         */

        if (saveRunLog) {
            File run = new File(ini.path + "run.log");
            File runTo = new File(ini.path + "save\\run\\");
            filesOperator.saveCache(run, runTo, "run");
            run.delete();
        }
        if (saveErrorLog) {
            File err = new File(ini.path + "errors.log");
            File errTo = new File(ini.path + "save\\errors\\");
            filesOperator.saveCache(err, errTo, "error");
            err.delete();
        }

        Events.menu();

        System.gc();

        //        显示UI
        System.out.println("[" + times.format + "]\n" + "MchUI:UI就绪中");
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadingAssemble: MchUI\n");
        if (ini.canStartUI) {
            new MchUI();
        } else {
            System.out.println("[" + times.format + "]\n" + "MchUI:UI无法就绪");
            LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadFail: MchUI\n");
        }

        started = true;
    }
}
