package com.github.zhuaidadaya.MCH.UI;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.Command.limitedTypes;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.Events.UPD.URLs;
import com.github.zhuaidadaya.MCH.Help.Helps;
import com.github.zhuaidadaya.MCH.UI.Color.displaySets;
import com.github.zhuaidadaya.MCH.Events.Events;
import com.github.zhuaidadaya.MCH.lib.filesOperator;

import javax.swing.*;
import java.awt.*;
import java.io.File;

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
    public static JButton Chinese_ZH = new JButton();
    public static JButton Chinese_TW = new JButton();
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

    public static JButton setting_command = new JButton();
    public static JLabel invalidCommand = new JLabel();
    public static JButton showInvalidCommand = new JButton();
    public static JButton noShowInvalidCommand = new JButton();

    public static JLabel showCommandsMethod = new JLabel();
    public static JButton firstBedrock = new JButton();
    public static JButton firstJava = new JButton();

    public static JButton iniFinished = new JButton();
    public static JButton iniHelper = new JButton();

    public static JLabel toWikiOrNot = new JLabel();
    public static JButton toWiki = new JButton();
    public static JButton toWikiNot = new JButton();

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
            jFrame.add(Chinese_ZH);
            jFrame.add(Chinese_TW);
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

            jFrame.add(setting_command);
            jFrame.add(invalidCommand);
            jFrame.add(showInvalidCommand);
            jFrame.add(noShowInvalidCommand);
            jFrame.add(showCommandsMethod);
            jFrame.add(firstBedrock);
            jFrame.add(firstJava);

            jFrame.add(iniFinished);
            jFrame.add(iniHelper);

            jFrame.add(toWikiOrNot);
            jFrame.add(toWiki);
            jFrame.add(toWikiNot);

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
                    setting_info.setBounds(240, 280, 80, 34);
                    if(! Config.settingIni) {
                        setting_command.setBounds(320, 280, 100, 34);
                    } else {
                        setting_command.setBounds(160, 280, 100, 34);
                    }

                    Language.setBounds(0, 5, 80, 30);
                    Chinese_ZH.setBounds(80, 5, 80, 30);
                    Chinese_TW.setBounds(170, 5, 80, 30);
                    English.setBounds(260, 5, 80, 30);
                    LanguageAuto.setBounds(350, 5, 80, 30);

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

                    aboutMCH.setBounds(0, 5, 100, 30);
                    PATH.setBounds(0, 35, 250, 30);
                    mchDirSize.setBounds(0, 65, 350, 30);
                    showDir.setBounds(0, 95, 100, 30);
                    deleteData.setBounds(105, 95, 100, 30);
                    versionInfo.setBounds(0, 145, 200, 40);

                    aboutDevelopOfMCH.setBounds(350, 5, 100, 30);
                    Developers.setBounds(350, 40, 300, 150);

                    //                    Command
                    invalidCommand.setBounds(5, 5, 110, 30);
                    showInvalidCommand.setBounds(110, 5, 80, 30);
                    noShowInvalidCommand.setBounds(200, 5, 80, 30);

                    showCommandsMethod.setBounds(5, 50, 110, 30);
                    firstBedrock.setBounds(110, 50, 80, 30);
                    firstBedrock.setFont(new Font(firstBedrock.getFont().getName(), firstBedrock.getFont().getStyle(), 11));
                    firstJava.setBounds(200, 50, 80, 30);

                    toWikiOrNot.setBounds(5, 90, 110, 30);
                    toWiki.setBounds(110, 90, 80, 30);
                    toWikiNot.setBounds(200, 90, 80, 30);

                    iniFinished.setBounds(535, 280, 100, 34);
                    iniHelper.setBounds(430, 280, 100, 34);

                    if(Config.settingIni) {
                        setting_upd.setVisible(false);
                        setting_info.setVisible(false);
                        iniFinished.setVisible(true);
                        iniHelper.setVisible(true);
                    } else {
                        setting_upd.setVisible(true);
                        setting_info.setVisible(true);
                        iniFinished.setVisible(false);
                        iniHelper.setVisible(false);
                    }
                }
            });
        }

        displaySets.settingsDisplay();

        Hades.addActionListener(e -> {
            Events.switchColor(2);
            displaySets.hadesColor();
        });

        iniFinished.addActionListener(e -> {
            jFrame.setVisible(false);
            Config.settingIni = false;
            jFrame.setAlwaysOnTop(Community.onTop);
            Config.defaultIniSetOver();
        });
        iniHelper.addActionListener(e -> {
            Helps.iniHelps();
        });

        Black.addActionListener(e -> Events.switchColor(1));
        White.addActionListener(e -> Events.switchColor(0));

        Chinese_ZH.addActionListener(e -> Events.switchLanguage(0));
        English.addActionListener(e -> Events.switchLanguage(1));
        LanguageAuto.addActionListener(e -> Events.switchLanguage(2));
        Chinese_TW.addActionListener(e -> Events.switchLanguage(3));

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

        showInvalidCommand.addActionListener(e -> Events.switchInvalidCommandShow(true));
        noShowInvalidCommand.addActionListener(e -> Events.switchInvalidCommandShow(false));

        firstBedrock.addActionListener(e -> Events.switchCommandMethod(limitedTypes.BEDROCK));
        firstJava.addActionListener(e -> Events.switchCommandMethod(limitedTypes.JAVA));

        toWiki.addActionListener(e -> Events.switchToWiki(true));
        toWikiNot.addActionListener(e -> Events.switchToWiki(false));

        showDir.addActionListener(e -> {
            if(! Community.isDaemons) {
                if (Community.os.equals("Linux")) {
                    try {
                        Runtime.getRuntime().exec("nautilus " + Config.path);
                    } catch (Exception exception) {
                        Errors.tips(500, 300, "open-files-cannot", "");
                    }
                } else {
                    try {
                        Runtime.getRuntime().exec("explorer.exe \"" + Config.path.replace("/","\\") + "\"");
                    } catch (Exception exception) {
                        Errors.tips(500, 300, "open-files-cannot", "");
                    }
                }
            }
        });

        setting_run.addActionListener(e -> {
            if(! Community.isDaemons) {
                Community.setsDisplayID = 0;
                displaySets.settingsDisplay();
            }
        });
        setting_display.addActionListener(e -> {
            if(! Community.isDaemons) {
                Community.setsDisplayID = 1;
                displaySets.settingsDisplay();
            }
        });
        setting_upd.addActionListener(e -> {
            if(! Community.isDaemons) {
                Community.setsDisplayID = 2;
                displaySets.settingsDisplay();
            }
        });
        setting_info.addActionListener(e -> {
            if(! Community.isDaemons) {
                Community.setsDisplayID = 3;
                displaySets.settingsDisplay();
            }
        });
        setting_command.addActionListener(e -> {
            if(! Community.isDaemons) {
                Community.setsDisplayID = 4;
                displaySets.settingsDisplay();
            }
        });

        checkUPD.addActionListener(e -> {
            if(Community.canUPD) {
                URLs.nowUPD = true;
            } else {
                Events.checkUPD();
            }
        });

        MenuUI2.deleteData.addActionListener(e -> {
            if(! Community.isDaemons) {
                File[] caches = new File(Config.path + "logs/").listFiles();

                filesOperator.DeleteFiles(caches);
            }
        });

        jFrame.setVisible(false);
    }
}
