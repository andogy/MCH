package project.Community.UI.Color;

import project.Community.Command.ini;
import project.Community.Command.limitedTypes;
import project.Community.Community;
import project.Community.Events.Errors;
import project.Community.Events.LoadAssembly;
import project.Community.Times.times;
import project.Community.UI.*;

import javax.swing.text.*;
import java.awt.*;
import java.util.Random;

import static project.Community.UI.Lang.initLanguage.lang;

public class displaySets extends Thread {

    //        创建颜色
    public static Color foregroundColor = new Color(214, 214, 214);
    public static Color backgroundColor = new Color(43, 43, 43);
    public static Color inputBoxColor = new Color(49, 51, 53);
    public static Color buttonColor = new Color(60, 63, 65);
    public static Color tipColor = new Color(58, 58, 58);
    public static Color grayColor = tipColor;

    public static void Color() {
        //        使用线程休眠减少CPU负担,15ms时间可以减少相当大的CPU负载,且用户无法察觉延迟
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //        去除button边框
        MchUI.menu.setBorderPainted(false);
        MchUI.menu.setFocusPainted(false);
        MchUI.command1.setBorder(null);

        exit.buttonEXIT.setBorderPainted(false);
        exit.buttonEXIT.setFocusPainted(false);
        exit.buttonEXITNot.setBorderPainted(false);
        exit.buttonEXITNot.setFocusPainted(false);

        MenuUI2.White.setBorderPainted(false);
        MenuUI2.White.setFocusPainted(false);
        MenuUI2.Black.setBorderPainted(false);
        MenuUI2.Black.setFocusPainted(false);
        MenuUI2.Chinese.setBorderPainted(false);
        MenuUI2.Chinese.setFocusPainted(false);
        MenuUI2.English.setBorderPainted(false);
        MenuUI2.English.setFocusPainted(false);
        MenuUI2.exButtonExit.setBorderPainted(false);
        MenuUI2.exButtonExit.setFocusPainted(false);
        MenuUI2.exButtonNarrow.setBorderPainted(false);
        MenuUI2.exButtonNarrow.setFocusPainted(false);
        MenuUI2.fastLoadYes.setBorderPainted(false);
        MenuUI2.fastLoadYes.setFocusPainted(false);
        MenuUI2.fastLoadNo.setBorderPainted(false);
        MenuUI2.fastLoadNo.setFocusPainted(false);
        MenuUI2.onTop.setBorderPainted(false);
        MenuUI2.onTop.setFocusPainted(false);
        MenuUI2.noOnTop.setBorderPainted(false);
        MenuUI2.noOnTop.setFocusPainted(false);
        MenuUI2.deleteData.setBorderPainted(false);
        MenuUI2.deleteData.setFocusPainted(false);
        MenuUI.helps.setFocusPainted(false);
        MenuUI.helps.setBorderPainted(false);
        MenuUI2.Hades.setBorderPainted(false);
        MenuUI2.Hades.setFocusPainted(false);
        MenuUI.user.setBorderPainted(false);
        MenuUI.user.setFocusPainted(false);
        MenuUI.gayhub.setBorderPainted(false);
        MenuUI2.checkUPD.setBorderPainted(false);
        MenuUI2.checkUPD.setFocusPainted(false);
        MenuUI.restart.setBorderPainted(false);
        MenuUI.restart.setFocusPainted(false);
        MenuUI.settings.setBorderPainted(false);
        MenuUI.settings.setFocusPainted(false);
        MenuUI.functions.setBorderPainted(false);
        MenuUI.functions.setFocusPainted(false);
        MenuUI.feedback.setBorderPainted(false);
        MenuUI.feedback.setFocusPainted(false);

        MenuUI2.saveCache.setBorderPainted(false);
        MenuUI2.saveCache.setFocusPainted(false);
        MenuUI2.notSaveCache.setBorderPainted(false);
        MenuUI2.notSaveCache.setFocusPainted(false);
        MenuUI2.saveErrorLogs.setBorderPainted(false);
        MenuUI2.saveErrorLogs.setFocusPainted(false);
        MenuUI2.notSaveErrorLogs.setBorderPainted(false);
        MenuUI2.notSaveErrorLogs.setFocusPainted(false);
        MenuUI2.saveRunLogs.setBorderPainted(false);
        MenuUI2.saveRunLogs.setFocusPainted(false);
        MenuUI2.notSaveRunLogs.setBorderPainted(false);
        MenuUI2.notSaveRunLogs.setFocusPainted(false);
        MenuUI2.autoUPD.setBorderPainted(false);
        MenuUI2.autoUPD.setFocusPainted(false);
        MenuUI2.noAutoUPD.setBorderPainted(false);
        MenuUI2.noAutoUPD.setFocusPainted(false);
        MenuUI2.saveAllHistory.setBorderPainted(false);
        MenuUI2.saveAllHistory.setFocusPainted(false);
        MenuUI2.saveHistory.setBorderPainted(false);
        MenuUI2.saveHistory.setFocusPainted(false);
        MenuUI2.notSaveHistory.setBorderPainted(false);
        MenuUI2.notSaveHistory.setFocusPainted(false);
        MenuUI2.setting_display.setBorderPainted(false);
        MenuUI2.setting_display.setFocusPainted(false);
        MenuUI2.setting_run.setBorderPainted(false);
        MenuUI2.setting_run.setFocusPainted(false);
        MenuUI2.LanguageAuto.setBorderPainted(false);
        MenuUI2.LanguageAuto.setFocusPainted(false);
        MenuUI2.setting_upd.setBorderPainted(false);
        MenuUI2.setting_upd.setFocusPainted(false);
        MenuUI2.showDir.setBorderPainted(false);
        MenuUI2.showDir.setFocusPainted(false);
        MenuUI2.setting_info.setBorderPainted(false);
        MenuUI2.setting_info.setFocusPainted(false);
        MenuUI2.setting_command.setBorderPainted(false);
        MenuUI2.setting_command.setFocusPainted(false);
        MenuUI2.showInvalidCommand.setBorderPainted(false);
        MenuUI2.showInvalidCommand.setFocusPainted(false);
        MenuUI2.noShowInvalidCommand.setBorderPainted(false);
        MenuUI2.noShowInvalidCommand.setFocusPainted(false);
        MenuUI2.firstBedrock.setBorderPainted(false);
        MenuUI2.firstBedrock.setFocusPainted(false);
        MenuUI2.firstJava.setBorderPainted(false);
        MenuUI2.firstJava.setFocusPainted(false);
        MenuUI2.iniFinished.setBorderPainted(false);
        MenuUI2.iniFinished.setFocusPainted(false);
        MenuUI2.iniHelper.setBorderPainted(false);
        MenuUI2.iniHelper.setFocusPainted(false);

        ExtraUI.turnOnMcBe.setBorderPainted(false);
        ExtraUI.turnOnMcBe.setFocusPainted(false);
        ExtraUI.turnOffMcBe.setBorderPainted(false);
        ExtraUI.turnOffMcBe.setFocusPainted(false);
        ExtraUI.launchers.setBorderPainted(false);
        ExtraUI.launchers.setFocusPainted(false);
        ExtraUI.randomProblem.setBorderPainted(false);
        ExtraUI.randomProblem.setFocusPainted(false);
        ExtraUI.functions.setBorderPainted(false);
        ExtraUI.functions.setFocusPainted(false);
        ExtraUI.saveFunc.setBorderPainted(false);
        ExtraUI.saveFunc.setFocusPainted(false);
        ExtraUI.Level0OfMLSL.setBorderPainted(false);
        ExtraUI.Level0OfMLSL.setFocusPainted(false);
        ExtraUI.Level1OfMLSL.setBorderPainted(false);
        ExtraUI.Level1OfMLSL.setFocusPainted(false);

        ini.continues.setBorderPainted(false);
        ini.continues.setFocusPainted(false);
        ini.autoSet.setBorderPainted(false);
        ini.autoSet.setFocusPainted(false);

        Color foregroundColor = new Color(214, 214, 214);
        Color backgroundColor = new Color(43, 43, 43);
        Color inputBoxColor = new Color(49, 51, 53);
        Color buttonColor = new Color(60, 63, 65);
        Color tipColor = new Color(58, 58, 58);
        Color grayColor = tipColor;
        Color caretColor = new Color(88, 195, 84);

        if(Community.ColorID == 0) {
            foregroundColor = new Color(0, 0, 0);
            backgroundColor = new Color(255, 255, 255);
            inputBoxColor = new Color(214, 214, 214);
            buttonColor = inputBoxColor;
            tipColor = new Color(255, 255, 255);
            grayColor = inputBoxColor;
        }

        //        固定颜色配置界面(初始化引导)
        ini.jt.setForeground(foregroundColor);
        ini.continues.setForeground(foregroundColor);
        ini.autoSet.setForeground(foregroundColor);

        ini.jFrame.getContentPane().setBackground(backgroundColor);
        ini.jt.setBackground(backgroundColor);
        ini.continues.setBackground(buttonColor);
        ini.autoSet.setBackground(buttonColor);

        loadingWindow.jFrame.getContentPane().setBackground(backgroundColor);
        loadingWindow.loadingTip.setForeground(foregroundColor);
        loadingWindow.loading.setBackground(inputBoxColor);
        loadingWindow.loading.setForeground(foregroundColor);

        //        以下为颜色设置
        //            ID=0为白色

        //            ID=1为黑色
        MchUI.jFrame.getContentPane().setBackground(backgroundColor);
        MenuUI.jFrame.getContentPane().setBackground(backgroundColor);
        MenuUI2.jFrame.getContentPane().setBackground(backgroundColor);
        exit.jFrame.getContentPane().setBackground(backgroundColor);
        ExtraUI.jFrame.getContentPane().setBackground(backgroundColor);

        MchUI.input_Command.setCaretColor(foregroundColor);

        MchUI.tips.setBackground(tipColor);
        MchUI.input_Command.setBackground(inputBoxColor);
        MchUI.menu.setBackground(buttonColor);
        MchUI.command1.setBackground(backgroundColor);
        MchUI.switchTip.setBackground(backgroundColor);
        MchUI.input_Command.setCaretColor(caretColor);
        MchUI.input_Command.setSelectionColor(caretColor);

        exit.buttonEXIT.setBackground(buttonColor);
        exit.buttonEXITNot.setBackground(buttonColor);
        exit.jTextArea.setBackground(backgroundColor);

        MenuUI2.White.setBackground(buttonColor);
        MenuUI2.User_Color.setBackground(buttonColor);
        MenuUI2.Color.setBackground(buttonColor);
        MenuUI2.deleteData.setBackground(buttonColor);
        MenuUI.helps.setBackground(buttonColor);
        MenuUI2.Hades.setBackground(buttonColor);
        MenuUI.user.setBackground(buttonColor);
        MenuUI.gayhub.setBackground(buttonColor);
        MenuUI2.checkUPD.setBackground(buttonColor);
        MenuUI2.checkReturn.setBackground(buttonColor);
        MenuUI.restart.setBackground(buttonColor);
        MenuUI.settings.setBackground(buttonColor);
        MenuUI.functions.setBackground(buttonColor);
        MenuUI.feedback.setBackground(buttonColor);
        MenuUI2.updateInfo.setBackground(backgroundColor);
        MenuUI2.showDir.setBackground(buttonColor);
        MenuUI2.versionInfo.setBackground(backgroundColor);
        MenuUI2.Developers.setBackground(backgroundColor);
        MenuUI.announcement.setBackground(backgroundColor);
        MenuUI2.iniFinished.setBackground(buttonColor);
        MenuUI2.iniHelper.setBackground(buttonColor);

        ExtraUI.functionEdit.setBackground(tipColor);
        ExtraUI.saveFunc.setBackground(buttonColor);

        ExtraUI.McBeStatus.setBackground(grayColor);

        switch(Community.LangSetID) {
            case 0 -> {
                MenuUI2.Chinese.setBackground(backgroundColor);
                MenuUI2.English.setBackground(buttonColor);
                MenuUI2.LanguageAuto.setBackground(buttonColor);
            }
            case 1 -> {
                MenuUI2.Chinese.setBackground(buttonColor);
                MenuUI2.English.setBackground(backgroundColor);
                MenuUI2.LanguageAuto.setBackground(buttonColor);
            }
            case 2 -> {
                MenuUI2.Chinese.setBackground(buttonColor);
                MenuUI2.English.setBackground(buttonColor);
                MenuUI2.LanguageAuto.setBackground(backgroundColor);
            }
        }

        if(Community.showCommandMethod.equals(limitedTypes.BEDROCK)) {
            MenuUI2.firstBedrock.setBackground(backgroundColor);
            MenuUI2.firstJava.setBackground(buttonColor);
        } else if(Community.showCommandMethod.equals(limitedTypes.JAVA)) {
            MenuUI2.firstBedrock.setBackground(buttonColor);
            MenuUI2.firstJava.setBackground(backgroundColor);
        }

        if(Community.showInvalidCommand) {
            MenuUI2.showInvalidCommand.setBackground(backgroundColor);
            MenuUI2.noShowInvalidCommand.setBackground(buttonColor);
        } else {
            MenuUI2.showInvalidCommand.setBackground(buttonColor);
            MenuUI2.noShowInvalidCommand.setBackground(backgroundColor);
        }

        if(Community.ColorID == 0) {
            MenuUI2.Black.setBackground(buttonColor);
            MenuUI2.White.setBackground(backgroundColor);
        } else if(Community.ColorID == 1) {
            MenuUI2.Black.setBackground(backgroundColor);
            MenuUI2.White.setBackground(buttonColor);
        }

        if(Community.historySaveID == 0) {
            MenuUI2.saveAllHistory.setBackground(backgroundColor);
            MenuUI2.saveHistory.setBackground(buttonColor);
            MenuUI2.notSaveHistory.setBackground(buttonColor);
        }
        if(Community.historySaveID == 1) {
            MenuUI2.saveAllHistory.setBackground(buttonColor);
            MenuUI2.saveHistory.setBackground(backgroundColor);
            MenuUI2.notSaveHistory.setBackground(buttonColor);
        }
        if(Community.historySaveID == 2) {
            MenuUI2.saveAllHistory.setBackground(buttonColor);
            MenuUI2.saveHistory.setBackground(buttonColor);
            MenuUI2.notSaveHistory.setBackground(backgroundColor);
        }

        if(Community.onTop) {
            MenuUI2.onTop.setBackground(backgroundColor);
            MenuUI2.noOnTop.setBackground(buttonColor);
        } else {
            MenuUI2.onTop.setBackground(buttonColor);
            MenuUI2.noOnTop.setBackground(backgroundColor);
        }

        if(Community.exitButtonWillExit) {
            MenuUI2.exButtonNarrow.setBackground(buttonColor);
            MenuUI2.exButtonExit.setBackground(backgroundColor);
        } else {
            MenuUI2.exButtonNarrow.setBackground(backgroundColor);
            MenuUI2.exButtonExit.setBackground(buttonColor);
        }

        if(Community.fastLoad) {
            MenuUI2.fastLoadYes.setBackground(backgroundColor);
            MenuUI2.fastLoadNo.setBackground(buttonColor);
        } else {
            MenuUI2.fastLoadYes.setBackground(buttonColor);
            MenuUI2.fastLoadNo.setBackground(backgroundColor);
        }

        if(Community.saveCache) {
            MenuUI2.notSaveCache.setBackground(buttonColor);
            MenuUI2.saveCache.setBackground(backgroundColor);
        } else {
            MenuUI2.notSaveCache.setBackground(backgroundColor);
            MenuUI2.saveCache.setBackground(buttonColor);
        }

        if(Community.saveErrorLog) {
            MenuUI2.notSaveErrorLogs.setBackground(buttonColor);
            MenuUI2.saveErrorLogs.setBackground(backgroundColor);
        } else {
            MenuUI2.notSaveErrorLogs.setBackground(backgroundColor);
            MenuUI2.saveErrorLogs.setBackground(buttonColor);
        }

        if(Community.saveRunLog) {
            MenuUI2.saveRunLogs.setBackground(backgroundColor);
            MenuUI2.notSaveRunLogs.setBackground(buttonColor);
        } else {
            MenuUI2.saveRunLogs.setBackground(buttonColor);
            MenuUI2.notSaveRunLogs.setBackground(backgroundColor);
        }

        if(Community.autoUPD) {
            MenuUI2.autoUPD.setBackground(backgroundColor);
            MenuUI2.noAutoUPD.setBackground(buttonColor);
        } else {
            MenuUI2.autoUPD.setBackground(buttonColor);
            MenuUI2.noAutoUPD.setBackground(backgroundColor);
        }

        if(ExtraUI.mcRunning) {
            ExtraUI.turnOnMcBe.setBackground(backgroundColor);
            ExtraUI.turnOffMcBe.setBackground(buttonColor);
        } else {
            ExtraUI.turnOnMcBe.setBackground(buttonColor);
            ExtraUI.turnOffMcBe.setBackground(backgroundColor);
        }


        if(Community.setsDisplayID == 0) {
            MenuUI2.setting_run.setBackground(backgroundColor);
            MenuUI2.setting_display.setBackground(buttonColor);
            MenuUI2.setting_upd.setBackground(buttonColor);
            MenuUI2.setting_info.setBackground(buttonColor);
            MenuUI2.setting_command.setBackground(buttonColor);
        } else if(Community.setsDisplayID == 1) {
            MenuUI2.setting_run.setBackground(buttonColor);
            MenuUI2.setting_display.setBackground(backgroundColor);
            MenuUI2.setting_upd.setBackground(buttonColor);
            MenuUI2.setting_info.setBackground(buttonColor);
            MenuUI2.setting_command.setBackground(buttonColor);
        } else if(Community.setsDisplayID == 2) {
            MenuUI2.setting_run.setBackground(buttonColor);
            MenuUI2.setting_display.setBackground(buttonColor);
            MenuUI2.setting_upd.setBackground(backgroundColor);
            MenuUI2.setting_info.setBackground(buttonColor);
            MenuUI2.setting_command.setBackground(buttonColor);
        } else if(Community.setsDisplayID == 3) {
            MenuUI2.setting_run.setBackground(buttonColor);
            MenuUI2.setting_display.setBackground(buttonColor);
            MenuUI2.setting_upd.setBackground(buttonColor);
            MenuUI2.setting_info.setBackground(backgroundColor);
            MenuUI2.setting_command.setBackground(buttonColor);
        } else if(Community.setsDisplayID == 4) {
            MenuUI2.setting_run.setBackground(buttonColor);
            MenuUI2.setting_display.setBackground(buttonColor);
            MenuUI2.setting_upd.setBackground(buttonColor);
            MenuUI2.setting_info.setBackground(buttonColor);
            MenuUI2.setting_command.setBackground(backgroundColor);
        }

        if(Community.extraDisplayID == 0) {
            ExtraUI.functions.setBackground(buttonColor);
            ExtraUI.launchers.setBackground(backgroundColor);
            ExtraUI.randomProblem.setBackground(buttonColor);
        } else if(Community.extraDisplayID == 1) {
            ExtraUI.functions.setBackground(buttonColor);
            ExtraUI.launchers.setBackground(buttonColor);
            ExtraUI.randomProblem.setBackground(backgroundColor);
        } else if(Community.extraDisplayID == 2) {
            ExtraUI.functions.setBackground(backgroundColor);
            ExtraUI.launchers.setBackground(buttonColor);
            ExtraUI.randomProblem.setBackground(buttonColor);
        }

        if(Community.minecraftListenFlushSpeedLevels == 0) {
            ExtraUI.Level0OfMLSL.setBackground(backgroundColor);
            ExtraUI.Level1OfMLSL.setBackground(buttonColor);
        } else if(Community.minecraftListenFlushSpeedLevels == 1) {
            ExtraUI.Level0OfMLSL.setBackground(buttonColor);
            ExtraUI.Level1OfMLSL.setBackground(backgroundColor);
        }

        MchUI.tips.setForeground(foregroundColor);
        MchUI.input_Command.setForeground(foregroundColor);
        MchUI.menu.setForeground(foregroundColor);
        MchUI.command1.setForeground(foregroundColor);
        MchUI.switchTip.setForeground(foregroundColor);

        exit.buttonEXIT.setForeground(foregroundColor);
        exit.buttonEXITNot.setForeground(foregroundColor);
        exit.jTextArea.setForeground(foregroundColor);

        MenuUI2.User_Color.setForeground(foregroundColor);
        MenuUI2.White.setForeground(foregroundColor);
        MenuUI2.Black.setForeground(foregroundColor);
        MenuUI2.Color.setForeground(foregroundColor);
        MenuUI2.PATH.setForeground(foregroundColor);
        MenuUI2.Color.setForeground(foregroundColor);
        MenuUI2.Black.setForeground(foregroundColor);
        MenuUI2.White.setForeground(foregroundColor);
        MenuUI2.Language.setForeground(foregroundColor);
        MenuUI2.Chinese.setForeground(foregroundColor);
        MenuUI2.English.setForeground(foregroundColor);
        MenuUI2.exButton.setForeground(foregroundColor);
        MenuUI2.exButtonExit.setForeground(foregroundColor);
        MenuUI2.exButtonNarrow.setForeground(foregroundColor);
        MenuUI2.fastLoad.setForeground(foregroundColor);
        MenuUI2.fastLoadNo.setForeground(foregroundColor);
        MenuUI2.fastLoadYes.setForeground(foregroundColor);
        MenuUI2.versionInfo.setForeground(foregroundColor);
        MenuUI2.onTops.setForeground(foregroundColor);
        MenuUI2.onTop.setForeground(foregroundColor);
        MenuUI2.noOnTop.setForeground(foregroundColor);
        MenuUI2.deleteData.setForeground(foregroundColor);
        MenuUI.helps.setForeground(foregroundColor);
        MenuUI2.Hades.setForeground(foregroundColor);
        MenuUI.user.setForeground(foregroundColor);
        MenuUI.gayhub.setForeground(foregroundColor);
        MenuUI2.checkUPD.setForeground(foregroundColor);
        MenuUI2.checkReturn.setForeground(foregroundColor);
        MenuUI.restart.setForeground(foregroundColor);
        MenuUI.settings.setForeground(foregroundColor);
        MenuUI.functions.setForeground(foregroundColor);
        MenuUI.feedback.setForeground(foregroundColor);
        MenuUI.announcement.setForeground(foregroundColor);
        MenuUI2.invalidCommand.setForeground(foregroundColor);
        MenuUI2.showInvalidCommand.setForeground(foregroundColor);
        MenuUI2.noShowInvalidCommand.setForeground(foregroundColor);

        MenuUI2.saveCacheOrNot.setForeground(foregroundColor);
        MenuUI2.saveCache.setForeground(foregroundColor);
        MenuUI2.notSaveCache.setForeground(foregroundColor);
        MenuUI2.saveErrorLogsOrNot.setForeground(foregroundColor);
        MenuUI2.saveErrorLogs.setForeground(foregroundColor);
        MenuUI2.notSaveErrorLogs.setForeground(foregroundColor);
        MenuUI2.saveRunLogsOrNot.setForeground(foregroundColor);
        MenuUI2.saveRunLogs.setForeground(foregroundColor);
        MenuUI2.notSaveRunLogs.setForeground(foregroundColor);
        MenuUI2.autoUpdOrNot.setForeground(foregroundColor);
        MenuUI2.autoUPD.setForeground(foregroundColor);
        MenuUI2.noAutoUPD.setForeground(foregroundColor);
        MenuUI2.saveHistoryOrNot.setForeground(foregroundColor);
        MenuUI2.saveAllHistory.setForeground(foregroundColor);
        MenuUI2.saveHistory.setForeground(foregroundColor);
        MenuUI2.notSaveHistory.setForeground(foregroundColor);
        MenuUI2.setting_run.setForeground(foregroundColor);
        MenuUI2.setting_display.setForeground(foregroundColor);
        MenuUI2.LanguageAuto.setForeground(foregroundColor);
        MenuUI2.colorAuto.setForeground(foregroundColor);
        MenuUI2.setting_upd.setForeground(foregroundColor);
        MenuUI2.updateInfo.setForeground(foregroundColor);
        MenuUI2.aboutMCH.setForeground(foregroundColor);
        MenuUI2.PATH.setForeground(foregroundColor);
        MenuUI2.showDir.setForeground(foregroundColor);
        MenuUI2.versionInfo.setForeground(foregroundColor);
        MenuUI2.setting_info.setForeground(foregroundColor);
        MenuUI2.aboutDevelopOfMCH.setForeground(foregroundColor);
        MenuUI2.Developers.setForeground(foregroundColor);
        MenuUI2.setting_command.setForeground(foregroundColor);
        MenuUI2.showCommandsMethod.setForeground(foregroundColor);
        MenuUI2.firstBedrock.setForeground(foregroundColor);
        MenuUI2.firstJava.setForeground(foregroundColor);
        MenuUI2.iniFinished.setForeground(foregroundColor);
        MenuUI2.iniHelper.setForeground(foregroundColor);

        ExtraUI.functionEdit.setForeground(foregroundColor);

        ExtraUI.McBe.setForeground(foregroundColor);
        ExtraUI.turnOnMcBe.setForeground(foregroundColor);
        ExtraUI.turnOffMcBe.setForeground(foregroundColor);
        ExtraUI.McBeStatus.setForeground(foregroundColor);
        ExtraUI.randomProblem.setForeground(foregroundColor);
        ExtraUI.launchers.setForeground(foregroundColor);
        ExtraUI.functions.setForeground(foregroundColor);
        ExtraUI.saveFunc.setForeground(foregroundColor);
        ExtraUI.Level0OfMLSL.setForeground(foregroundColor);
        ExtraUI.Level1OfMLSL.setForeground(foregroundColor);
        ExtraUI.minecraftListenSpeedLevel.setForeground(foregroundColor);
    }

