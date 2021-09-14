package com.github.zhuaidadaya.MCH.lib;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.Events.LoadAssembly;
import com.github.zhuaidadaya.MCH.UI.Lang.languageSet;
import com.github.zhuaidadaya.MCH.UI.loadingWindow;
import com.github.zhuaidadaya.MCH.lib.json.JSONArray;
import com.github.zhuaidadaya.MCH.lib.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Resources extends Thread {
    public static int ErrCounter = 0;

    public static void fixResource(String resource, String fixTarget, boolean lineWrap) {
        try {
            createPath(fixTarget.substring(0, fixTarget.lastIndexOf("/")));

            ErrCounter += 1;

            LoadAssembly.badLoadAssembly("[Resource Thread/INFO] fixing resource:" + fixTarget, Resources.initLanguage.lang.get("fixing_resource"));

            //            InputStream in = new InputStreamReader(getResource(resource), StandardCharsets.UTF_8));

            BufferedReader br = new BufferedReader(new InputStreamReader(getResource(resource), StandardCharsets.UTF_8));

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

    public static InputStream getResource(String resource) {
        //        return new File(Objects.requireNonNull(initLanguage.class.getResource(resource)).getFile());
        return Resources.class.getResourceAsStream(resource);
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

    public static class initLanguage {
        public static HashMap<String, String> lang = new HashMap<>();

        public initLanguage() {
            createPath(Config.resPath);

            initLang("languages.json","/com/github/zhuaidadaya/resources/resource_files/","");
            initLang("commands/commands.json","/com/github/zhuaidadaya/resources/resource_files/","");
        }

        public static void initLang(String langFile,String resourceRoot, String targetLanguage) {
            String languagesPath = Config.resPath + langFile;

            File f = null;
            if(new File(Config.resPath + langFile).isFile()) {
                f = new File(Config.resPath + langFile);
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

                Config.languageSet = "Language@Auto";
                if(! (ErrCounter < 4)) {
                    Errors.errors(null, e, true, "LanguageParse", "", 700, 520, false);
                }

                //修复语言文件
                fixResource(resourceRoot + langFile, languagesPath, false);

                Errors.errors(null, e, false, "languageInit", "null", 700, 520, false);

                if(ErrCounter < 5) {
                    initLang(langFile,resourceRoot,targetLanguage);
                }
            } catch (Exception e) {
                Config.languageSet = "Language@Auto";
                if(! (ErrCounter < 4)) {
                    Errors.errors(null, e, true, "LanguageParse", "", 700, 520, false);
                }

                //修复语言文件
                fixResource(resourceRoot + langFile, languagesPath, false);

                loadingWindow.loading.append(Arrays.toString(e.getStackTrace()).replace(", ", "\n") + "\n");
                Errors.errors(null, e, false, json.toString(), "null", 700, 520, false);

                if(ErrCounter < 5) {
                    initLang(langFile,resourceRoot,targetLanguage);
                }
            } catch (Error error) {
                if(! (ErrCounter < 4)) {
                    Errors.errors(error, null, true, "LanguageParse", "Your Language File Has Some Error\nPlease Check that and change\nif You Do not Know Error where is\nPlease use Language File by MCH", 700, 520, true);
                }

                if(ErrCounter < 5) {
                    initLang(langFile,resourceRoot,targetLanguage);
                }
            }

            if(ErrCounter < 4) {
                languageSet.Language();
            }
        }

        public static void initFromSelf(String langFile,String resourceRoot,String targetLanguage) {
            InputStream f = getResource(resourceRoot + langFile);

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
                if(! (ErrCounter < 4)) {
                    Config.languageSet = "Language@Auto";
                    if(e instanceof Exception) {
                        Errors.errors(null, (Exception) e, true, "LanguageParse", "Your Language File Has Some Error\nPlease Check that and change\nif You Do not Know Error where is\nPlease use Language File by MCH", 700, 520, true);
                    } else {
                        Errors.errors((Error) e, null, true, "LanguageParse", "Your Language File Has Some Error\nPlease Check that and change\nif You Do not Know Error where is\nPlease use Language File by MCH", 700, 520, true);
                    }
                }

                if(ErrCounter < 5) {
                    ErrCounter++;

                    initFromSelf(langFile,resourceRoot,targetLanguage);
                }
            }

            if(ErrCounter < 4) {
                ErrCounter++;

                initFromSelf(langFile,resourceRoot,targetLanguage);

                languageSet.Language();
            }
        }
    }
}