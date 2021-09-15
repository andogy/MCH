package com.github.zhuaidadaya.MCH.UI;

import com.github.zhuaidadaya.MCH.Events.Events;
import com.github.zhuaidadaya.MCH.Events.LoadAssembly;
import com.github.zhuaidadaya.MCH.lib.Log;

import javax.swing.*;

public class exit extends JFrame {
    public static JButton buttonEXIT = new JButton();
    public static JButton buttonEXITNot = new JButton();
    public static JFrame jFrame = new JFrame();
    public static JTextArea jTextArea = new JTextArea();

    public static void Ex() {
        Log.writeLog("[Main Thread/INFO] Stopping MCH");

        try {
            Events.closeStreamOfUPD();
        } catch (Exception ignored) {

        }

        System.exit(1);
    }

}
