package com.github.zhuaidadaya.MCH.lib;

import com.github.zhuaidadaya.MCH.Config.ConfigMain;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.Events.LoadAssembly;
import com.github.zhuaidadaya.MCH.UI.loadingWindow;
import com.github.zhuaidadaya.MCH.lib.json.JSONArray;
import com.github.zhuaidadaya.MCH.lib.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Resources extends Thread {
    public static InputStream getResource(String resource, Class<?> getC) {
        return getC.getResourceAsStream(resource);
    }

    public static URL getResourceByFile(String resource, Class<?> getC) {
        return getC.getResource(resource);
    }

    public static int getElementsCounter(Iterator<String> iterator) {
        int result = 0;
        while(iterator.hasNext()) {
            result += 1;
            iterator.next();
        }

        return result;
    }

    public static void createPath(String path) {
        if(! new File(path).isDirectory())
            new File(path).mkdirs();
    }

    public void fixResource(String resource, String fixTarget, boolean lineWrap) {
        try {
            createPath(fixTarget.substring(0, fixTarget.lastIndexOf("/")));

            LoadAssembly.badLoadAssembly("[Resource Thread/INFO] fixing resource:" + fixTarget, Resources.initLanguage.lang.get("fixing_resource"));

            //            InputStream in = new InputStreamReader(getResource(resource), StandardCharsets.UTF_8));

            BufferedReader br = new BufferedReader(new InputStreamReader(getResource(resource, this.getClass()), StandardCharsets.UTF_8));

            BufferedWriter writer = new BufferedWriter(new FileWriter(fixTarget, StandardCharsets.UTF_8));
            //            int out;
            String out;

            int interval_gc_cache = 300;
            int interval_gc = interval_gc_cache;
            int gcCount = 0;

            long length = 0;

            //            while((out = in.read(new byte[1024])) != - 1) {
            //                System.out.println(out1);
            //
            //                writer.write(bytes, 0, out1);
            //            }

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

        }
    }

    public static class initLanguage {
        public static HashMap<String, String> lang = new HashMap<>();

        public initLanguage() {
            createPath(ConfigMain.resPath);

            //            initLang("languages.json", "/com/github/zhuaidadaya/resources/resource_files/", "");
            //            initLang("commands/commands.json", "/com/github/zhuaidadaya/resources/resource_files/", "");

            initFromSelf("languages.json", "/com/github/zhuaidadaya/resources/resource_files/", "", this.getClass());
            initFromSelf("commands.json", "/com/github/zhuaidadaya/resources/resource_files/", "", this.getClass());
        }

        public static void initLang(String langFile, String resourceRoot, String targetLanguage) {
            String languagesPath = ConfigMain.resPath + langFile;

            File f = null;
            if(new File(ConfigMain.resPath + langFile).isFile()) {
                f = new File(ConfigMain.resPath + langFile);
            } else {

            }

            StringBuilder json = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8));
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
                if(targetLanguage.equals("")) {
                    if(Community.LangID == 0) {
                        targetLanguage = "chinese";
                    } else if(Community.LangID == 1) {
                        targetLanguage = "english";
                    } else if(Community.LangID == 3) {
                        targetLanguage = "chinese_tw";
                    }
                }

                for(int i = 0; ; i++) {
                    language = new JSONObject(languages.get(i).toString());
                    if(language.keys().next().equals(targetLanguage))
                        break;
                }
                JSONArray languageText = new JSONArray(language.get(language.keys().next()).toString());
                int i = languageText.length();
                i--;
                JSONObject inMap = new JSONObject(languageText.get(i).toString());
                int inMapKeyCounter;
                String inMapKey = inMap.keys().next();
                lang.put(inMapKey, inMap.getString(inMapKey));

                while(i + 1 != 0) {
                    inMap = new JSONObject(languageText.get(i).toString());

                    Iterator<String> in = inMap.keys();

                    inMapKeyCounter = getElementsCounter(in);

                    in = inMap.keys();

                    while(inMapKeyCounter != 0) {
                        inMapKeyCounter -= 1;

                        String next = in.next();
                        lang.put(next, inMap.getString(next));
                    }

                    i--;
                }
            } catch (FileNotFoundException e) {

                ConfigMain.languageSet = "Language@Auto";

                //修复语言文件
                new Resources().fixResource(resourceRoot + langFile, languagesPath, false);

                Errors.errors(null, e, false, "languageInit", "null", 700, 520, false,false);

            } catch (Exception e) {
                ConfigMain.languageSet = "Language@Auto";

                //修复语言文件
                new Resources().fixResource(resourceRoot + langFile, languagesPath, false);

                loadingWindow.loading.append(Arrays.toString(e.getStackTrace()).replace(", ", "\n") + "\n");
                Errors.errors(null, e, false, json.toString(), "null", 700, 520, false,false);

            } catch (Error error) {

            }
        }

        public static void initFromSelf(String langFile, String resourceRoot, String targetLanguage, Class<?> initFrom) {
            InputStream f = getResource(resourceRoot + langFile, initFrom);

            StringBuilder json = new StringBuilder();

            try {
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
                if(targetLanguage.equals("")) {
                    if(Community.LangID == 0) {
                        targetLanguage = "chinese";
                    } else if(Community.LangID == 1) {
                        targetLanguage = "english";
                    } else if(Community.LangID == 3) {
                        targetLanguage = "chinese_tw";
                    }
                }

                for(int i = 0; ; i++) {
                    language = new JSONObject(languages.get(i).toString());
                    if(language.keys().next().equals(targetLanguage))
                        break;
                }

                JSONArray languageText = new JSONArray(language.get(language.keys().next()).toString());

                int i = languageText.length();
                i--;
                JSONObject inMap = new JSONObject(languageText.get(i).toString());
                int inMapKeyCounter;
                String inMapKey = inMap.keys().next();
                lang.put(inMapKey, inMap.getString(inMapKey));

                while(i + 1 != 0) {
                    inMap = new JSONObject(languageText.get(i).toString());

                    Iterator<String> in = inMap.keys();

                    inMapKeyCounter = getElementsCounter(in);

                    in = inMap.keys();

                    while(inMapKeyCounter != 0) {
                        inMapKeyCounter -= 1;

                        String next = in.next();
                        lang.put(next, inMap.getString(next));
                    }

                    i--;
                }
            } catch (Exception | Error e) {
                ConfigMain.languageSet = "Language@Auto";
                if(e instanceof Exception) {
                    Errors.errors(null, (Exception) e, true, "LanguageParse", "Your Language File Has Some Error\nPlease Check that and change\nif You Do not Know Error where is\nPlease use Language File by MCH", 700, 520, true,false);
                } else {
                    Errors.errors((Error) e, null, true, "LanguageParse", "Your Language File Has Some Error\nPlease Check that and change\nif You Do not Know Error where is\nPlease use Language File by MCH", 700, 520, true,false);
                }
            }
        }
    }
}