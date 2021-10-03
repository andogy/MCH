package com.github.zhuaidadaya.MCH.Events;

import com.github.zhuaidadaya.MCH.UI.loadingWindow;
import com.github.zhuaidadaya.MCH.lib.Log;

import java.awt.*;

public class LoadAssembly {

    public static void loadAssembly(Object Assembly, Object status, Color statusColor, boolean warn) {
        try {
            loadingWindow.status.setText(status.toString());
            loadingWindow.status.setForeground(statusColor);
        } catch (Exception e) {

        }

        try {
            Log.writeLog(Assembly);
        } catch (Exception e) {
            Errors.errors(null, e, false, "loadAssembly", "", 700, 520, false,false);
        }

        //try {
        //    Thread.sleep(40);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
    }

    public static void loadAssembly(Object Assembly, Object status, boolean warn) {
        loadAssembly(Assembly, status, new Color(232, 107, 22), warn);
    }

    public static void badLoadAssembly(Object Assembly, Object status) {
        loadAssembly(Assembly, status, Color.RED, true);
    }

    public static void loadAssembly(Object Assembly) {
        loadAssembly(Assembly, "", false);
    }
}