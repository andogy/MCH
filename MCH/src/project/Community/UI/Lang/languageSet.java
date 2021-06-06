package project.Community.UI.Lang;

import project.Community.Command.ini;
import project.Community.Community;
import project.Community.Events.Errors;
import project.Community.Events.LoadAssembly;
import project.Community.Times.times;
import project.Community.UI.*;

import static project.Community.UI.Lang.initLanguage.languageMap;

import static project.Community.Community.ver;

public class languageSet extends Thread {
    public static String c = "";
    public static String type = "";

    public static void Language() {

        //            ID=0为中文
//        if (Community.LangID == 0) {
            MchUI.menu.setText(languageMap.get("menuButton"));
            MchUI.jFrame.setTitle(languageMap.get("mch"));

            MenuUI.jFrame.setTitle(languageMap.get("menu"));

            exit.jTextArea.setText(languageMap.get(".exit"));
            exit.buttonEXIT.setText(languageMap.get("exit"));
            exit.buttonEXITNot.setText(languageMap.get("exitNot"));

            MenuUI2.PATH.setText(languageMap.get("configPath") + ini.path);
            MenuUI2.exButton.setText(languageMap.get("exButton"));
            MenuUI2.exButtonExit.setText(languageMap.get("exitMCH"));
            MenuUI2.exButtonNarrow.setText(languageMap.get("smallerMCH"));
            MenuUI2.Color.setText("颜色:");
            MenuUI2.Black.setText("黑色");
            MenuUI2.White.setText("白色");
            MenuUI2.Language.setText("语言:");
            MenuUI2.Chinese.setText("中文");
            MenuUI2.English.setText("英文");
            MenuUI2.fastLoad.setText("快速加载:");
            MenuUI2.fastLoadNo.setText("禁用");
            MenuUI2.fastLoadYes.setText("启用");
            MenuUI2.versionInfo.setText("版本: " + ver + "\n" +
                    "版本ID:" + Community.verID + "\n");
            MenuUI2.onTops.setText("置于顶层:");
            MenuUI2.onTop.setText("启用");
            MenuUI2.noOnTop.setText("禁用");
            MenuUI2.deleteData.setText("删除数据");
            MenuUI.helps.setText("帮助");
            MenuUI2.Hades.setText("阴间");
            MenuUI.user.setText("用户协议");
            MenuUI.restart.setText("重启MCH");
            MenuUI.settings.setText("设置");

            MenuUI2.setting_run.setText("运行");
            MenuUI2.setting_display.setText("显示");

            if (Community.setsDisplayID == 0) {
                MenuUI2.jFrame.setTitle("设置-运行");
            } else if (Community.setsDisplayID == 1) {
                MenuUI2.jFrame.setTitle("设置-显示");
            } else if (Community.setsDisplayID == 2) {
                MenuUI2.jFrame.setTitle("设置-更新");
            } else if (Community.setsDisplayID == 3) {
                MenuUI2.jFrame.setTitle("设置-MCH详情");
            }


            MenuUI.functions.setText("功能");
            MenuUI.feedback.setText("反馈");
            MenuUI2.saveCacheOrNot.setText("保留缓存:");
            MenuUI2.saveCache.setText("保留");
            MenuUI2.notSaveCache.setText("删除");
            MenuUI2.saveErrorLogsOrNot.setText("错误日志:");
            MenuUI2.saveErrorLogs.setText("保存");
            MenuUI2.notSaveErrorLogs.setText("删除");
            MenuUI2.saveRunLogsOrNot.setText("运行日志");
            MenuUI2.saveRunLogs.setText("保存");
            MenuUI2.notSaveRunLogs.setText("删除");
            MenuUI2.autoUpdOrNot.setText("自动更新");
            MenuUI2.autoUPD.setText("启用");
            MenuUI2.noAutoUPD.setText("禁用");
            MenuUI2.saveHistoryOrNot.setText("历史纪录:");
            MenuUI2.saveHistory.setText("保存最近");
            MenuUI2.saveAllHistory.setText("保存");
            MenuUI2.notSaveHistory.setText("删除");
            MenuUI2.LanguageAuto.setText("自动");
            MenuUI2.setting_upd.setText("更新");
            MenuUI2.setting_info.setText("关于");
            MenuUI2.aboutMCH.setText("关于MCH:");
            MenuUI2.showDir.setText("查看文件");
            MenuUI2.aboutDevelopOfMCH.setText("关于开发者:");
            MenuUI2.Developers.setText("""
                    MCH有两位开发者,以下是他们的称呼以及联系方式:
                    开发者andogy   可以称呼为翅膀
                    通过qq与他联系:2293332045

                    开发者zhuaidadaya   可以称呼为草
                    通过qq或微信联系:
                    qq:1501917367
                    WeChat:zhuaidadaya""");

            if (Community.extraDisplayID == 0) {
                ExtraUI.jFrame.setTitle("功能-启动器");
            } else if (Community.extraDisplayID == 1) {
                ExtraUI.jFrame.setTitle("功能-随机问题");
            } else if (Community.extraDisplayID == 2) {
                ExtraUI.jFrame.setTitle("功能-Function编辑器");
            }
            ExtraUI.McBe.setText("MC基岩版:");
            ExtraUI.turnOnMcBe.setText("启动");
            ExtraUI.launchers.setText("启动器");
            ExtraUI.turnOffMcBe.setText("关闭");
            ExtraUI.randomProblem.setText("随机问题");
            ExtraUI.functions.setText("Function");
            ExtraUI.saveFunc.setText("保存至");
            ExtraUI.minecraftListenSpeedLevel.setText("刷新速度:");
            ExtraUI.Level0OfMLSL.setText("稍慢");
            ExtraUI.Level1OfMLSL.setText("最快");

            if (Community.canUPD) {
                MenuUI2.checkUPD.setText("更新");
            } else {
                MenuUI2.checkUPD.setText("检查更新");
            }

            if (!Community.functionEditing) {
                ExtraUI.functionEdit.setText("未启用Function编辑器 ");
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
//            MenuUI2.saveHistory.setText("save some");
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