    public static void hadesColor() {

        MchUI.jFrame.getContentPane().setBackground(randomColor.getColor());
        MenuUI.jFrame.getContentPane().setBackground(randomColor.getColor());
        MenuUI2.jFrame.getContentPane().setBackground(randomColor.getColor());
        exit.jFrame.getContentPane().setBackground(randomColor.getColor());
        ExtraUI.jFrame.getContentPane().setBackground(randomColor.getColor());

        MchUI.tips.setBackground(randomColor.getColor());
        MchUI.input_Command.setBackground(randomColor.getColor());
        MchUI.menu.setBackground(randomColor.getColor());
        MchUI.command1.setBackground(randomColor.getColor());

        exit.buttonEXIT.setBackground(randomColor.getColor());
        exit.buttonEXITNot.setBackground(randomColor.getColor());
        exit.jTextArea.setBackground(randomColor.getColor());

        MenuUI2.White.setBackground(randomColor.getColor());
        MenuUI2.User_Color.setBackground(randomColor.getColor());
        MenuUI2.Color.setBackground(randomColor.getColor());
        MenuUI2.Black.setBackground(randomColor.getColor());
        MenuUI2.White.setBackground(randomColor.getColor());
        MenuUI2.deleteData.setBackground(randomColor.getColor());
        MenuUI.helps.setBackground(randomColor.getColor());
        MenuUI2.Hades.setBackground(randomColor.getColor());
        MenuUI2.Chinese.setBackground(randomColor.getColor());
        MenuUI2.English.setBackground(randomColor.getColor());
        MenuUI2.onTop.setBackground(randomColor.getColor());
        MenuUI2.noOnTop.setBackground(randomColor.getColor());
        MenuUI2.exButtonNarrow.setBackground(randomColor.getColor());
        MenuUI2.exButtonExit.setBackground(randomColor.getColor());
        MenuUI2.fastLoadYes.setBackground(randomColor.getColor());
        MenuUI2.fastLoadNo.setBackground(randomColor.getColor());
        MenuUI.user.setBackground(randomColor.getColor());
        MenuUI2.checkUPD.setBackground(randomColor.getColor());
        MenuUI2.checkReturn.setBackground(randomColor.getColor());
        MenuUI.restart.setBackground(randomColor.getColor());
        MenuUI.settings.setBackground(randomColor.getColor());
        MenuUI.functions.setBackground(randomColor.getColor());
        MenuUI.gayhub.setBackground(randomColor.getColor());
        MenuUI.feedback.setBackground(randomColor.getColor());
        MenuUI2.setting_upd.setBackground(randomColor.getColor());
        MenuUI2.updateInfo.setBackground(randomColor.getColor());
        MenuUI2.showDir.setBackground(randomColor.getColor());
        MenuUI2.versionInfo.setBackground(randomColor.getColor());
        MenuUI.announcement.setBackground(randomColor.getColor());

        MenuUI2.saveCache.setBackground(randomColor.getColor());
        MenuUI2.notSaveCache.setBackground(randomColor.getColor());
        MenuUI2.saveErrorLogs.setBackground(randomColor.getColor());
        MenuUI2.notSaveErrorLogs.setBackground(randomColor.getColor());
        MenuUI2.saveRunLogs.setBackground(randomColor.getColor());
        MenuUI2.notSaveRunLogs.setBackground(randomColor.getColor());
        MenuUI2.autoUPD.setBackground(randomColor.getColor());
        MenuUI2.noAutoUPD.setBackground(randomColor.getColor());
        MenuUI2.saveAllHistory.setBackground(randomColor.getColor());
        MenuUI2.saveHistory.setBackground(randomColor.getColor());
        MenuUI2.notSaveHistory.setBackground(randomColor.getColor());
        MenuUI2.setting_run.setBackground(randomColor.getColor());
        MenuUI2.setting_display.setBackground(randomColor.getColor());
        MenuUI2.LanguageAuto.setBackground(randomColor.getColor());
        MenuUI2.colorAuto.setBackground(randomColor.getColor());
        MenuUI2.Developers.setBackground(randomColor.getColor());
        MenuUI2.setting_info.setBackground(randomColor.getColor());
        MenuUI2.setting_command.setBackground(randomColor.getColor());
        MenuUI2.showInvalidCommand.setBackground(randomColor.getColor());
        MenuUI2.noShowInvalidCommand.setBackground(randomColor.getColor());
        MenuUI2.firstBedrock.setBackground(randomColor.getColor());
        MenuUI2.firstJava.setBackground(randomColor.getColor());

        ExtraUI.launchers.setBackground(randomColor.getColor());
        ExtraUI.randomProblem.setBackground(randomColor.getColor());

        ExtraUI.functionEdit.setBackground(randomColor.getColor());

        ExtraUI.turnOnMcBe.setBackground(randomColor.getColor());
        ExtraUI.turnOffMcBe.setBackground(randomColor.getColor());
        ExtraUI.McBeStatus.setBackground(randomColor.getColor());
        ExtraUI.functions.setBackground(randomColor.getColor());
        ExtraUI.saveFunc.setBackground(randomColor.getColor());
        ExtraUI.Level0OfMLSL.setBackground(randomColor.getColor());
        ExtraUI.Level1OfMLSL.setBackground(randomColor.getColor());


        MchUI.tips.setForeground(randomColor.getColor());
        MchUI.input_Command.setForeground(randomColor.getColor());
        MchUI.menu.setForeground(randomColor.getColor());
        MchUI.command1.setForeground(randomColor.getColor());

        exit.buttonEXIT.setForeground(randomColor.getColor());
        exit.buttonEXITNot.setForeground(randomColor.getColor());
        exit.jTextArea.setForeground(randomColor.getColor());

        MenuUI2.User_Color.setForeground(randomColor.getColor());
        MenuUI2.White.setForeground(randomColor.getColor());
        MenuUI2.Black.setForeground(randomColor.getColor());
        MenuUI2.Color.setForeground(randomColor.getColor());
        MenuUI2.PATH.setForeground(randomColor.getColor());
        MenuUI2.Color.setForeground(randomColor.getColor());
        MenuUI2.Black.setForeground(randomColor.getColor());
        MenuUI2.White.setForeground(randomColor.getColor());
        MenuUI2.Language.setForeground(randomColor.getColor());
        MenuUI2.Chinese.setForeground(randomColor.getColor());
        MenuUI2.English.setForeground(randomColor.getColor());
        MenuUI2.exButton.setForeground(randomColor.getColor());
        MenuUI2.exButtonExit.setForeground(randomColor.getColor());
        MenuUI2.exButtonNarrow.setForeground(randomColor.getColor());
        MenuUI2.fastLoad.setForeground(randomColor.getColor());
        MenuUI2.fastLoadNo.setForeground(randomColor.getColor());
        MenuUI2.fastLoadYes.setForeground(randomColor.getColor());
        MenuUI2.versionInfo.setForeground(randomColor.getColor());
        MenuUI2.onTops.setForeground(randomColor.getColor());
        MenuUI2.onTop.setForeground(randomColor.getColor());
        MenuUI2.noOnTop.setForeground(randomColor.getColor());
        MenuUI2.deleteData.setForeground(randomColor.getColor());
        MenuUI.helps.setForeground(randomColor.getColor());
        MenuUI2.Hades.setForeground(randomColor.getColor());
        MenuUI.user.setForeground(randomColor.getColor());
        MenuUI2.checkUPD.setForeground(randomColor.getColor());
        MenuUI2.checkReturn.setForeground(randomColor.getColor());
        MenuUI.restart.setForeground(randomColor.getColor());
        MenuUI.settings.setForeground(randomColor.getColor());
        MenuUI.feedback.setForeground(randomColor.getColor());
        MenuUI.announcement.setForeground(randomColor.getColor());

        MenuUI2.saveCacheOrNot.setForeground(randomColor.getColor());
        MenuUI2.saveCache.setForeground(randomColor.getColor());
        MenuUI2.notSaveCache.setForeground(randomColor.getColor());
        MenuUI2.saveErrorLogsOrNot.setForeground(randomColor.getColor());
        MenuUI2.saveErrorLogs.setForeground(randomColor.getColor());
        MenuUI2.notSaveErrorLogs.setForeground(randomColor.getColor());
        MenuUI2.saveRunLogsOrNot.setForeground(randomColor.getColor());
        MenuUI2.saveRunLogs.setForeground(randomColor.getColor());
        MenuUI2.notSaveRunLogs.setForeground(randomColor.getColor());
        MenuUI2.autoUpdOrNot.setForeground(randomColor.getColor());
        MenuUI2.autoUPD.setForeground(randomColor.getColor());
        MenuUI2.noAutoUPD.setForeground(randomColor.getColor());
        MenuUI2.saveHistoryOrNot.setForeground(randomColor.getColor());
        MenuUI2.saveAllHistory.setForeground(randomColor.getColor());
        MenuUI2.saveHistory.setForeground(randomColor.getColor());
        MenuUI2.notSaveHistory.setForeground(randomColor.getColor());
        MenuUI2.setting_run.setForeground(randomColor.getColor());
        MenuUI2.setting_display.setForeground(randomColor.getColor());
        MenuUI2.LanguageAuto.setForeground(randomColor.getColor());
        MenuUI2.colorAuto.setForeground(randomColor.getColor());
        MenuUI2.setting_upd.setForeground(randomColor.getColor());
        MenuUI2.updateInfo.setForeground(randomColor.getColor());
        MenuUI2.aboutMCH.setForeground(randomColor.getColor());
        MenuUI2.PATH.setForeground(randomColor.getColor());
        MenuUI2.mchDirSize.setForeground(randomColor.getColor());
        MenuUI2.showDir.setForeground(randomColor.getColor());
        MenuUI2.versionInfo.setForeground(randomColor.getColor());
        MenuUI2.setting_info.setForeground(randomColor.getColor());
        MenuUI2.aboutDevelopOfMCH.setForeground(randomColor.getColor());
        MenuUI2.Developers.setForeground(randomColor.getColor());
        MenuUI2.setting_command.setForeground(randomColor.getColor());
        MenuUI2.invalidCommand.setForeground(randomColor.getColor());
        MenuUI2.showCommandsMethod.setForeground(randomColor.getColor());
        MenuUI2.showInvalidCommand.setForeground(randomColor.getColor());
        MenuUI2.noShowInvalidCommand.setForeground(randomColor.getColor());
        MenuUI2.firstBedrock.setForeground(randomColor.getColor());
        MenuUI2.firstJava.setForeground(randomColor.getColor());

        ExtraUI.functionEdit.setBackground(randomColor.getColor());

        ExtraUI.McBe.setForeground(randomColor.getColor());
        ExtraUI.turnOnMcBe.setForeground(randomColor.getColor());
        ExtraUI.turnOffMcBe.setForeground(randomColor.getColor());
        ExtraUI.McBeStatus.setForeground(randomColor.getColor());
        ExtraUI.randomProblem.setForeground(randomColor.getColor());
        ExtraUI.launchers.setForeground(randomColor.getColor());
        ExtraUI.functions.setForeground(randomColor.getColor());
        ExtraUI.saveFunc.setForeground(randomColor.getColor());
        ExtraUI.minecraftListenSpeedLevel.setForeground(randomColor.getColor());
        ExtraUI.Level0OfMLSL.setForeground(randomColor.getColor());
        ExtraUI.Level1OfMLSL.setForeground(randomColor.getColor());
    }

