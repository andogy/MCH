package project.Community.UI.Lang;

import project.Community.Command.ini;
import project.Community.Community;
import project.Community.Events.Errors;
import project.Community.Events.LoadAssembly;
import project.Community.Times.times;
import project.Community.UI.*;

import static project.Community.Community.ver;
import static project.Community.UI.Lang.initLanguage.lang;

public class languageSet extends Thread {
    public static String c = "";
    public static String type = "";

    public static void Language() {
        //        init();

        //            ID=0为中文
        //        if (Community.LangID == 0) {
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
            MenuUI2.jFrame.setTitle("upd-title");
        } else if (Community.setsDisplayID == 3) {
            MenuUI2.jFrame.setTitle("info_title");
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

        if (Community.canUPD) {
            MenuUI2.checkUPD.setText(lang.get("UPD"));
        } else {
            MenuUI2.checkUPD.setText(lang.get("checkUPD"));
        }

        if (!Community.functionEditing) {
            ExtraUI.functionEdit.setText(lang.get("disableFunctionEdit"));
        }

        if (Errors.CannotHandle) {
            Errors.jTextArea.setText("""
                    MCH遇到了一个无法自动处理的错误
                    请将此错误反馈给开发人员,谢谢
                                        
                    正在尝试重启MCH
                                        
                    你可以去到MCH的目录查看错误信息""");
        }
        //            MenuUI2.Color.setText("Color:");
        //            MenuUI2.Black.setText("Black");
        //            MenuUI2.White.setText("White");
        //            MenuUI2.Language.setText("Language:");
        //            MenuUI2.Chinese.setText("Chinese");
        //            MenuUI2.English.setText("English");
        //            MenuUI2.fastLoad.setText("Fast Load:");
        //            MenuUI2.fastLoadNo.setText("Disable");
        //            MenuUI2.fastLoadYes.setText("Enable");
        //            MenuUI2.versionInfo.setText("Version: " + ver + "\n" +
        //                    "Version ID:" + Community.verID);
        //            MenuUI2.onTops.setText("onTop:");
        //            MenuUI2.onTop.setText("Enable");
        //            MenuUI2.noOnTop.setText("Disable");
        //            MenuUI2.deleteData.setText("Delete Data");
        //            MenuUI.helps.setText("Helps");
        //            MenuUI2.Hades.setText("Hades");
        //            MenuUI.user.setText("agreement");
        //            MenuUI.restart.setText("restart MCH");
        //            MenuUI.settings.setText("settings");
        //            MenuUI.functions.setText("functions");
        //            MenuUI.feedback.setText("feedback");
        //
        //
        //            MenuUI2.setting_run.setText("run");
        //            MenuUI2.setting_display.setText("display");
        //
        //            if (Community.setsDisplayID == 0) {
        //                MenuUI2.jFrame.setTitle("settings-run");
        //            } else if (Community.setsDisplayID == 1) {
        //                MenuUI2.jFrame.setTitle("settings-display");
        //            } else if (Community.setsDisplayID == 2) {
        //                MenuUI2.jFrame.setTitle("settings-update");
        //            } else if (Community.setsDisplayID == 3) {
        //                MenuUI2.jFrame.setTitle("settings-MCH info");
        //            }
        //
        //            MenuUI2.saveCacheOrNot.setText("save cache:");
        //            MenuUI2.saveCache.setText("save");
        //            MenuUI2.notSaveCache.setText("delete");
        //            MenuUI2.saveErrorLogsOrNot.setText("error log:");
        //            MenuUI2.saveErrorLogs.setText("save");
        //            MenuUI2.notSaveErrorLogs.setText("delete");
        //            MenuUI2.saveRunLogsOrNot.setText("run log:");
        //            MenuUI2.saveRunLogs.setText("save");
        //            MenuUI2.notSaveRunLogs.setText("delete");
        //            MenuUI2.autoUpdOrNot.setText("auto UPD");
        //            MenuUI2.autoUPD.setText("Enable");
        //            MenuUI2.noAutoUPD.setText("Disable");
        //            MenuUI2.saveHistoryOrNot.setText("history:");
        //            MenuUI2.saveHistory.setText();
        //            MenuUI2.saveAllHistory.setText("save all");
        //            MenuUI2.notSaveHistory.setText("delete");
        //            MenuUI2.LanguageAuto.setText("auto");
        //            MenuUI2.setting_upd.setText("update");
        //            MenuUI2.setting_info.setText("about");
        //            MenuUI2.aboutMCH.setText("about MCH:");
        //            MenuUI2.showDir.setText("show files");
        //            MenuUI2.aboutDevelopOfMCH.setText("about developer:");
        //            MenuUI2.Developers.setText("""
        //                    MCH have two developers,here is their contact:
        //                    "andogy"   can called "翅膀"(ChiBang)
        //                    contact at qq:2293332045
        //
        //                    "zhuaidadaya"   can called "草"(grass)
        //                    contact at qq or WeChat:
        //                    qq:1501917367
        //                    WeChat:zhuaidadaya""");
        //
        //            if (Community.extraDisplayID == 0) {
        //                ExtraUI.jFrame.setTitle("functions-launcher");
        //            } else if (Community.extraDisplayID == 1) {
        //                ExtraUI.jFrame.setTitle("functions-random problem");
        //            } else if (Community.extraDisplayID == 2) {
        //                ExtraUI.jFrame.setTitle("functions-function editor");
        //            }
        //
        //            ExtraUI.McBe.setText("MC bedrock:");
        //            ExtraUI.turnOnMcBe.setText("run");
        //            ExtraUI.turnOffMcBe.setText("off");
        //            ExtraUI.saveFunc.setText("save");
        //            ExtraUI.launchers.setText("launcher");
        //            ExtraUI.randomProblem.setText("problems");
        //            ExtraUI.functions.setText("Function");
        //            ExtraUI.saveFunc.setText("save to");
        //            ExtraUI.minecraftListenSpeedLevel.setText("flush speed:");
        //            ExtraUI.Level0OfMLSL.setText("slow");
        //            ExtraUI.Level1OfMLSL.setText("fast");
        //
        //            if (Community.canUPD) {
        //                MenuUI2.checkUPD.setText("UPD now");
        //            } else {
        //                MenuUI2.checkUPD.setText("Check UPD");
        //            }
        //
        //            if (!Community.functionEditing) {
        //                ExtraUI.functionEdit.setText("Function edit is no enable");
        //            }
        //
        //                        if (Errors.CannotHandle) {
        //            Errors.jTextArea.setText("""
        //                    There are some unhandled errors in the MCH
        //                    Please send there error to feedback,thanks you
        //
        //                    Trying restart the MCH
        //
        //                    You can go to the MCH directory to look the error info""");
        //                        }
        //        }
    }

    public static String getWord(String word) {
        return lang.get(word);
    }

    @Override
    public void run() {
        System.out.println("[" + times.format + "]\n" + "language:语言就绪");
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadSucceed: language\n");
        while (!Errors.CannotHandle) {
            if (!Community.isDaemons) {
                try {
                    Thread.sleep(15);
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
