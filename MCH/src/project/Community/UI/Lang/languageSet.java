package project.Community.UI.Lang;

import project.Community.Command.ini;
import project.Community.Community;
import project.Community.Events.Errors;
import project.Community.Events.LoadAssembly;
import project.Community.Times.times;
import project.Community.UI.*;
import project.Community.lib.Resources;

import java.awt.*;

import static project.Community.Community.ver;
import static project.Community.lib.Resources.initLanguage.*;

public class languageSet extends Thread {
    public static String c = "";
    public static String type = "";

    public static void Language() {
        MchUI.menu.setText(lang.get("menuButton"));
        MchUI.jFrame.setTitle(lang.get("mch"));

        MenuUI.jFrame.setTitle(lang.get("menu"));

        exit.jTextArea.setText(lang.get(".exit"));
        exit.buttonEXIT.setText(lang.get("exit"));
        exit.buttonEXITNot.setText(lang.get("exitNot"));

        MenuUI2.PATH.setText(lang.get("configPath") + ini.path);
        MenuUI2.exButton.setText(lang.get("exButton"));
        MenuUI2.exButtonExit.setText(lang.get("exitMCH"));
        MenuUI2.exButtonNarrow.setText(lang.get("smallerMCH"));
        MenuUI2.Color.setText(lang.get("colorSet"));
        MenuUI2.Black.setText(lang.get("black"));
        MenuUI2.White.setText(lang.get("white"));
        MenuUI2.Language.setText(lang.get("languageSet"));
        MenuUI2.Chinese.setText(lang.get("chinese"));
        MenuUI2.English.setText(lang.get("english"));
        MenuUI2.fastLoad.setText(lang.get("fastLoad"));
        MenuUI2.fastLoadNo.setText(lang.get("disable"));
        MenuUI2.fastLoadYes.setText(lang.get("enable"));
        MenuUI2.versionInfo.setText(lang.get("version") + ver + "\n" + lang.get("versionID") + Community.verID + "\n");
        MenuUI2.onTops.setText(lang.get("onTop"));
        MenuUI2.onTop.setText(lang.get("enable"));
        MenuUI2.noOnTop.setText(lang.get("disable"));
        MenuUI2.deleteData.setText(lang.get("deleteData"));
        MenuUI.helps.setText(lang.get("help"));
        //            MenuUI2.Hades.setText("阴间");
        MenuUI.user.setText(lang.get("agreement"));
        MenuUI.restart.setText(lang.get("restartMCH"));
        MenuUI.settings.setText(lang.get("settings"));

        MenuUI2.setting_run.setText(lang.get("run"));
        MenuUI2.setting_display.setText(lang.get("display"));

        if (Community.setsDisplayID == 0) {
            MenuUI2.jFrame.setTitle(lang.get("run_title"));
        } else if (Community.setsDisplayID == 1) {
            MenuUI2.jFrame.setTitle(lang.get("display-title"));
        } else if (Community.setsDisplayID == 2) {
            MenuUI2.jFrame.setTitle(lang.get("upd_title"));
        } else if (Community.setsDisplayID == 3) {
            MenuUI2.jFrame.setTitle(lang.get("info_title"));
        } else if (Community.setsDisplayID == 4) {
            MenuUI2.jFrame.setTitle(lang.get("command_title"));
        }


        MenuUI.functions.setText(lang.get("functions"));
        MenuUI.feedback.setText(lang.get("feedback"));
        MenuUI2.saveCacheOrNot.setText(lang.get("save_cache"));
        MenuUI2.saveCache.setText(lang.get("save"));
        MenuUI2.notSaveCache.setText(lang.get("delete"));
        MenuUI2.saveErrorLogsOrNot.setText(lang.get("save_err"));
        MenuUI2.saveErrorLogs.setText(lang.get("save"));
        MenuUI2.notSaveErrorLogs.setText(lang.get("delete"));
        MenuUI2.saveRunLogsOrNot.setText(lang.get("runLog"));
        MenuUI2.saveRunLogs.setText(lang.get("save"));
        MenuUI2.notSaveRunLogs.setText(lang.get("delete"));
        MenuUI2.autoUpdOrNot.setText(lang.get("autoUPD"));
        MenuUI2.autoUPD.setText(lang.get("enable"));
        MenuUI2.noAutoUPD.setText(lang.get("disable"));
        MenuUI2.saveHistoryOrNot.setText(lang.get("history"));
        MenuUI2.saveHistory.setText(lang.get("save_some"));
        MenuUI2.saveAllHistory.setText(lang.get("save_all"));
        MenuUI2.notSaveHistory.setText(lang.get("delete"));
        MenuUI2.LanguageAuto.setText(lang.get("auto"));
        MenuUI2.setting_upd.setText(lang.get("UPD"));
        MenuUI2.setting_info.setText(lang.get("about"));
        MenuUI2.aboutMCH.setText(lang.get("aboutMCH"));
        MenuUI2.showDir.setText(lang.get("viewFile"));
        MenuUI2.aboutDevelopOfMCH.setText(lang.get("developer"));
        MenuUI2.Developers.setText(lang.get("aboutDeveloper"));
        MenuUI2.setting_command.setText(lang.get("setting_command"));
        MenuUI2.invalidCommand.setText(lang.get("invalidCommand"));
        MenuUI2.showInvalidCommand.setText(lang.get("showInvalidCommand"));
        MenuUI2.noShowInvalidCommand.setText(lang.get("hideInvalidCommand"));
        MenuUI2.showCommandsMethod.setText(lang.get("priority_display"));
        MenuUI2.firstBedrock.setText(lang.get("bedrock"));
        MenuUI2.firstJava.setText(lang.get("java"));
        MenuUI2.iniFinished.setText(lang.get("finish"));
        MenuUI2.iniHelper.setText(lang.get("ini_help"));
        MenuUI2.toWikiOrNot.setText(lang.get("toWikiOnOrOff"));
        MenuUI2.toWiki.setText(lang.get("toWikiOn"));
        MenuUI2.toWikiNot.setText(lang.get("toWikiOff"));

        if (Community.extraDisplayID == 0) {
            ExtraUI.jFrame.setTitle(lang.get("functions_launcher"));
        } else if (Community.extraDisplayID == 1) {
            ExtraUI.jFrame.setTitle(lang.get("functions_problems"));
        } else if (Community.extraDisplayID == 2) {
            ExtraUI.jFrame.setTitle("functions_FunctionEditor");
        }
        ExtraUI.McBe.setText(lang.get("mcBedrock"));
        ExtraUI.turnOnMcBe.setText(lang.get("run"));
        ExtraUI.launchers.setText(lang.get("launcher"));
        ExtraUI.turnOffMcBe.setText(lang.get("off"));
        ExtraUI.randomProblem.setText(lang.get("randomProblem"));
        ExtraUI.functions.setText("Function");
        ExtraUI.saveFunc.setText(lang.get("saveTo"));
        ExtraUI.minecraftListenSpeedLevel.setText(lang.get("flushSpeed"));
        ExtraUI.Level0OfMLSL.setText(lang.get("slow"));
        ExtraUI.Level1OfMLSL.setText(lang.get("fast"));

        loadingWindow.loadingTip.setText(lang.get("resource_loading"));
        loadingWindow.loadStatus.setText(lang.get("load_status"));

        if (Community.canUPD) {
            MenuUI2.checkUPD.setText(lang.get("UPD"));
        } else {
            MenuUI2.checkUPD.setText(lang.get("checkUPD"));
        }

        if (!Community.functionEditing) {
            ExtraUI.functionEdit.setText(lang.get("disableFunctionEdit"));
        }

        if (Errors.CannotHandle) {
            Errors.jTextArea.setText(lang.get("CannotHandleErr"));
        }
    }

    public static String getCommandWord(String word) {
        return commands.get(word);
    }

    @Override
    public void run() {
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadSucceed: language\n",lang.get("loading_lang_succeed"), new Color(99,128,87));
        while (!Errors.CannotHandle) {
            if (!Community.isDaemons) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Language();
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