    public static void colorCode() {

        int selectLength;
        boolean setCodeColor = true;
        try {
            selectLength = MchUI.input_Command.getSelectedText().length();
            if(MchUI.input_Command.getSelectedText().isEmpty()) {
                selectLength = 0;
            }

            if((selectLength > 0)) {
                setCodeColor = false;
            }
        } catch (Exception ignored) {

        }

        if(MchUI.input_Command.getText().contains("§")) {
            if(setCodeColor) {
                try {
                    String colorCode = "§";

                    String s = MchUI.input_Command.getText();

                    if(s.contains(colorCode)) {
                        Document doc = MchUI.input_Command.getDocument();
                        StyleContext sc = StyleContext.getDefaultStyleContext();
                        StyleContext sc_normal = StyleContext.getDefaultStyleContext();
                        AttributeSet asset = null;

                        AttributeSet asset_normal = null;
                        if(Community.ColorID == 0) {
                            asset_normal = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);
                        } else if(Community.ColorID == 1) {
                            asset_normal = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.white);
                        }
                        String command_text = MchUI.input_Command.getText();
                        int inputPoint = MchUI.input_Command.getCaretPosition();

                        doc.remove(0, command_text.length());

                        doc.insertString(0, command_text, asset_normal);


                        String inp = MchUI.input_Command.getText();
                        int point = inp.length();

                        while(inp.contains("§")) {
                            boolean noHaveThisColor = false;

                            inp = inp.substring(inp.indexOf("§"));

                            int tabPoint = point - inp.length();
                            //                        System.out.println(tabPoint);

                            if(inp.indexOf("§0") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);
                            } else if(inp.indexOf("§1") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(0, 0, 170));
                            } else if(inp.indexOf("§2") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(0, 170, 0));
                            } else if(inp.indexOf("§3") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(0, 170, 170));
                            } else if(inp.indexOf("§4") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(170, 0, 0));
                            } else if(inp.indexOf("§5") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(170, 0, 170));
                            } else if(inp.indexOf("§6") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 170, 0));
                            } else if(inp.indexOf("§7") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(170, 170, 170));
                            } else if(inp.indexOf("§8") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(85, 85, 85));
                            } else if(inp.indexOf("§9") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(85, 85, 255));
                            } else if(inp.indexOf("§a") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(85, 255, 85));
                            } else if(inp.indexOf("§b") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(85, 255, 255));
                            } else if(inp.indexOf("§c") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 85, 85));
                            } else if(inp.indexOf("§d") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 85, 255));
                            } else if(inp.indexOf("§e") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 255, 85));
                            } else if(inp.indexOf("§f") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 255, 255));
                            } else if(inp.indexOf("§g") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(55, 53, 1));
                            } else if(inp.indexOf("§r") == 0) {
                                if(Community.ColorID == 0) {
                                    asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);
                                } else if(Community.ColorID == 1) {
                                    asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.white);
                                }
                            } else if(inp.indexOf("§§") == 0) {
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 255, 255, 125));
                            } else {
                                noHaveThisColor = true;
                                asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 255, 255, 125));
                            }

                            //                        System.out.println(inp);

                            doc.remove(tabPoint, inp.length());
                            doc.insertString(tabPoint, inp, asset);
                            MchUI.input_Command.setCaretPosition(inputPoint);
                            if(noHaveThisColor) {

                                if(Community.ColorID == 0) {
                                    asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);
                                } else if(Community.ColorID == 1) {
                                    asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.white);
                                }

                                doc.remove(tabPoint + 2, inp.length() - 2);
                                doc.insertString(tabPoint + 2, inp.substring(2), asset);
                                MchUI.input_Command.setCaretPosition(inputPoint);
                            }

                            inp = inp.substring(inp.indexOf("§") + 1);
                        }
                    } else {
                        Document doc = MchUI.input_Command.getDocument();
                        StyleContext sc_normal = StyleContext.getDefaultStyleContext();
                        AttributeSet asset_normal = null;
                        if(Community.ColorID == 0) {
                            asset_normal = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);
                        } else if(Community.ColorID == 1) {
                            asset_normal = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.white);
                        }
                        int inputPoint = MchUI.input_Command.getCaretPosition();
                        String command_text = MchUI.input_Command.getText();
                        doc.remove(0, MchUI.input_Command.getText().length());
                        MchUI.input_Command.setText("");
                        doc.insertString(0, command_text, asset_normal);
                        MchUI.input_Command.setCaretPosition(inputPoint);
                    }
                } catch (Exception ignored) {

                }
            }
        }
    }

    public static void settingsDisplay() {
        MenuUI2.saveCacheOrNot.setVisible(false);
        MenuUI2.saveCache.setVisible(false);
        MenuUI2.notSaveCache.setVisible(false);
        MenuUI2.saveErrorLogsOrNot.setVisible(false);
        MenuUI2.saveErrorLogs.setVisible(false);
        MenuUI2.notSaveErrorLogs.setVisible(false);
        MenuUI2.saveRunLogsOrNot.setVisible(false);
        MenuUI2.saveRunLogs.setVisible(false);
        MenuUI2.notSaveRunLogs.setVisible(false);
        MenuUI2.autoUpdOrNot.setVisible(false);
        MenuUI2.autoUPD.setVisible(false);
        MenuUI2.noAutoUPD.setVisible(false);
        MenuUI2.saveHistoryOrNot.setVisible(false);
        MenuUI2.saveHistory.setVisible(false);
        MenuUI2.saveAllHistory.setVisible(false);
        MenuUI2.notSaveHistory.setVisible(false);
        MenuUI2.Color.setVisible(false);
        MenuUI2.Black.setVisible(false);
        MenuUI2.White.setVisible(false);
        MenuUI2.Hades.setVisible(false);
        MenuUI2.User_Color.setVisible(false);
        MenuUI2.colorAuto.setVisible(false);
        MenuUI2.exButton.setVisible(false);
        MenuUI2.exButtonExit.setVisible(false);
        MenuUI2.exButtonNarrow.setVisible(false);
        MenuUI2.Language.setVisible(false);
        MenuUI2.Chinese.setVisible(false);
        MenuUI2.LanguageAuto.setVisible(false);
        MenuUI2.English.setVisible(false);
        MenuUI2.fastLoad.setVisible(false);
        MenuUI2.fastLoadYes.setVisible(false);
        MenuUI2.fastLoadNo.setVisible(false);
        MenuUI2.onTops.setVisible(false);
        MenuUI2.onTop.setVisible(false);
        MenuUI2.noOnTop.setVisible(false);
        MenuUI2.checkUPD.setVisible(false);
        MenuUI2.checkReturn.setVisible(false);
        MenuUI2.updateInfo.setVisible(false);
        MenuUI2.aboutMCH.setVisible(false);
        MenuUI2.PATH.setVisible(false);
        MenuUI2.mchDirSize.setVisible(false);
        MenuUI2.showDir.setVisible(false);
        MenuUI2.versionInfo.setVisible(false);
        MenuUI2.aboutDevelopOfMCH.setVisible(false);
        MenuUI2.Developers.setVisible(false);
        MenuUI2.deleteData.setVisible(false);
        MenuUI2.invalidCommand.setVisible(false);
        MenuUI2.showInvalidCommand.setVisible(false);
        MenuUI2.noShowInvalidCommand.setVisible(false);
        MenuUI2.showCommandsMethod.setVisible(false);
        MenuUI2.firstBedrock.setVisible(false);
        MenuUI2.firstJava.setVisible(false);
        switch(Community.setsDisplayID) {
            case 0 -> {
                MenuUI2.saveCacheOrNot.setVisible(true);
                MenuUI2.saveCache.setVisible(true);
                MenuUI2.notSaveCache.setVisible(true);
                MenuUI2.saveErrorLogsOrNot.setVisible(true);
                MenuUI2.saveErrorLogs.setVisible(true);
                MenuUI2.notSaveErrorLogs.setVisible(true);
                MenuUI2.saveRunLogsOrNot.setVisible(true);
                MenuUI2.saveRunLogs.setVisible(true);
                MenuUI2.notSaveRunLogs.setVisible(true);
                MenuUI2.autoUpdOrNot.setVisible(true);
                MenuUI2.autoUPD.setVisible(true);
                MenuUI2.noAutoUPD.setVisible(true);
                MenuUI2.saveHistoryOrNot.setVisible(true);
                MenuUI2.saveHistory.setVisible(true);
                MenuUI2.saveAllHistory.setVisible(true);
                MenuUI2.notSaveHistory.setVisible(true);
                MenuUI2.exButton.setVisible(true);
                MenuUI2.exButtonExit.setVisible(true);
                MenuUI2.exButtonNarrow.setVisible(true);
                MenuUI2.fastLoad.setVisible(true);
                MenuUI2.fastLoadYes.setVisible(true);
                MenuUI2.fastLoadNo.setVisible(true);
            }
            case 1 -> {
                MenuUI2.Color.setVisible(true);
                MenuUI2.Black.setVisible(true);
                MenuUI2.White.setVisible(true);
                MenuUI2.Hades.setVisible(true);
                MenuUI2.User_Color.setVisible(true);
                MenuUI2.colorAuto.setVisible(true);
                MenuUI2.Language.setVisible(true);
                MenuUI2.Chinese.setVisible(true);
                MenuUI2.English.setVisible(true);
                MenuUI2.LanguageAuto.setVisible(true);
                MenuUI2.onTops.setVisible(true);
                MenuUI2.onTop.setVisible(true);
                MenuUI2.noOnTop.setVisible(true);
            }
            case 2 -> {
                MenuUI2.checkUPD.setVisible(true);
                MenuUI2.checkReturn.setVisible(true);
                MenuUI2.updateInfo.setVisible(true);
            }
            case 3 -> {
                MenuUI2.aboutMCH.setVisible(true);
                MenuUI2.PATH.setVisible(true);
                MenuUI2.mchDirSize.setVisible(true);
                MenuUI2.showDir.setVisible(true);
                MenuUI2.versionInfo.setVisible(true);
                MenuUI2.aboutDevelopOfMCH.setVisible(true);
                MenuUI2.Developers.setVisible(true);
                MenuUI2.deleteData.setVisible(true);
            }
            case 4 -> {
                MenuUI2.invalidCommand.setVisible(true);
                MenuUI2.showInvalidCommand.setVisible(true);
                MenuUI2.noShowInvalidCommand.setVisible(true);
                MenuUI2.showCommandsMethod.setVisible(true);
                MenuUI2.firstBedrock.setVisible(true);
                MenuUI2.firstJava.setVisible(true);
            }
        }
    }

    public static void extraDisplay() {
        ExtraUI.McBe.setVisible(false);
        ExtraUI.turnOnMcBe.setVisible(false);
        ExtraUI.turnOffMcBe.setVisible(false);
        ExtraUI.McBeStatus.setVisible(false);
        ExtraUI.functionEdit.setVisible(false);
        ExtraUI.saveFunc.setVisible(false);
        ExtraUI.minecraftListenSpeedLevel.setVisible(false);
        ExtraUI.Level0OfMLSL.setVisible(false);
        ExtraUI.Level1OfMLSL.setVisible(false);
        if(Community.extraDisplayID == 0) {
            ExtraUI.McBe.setVisible(true);
            ExtraUI.turnOnMcBe.setVisible(true);
            ExtraUI.turnOffMcBe.setVisible(true);
            ExtraUI.McBeStatus.setVisible(true);
            ExtraUI.minecraftListenSpeedLevel.setVisible(true);
            ExtraUI.Level0OfMLSL.setVisible(true);
            ExtraUI.Level1OfMLSL.setVisible(true);
        } else if(Community.extraDisplayID == 1) {

        } else if(Community.extraDisplayID == 2) {
            if(Community.functionEditing) {
                ExtraUI.saveFunc.setVisible(true);
                ExtraUI.functionEdit.setEditable(true);
            } else {
                ExtraUI.functionEdit.setEditable(false);
            }
            ExtraUI.functionEdit.setVisible(true);
        }
    }

    @Override
    public void run() {
        int counts = 0;
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadSucceed: color\n",lang.get("loading_color_succeed"),new Color(99,128,87));
        while(! Errors.CannotHandle) {
            counts++;
            try {
                if(! Community.isDaemons) {
                    if(Community.ColorSetID != 3) {
                        Color();
                    } else {
                        //
                    }
                    colorCode();
                    if(counts >= 120) {
                        settingsDisplay();
                        extraDisplay();
                        counts = 0;
                    }
                    Thread.sleep(15);
                } else {
                    counts = 100;
                    Thread.sleep(500);
                }
            } catch (Exception ignored) {
            }
        }
    }
}

class randomColor {
    public static Color getColor() {
        return new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
    }
}