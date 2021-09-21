package com.github.zhuaidadaya.MCH.lib;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.LoadAssembly;
import com.github.zhuaidadaya.MCH.UI.loadingWindow;
import com.github.zhuaidadaya.MCH.lib.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class ExtraLoader {
    public static HashMap<String, HashMap> extras = new HashMap<>();
    public static HashSet<String> extraList = new HashSet<>();

    public static void upLoad() {
        try {
            Community.lis.uploadList(ExtraLoader.extras.keySet().toArray());
        } catch (Exception e) {
            upLoad();
        }
    }

    public void LoadExtra() {
        File f = new File(Config.path + "/extra/");

        loadingWindow.progress.setValue(0);
        loadingWindow.progress.setMaximum(f.listFiles().length);

        for(File extra : Objects.requireNonNull(f.listFiles())) {
            LoadAssembly.loadAssembly("[Extra Thread/INFO] loading extra: " + extra.getName());
            new Thread(() -> {
                extraList.add(extra.getAbsolutePath());
                if(extra.getName().contains(".jar")) {
                    ExtraLoader.this.getMANIFEST(extra.getAbsoluteFile());
                }
            }).start();
        }

//        for(String s : extras.keySet()) {
//            System.out.print(s + ":");
//            System.out.println(extras.get(s));
//        }

        Community.lis.uploadList(ExtraLoader.extras.keySet().toArray(new String[0]));

        //        Community.lis.showWindow();
    }

    public void getMANIFEST(File f) {
        try {
            URL url = new URL("jar:file:/" + f.getAbsoluteFile() + "!/conf/extra.json");
            InputStream inp = url.openStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(inp));

            String s;
            StringBuilder al = new StringBuilder();
            boolean loaded = false;
            try {
                while((s = br.readLine()) != null) {
                    al.append(s);
                }
            } catch (Exception e) {

            }

            JSONObject conf = new JSONObject(al.toString());

            try {
                loadExtra(f.getAbsolutePath(), conf.get("loader").toString(), f, false, false, conf);
                loaded = true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(! loaded) {
                loadExtra(f.getAbsolutePath(), "Mex.Declared", f, false, false, conf);
            }
        } catch (Exception e) {
            loadExtra(f.getAbsolutePath(), "Mex.Declared", f, false, true, null);
        }
    }

    public void loadExtra(String path, String loader, File f, boolean unloaded, boolean noConf, JSONObject conf) {
        HashMap<String, String> h = new HashMap<>();

        try {
            URL[] urls = new URL[]{};
            ExtraClassLoader classLoader = new ExtraClassLoader(urls, ClassLoader.getSystemClassLoader());
            classLoader.addJar(new File(path).toURI().toURL());
            Class<?> clazz = classLoader.loadClass(loader);
            Method method = clazz.getDeclaredMethod("onLoad");

            method.invoke(clazz.getDeclaredConstructor().newInstance());

            boolean loadF = false;
            Object res = null;
            try {
                Method r = clazz.getDeclaredMethod("r");

                res = r.invoke(clazz.getDeclaredConstructor().newInstance());

                //                System.out.println(res);
                if(res == null)
                    loadF = true;
            } catch (Exception ex) {
                loadF = true;
            }

            if(loadF) {
                LoadAssembly.badLoadAssembly(String.format("not-init-extra: %s", f.getAbsoluteFile()), "");
                h.put("reg-status", "no reg");
            } else {
                if(new File(res.toString()).isFile() & extraList.contains(res.toString()))
                    h.put("reg-status", "reg");
                else
                    h.put("reg-status", "fake reg");
            }

            if(unloaded)
                h.put("loaded", "false");
            else
                h.put("loaded", "true");

            extras.put(f.getName(), h);

            classLoader.close();
        } catch (Exception e) {
            h.put("reg-status", "no red");

            h.put("loaded", "false");

            extras.put(f.getName(), h);
        }

        if(noConf)
            h.put("conf", "null");
        else
            for(Object o : conf.keySet())
                h.put(o.toString(), conf.get(o.toString()).toString());

            loadingWindow.progress.setValue(loadingWindow.progress.getValue() + 1);

        upLoad();
    }
}
