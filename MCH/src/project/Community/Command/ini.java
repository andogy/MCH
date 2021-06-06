package project.Community.Command;

import project.Community.Community;
import project.Community.Events.Events;
import project.Community.Events.LoadAssembly;
import project.Community.Help.Helps;
import project.Community.Times.times;
import project.Community.UI.MchUI;
import project.Community.UI.MenuUI;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Locale;

public class ini {
    public static JTextArea jt = new JTextArea();
    public static JFrame jFrame = new JFrame();
    public static JButton continues = new JButton();
    public static JButton autoSet = new JButton();
    public static JButton Black = new JButton();
    public static JButton White = new JButton();
    public static JButton FastLoad = new JButton();
    public static JButton SafeLoad = new JButton();
    public static JButton onTop = new JButton();
    public static JButton noOnTop = new JButton();
    public static JButton GoHelp = new JButton();
    public static JButton GoMchUI = new JButton();

    public static boolean iniHas = false;

    public static String colorSet = "Color@White";
    public static String languageSet = "Language@Auto";
    public static String exButtonSet = "Button@Ex.exit";
    public static String fastLoadSet = "Load@Fast";
    public static String onTopSet = "Display@OnTop";
    public static String saveCache = "Cache@Delete";
    public static String saveErrorLog = "ErrLog@Delete";
    public static String saveRunLog = "RunLog@Delete";
    public static String autoPUDSet = "UPD@Self";
    public static String saveHistorySet = "History@SaveAll";
    public static String input_command = "Input@";
    public static String minecraftListenFlushSpeedLevel = "MinecraftListenFlushSpeedLevel@0";

    public static boolean canStartUI = true;

    public static String sets = "settings.ini";
    public static String path = "C:\\.MCH\\";

    public ini() {
        //获得屏幕大小
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        //设置窗口位置
        jFrame.setLocation(width / 2 - 500 / 2, height / 2 - 300 / 2);

        System.out.println("[" + times.format + "]\n" + "ini:配置文件就绪");
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadSucceed: ini\n");
        //        while (true) {
        //                if (Errors.CannotHandle) {
        //                    break;
        //                }
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        iniHas = false;

        create();
        parsing();
    }

    public static void parsing() {
        try {

            String src = path + sets;
            File ini = new File(src);

            // 构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader(ini));
            String s;

            while ((s = br.readLine()) != null) {

                int re = s.indexOf("/*");

                if (re == -1) {
                    Reads(s);
                }
            }

            br.close();

            System.gc();
        } catch (Exception ignored) {
        }
    }

    public static void create() {
        try {
            FileReader fr = new FileReader(path + sets);
            try {
                fr.close();
                iniHas = true;
            } catch (IOException e) {
                fr.close();
                e.printStackTrace();
            }
        } catch (Exception e) {
            File f = new File(path);
            f.mkdirs();

            jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

            canStartUI = false;

            jFrame.setSize(500, 320);

            jFrame.setResizable(false);

            jFrame.setTitle("Minecraft Commands Helper");

            if (Community.LangSetID == 0) {
                jt.setText("""
                        出现这个界面可能是因为缺少配置文件
                        这不需要你去补全任何文件,只需要照问题选择即可
                        是否继续?

                                                
                                                
                                                
                                                



                        1/5""");
                continues.setText("继续");
                autoSet.setText("自动设置");
            } else if (Community.LangID == 1) {
                jt.setText("""
                        This interface May Appear Due To a Lack of Configuration File
                        This Does not Require You to Complete the File, Just Choose According to The Question
                        Please Select the Display Language of This Program
                        Do you want to continue?
                                               
                                               
                                               
                                               



                        1/5""");
                continues.setText("continue");
                autoSet.setText("auto set");
            }

            continues.setVisible(true);
            autoSet.setVisible(true);

            Black.setVisible(false);
            White.setVisible(false);

            FastLoad.setVisible(false);
            SafeLoad.setVisible(false);

            GoHelp.setVisible(false);
            GoMchUI.setVisible(false);

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
                    Black.setBounds(50, 200, 150, 30);
                    White.setBounds(50, 240, 150, 30);
                    FastLoad.setBounds(50, 200, 150, 30);
                    SafeLoad.setBounds(50, 240, 150, 30);
                    noOnTop.setBounds(50, 200, 150, 30);
                    onTop.setBounds(50, 240, 150, 30);
                    GoHelp.setBounds(50, 200, 150, 30);
                    GoMchUI.setBounds(50, 240, 150, 30);

                }
            };

