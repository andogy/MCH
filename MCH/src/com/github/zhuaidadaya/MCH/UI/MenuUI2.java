package com.github.zhuaidadaya.MCH.UI;

import com.github.zhuaidadaya.MCH.Config.ConfigUtil;
import com.github.zhuaidadaya.MCH.Command.limitedTypes;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.Events.Events;
import com.github.zhuaidadaya.MCH.Events.UPD.URLs;
import com.github.zhuaidadaya.MCH.Help.Helps;
import com.github.zhuaidadaya.MCH.UI.Color.displaySets;
import com.github.zhuaidadaya.MCH.lib.OpenInExplore;
import com.github.zhuaidadaya.MCH.lib.filesOperator;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MenuUI2 extends Community {
    public static JFrame jFrame = new JFrame();

    public static JLabel saveCacheOrNot = new JLabel();
    public static JButton saveCache = new JButton();
    public static JButton notSaveCache = new JButton();

    public static JLabel saveErrorLogsOrNot = new JLabel();
    public static JButton saveErrorLogs = new JButton();
    public static JButton noSaveErrorLogs = new JButton();

    public static JLabel saveRunLogsOrNot = new JLabel();
    public static JButton saveRunLogs = new JButton();
    public static JButton noSaveRunLogs = new JButton();

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

    public static void show() {
        jFrame.setVisible(true);
    }

    public static void UI() {
        uiSizeMap.put(jFrame, new Dimension(650, 350));

        jFrame.setSize(uiSizeMap.getDimension(jFrame));

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
            uiSizeMap.put(saveCacheOrNot, new Rectangle(0, 5, 80, 30));
            uiSizeMap.put(saveCache, new Rectangle(80, 5, 80, 30));
            uiSizeMap.put(notSaveCache, new Rectangle(170, 5, 80, 30));

            uiSizeMap.put(saveErrorLogsOrNot, new Rectangle(0, 45, 80, 30));

            uiSizeMap.put(saveErrorLogs, new Rectangle(80, 45, 80, 30));
            uiSizeMap.put(noSaveErrorLogs, new Rectangle(170, 45, 80, 30));

            uiSizeMap.put(saveRunLogsOrNot, new Rectangle(0, 85, 80, 30));

            uiSizeMap.put(saveRunLogs, new Rectangle(80, 85, 80, 30));
            uiSizeMap.put(noSaveRunLogs, new Rectangle(170, 85, 80, 30));

            uiSizeMap.put(autoUpdOrNot, new Rectangle(0, 125, 80, 30));
            uiSizeMap.put(autoUPD, new Rectangle(80, 125, 80, 30));
            uiSizeMap.put(noAutoUPD, new Rectangle(170, 125, 80, 30));

            uiSizeMap.put(fastLoad, new Rectangle(0, 165, 80, 30));
            uiSizeMap.put(fastLoadYes, new Rectangle(80, 165, 80, 30));
            uiSizeMap.put(fastLoadNo, new Rectangle(170, 165, 80, 30));

            uiSizeMap.put(saveHistoryOrNot, new Rectangle(270, 5, 90, 30));
            uiSizeMap.put(saveAllHistory, new Rectangle(340, 5, 90, 30));
            uiSizeMap.put(saveHistory, new Rectangle(440, 5, 100, 30));
            uiSizeMap.put(notSaveHistory, new Rectangle(550, 5, 80, 30));

            uiSizeMap.put(exButton, new Rectangle(270, 45, 80, 30));
            uiSizeMap.put(exButtonExit, new Rectangle(340, 45, 90, 30));
            uiSizeMap.put(exButtonNarrow, new Rectangle(440, 45, 100, 30));

            uiSizeMap.put(setting_run, new Rectangle(0, 280, 80, 34));
            uiSizeMap.put(setting_display, new Rectangle(80, 280, 80, 34));
            uiSizeMap.put(setting_upd, new Rectangle(160, 280, 80, 34));
            uiSizeMap.put(setting_info, new Rectangle(240, 280, 80, 34));
            if(! ConfigUtil.settingIni) {
                uiSizeMap.put(setting_command, new Rectangle(320, 280, 100, 34));
            } else {
                uiSizeMap.put(setting_command, new Rectangle(160, 280, 100, 34));
            }

            uiSizeMap.put(Language, new Rectangle(0, 5, 80, 30));
            uiSizeMap.put(Chinese_ZH, new Rectangle(80, 5, 80, 30));
            uiSizeMap.put(Chinese_TW, new Rectangle(170, 5, 80, 30));
            uiSizeMap.put(English, new Rectangle(260, 5, 80, 30));
            uiSizeMap.put(LanguageAuto, new Rectangle(350, 5, 80, 30));

            uiSizeMap.put(Color, new Rectangle(0, 45, 80, 30));
            uiSizeMap.put(White, new Rectangle(80, 45, 80, 30));
            uiSizeMap.put(Black, new Rectangle(170, 45, 80, 30));

            uiSizeMap.put(onTops, new Rectangle(0, 85, 80, 30));
            uiSizeMap.put(onTop, new Rectangle(80, 85, 80, 30));
            uiSizeMap.put(noOnTop, new Rectangle(170, 85, 80, 30));

            uiSizeMap.put(checkUPD, new Rectangle(0, 5, 100, 30));
            uiSizeMap.put(checkReturn, new Rectangle(0, 45, 220, 135));
            uiSizeMap.put(updateInfo, new Rectangle(230, 0, 300, 195));

            uiSizeMap.put(aboutMCH, new Rectangle(0, 5, 100, 30));
            uiSizeMap.put(PATH, new Rectangle(0, 35, 250, 30));
            uiSizeMap.put(mchDirSize, new Rectangle(0, 65, 350, 30));
            uiSizeMap.put(showDir, new Rectangle(0, 95, 100, 30));
            uiSizeMap.put(deleteData, new Rectangle(105, 95, 100, 30));
            uiSizeMap.put(versionInfo, new Rectangle(0, 145, 200, 40));

            uiSizeMap.put(aboutDevelopOfMCH, new Rectangle(350, 5, 100, 30));
            uiSizeMap.put(Developers, new Rectangle(350, 40, 300, 150));

            uiSizeMap.put(invalidCommand, new Rectangle(5, 5, 110, 30));
            uiSizeMap.put(showInvalidCommand, new Rectangle(110, 5, 80, 30));
            uiSizeMap.put(noShowInvalidCommand, new Rectangle(200, 5, 80, 30));

            uiSizeMap.put(showCommandsMethod, new Rectangle(5, 50, 110, 30));
            uiSizeMap.put(firstBedrock, new Rectangle(110, 50, 80, 30));
            uiSizeMap.put(firstJava, new Rectangle(200, 50, 80, 30));

            uiSizeMap.put(toWikiOrNot, new Rectangle(5, 90, 110, 30));
            uiSizeMap.put(toWiki, new Rectangle(110, 90, 80, 30));
            uiSizeMap.put(toWikiNot, new Rectangle(200, 90, 80, 30));

            uiSizeMap.put(iniFinished, new Rectangle(535, 280, 100, 34));
            uiSizeMap.put(iniHelper, new Rectangle(430, 280, 100, 34));

            uiSizeMap.put(deleteData, new Rectangle(0, 40 + 35 + 100 + 30 + 40, 110, 30));

            setting_upd.setVisible(! ConfigUtil.settingIni);
            setting_info.setVisible(! ConfigUtil.settingIni);
            iniFinished.setVisible(ConfigUtil.settingIni);
            iniHelper.setVisible(ConfigUtil.settingIni);
        }

        {

            jFrame.add(saveCacheOrNot);
            jFrame.add(saveCache);
            jFrame.add(notSaveCache);

            jFrame.add(saveErrorLogsOrNot);
            jFrame.add(saveErrorLogs);
            jFrame.add(noSaveErrorLogs);

            jFrame.add(saveRunLogsOrNot);
            jFrame.add(saveRunLogs);
            jFrame.add(noSaveRunLogs);

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
                    jFrame.setSize(uiSizeMap.getDimension(jFrame));

                    saveCacheOrNot.setBounds(uiSizeMap.getRectangle(saveCacheOrNot));

                    saveCache.setBounds(uiSizeMap.getRectangle(saveCache));
                    notSaveCache.setBounds(uiSizeMap.getRectangle(notSaveCache));

                    saveErrorLogsOrNot.setBounds(uiSizeMap.getRectangle(saveErrorLogsOrNot));
                    saveErrorLogs.setBounds(uiSizeMap.getRectangle(saveErrorLogs));
                    noSaveErrorLogs.setBounds(uiSizeMap.getRectangle(noSaveErrorLogs));

                    saveRunLogsOrNot.setBounds(uiSizeMap.getRectangle(saveRunLogsOrNot));
                    saveRunLogs.setBounds(uiSizeMap.getRectangle(saveRunLogs));
                    noSaveRunLogs.setBounds(uiSizeMap.getRectangle(noSaveRunLogs));

                    autoUpdOrNot.setBounds(uiSizeMap.getRectangle(autoUpdOrNot));
                    autoUPD.setBounds(uiSizeMap.getRectangle(autoUPD));
                    noAutoUPD.setBounds(uiSizeMap.getRectangle(noAutoUPD));

                    fastLoad.setBounds(uiSizeMap.getRectangle(fastLoad));
                    fastLoadYes.setBounds(uiSizeMap.getRectangle(fastLoadYes));
                    fastLoadNo.setBounds(uiSizeMap.getRectangle(fastLoadNo));

                    saveHistoryOrNot.setBounds(uiSizeMap.getRectangle(saveHistoryOrNot));
                    saveAllHistory.setBounds(uiSizeMap.getRectangle(saveAllHistory));
                    saveHistory.setBounds(uiSizeMap.getRectangle(saveHistory));
                    notSaveHistory.setBounds(uiSizeMap.getRectangle(notSaveHistory));

                    exButton.setBounds(uiSizeMap.getRectangle(exButton));
                    exButtonExit.setBounds(uiSizeMap.getRectangle(exButtonExit));
                    exButtonNarrow.setBounds(uiSizeMap.getRectangle(exButtonNarrow));

                    setting_run.setBounds(uiSizeMap.getRectangle(setting_run));
                    setting_display.setBounds(uiSizeMap.getRectangle(setting_display));
                    setting_upd.setBounds(160, 280, 80, 34);
                    setting_info.setBounds(240, 280, 80, 34);
                    setting_command.setBounds(uiSizeMap.getRectangle(setting_command));

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

                    deleteData.setBounds(0, 40 + 35 + 100 + 30 + 40, 110, 30);

                    setting_upd.setVisible(! ConfigUtil.settingIni);
                    setting_info.setVisible(! ConfigUtil.settingIni);
                    iniFinished.setVisible(ConfigUtil.settingIni);
                    iniHelper.setVisible(ConfigUtil.settingIni);
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
            ConfigUtil.settingIni = false;
            jFrame.setAlwaysOnTop(Community.onTop);
            ConfigUtil.defaultIniSetOver();
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
        noSaveErrorLogs.addActionListener(e -> Events.switchSaveErrorLog(false));

        saveRunLogs.addActionListener(e -> Events.switchSaveRunLog(true));
        noSaveRunLogs.addActionListener(e -> Events.switchSaveRunLog(false));

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
                OpenInExplore.open(ConfigUtil.path);
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
                new Thread(() -> {
                    File[] caches = new File(ConfigUtil.path + "logs/").listFiles();

                    filesOperator.fileCount = 0;
                    filesOperator.countFiles(caches);;

                    loadingWindow.ui();

                    loadingWindow.progress.setMaximum(Math.toIntExact(filesOperator.fileCount));

                    filesOperator.DeleteFiles(caches);

                    loadingWindow.jFrame.setVisible(false);
                }).start();
            }
        });
    }
}
