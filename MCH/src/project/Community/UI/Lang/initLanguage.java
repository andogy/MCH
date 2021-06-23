package project.Community.UI.Lang;

import org.json.JSONArray;
import org.json.JSONObject;
import project.Community.Community;
import project.Community.Events.Errors;
import project.Community.UI.loadingWindow;

import java.io.*;
import java.net.URL;
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
        StringBuilder json = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(languagesPath, StandardCharsets.UTF_8));
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
            fixResource("/project/resources/json_files/languages.json", languagesPath);

            Errors.errors(null, e, false, "languageInit");

            init();
        } catch (Exception e) {
            System.out.println("语言文件损坏");

            //修复语言文件
            fixResource("/project/resources/json_files/languages.json", languagesPath);

            e.printStackTrace();

            loadingWindow.loading.setText(Arrays.toString(e.getStackTrace()));
            Errors.errors(null, e, false, json.toString());

            init();
        }
    }

    public static void fixResource(String resource, String fixTarget) {
        try {
            URL res = initLanguage.class.getResource(resource);
            BufferedReader br = new BufferedReader(new FileReader(new File(res.toURI()), StandardCharsets.UTF_8));
            String out = "";

            FileWriter writer = new FileWriter(fixTarget);

            while((out = br.readLine()) != null) {
                writer.write(out);
            }

            br.close();
            writer.close();
        } catch (Exception ignored) {

        }
    }

    public static void initCommand() {
        String commandPath = "C:\\.MCH\\commands.json";

        try {
            BufferedReader br = new BufferedReader(new FileReader(commandPath, StandardCharsets.UTF_8));
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
            fixResource("/project/resources/json_files/commands.json", commandPath);
        }
    }
}