            jFrame.setLayout(layoutManager);

            jFrame.setVisible(true);

            jFrame.add(jt);
            jFrame.add(continues);
            jFrame.add(autoSet);
            jFrame.add(Black);
            jFrame.add(White);
            jFrame.add(FastLoad);
            jFrame.add(SafeLoad);
            jFrame.add(onTop);
            jFrame.add(noOnTop);
            jFrame.add(GoHelp);
            jFrame.add(GoMchUI);

            continues.addActionListener(e1 -> {
                setDefaultColor();
            });
            autoSet.addActionListener(e1 -> {
                continues.setVisible(false);
                autoSet.setVisible(false);

                colorSet = "Color@White";
                languageSet = "Language@Auto";
                exButtonSet = "Button@Ex.Exit";
                fastLoadSet = "Load@Fast";
                onTopSet = "Display@Default";
                saveCache = "Cache@Delete";
                saveErrorLog = "ErrorLog@Save";
                saveRunLog = "RunLog@Save";
                autoPUDSet = "UPD@MCH";
                saveHistorySet = "History@SaveAll";

                //                goHelp();
                defaultIniSetOver();
            });
            Black.addActionListener(e1 -> {
                Events.switchColor(1);
                setDefaultLoad();
            });
            White.addActionListener(e1 -> {
                Events.switchColor(0);
                setDefaultLoad();
            });
            FastLoad.addActionListener(e1 -> {
                Events.switchFastLoad(true);
                onTops();
            });
            SafeLoad.addActionListener(e1 -> {
                Events.switchFastLoad(false);
                onTops();
            });
            onTop.addActionListener(e1 -> {
                Events.switchOnTop(true);
                if (!MchUI.jFrame.isVisible()) {
                    goHelp();
                } else {
                    defaultIniSetOver();
                }
            });
            noOnTop.addActionListener(e1 -> {
                Events.switchOnTop(false);
                if (!MchUI.jFrame.isVisible()) {
                    goHelp();
                } else {
                    defaultIniSetOver();
                }
            });
            GoHelp.addActionListener(e1 -> {
                Helps.Helps();
                defaultIniSetOver();
            });
            GoMchUI.addActionListener(e1 -> {
                defaultIniSetOver();
            });

        }
    }

    public static void setDefaultColor() {
        if (Community.LangID == 0) {
            Black.setText("黑色");
            White.setText("白色");
            jt.setText("""
                    请选择此程序的默认颜色









                    2/4""");
        } else if (Community.LangID == 1) {
            Black.setText("Black");
            White.setText("White");
            jt.setText("""
                    Please Select the Display Color of This Program









                    2/5""");
        }
        continues.setVisible(false);
        autoSet.setVisible(false);

        Black.setVisible(true);
        White.setVisible(true);
    }

    public static void setDefaultLoad() {
        if (Community.LangID == 0) {
            FastLoad.setText("快速加载");
            SafeLoad.setText("安全加载");
            jt.setText("""
                    请选择是否启用快速加载

                    开启快速加载可以加快加载速度,同时内存的占用也会偏高

                    如果内存条件允许MCH使用超过200MB的内存
                    非常建议开启此功能




                    3/5""");
        } else if (Community.LangID == 1) {
            FastLoad.setText("Fast Load");
            SafeLoad.setText("Safe Load");
            jt.setText("""
                    Please Select Whether Fast Load is Enable or Not?

                    Turn on Fast Load can Speed Up Load,But the Memory Used Will Be High

                    if Memory Condition Allows MCH Use More than 200MB
                    Highly Recommended Turn on Fast Load




                    3/5""");
        }
        Black.setVisible(false);
        White.setVisible(false);

        FastLoad.setVisible(true);
        SafeLoad.setVisible(true);
    }

    public static void onTops() {
        if (Community.LangID == 0) {
            onTop.setText("置顶");
            noOnTop.setText("不置顶");
            jt.setText("""
                    是否将MCH置顶显示?









                    4/5""");
        } else if (Community.LangID == 1) {
            onTop.setText("on top");
            noOnTop.setText("no top");
            jt.setText("""
                    Do you wanna display MCH on top?









                    4/5""");
        }
        FastLoad.setVisible(false);
        SafeLoad.setVisible(false);

        onTop.setVisible(true);
        noOnTop.setVisible(true);
    }

    public static void goHelp() {
        if (Community.LangID == 0) {
            GoHelp.setText("去看看");
            GoMchUI.setText("不需要");
            jt.setText("""
                    配置已完成,是否去到帮助页面?









                    5/5""");
        } else if (Community.LangID == 1) {
            GoHelp.setText("Go See");
            GoMchUI.setText("No See");
            jt.setText("""
                    settings config is finished,Do you want to go to the help page?









                    5/5""");
        }
        onTop.setVisible(false);
        noOnTop.setVisible(false);

        GoHelp.setVisible(true);
        GoMchUI.setVisible(true);
    }

    public static void defaultIniSetOver() {
        System.out.println("[" + times.format + "]\n" + "MchUI:UI重新就绪中");
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadingAssemble: MchUI\n");
        if (!MchUI.jFrame.isVisible()) {
            new MchUI();
        }
        jFrame.setVisible(false);
        iniHas = true;
        if (MenuUI.OpenMenu) {
            new MenuUI();
        }
    }

    public static void WriteIni() throws Exception {
        FileWriter fl = new FileWriter(path + sets, false);
        fl.write(colorSet + "\n" + languageSet + "\n" + exButtonSet + "\n" + fastLoadSet + "\n" + onTopSet + "\n" + saveCache + "\n" + saveRunLog + "\n" + saveErrorLog + "\n" + autoPUDSet + "\n" + saveHistorySet + "\n" + minecraftListenFlushSpeedLevel + "\n"  + input_command);
        fl.close();
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
                }

                if (Black != -1) {
                    Community.ColorID = 1;
                    Community.ColorSetID = 1;
                    colorSet = "Color@Black";
                }

                if (White != -1) {
                    Community.ColorID = 0;
                    Community.ColorSetID = 0;
                    colorSet = "Color@White";
                }

                if (auto != -1) {
                    Community.ColorSetID = 3;
                    colorSet = "Color@Auto";
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

            if (!(Chinese != -1 & English != -1 & auto != -1)) {
                if (Chinese != -1) {
                    Community.LangID = 0;
                    Community.LangSetID = 0;
                    languageSet = "Language@Chinese";
                }

                if (English != -1) {
                    Community.LangID = 1;
                    Community.LangSetID = 1;
                    languageSet = "Language@English";
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
                }
            } else {
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

        //        退出按钮
        {
            int exit = s.indexOf("button@ex.exit");
            int exitNot = s.indexOf("button@ex.smaller");

            if (!(exit != -1 & exitNot != -1)) {
                if (exit != -1) {
                    Community.exitButtonWillExit = true;
                    exButtonSet = "Button@Ex.Exit";
                }
                if (exitNot != -1) {
                    Community.exitButtonWillExit = false;
                    exButtonSet = "Button@Ex.Smaller";
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
                }
                if (safe != -1) {
                    Community.fastLoad = false;
                    fastLoadSet = "Load@Safe";
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
                }
                if (noOnTop != -1) {
                    Community.onTop = false;
                    onTopSet = "Display@Default";
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
                }

                if (notSaveCaches != -1) {
                    Community.saveCache = false;
                    saveCache = "Cache@Delete";
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
                }

                if (notSaveErrorLogs != -1) {
                    Community.saveErrorLog = false;
                    saveErrorLog = "ErrorLog@Delete";
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
                }

                if (notSaveRunLog != -1) {
                    Community.saveRunLog = false;
                    saveRunLog = "RunLog@Delete";
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
                }

                if (noAutoUPD != -1) {
                    Community.autoUPD = false;
                    autoPUDSet = "UPD@Self";
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
                }

                if (saveHistory != -1) {
                    Community.historySaveID = 1;
                    saveHistorySet = "History@SaveSome";
                }

                if (notSaveHistory != -1) {
                    Community.historySaveID = 2;
                    saveHistorySet = "History@Delete";
                }
            }
        }

        {
            if (!Community.started) {
                int input = s.indexOf("input@");

                if (input != -1) {
                    String setCommand = s.substring(s.indexOf("input@") + 6);
                    MchUI.input_Command.setText(setCommand);
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
                }

                if (levels1 != -1) {
                    Community.minecraftListenFlushSpeedLevels = 1;
                    minecraftListenFlushSpeedLevel = "MinecraftListenFlushSpeedLevel@1";
                }
            }
        }
    }
}
