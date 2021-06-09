package project.Community.UI.Lang;

import org.json.JSONArray;
import org.json.JSONObject;
import project.Community.Community;
import project.Community.Events.Errors;
import project.Community.UI.loadingWindow;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

public class initLanguage {
    public static HashMap<String, String> lang = new HashMap<>();
    public static HashMap<String ,String> commands = new HashMap<>();

    public initLanguage() {
        init();
        initCommand();
    }

    public static void init() {
        String languagesPath = "C:\\.MCH\\languages.json";
        StringBuilder json = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(languagesPath, StandardCharsets.UTF_8));
            String brRead;

            while ((brRead = br.readLine()) != null) {
                json.append(brRead).append("\n");
            }

            br.close();

            JSONObject js = new JSONObject(json.toString());

            //            获得语言列表
            JSONArray languages = new JSONArray(js.get("languages").toString());

            new JSONObject();
            JSONObject language;
            String targetLanguage = "";
            if (Community.LangID == 0) {
                targetLanguage = "chinese";
            } else if (Community.LangID == 1) {
                targetLanguage = "english";
            }

            for (int i = 0; ; i++) {
                language = new JSONObject(languages.get(i).toString());
                if (language.keys().next().equals(targetLanguage)) {
                    break;
                }
            }
            JSONArray languageText = new JSONArray(language.get(language.keys().next()).toString());

            int i = languageText.length();
            i--;
            JSONObject inMap = new JSONObject(languageText.get(i).toString());
            String inMapKey = inMap.keys().next();
            lang.put(inMapKey, inMap.getString(inMapKey));

