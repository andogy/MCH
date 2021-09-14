package com.github.zhuaidadaya.MCH.Events;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.UI.loadingWindow;
import com.github.zhuaidadaya.MCH.lib.Log;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

public class LoadAssembly {

    public static void loadAssembly(Object Assembly, Object status, Color statusColor,boolean warn) {
        File f = new File(Config.path + "logs/run/latest.log");

        try {
            loadingWindow.loading.setText(Assembly + "\n" + loadingWindow.loading.getText());
            loadingWindow.status.setText(status.toString());
            loadingWindow.status.setForeground(statusColor);
        } catch (Exception e) {

        }

        if(Community.saveRunLog) {
            if(f.length() > 2048000) {
                f.delete();
            }

            try {
                Log.writeLog(f.getPath(),true, StandardCharsets.UTF_8,Assembly,warn);
            } catch (Exception e) {
                Errors.errors(null, e, false, "loadAssembly","",700,520,false);
            }

            if(f.length() > 10240000) {
                try {
                    FileWriter fileWriter = new FileWriter(f, true);
                    fileWriter.write("");
                } catch (Exception ignored) {

                }
            }
        } else {
            f.delete();
        }

//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void loadAssembly(Object Assembly, Object status,boolean warn) {
        loadAssembly(Assembly, status, new Color(232, 107, 22),warn);
    }

    public static void badLoadAssembly(Object Assembly, Object status) {
        loadAssembly(Assembly, status, Color.RED,true);
    }

    public static void loadAssembly(Object Assembly) {
        loadAssembly(Assembly,"",false);
    }
}