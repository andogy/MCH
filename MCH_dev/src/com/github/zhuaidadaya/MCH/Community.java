package com.github.zhuaidadaya.MCH;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.Command.limitedTypes;
import com.github.zhuaidadaya.MCH.Events.Events;
import com.github.zhuaidadaya.MCH.Events.KeyListener.listener;
import com.github.zhuaidadaya.MCH.Events.LoadAssembly;
import com.github.zhuaidadaya.MCH.Events.UPD.URLs;
import com.github.zhuaidadaya.MCH.Events.UPD.countTime;
import com.github.zhuaidadaya.MCH.Events.daemons;
import com.github.zhuaidadaya.MCH.Events.historyReader;
import com.github.zhuaidadaya.MCH.Events.mchDir.dirSize;
import com.github.zhuaidadaya.MCH.Times.times;
import com.github.zhuaidadaya.MCH.UI.*;
import com.github.zhuaidadaya.MCH.UI.Color.displaySets;
import com.github.zhuaidadaya.MCH.UI.Lang.languageSet;
import com.github.zhuaidadaya.MCH.lib.*;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

import static com.github.zhuaidadaya.MCH.lib.Resources.initLanguage.lang;

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

    public static boolean autoUPD = false;

    public static boolean showInvalidCommand = true;

    public static boolean perf = false;

    public static limitedTypes showCommands = limitedTypes.ALL_EDITION;
    public static limitedTypes showCommandMethod = limitedTypes.BEDROCK;

    public static boolean toWiki = false;

    public static HashMap<String, String> conf = new HashMap<>();
    public static HashMap<String, String> extraConf = new HashMap<>();

    public static String os = System.getProperty("os.name");

    public static String UPD_ID = "157";
    public static String verID = "11445010154/0-0-1-54";
    public static String ver = "ah-54";

    public static extraLists lis = new extraLists();
}