            while (i != 0) {
                i--;
                inMap = new JSONObject(languageText.get(i).toString());
                inMapKey = inMap.keys().next();
                lang.put(inMapKey, inMap.getString(inMapKey));

                //                System.out.println("init: " + inMap + "\n" + "init to:" + lang.get(inMapKey) + "\n" + "------------------------------------");
            }
        } catch (FileNotFoundException e) {
            try {
                File f = new File("C:\\.MCH\\languages.json");
                FileWriter fr = new FileWriter(f);
                fr.write("""
                        {
                          "languages": [
                            {
                              "chinese": [
                                {
                                  "menuButton": "菜单"
                                },
                                {
                                  "mch": "Minecraft命令助手"
                                },
                                {
                                  "menu": "菜单页"
                                },
                                {
                                  ".exit": "\\".exit\\"是一个在此程序中用于退出的命令,并非游戏内指令\\n是否要继续退出?"
                                },
                                {
                                  "exit": "退出"
                                },
                                {
                                  "exitNot": "不退出"
                                },
                                {
                                  "configPath": "配置路径:  "
                                },
                                {
                                  "exButton": "退出按钮:"
                                },
                                {
                                  "exitMCH": "退出MCH"
                                },
                                {
                                  "smallerMCH": "缩小UI"
                                },
                                {
                                  "colorSet": "颜色:"
                                },
                                {
                                  "black": "黑色"
                                },
                                {
                                  "white": "白色"
                                },
                                {
                                  "languageSet": "语言:"
                                },
                                {
                                  "chinese": "中文"
                                },
                                {
                                  "english": "英文"
                                },
                                {
                                  "fastLoad": "快速加载:"
                                },
                                {
                                  "disable": "禁用"
                                },
                                {
                                  "enable": "启用"
                                },
                                {
                                  "version": "版本: "
                                },
                                {
                                  "versionID": "版本ID:"
                                },
                                {
                                  "onTop": "置于顶层:"
                                },
                                {
                                  "deleteData": "删除数据"
                                },
                                {
                                  "help": "帮助"
                                },
                                {
                                  "agreement": "用户协议"
                                },
                                {
                                  "restartMCH": "重启MCH"
                                },
                                {
                                  "settings": "设置"
                                },
                                {
                                  "run": "运行"
                                },
                                {
                                  "display": "显示"
                                },
                                {
                                  "run_title": "设置-运行"
                                },
                                {
                                  "display-title": "设置-显示"
                                },
                                {
                                  "upd_title": "设置-更新"
                                },
                                {
                                  "info_title": "设置-MCH详情"
                                },
                                {
                                  "functions": "功能"
                                },
                                {
                                  "feedback": "反馈"
                                },
                                {
                                  "save": "保留"
                                },
                                {
                                  "delete": "删除"
                                },
                                {
                                  "save_cache": "保留缓存:"
                                },
                                {
                                  "save_err": "错误日志:"
                                },
                                {
                                  "runLog": "运行日志"
                                },
                                {
                                  "autoUPD": "自动更新"
                                },
                                {
                                  "history": "历史纪录:"
                                },
                                {
                                  "save_all": "保留全部"
                                },
                                {
                                  "save_some": "保留最近"
                                },
                                {
                                  "auto": "自动"
                                },
                                {
                                  "UPD": "更新"
                                },
                                {
                                  "about": "关于"
                                },
                                {
                                  "aboutMCH": "关于MCH:"
                                },
                                {
                                  "viewFile": "查看文件"
                                },
                                {
                                  "developer": "关于开发者:"
                                },
                                {
                                  "aboutDeveloper": "MCH有两位开发者,以下是他们的称呼以及联系方式:\\n开发者andogy   可以称呼为翅膀\\n通过qq与他联系:2293332045\\n\\n开发者zhuaidadaya   可以称呼为草\\n通过qq或微信联系:\\nqq:1501917367\\nWeChat:zhuaidadaya"
                                },
                                {
                                  "functions_launcher": "功能-启动器"
                                },
                                {
                                  "functions_problems": "功能-随机问题"
                                },
                                {
                                  "functions_FunctionEitor": "功能-Function编辑器"
                                },
                                {
                                  "mcBedrock": "MC基岩版:"
                                },
                                {
                                  "launcher": "启动器"
                                },
                                {
                                  "off": "关闭"
                                },
                                {
                                  "randomProblem": "随机问题"
                                },
                                {
                                  "saveTo": "保存至"
                                },
                                {
                                  "flushSpeed": "刷新速度:"
                                },
                                {
                                  "slow": "稍慢"
                                },
                                {
                                  "fast": "最快"
                                },
                                {
                                  "checkUPD": "检查更新"
                                },
                                {
                                  "disableFunctionEdit": "未启用Function编辑器"
                                },
                                {
                                  "CannotHandleErr": "MCH遇到了一个无法自动处理的错误\\n请将此错误反馈给开发人员,谢谢\\n\\n正在尝试重启MCH\\n\\n你可以去到MCH的目录查看错误信息"
                                },
                                {
                                  "commandsNotFound": "未安装命令解析库\\n(pre版本暂未内置)\\n下载地址:\\n https://raw.githubusercontent.com/zhuaidadaya/MCH/main/MCH/src/project/resources/Json/test.json"
                                },
                                {
                                  "commandEnd": "无法找到此命令的这种用法"
                                },
                                {
                                  "pageOut": "页码超出限制"
                                },
                                {
                                  "@page": "-页码"
                                },
                                {
                                  "@boolean_true": "-启用"
                                },
                                {
                                  "@boolean_false": "-禁用"
                                },
                                {
                                  "@appointName": "-指定名称"
                                },
                                {
                                  "@sel_s": "-执行命令的实体"
                                },
                                {
                                  "@sel_r": "-随机玩家"
                                },
                                {
                                  "@sel_p": "-最近的玩家"
                                },
                                {
                                  "@sel_e": "-所有实体"
                                },
                                {
                                  "@sel_a": "-所有玩家"
                                },
                                {
                                  "@command_help": "-查找命令用法"
                                },
                                {
                                  "@command_gamemode": "-设定游戏模式"
                                },
                                {
                                  "@command_gamerule": "-设定或查询游戏规则"
                                },
                                {
                                  "@adventure": "-冒险"
                                },
                                {
                                  "@default": "-默认"
                                },
                                {
                                  "@creative": "-创造"
                                },
                                {
                                  "@survival": "-生存"
                                },
                                {
                                  "@spectator": "-旁观 (仅java版可用)"
                                },
                                {
                                  "@comamnd_ability": "-给予或清除玩家能力"
                                },
                                {
                                  "@ability_worldbuilder": "-给予玩家成为世界建造者的能力"
                                },
                                {
                                  "@ability_mayfly": "-给予玩家飞行能力"
                                },
                                {
                                  "@ability_mute": "-禁言玩家"
                                },
                                {
                                  "@command_achievenment": "-给予或清除玩家的成就(已失效)"
                                },
                                {
                                  "@command_advancement": "-添加、清除、查询玩家的进度"
                                },
                                {
                                  "@grant": "-给予成就"
                                },
                                {
                                  "@revoke": "-清除成就"
                                },
                                {
                                  "@advancement_only": "-指定一个成就"
                                },
                                {
                                  "@advancement_everything": "-所有成就"
                                },
                                {
                                  "@advancement_condition": "-条件"
                                },
                                {
                                  "@advancement_through": "-指定进度及其全部上游和下游进度"
                                },
                                {
                                  "@advancement_from": "-指定进度及其下游进度"
                                },
                                {
                                  "@advancement_until": "-指定进度及其上游进度"
                                }
                              ]
                            },
                            {
                              "english": [
                                {
                                  "menuButton": "menus"
                                },
                                {
                                  "mch": "Minecraft Command Helper"
                                },
                                {
                                  "menu": "menu page"
                                },
                                {
                                  ".exit": "\\".exit\\" is a command used by this program to exit,This not an in-game command \\nDo you want to continue to exit"
                                },
                                {
                                  "exit": "exit"
                                },
                                {
                                  "exitNot": "exit not"
                                },
                                {
                                  "configPath": "config path:  "
                                },
                                {
                                  "exButton": "exit button:"
                                },
                                {
                                  "exitMCH": "Exit MCH"
                                },
                                {
                                  "smallerMCH": "Reduce UI"
                                },
                                {
                                  "colorSet": "Color:"
                                },
                                {
                                  "black": "black"
                                },
                                {
                                  "white": "white"
                                },
                                {
                                  "languageSet": "language:"
                                },
                                {
                                  "chinese": "Chinese"
                                },
                                {
                                  "english": "english"
                                },
                                {
                                  "fastLoad": "fast load:"
                                },
                                {
                                  "disable": "disable"
                                },
                                {
                                  "enable": "enable"
                                },
                                {
                                  "version": "version: "
                                },
                                {
                                  "versionID": "version ID:"
                                },
                                {
                                  "onTop": "on top:"
                                },
                                {
                                  "deleteData": "delete data"
                                },
                                {
                                  "help": "help"
                                },
                                {
                                  "agreement": "agreement"
                                },
                                {
                                  "restartMCH": "restart MCH"
                                },
                                {
                                  "settings": "settings"
                                },
                                {
                                  "run": "run"
                                },
                                {
                                  "display": "display"
                                },
                                {
                                  "run_title": "settings-run"
                                },
                                {
                                  "display-title": "settings-display"
                                },
                                {
                                  "upd_title": "settings-update"
                                },
                                {
                                  "info_title": "settings-MCH info"
                                },
                                {
                                  "functions": "functions"
                                },
                                {
                                  "feedback": "feedback"
                                },
                                {
                                  "save": "save"
                                },
                                {
                                  "delete": "delete"
                                },
                                {
                                  "save_cache": "save cache:"
                                },
                                {
                                  "save_err": "error log:"
                                },
                                {
                                  "runLog": "run log"
                                },
                                {
                                  "autoUPD": "auto UPD"
                                },
                                {
                                  "history": "history:"
                                },
                                {
                                  "save_all": "save all"
                                },
                                {
                                  "save_some": "save some"
                                },
                                {
                                  "auto": "auto"
                                },
                                {
                                  "UPD": "UPD"
                                },
                                {
                                  "about": "about"
                                },
                                {
                                  "aboutMCH": "about MCH:"
                                },
                                {
                                  "viewFile": "view files"
                                },
                                {
                                  "developer": "about developer:"
                                },
                                {
                                  "aboutDeveloper": "MCH have two developers,here is their contact:\\n\\"andogy\\"   can called \\"翅膀\\"(ChiBang)\\ncontact at qq:2293332045\\n\\n\\"zhuaidadaya\\"   can called \\"草\\"(grass)\\ncontact at qq or WeChat\\nqq:1501917367\\nWeChat:zhuaidadaya"
                                },
                                {
                                  "functions_launcher": "functions-launcher"
                                },
                                {
                                  "functions_problems": "functions-random problems"
                                },
                                {
                                  "functions_FunctionEitor": "functions-Function editor"
                                },
                                {
                                  "mcBedrock": "MC Bedrock:"
                                },
                                {
                                  "launcher": "launcher"
                                },
                                {
                                  "off": "off"
                                },
                                {
                                  "randomProblem": "problems"
                                },
                                {
                                  "saveTo": "save to"
                                },
                                {
                                  "flushSpeed": "flush speed:"
                                },
                                {
                                  "slow": "slow"
                                },
                                {
                                  "fast": "fast"
                                },
                                {
                                  "checkUPD": "check UPD"
                                },
                                {
                                  "disableFunctionEdit": "Function edit is no enable"
                                },
                                {
                                  "CannotHandleErr": "There are some unhandled errors in the MCH\\nPlease send there error to feedback,thanks you\\n\\nTrying restart the MCH\\n\\nYou can go to the MCH directory to look the error info"
                                },
                                {
                                  "commandsNotFound": "command resolution library is not installed\\n(pre version no built in)\\ndownload:\\nhttps://raw.githubusercontent.com/zhuaidadaya/MCH/main/MCH/src/project/resources/Json/test.json"
                                },
                                {
                                  "commandEnd": "This usage of this command cannot be found"
                                },
                                {
                                  "pageOut": "page out of max"
                                },
                                {
                                  "@page": "-page"
                                },
                                {
                                  "@boolean_true": "-enable"
                                },
                                {
                                  "@boolean_false": "-disable"
                                },
                                {
                                  "appointName": "-specify name"
                                },
                                {
                                  "@sel_s": "-entity that execute command"
                                },
                                {
                                  "@sel_r": "-random player"
                                },
                                {
                                  "@sel_p": "-nearly player"
                                },
                                {
                                  "@sel_e": "-all entities"
                                },
                                {
                                  "@sel_a": "-all players"
                                },
                                {
                                  "@command_help": "-find usage of command"
                                },
                                {
                                  "@command_gamemode": "-set gamemode"
                                },
                                {
                                  "@command_gamerule": "-set or query gamerule"
                                },
                                {
                                  "@adventure": "-adventure mode"
                                },
                                {
                                  "@default": "-default mode"
                                },
                                {
                                  "@creative": "-creative mode"
                                },
                                {
                                  "@survival": "-survival mode"
                                },
                                {
                                  "@spectator": "-spectator mode (only java edition)"
                                },
                                {
                                  "@ability_worldbuilder": "-give player builder world ability"
                                },
                                {
                                  "@ability_mayfly": "-give player fly ability"
                                },
                                {
                                  "@ability_mute": "-mute for player"
                                },
                                {
                                  "@command_achievenment": "-give or clear player's achievement(invalid)"
                                },
                                {
                                  "@command_advancement": "-add、clear、query player's advancement"
                                },
                                {
                                  "@grant": "-give advancement"
                                },
                                {
                                  "@revoke": "-clear advancement"
                                },
                                {
                                  "@advancement_only": "-specify advancement"
                                },
                                {
                                  "@advancement_everything": "-all advancement"
                                },
                                {
                                  "@advancement_through": "-advancement and its upstream and downstream"
                                },
                                {
                                  "@advancement_from": "-advancement and its downstream"
                                },
                                {
                                  "@advancement_until": "-advancement and its upstream"
                                }
                              ]
                            }
                          ]
                        }
                        """);
                fr.close();
                init();
            } catch (Exception ignored) {

            }
            Errors.errors(null, e, false, "languageInit");
        } catch (Exception e) {
            System.out.println("语言文件损坏");
            loadingWindow.loading.setText(Arrays.toString(e.getStackTrace()));
            Errors.errors(null, e, false, json.toString());
        }
    }

    public static void initCommand() {
        String languagesPath = "C:\\.MCH\\commands.json";

        try {
            BufferedReader br = new BufferedReader(new FileReader(languagesPath,StandardCharsets.UTF_8));
            String brRead;
            StringBuilder json = new StringBuilder();

            while ((brRead = br.readLine()) != null) {
                json.append(brRead);
            }

            JSONObject js = new JSONObject(json.toString());

            //            获得语言列表
            JSONArray languages = new JSONArray(js.get("languages").toString());

            new JSONObject();
            JSONObject language;
            String targetLanguage = "";
            if (Community.LangID == 0) {
                targetLanguage = "chinese";
            } else if (Community.LangID == 1) {
                targetLanguage = "english";
            }

            for (int i = 0; ; i++) {
                language = new JSONObject(languages.get(i).toString());
                if (language.keys().next().equals(targetLanguage)) {
                    break;
                }
            }
            JSONArray languageText = new JSONArray(language.get(language.keys().next()).toString());

            int i = languageText.length();
            i--;
            JSONObject inMap = new JSONObject(languageText.get(i).toString());
            String inMapKey = inMap.keys().next();
            commands.put(inMapKey, inMap.getString(inMapKey));

            while (i != 0) {
                i--;
                inMap = new JSONObject(languageText.get(i).toString());
                inMapKey = inMap.keys().next();
                commands.put(inMapKey, inMap.getString(inMapKey));

                //                System.out.println("init: " + inMap + "\n" + "init to:" + lang.get(inMapKey) + "\n" + "------------------------------------");
            }
        } catch (Exception e) {

        }
    }
}
