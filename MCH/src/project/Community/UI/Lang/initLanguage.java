package project.Community.UI.Lang;

import org.json.JSONArray;
import org.json.JSONObject;
import project.Community.Community;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class initLanguage {
    public static HashMap<String, String> languageMap = new HashMap<>();

    public initLanguage() {
        init();
    }

    public static void init() {
        try {

            BufferedReader br = new BufferedReader(new FileReader("G:\\Code-Java\\MCH\\MCH\\src\\project\\resources\\Json\\language.json"));

            String brRead = "";
            StringBuilder json = new StringBuilder();
            while ((brRead = br.readLine()) != null) {
                json.append(brRead);
            }

            JSONObject js = new JSONObject(json.toString());

            //            获得语言列表
            JSONArray languages = new JSONArray(js.get("languages").toString());

            JSONObject language = new JSONObject();
            String targetLanguage = "";
            if (Community.LangID == 0) {
                targetLanguage = "chinese";
            } else if (Community.LangID == 1) {
                targetLanguage = "english";
            }
            for(int i = 0;;i++) {
                language = new JSONObject(languages.get(i).toString());
                if (language.keys().next().equals(targetLanguage)) {
                    break;
                }
            }
            JSONArray languageText = new JSONArray(language.get(language.keys().next()).toString());

            System.out.println(languageText);

            int i = languageText.length();
            do {
                i--;
                JSONObject inMap = new JSONObject(languageText.get(i).toString());
                String inMapKey = inMap.keys().next();
                languageMap.put(inMapKey, inMap.getString(inMapKey));

                System.out.println("init: " + inMap + "\n" + "init to:" + languageMap.get(inMapKey) + "\n" +
                        "------------------------------------");
            } while (i != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
