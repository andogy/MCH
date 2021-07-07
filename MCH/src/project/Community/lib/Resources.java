package project.Community.lib;

import org.json.JSONArray;
import org.json.JSONObject;
import project.Community.Community;
import project.Community.Events.Errors;
import project.Community.Events.LoadAssembly;
import project.Community.Times.times;
import project.Community.UI.Lang.languageSet;
import project.Community.UI.loadingWindow;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

import static project.Community.lib.Resources.initLanguage.lang;

public class Resources extends Thread {

    public static void fixResource(String resource, String fixTarget, boolean lineWrap) {
        try {
            LoadAssembly.badLoadAssembly("[" + times.format + "]" + "\n" + "fixing resource:" + fixTarget + "\n", lang.get("fixing_resource"));

            BufferedReader br = new BufferedReader(new InputStreamReader(getResource(resource), StandardCharsets.UTF_8));

            FileWriter writer = new FileWriter(fixTarget, StandardCharsets.UTF_8);
            String out;

            int interval_gc_cache = 300;
            int interval_gc = interval_gc_cache;
            int gcCount = 0;

            long length = 0;

            while((out = br.readLine()) != null) {
                length += out.length();

                String replace = out.replace("  ", "").replace(" \"", "\"").replace(" [", "[").replace(" {", "{");
                if(! lineWrap) {
                    writer.write(replace);
                } else {
                    writer.write(replace + "\n");
                }
                writer.flush();
                interval_gc -= 1;
                if(interval_gc <= 0) {
                    interval_gc = interval_gc_cache;
                    gcCount += 1;
                    System.gc();
                }

                loadingWindow.percentage.setText(out.length() + "/" + length);
            }

            br.close();
            writer.close();

            loadingWindow.percentage.setText("");
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    public static InputStream getResource(String resource) {
        InputStream in = Resources.class.getResourceAsStream(resource);
        //        return new File(Objects.requireNonNull(initLanguage.class.getResource(resource)).getFile());
        return in;
    }

    public static class initLanguage {
        public static HashMap<String, String> lang = new HashMap<>();
        public static HashMap<String, String> commands = new HashMap<>();

        public initLanguage() {
            init();
            initCommand();
        }

        public static void initFromSelf() {
            //        File f = Resources.getResource("/project/resources/resource_files/languages.json");
            InputStream f = getResource("/project/resources/resource_files/languages.json");

            StringBuilder json = new StringBuilder();

            try {
                //            BufferedReader br = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8));
                BufferedReader br = new BufferedReader(new InputStreamReader(f, StandardCharsets.UTF_8));
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
                }
            } catch (Exception | Error e) {

            }

            initCommandFromSelf();

            languageSet.Language();
        }

        public static void initCommandFromSelf() {
            //        File f = Resources.getResource("/project/resources/resource_files/commands.json");
            InputStream f = getResource("/project/resources/resource_files/commands.json");

            try {
                //            BufferedReader br = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8));
                BufferedReader br = new BufferedReader(new InputStreamReader(f, StandardCharsets.UTF_8));
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
            } catch (Exception | Error e) {

            }
        }

        public static void init() {
            String languagesPath = "C:\\.MCH\\languages.json";

            File f = null;
            if(new File("C:\\.MCH\\languages.json").isFile()) {
                f = new File("C:\\.MCH\\languages.json");
            } else {
                //            f = Resources.getResource("/project/resources/resource_files/languages.json");
            }
            StringBuilder json = new StringBuilder();

            try {
                            BufferedReader br = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8));
//                BufferedReader br = new BufferedReader(new FileReader(f));
                String brRead;

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
                fixResource("/project/resources/resource_files/languages.json", languagesPath, false);

                Errors.errors(null, e, false, "languageInit");

                init();
            } catch (Exception e) {
                e.printStackTrace();
                //修复语言文件
                fixResource("/project/resources/resource_files/languages.json", languagesPath, false);

                loadingWindow.loading.append(Arrays.toString(e.getStackTrace()) + "\n");
                Errors.errors(null, e, false, json.toString());

                init();
            } catch (Error error) {

            }

            languageSet.Language();
        }

        public static void initCommand() {
            String commandPath = "C:\\.MCH\\commands.json";

            File f = null;
            if(new File("C:\\.MCH\\commands.json").isFile()) {
                f = new File("C:\\.MCH\\commands.json");
            } else {
                //            f = Resources.getResource("/project/resources/resource_files/commands.json");
            }

            try {
                            BufferedReader br = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8));
//                BufferedReader br = new BufferedReader(new FileReader(f));
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
                e.printStackTrace();
                fixResource("/project/resources/resource_files/commands.json", commandPath, false);
            }
        }
    }
}