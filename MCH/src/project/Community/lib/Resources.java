package project.Community.lib;

import project.Community.Command.ini;
import project.Community.Community;
import project.Community.Events.Errors;
import project.Community.Events.LoadAssembly;
import project.Community.Times.times;
import project.Community.UI.Lang.languageSet;
import project.Community.UI.loadingWindow;
import project.Community.lib.json.JSONArray;
import project.Community.lib.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import static project.Community.lib.Resources.initLanguage.lang;

public class Resources extends Thread {
    public static int ErrCounter = 0;

    public static void fixResource(String resource, String fixTarget, boolean lineWrap) {
        try {
            ErrCounter += 1;

            LoadAssembly.badLoadAssembly("[" + times.format + "]" + "\n" + "fixing resource:" + fixTarget + "\n", lang.get("fixing_resource"));

            BufferedReader br = new BufferedReader(new InputStreamReader(getResource(resource), StandardCharsets.UTF_8));

            BufferedWriter writer = new BufferedWriter(new FileWriter(fixTarget, StandardCharsets.UTF_8));
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

    public static void fixJava(String resource, String fixTarget, boolean lineWrap) {
        try {
            new File("C:\\.MCH\\java").mkdirs();
            InputStream in = getResource(resource);

            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            OutputStream writer = (new FileOutputStream(fixTarget));
            String out;

            int interval_gc_cache = 300;
            int interval_gc = interval_gc_cache;
            int gcCount = 0;

            long length = 0;

            int out1;
            byte[] bytes = new byte[16384 * 4];
            while((out1 = in.read(bytes)) != - 1) {
                System.out.println(out1);

                writer.write(bytes, 0, out1);
            }

            //            while((out = br.readLine()) != null) {
            //                length += out.length();
            //
            //                String replace = out.replace("  ", "").replace(" \"", "\"").replace(" [", "[").replace(" {", "{");
            //                if(! lineWrap) {
            //                    writer.write(replace);
            //                } else {
            //                    writer.write(replace + "\n");
            //                }
            //                writer.flush();
            //                interval_gc -= 1;
            //                if(interval_gc <= 0) {
            //                    interval_gc = interval_gc_cache;
            //                    gcCount += 1;
            //                    System.gc();
            //                }
            //
            //                loadingWindow.percentage.setText(out.length() + "/" + length);
            //            }

            br.close();
            writer.close();

            loadingWindow.percentage.setText("");
        } catch (Exception ignored) {
            ignored.printStackTrace();
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
                int inMapKeyCounter = 0;
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
                    ini.languageSet ="Language@Auto";
                    try {
                        ini.WriteIni();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if(e instanceof Exception) {
                        Errors.errors(null, (Exception) e, true, "LanguageParse", "Your Language File Has Some Error\nPlease Check that and change\nif You Do not Know Error where is\nPlease use Language File by MCH");
                    } else if(e instanceof Error) {
                        Errors.errors((Error) e, null, true, "LanguageParse", "Your Language File Has Some Error\nPlease Check that and change\nif You Do not Know Error where is\nPlease use Language File by MCH");
                    }
                }

                if(ErrCounter < 5) {
                    initFromSelf();
                }
            }

            if(ErrCounter < 4) {
                initCommandFromSelf();

                languageSet.Language();
            }
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
                } else if(Community.LangID == 3) {
                    targetLanguage = "chinese_tw";
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
                int inMapKeyCounter = 0;
                String inMapKey = inMap.keys().next();
                commands.put(inMapKey, inMap.getString(inMapKey));

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
                    ini.languageSet ="Language@Auto";
                    try {
                        ini.WriteIni();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if(e instanceof Exception) {
                        Errors.errors(null, (Exception) e, true, "LanguageParse", "Your Language File Has Some Error\nPlease Check that and change\nif You Do not Know Error where is\nPlease use Language File by MCH");
                    } else if(e instanceof Error) {
                        Errors.errors((Error) e, null, true, "LanguageParse", "Your Language File Has Some Error\nPlease Check that and change\nif You Do not Know Error where is\nPlease use Language File by MCH");
                    }

                    if(ErrCounter < 5) {
                        initCommandFromSelf();
                    }
                }
            }

            if(ErrCounter < 4) {
                languageSet.Language();
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
                } else if(Community.LangID == 3) {
                    targetLanguage = "chinese_tw";
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
                int inMapKeyCounter = 0;
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
                ini.languageSet ="Language@Auto";
                try {
                    ini.WriteIni();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(! (ErrCounter < 4)) {
                    Errors.errors(null, e, true, "LanguageParse", "");
                }

                //修复语言文件
                fixResource("/project/resources/resource_files/languages.json", languagesPath, false);

                Errors.errors(null, e, false, "languageInit", "null");

                if(ErrCounter < 5) {
                    init();
                }
            } catch (Exception e) {
                ini.languageSet ="Language@Auto";
                try {
                    ini.WriteIni();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(! (ErrCounter < 4)) {
                    Errors.errors(null, e, true, "LanguageParse", "");
                }

                e.printStackTrace();
                //修复语言文件
                fixResource("/project/resources/resource_files/languages.json", languagesPath, false);

                loadingWindow.loading.append(Arrays.toString(e.getStackTrace()) + "\n");
                Errors.errors(null, e, false, json.toString(), "null");

                if(ErrCounter < 5) {
                    init();
                }
            } catch (Error error) {
                if(! (ErrCounter < 4)) {
                    Errors.errors(error, null, true, "LanguageParse", "Your Language File Has Some Error\nPlease Check that and change\nif You Do not Know Error where is\nPlease use Language File by MCH");
                }

                if(ErrCounter < 5) {
                    init();
                }
            }

            if(ErrCounter < 4) {
                languageSet.Language();
            }
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
                } else if(Community.LangID == 3) {
                    targetLanguage = "chinese_tw";
                }

                for(int i = 0; ; i++) {
                    language = new JSONObject(languages.get(i).toString());
                    if(language.keys().next().equals(targetLanguage)) {
                        break;
                    }
                }
                JSONArray languageText = new JSONArray(language.get(language.keys().next()).toString());
                int i = languageText.length();
                int inMapKeyCounter = 0;
                i--;
                JSONObject inMap = new JSONObject(languageText.get(i).toString());
                String inMapKey = inMap.keys().next();
                commands.put(inMapKey, inMap.getString(inMapKey));

                while(i + 1 != 0) {
                    inMap = new JSONObject(languageText.get(i).toString());

                    Iterator<String> in = inMap.keys();

                    inMapKeyCounter = getElementsCounter(in);

                    in = inMap.keys();

                    while(inMapKeyCounter != 0) {
                        inMapKeyCounter -= 1;

                        String next = in.next();
                        commands.put(next, inMap.getString(next));
                    }

                    i--;
                }
            } catch (Exception e) {
                ini.languageSet ="Language@Auto";
                try {
                    ini.WriteIni();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(! (ErrCounter < 4)) {
                    Errors.errors(null, e, true, "LanguageParse", "Your Language File Has Some Error\nPlease Check that and change\nif You Do not Know Error where is\nPlease use Language File by MCH");
                }

                e.printStackTrace();
                fixResource("/project/resources/resource_files/commands.json", commandPath, false);

                if(ErrCounter < 5) {
                    initCommand();
                }
            }

            if(ErrCounter < 4) {
                languageSet.Language();
            }
        }
    }
}