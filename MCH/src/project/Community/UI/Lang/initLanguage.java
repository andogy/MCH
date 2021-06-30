package project.Community.UI.Lang;

import org.json.JSONArray;
import org.json.JSONObject;
import project.Community.Community;
import project.Community.Events.Errors;
import project.Community.UI.loadingWindow;
import project.Community.lib.Resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

public class initLanguage {
    public static HashMap<String, String> lang = new HashMap<>();
    public static HashMap<String, String> commands = new HashMap<>();

    public initLanguage() {
        init();
        initCommand();
    }

    public static void init() {
        String languagesPath = "C:\\.MCH\\languages.json";

        File f;
        if(new File("C:\\.MCH\\languages.json").isFile()) {
            f = new File("C:\\.MCH\\languages.json");
        } else {
            f = Resources.getResource("/project/resources/resource_files/languages.json");
        }

        StringBuilder json = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8));
            String brRead;

            while((brRead = br.readLine()) != null) {
                json.append(brRead).append("\n");
            }

            br.close();

            JSONObject js = new JSONObject(json.toString());

            //            获得语言列表
            JSONArray languages = new JSONArray(js.get("languages").toString());

            new JSONObject();
            JSONObject language;
            String targetLanguage = "";
            if(Community.LangID == 0) {
                targetLanguage = "chinese";
            } else if(Community.LangID == 1) {
                targetLanguage = "english";
            }

            for(int i = 0; ; i++) {
                language = new JSONObject(languages.get(i).toString());
                if(language.keys().next().equals(targetLanguage)) {
                    break;
                }
            }
            JSONArray languageText = new JSONArray(language.get(language.keys().next()).toString());

            int i = languageText.length();
            i--;
            JSONObject inMap = new JSONObject(languageText.get(i).toString());
            String inMapKey = inMap.keys().next();
            lang.put(inMapKey, inMap.getString(inMapKey));

            while(i != 0) {
                i--;
                inMap = new JSONObject(languageText.get(i).toString());
                inMapKey = inMap.keys().next();
                lang.put(inMapKey, inMap.getString(inMapKey));

                //                System.out.println("init: " + inMap + "\n" + "init to:" + lang.get(inMapKey) + "\n" + "------------------------------------");
            }
        } catch (FileNotFoundException e) {

            //修复语言文件
            Resources.fixResource("/project/resources/resource_files/languages.json", languagesPath, false);

            Errors.errors(null, e, false, "languageInit");

            init();
        } catch (Exception e) {
            //修复语言文件
            Resources.fixResource("/project/resources/resource_files/languages.json", languagesPath, false);

            loadingWindow.loading.append(Arrays.toString(e.getStackTrace()) + "\n");
            Errors.errors(null, e, false, json.toString());

            init();
        }

        languageSet.Language();
    }

    public static void initCommand() {
        String commandPath = "C:\\.MCH\\commands.json";

        File f;
        if(new File("C:\\.MCH\\commands.json").isFile()) {
            f = new File("C:\\.MCH\\commands.json");
        } else {
            f = Resources.getResource("/project/resources/resource_files/commands.json");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8));
            String brRead;
            StringBuilder json = new StringBuilder();

            while((brRead = br.readLine()) != null) {
                json.append(brRead);
            }

            br.close();

            JSONObject js = new JSONObject(json.toString());

            //            获得语言列表
            JSONArray languages = new JSONArray(js.get("languages").toString());

            new JSONObject();
            JSONObject language;
            String targetLanguage = "";
            if(Community.LangID == 0) {
                targetLanguage = "chinese";
            } else if(Community.LangID == 1) {
                targetLanguage = "english";
            }

            for(int i = 0; ; i++) {
                language = new JSONObject(languages.get(i).toString());
                if(language.keys().next().equals(targetLanguage)) {
                    break;
                }
            }
            JSONArray languageText = new JSONArray(language.get(language.keys().next()).toString());

            int i = languageText.length();
            i--;
            JSONObject inMap = new JSONObject(languageText.get(i).toString());
            String inMapKey = inMap.keys().next();
            commands.put(inMapKey, inMap.getString(inMapKey));

            while(i != 0) {
                i--;
                inMap = new JSONObject(languageText.get(i).toString());
                inMapKey = inMap.keys().next();
                commands.put(inMapKey, inMap.getString(inMapKey));
            }
        } catch (Exception e) {
            Resources.fixResource("/project/resources/resource_files/commands.json", commandPath, false);
        }
    }
}

