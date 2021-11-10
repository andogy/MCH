package com.github.zhuaidadaya.MCH.lib.extras;

import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Config.ConfigMain;
import com.github.zhuaidadaya.MCH.Events.LoadAssembly;
import com.github.zhuaidadaya.MCH.UI.loadingWindow;
import com.github.zhuaidadaya.MCH.Logger;
import com.github.zhuaidadaya.MCH.lib.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

public class ExtraLoader {
    ExtraVersion exCache = new ExtraVersion();
    Logger logger = new Logger("Extra Thread");

    public void LoadExtra(boolean mThreads) {
        File f = new File(ConfigMain.path + "/extra/");

        loadingWindow.progress.setValue(0);
        loadingWindow.progress.setMaximum(f.listFiles().length);

        for(File extra : Objects.requireNonNull(f.listFiles())) {
            logger.info("loading extra: " + extra.getName());
            if(mThreads) {
                new Thread(() -> {
                    if(extra.getName().contains(".jar")) {
                        Community.extraVersions.put(getMANIFEST(extra.getAbsoluteFile()));
                    }
                }).start();
            } else {
                if(extra.getName().contains(".jar")) {
                    Community.extraVersions.put(getMANIFEST(extra.getAbsoluteFile()));
                }
            }
        }

        //        for(String s : extras.keySet()) {
        //            System.out.print(s + ":");
        //            System.out.println(extras.get(s));
        //        }

        //        Community.lis.showWindow();
    }

    public ExtraVersion getMANIFEST(File f) {
        ExtraVersion exv = new ExtraVersion();

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
                exv = loadExtra(f.getAbsolutePath(), conf.get("loader").toString(), f, false, false, conf, "");
                loaded = true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(! loaded) {
                exv = loadExtra(f.getAbsolutePath(), "Mex.Declared", f, false, false, conf, "");
            }
        } catch (Exception e) {
            exv = loadExtra(f.getAbsolutePath(), "Mex.Declared", f, false, true, null, "");
        }

        return exv;
    }

    public void customLoader(Class<?> clazz, String methodPath) {
        try {
            Method method = clazz.getDeclaredMethod(methodPath);

            method.invoke(clazz.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
        }
    }

    public ExtraVersion loadExtra(String path, String loader, File f, boolean unloaded, boolean noConf, JSONObject conf, String methodPath) {
        HashMap<String, String> h = new HashMap<>();
        ExtraVersion exv = new ExtraVersion();

        try {
            URL[] urls = new URL[]{};
            ExtraClassLoader classLoader = new ExtraClassLoader(urls, ClassLoader.getSystemClassLoader());
            classLoader.addJar(new File(path).toURI().toURL());
            Class<?> clazz = classLoader.loadClass(loader);

            if(methodPath.equals(""))
                methodPath = "onLoad";

            customLoader(clazz, methodPath);

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
                if(new File(res.toString()).isFile())
                    h.put("reg-status", "reg");
                else
                    h.put("reg-status", "fake reg");
            }

            if(unloaded)
                h.put("loaded", "false");
            else
                h.put("loaded", "true");

            classLoader.close();
        } catch (Exception e) {
            h.put("reg-status", "no red");

            h.put("loaded", "false");
        }

        if(noConf)
            h.put("conf", "null");
        else
            for(Object o : conf.keySet())
                h.put(o.toString(), conf.get(o.toString()).toString());

        loadingWindow.progress.setValue(loadingWindow.progress.getValue() + 1);

        for(Object o : h.keySet())
            exv.uploadCustomInfo(o, h.get(o));

        return exv;
    }
}
