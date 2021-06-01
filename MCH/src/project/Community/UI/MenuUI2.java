package project.Community.UI;

import project.Community.Command.ini;
import project.Community.Community;
import project.Community.Events.Events;
import project.Community.Events.UPD.URLs;
import project.Community.UI.Color.displaySets;
import project.Community.lib.filesOperator;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuUI2 {
    public static JFrame jFrame = new JFrame();

    public static JLabel saveCacheOrNot = new JLabel();
    public static JButton saveCache = new JButton();
    public static JButton notSaveCache = new JButton();

    public static JLabel saveErrorLogsOrNot = new JLabel();
    public static JButton saveErrorLogs = new JButton();
    public static JButton notSaveErrorLogs = new JButton();

    public static JLabel saveRunLogsOrNot = new JLabel();
    public static JButton saveRunLogs = new JButton();
    public static JButton notSaveRunLogs = new JButton();

    public static JLabel autoUpdOrNot = new JLabel();
    public static JButton autoUPD = new JButton();
    public static JButton noAutoUPD = new JButton();

    public static JButton setting_display = new JButton();
    public static JButton setting_run = new JButton();
    public static JButton setting_upd = new JButton();
    public static JButton setting_info = new JButton();

    public static JLabel saveHistoryOrNot = new JLabel();
    public static JButton saveHistory = new JButton();
    public static JButton saveAllHistory = new JButton();
    public static JButton notSaveHistory = new JButton();

    public static JLabel Color = new JLabel();
    public static JButton Black = new JButton();
    public static JButton White = new JButton();
    public static JButton Hades = new JButton();
    public static JButton colorAuto = new JButton();
    public static JButton User_Color = new JButton();

    public static JLabel exButton = new JLabel();
    public static JButton exButtonExit = new JButton();
    public static JButton exButtonNarrow = new JButton();
    public static JLabel Language = new JLabel();
    public static JButton Chinese = new JButton();
    public static JButton English = new JButton();
    public static JButton LanguageAuto = new JButton();

    public static JLabel fastLoad = new JLabel();
    public static JButton fastLoadYes = new JButton();
    public static JButton fastLoadNo = new JButton();
    public static JLabel onTops = new JLabel();
    public static JButton onTop = new JButton();
    public static JButton noOnTop = new JButton();

    public static JButton checkUPD = new JButton();
    public static JTextArea checkReturn = new JTextArea();
    public static JTextArea updateInfo = new JTextArea();

    public static JLabel aboutMCH = new JLabel();
    public static JLabel PATH = new JLabel();
    public static JButton showDir = new JButton();
    public static JLabel mchDirSize = new JLabel();
    public static JTextArea versionInfo = new JTextArea();
    public static JLabel aboutDevelopOfMCH = new JLabel();
    public static JTextArea Developers = new JTextArea();
    public static JButton deleteData = new JButton();

    public MenuUI2() {
        UI();
    }

    public static void UI() {

        jFrame.setSize(650, 350);

        //        窗口初始化设置
        //获得屏幕大小
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        //设置窗口位置
        jFrame.setLocation(width / 2 - jFrame.getWidth() / 2, height / 2 - jFrame.getHeight() / 2);

        jFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        jFrame.setResizable(false);

        Hades.setVisible(false);

        versionInfo.setEditable(false);

        Developers.setEditable(false);

        checkReturn.setEditable(false);

        updateInfo.setEditable(false);

        {

            jFrame.add(saveCacheOrNot);
            jFrame.add(saveCache);
            jFrame.add(notSaveCache);

            jFrame.add(saveErrorLogsOrNot);
            jFrame.add(saveErrorLogs);
            jFrame.add(notSaveErrorLogs);

            jFrame.add(saveRunLogsOrNot);
            jFrame.add(saveRunLogs);
            jFrame.add(notSaveRunLogs);

            jFrame.add(autoUpdOrNot);
            jFrame.add(autoUPD);
            jFrame.add(noAutoUPD);

            jFrame.add(saveHistoryOrNot);
            jFrame.add(saveAllHistory);
            jFrame.add(saveHistory);
            jFrame.add(notSaveHistory);

            jFrame.add(fastLoad);
            jFrame.add(fastLoadYes);
            jFrame.add(fastLoadNo);

            jFrame.add(exButton);
            jFrame.add(exButtonExit);
            jFrame.add(exButtonNarrow);

            jFrame.add(setting_run);
            jFrame.add(setting_display);
            jFrame.add(setting_upd);
            jFrame.add(setting_info);

            jFrame.add(Language);
            jFrame.add(Chinese);
            jFrame.add(English);
            jFrame.add(LanguageAuto);

            jFrame.add(Color);
            jFrame.add(White);
            jFrame.add(Black);
            jFrame.add(colorAuto);

            jFrame.add(onTops);
            jFrame.add(onTop);
            jFrame.add(noOnTop);

            jFrame.add(checkUPD);
            jFrame.add(checkReturn);
            jFrame.add(updateInfo);

            jFrame.add(aboutMCH);
            jFrame.add(PATH);
            jFrame.add(showDir);
            jFrame.add(mchDirSize);
            jFrame.add(versionInfo);
            jFrame.add(aboutDevelopOfMCH);
            jFrame.add(Developers);
            jFrame.add(deleteData);

            jFrame.setLayout(new LayoutManager() {
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
                    saveCacheOrNot.setBounds(0, 5, 80, 30);

                    saveCache.setBounds(80, 5, 80, 30);
                    notSaveCache.setBounds(170, 5, 80, 30);

                    saveErrorLogsOrNot.setBounds(0, 45, 80, 30);

                    saveErrorLogs.setBounds(80, 45, 80, 30);
                    notSaveErrorLogs.setBounds(170, 45, 80, 30);

                    saveRunLogsOrNot.setBounds(0, 85, 80, 30);

                    saveRunLogs.setBounds(80, 85, 80, 30);
                    notSaveRunLogs.setBounds(170, 85, 80, 30);

                    autoUpdOrNot.setBounds(0, 125, 80, 30);
                    autoUPD.setBounds(80, 125, 80, 30);
                    noAutoUPD.setBounds(170, 125, 80, 30);

                    fastLoad.setBounds(0, 165, 80, 30);
                    fastLoadYes.setBounds(80, 165, 80, 30);
                    fastLoadNo.setBounds(170, 165, 80, 30);

                    saveHistoryOrNot.setBounds(270, 5, 90, 30);
                    saveAllHistory.setBounds(340, 5, 90, 30);
                    saveHistory.setBounds(440, 5, 100, 30);
                    notSaveHistory.setBounds(550, 5, 80, 30);

                    exButton.setBounds(270, 45, 80, 30);
                    exButtonExit.setBounds(340, 45, 90, 30);
                    exButtonNarrow.setBounds(440, 45, 100, 30);

                    setting_run.setBounds(0, 280, 80, 34);
                    setting_display.setBounds(80, 280, 80, 34);
                    setting_upd.setBounds(160, 280, 80, 34);
                    setting_info.setBounds(240,280,80,34);

                    Language.setBounds(0, 5, 80, 30);
                    Chinese.setBounds(80, 5, 80, 30);
                    English.setBounds(170, 5, 80, 30);
                    LanguageAuto.setBounds(260, 5, 80, 30);

                    Color.setBounds(0, 45, 80, 30);
                    White.setBounds(80, 45, 80, 30);
                    Black.setBounds(170, 45, 80, 30);
                    //                    colorAuto.setBounds(260,45,80,30);

                    onTops.setBounds(0, 85, 80, 30);
                    onTop.setBounds(80, 85, 80, 30);
                    noOnTop.setBounds(170, 85, 80, 30);

                    checkUPD.setBounds(0, 5, 100, 30);
                    checkReturn.setBounds(0, 45, 220, 135);
                    updateInfo.setBounds(230, 0, 300, 195);

                    aboutMCH.setBounds(0,5,100,30);
                    PATH.setBounds(0,35,200,30);
                    mchDirSize.setBounds(0,65,350,30);
                    showDir.setBounds(0,95,100,30);
                    deleteData.setBounds(105,95,100,30);
                    versionInfo.setBounds(0,145,200,40);

                    aboutDevelopOfMCH.setBounds(350,5,100,30);
                    Developers.setBounds(350,40,300,150);
                }
            });
        }

        displaySets.settingsDisplay();

        MenuUI2.Hades.addActionListener(e -> {
            Events.switchColor(2);
            displaySets.hadesColor();
        });


        Black.addActionListener(e -> Events.switchColor(1));
        White.addActionListener(e -> Events.switchColor(0));

        Chinese.addActionListener(e -> Events.switchLanguage(0));
        English.addActionListener(e -> Events.switchLanguage(1));
        LanguageAuto.addActionListener(e -> Events.switchLanguage(2));

        exButtonNarrow.addActionListener(e -> Events.switchExButtonWillExit(false));
        exButtonExit.addActionListener(e -> Events.switchExButtonWillExit(true));

        fastLoadYes.addActionListener(e -> Events.switchFastLoad(true));
        fastLoadNo.addActionListener(e -> Events.switchFastLoad(false));

        onTop.addActionListener(e -> Events.switchOnTop(true));
        noOnTop.addActionListener(e -> Events.switchOnTop(false));

        saveCache.addActionListener(e -> Events.switchSaveUpdCache(true));
        notSaveCache.addActionListener(e -> Events.switchSaveUpdCache(false));

        saveErrorLogs.addActionListener(e -> Events.switchSaveErrorLog(true));
        notSaveErrorLogs.addActionListener(e -> Events.switchSaveErrorLog(false));

        saveRunLogs.addActionListener(e -> Events.switchSaveRunLog(true));
        notSaveRunLogs.addActionListener(e -> Events.switchSaveRunLog(false));

        autoUPD.addActionListener(e -> Events.switchAutoUPD(true));
        noAutoUPD.addActionListener(e -> Events.switchAutoUPD(false));

        saveAllHistory.addActionListener(e -> Events.switchHistorySaveID(0));
        saveHistory.addActionListener(e -> Events.switchHistorySaveID(1));
        notSaveHistory.addActionListener(e -> Events.switchHistorySaveID(2));

        showDir.addActionListener(e -> {
            if (!Community.isDaemons) {
                try {
                    Runtime.getRuntime().exec("explorer.exe C:\\.MCH\\");
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        setting_run.addActionListener(e -> {
            if (!Community.isDaemons) {
                Community.setsDisplayID = 0;
                displaySets.settingsDisplay();
            }
        });
        setting_display.addActionListener(e -> {
            if (!Community.isDaemons) {
                Community.setsDisplayID = 1;
                displaySets.settingsDisplay();
            }
        });
        setting_upd.addActionListener(e -> {
            if (!Community.isDaemons) {
                Community.setsDisplayID = 2;
                displaySets.settingsDisplay();
            }
        });
        setting_info.addActionListener(e -> {
            if (!Community.isDaemons) {
                Community.setsDisplayID = 3;
                displaySets.settingsDisplay();
            }
        });

        checkUPD.addActionListener(e -> {
            if (Community.canUPD) {
                URLs.nowUPD = true;
            } else {
                Events.checkUPD();
            }
        });

        MenuUI2.deleteData.addActionListener(e -> {
            if (!Community.isDaemons) {
                new File(ini.path + "run.log").delete();

                File[] caches = new File(ini.path).listFiles();

                filesOperator.DeleteFiles(caches);
            }
        });

        jFrame.setVisible(false);
    }
}