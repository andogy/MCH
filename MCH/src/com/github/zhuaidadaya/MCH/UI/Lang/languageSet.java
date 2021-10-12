package com.github.zhuaidadaya.MCH.UI.Lang;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.UI.*;
import com.github.zhuaidadaya.MCH.lib.json.JSONObject;

import java.util.HashMap;

import static com.github.zhuaidadaya.MCH.lib.Resources.initLanguage.lang;

public class languageSet extends Thread {
    public static String c = "";
    public static String type = "";

    public static HashMap<String, String> langFile = new HashMap<>();

    public static void Language() {
        try {
            MchUI.menu.setText(lang.get("menuButton"));
            MchUI.jFrame.setTitle(lang.get("mch"));

            MenuUI.jFrame.setTitle(lang.get("menu"));

            exit.jTextArea.setText(lang.get(".exit"));
            exit.buttonEXIT.setText(lang.get("exit"));
            exit.buttonEXITNot.setText(lang.get("exitNot"));

            MenuUI2.PATH.setText(lang.get("configPath") + Config.path);
            MenuUI2.exButton.setText(lang.get("exButton"));
            MenuUI2.exButtonExit.setText(lang.get("exitMCH"));
            MenuUI2.exButtonNarrow.setText(lang.get("smallerMCH"));
            MenuUI2.Color.setText(lang.get("colorSet"));
            MenuUI2.Black.setText(lang.get("black"));
            MenuUI2.White.setText(lang.get("white"));
            MenuUI2.Language.setText(lang.get("languageSet"));
            MenuUI2.Chinese_ZH.setText(lang.get("chinese"));
            MenuUI2.English.setText(lang.get("english"));
            MenuUI2.Chinese_TW.setText(lang.get("chinese_tw"));
            MenuUI2.fastLoad.setText(lang.get("fastLoad"));
            MenuUI2.fastLoadNo.setText(lang.get("disable"));
            MenuUI2.fastLoadYes.setText(lang.get("enable"));
            MenuUI2.versionInfo.setText(lang.get("version") + Community.ver + "\n" + lang.get("versionID") + Community.verID + "\n");
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

            if(Community.setsDisplayID == 0) {
                MenuUI2.jFrame.setTitle(lang.get("run_title"));
            } else if(Community.setsDisplayID == 1) {
                MenuUI2.jFrame.setTitle(lang.get("display-title"));
            } else if(Community.setsDisplayID == 2) {
                MenuUI2.jFrame.setTitle(lang.get("upd_title"));
            } else if(Community.setsDisplayID == 3) {
                MenuUI2.jFrame.setTitle(lang.get("info_title"));
            } else if(Community.setsDisplayID == 4) {
                MenuUI2.jFrame.setTitle(lang.get("command_title"));
            }


            MenuUI.functions.setText(lang.get("functions"));
            MenuUI.feedback.setText(lang.get("feedback"));
            MenuUI2.saveCacheOrNot.setText(lang.get("save_cache"));
            MenuUI2.saveCache.setText(lang.get("save"));
            MenuUI2.notSaveCache.setText(lang.get("delete"));
            MenuUI2.saveErrorLogsOrNot.setText(lang.get("save_err"));
            MenuUI2.saveErrorLogs.setText(lang.get("save"));
            MenuUI2.noSaveErrorLogs.setText(lang.get("delete"));
            MenuUI2.saveRunLogsOrNot.setText(lang.get("runLog"));
            MenuUI2.saveRunLogs.setText(lang.get("save"));
            MenuUI2.noSaveRunLogs.setText(lang.get("delete"));
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

            if(Community.extraDisplayID == 0) {
                ExtraUI.jFrame.setTitle(lang.get("functions_launcher"));
            } else if(Community.extraDisplayID == 1) {
                ExtraUI.jFrame.setTitle(lang.get("functions_problems"));
            } else if(Community.extraDisplayID == 2) {
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

            if(! MinecraftLauncher.launching)
                MinecraftLauncher.launch.setText(lang.get("launcher_minecraft"));

            MinecraftLauncher.downloadAndLaunch.setText(lang.get("downloads_safe_launch"));
            MinecraftLauncher.downloadAndFastLaunch.setText(lang.get("downloads_fast_launch"));

            loadingWindow.loadingTip.setText(lang.get("resource_loading"));
            loadingWindow.loadStatus.setText(lang.get("load_status"));

            MinecraftLauncher.switchDownloadPanel.setText(lang.get("download_vanilla"));
            MinecraftLauncher.switchLauncherPanel.setText(lang.get("launcher"));

            MinecraftLauncher.showSelect.setText(lang.get("version_show_select"));
            MinecraftLauncher.freshList.setText(lang.get("fresh_list"));
            MinecraftLauncher.showAll.setText(lang.get("show_all_ver"));
            MinecraftLauncher.showRelease.setText(lang.get("show_release_ver"));
            MinecraftLauncher.showSnapshot.setText(lang.get("show_snapshot_ver"));
            MinecraftLauncher.showAlpha.setText(lang.get("show_alpha_ver"));

            MinecraftLauncher.startDownload.setText(lang.get("downloads"));
            MinecraftLauncher.downloadName_label.setText(lang.get("download_name"));

            MinecraftLauncher.checkResOption.setText(lang.get("checkResOption"));
            MinecraftLauncher.checkRes.setText(lang.get("enable"));
            MinecraftLauncher.noCheckRes.setText(lang.get("disable"));

            MinecraftLauncher.switchSettingsPanel.setText(lang.get("settings"));

            MinecraftLauncher.switchJavaPanel.setText(lang.get("java_settings"));
            MinecraftLauncher.switchRunningPanel.setText(lang.get("running_manager"));

            MinecraftLauncher.setUsingJava.setText(lang.get("select_java"));
            MinecraftLauncher.importJava.setText(lang.get("import_java"));

            MinecraftLauncher.switchMinecraftAreaPanel.setText(lang.get("area"));
            MinecraftLauncher.minecraftAreaDescription.setText(lang.get("area_description"));

            MinecraftLauncher.removeJava.setText(lang.get("remove"));

            MinecraftLauncher.forceStopMinecraft.setText(lang.get("force_stop"));

            MinecraftLauncher.removeInstance.setText(lang.get("remove_instance"));

            MinecraftLauncher.logs.setText(lang.get("logs"));

            MinecraftLauncher.switchAccountPanel.setText(lang.get("account"));
            MinecraftLauncher.addAccount.setText(lang.get("addAccount"));
            MinecraftLauncher.removeAccount.setText(lang.get("removeAccount"));
            MinecraftLauncher.selectAccount.setText(MinecraftLauncher.userSelected ? lang.get("selected") : lang.get("selectAccount"));

            MinecraftLauncher.accountNameTip.setText(MinecraftLauncher.addAccountType.equals("mojang") ? lang.get("account_email") : lang.get("account_name"));
            MinecraftLauncher.accountPasswordTip.setText(MinecraftLauncher.addAccountType.equals("mojang") ? lang.get("account_password") : "UUID");

            MinecraftLauncher.addThisAccount.setText(lang.get("addAccount"));
            MinecraftLauncher.addOfflineAccount.setText(lang.get("offline_account"));
            MinecraftLauncher.addMojangAccount.setText(lang.get("mojang_account"));

            MinecraftLauncher.addMinecraftArea.setText(lang.get("add_minecraft_area"));
            MinecraftLauncher.removeMinecraftArea.setText(lang.get("remove_minecraft_area"));
            MinecraftLauncher.renameMinecraftArea.setText(lang.get("rename_minecraft_area"));
            MinecraftLauncher.selectThisArea.setText(MinecraftLauncher.areaSelected ? lang.get("selected") : lang.get("select_minecraft_area"));

            MinecraftLauncher.exportLogs.setText(MinecraftLauncher.exportingLogs ? lang.get("exporting") : lang.get("export"));

            MinecraftLauncher.runMinecraftType.setText(lang.get("run_type"));
            MinecraftLauncher.runMinecraftClient.setText(lang.get("client"));
            MinecraftLauncher.runMinecraftServer.setText(lang.get("server"));

            try {
                new JSONObject(MinecraftLauncher.minecraftVersions.get(MinecraftLauncher.verList.getSelectedValue().toString()).toString()).get("path");

                boolean downloading = false;
                try {
                    downloading = new JSONObject(MinecraftLauncher.downloadingMinecraft.get(MinecraftLauncher.verList.getSelectedValue()).toString()).get("status").equals("downloading");
                } catch (Exception ex) {

                }

                boolean deleting= false;
                try {
                    downloading = MinecraftLauncher.deletingMinecraft.contains(MinecraftLauncher.verList.getSelectedValue());
                } catch (Exception ex) {

                }

                if(downloading) {
                    MinecraftLauncher.deleteWarning.setText(String.format(lang.get("stop_download_and_delete_version"), MinecraftLauncher.verList.getSelectedValue().toString()));
                } else if(deleting) {
                    MinecraftLauncher.deleteWarning.setText(String.format(lang.get("deleting"), MinecraftLauncher.verList.getSelectedValue().toString()));
                } else {
                    MinecraftLauncher.deleteWarning.setText(String.format(lang.get("delete_version_warning"), MinecraftLauncher.verList.getSelectedValue().toString()));
                }

                MinecraftLauncher.deleteVersion.setText(lang.get("delete_version"));
            } catch (Exception e) {

            }

            if(Community.canUPD) {
                MenuUI2.checkUPD.setText(lang.get("UPD"));
            } else {
                MenuUI2.checkUPD.setText(lang.get("checkUPD"));
            }

            if(! Community.functionEditing) {
                ExtraUI.functionEdit.setText(lang.get("disableFunctionEdit"));
            }
        } catch (Exception e) {

        }
    }

    public static String getCommandWord(String word) {
        return lang.get(word);
    }

    @Override
    public void run() {
        langFile.put("languages.json", "/com/github/zhuaidadaya/resources/resource_files/");

        while(! Errors.CannotHandle) {
            if(! Community.isDaemons) {
                try {
                    Thread.sleep(200);
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
