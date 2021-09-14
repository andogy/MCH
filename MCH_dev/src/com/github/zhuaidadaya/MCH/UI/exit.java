package com.github.zhuaidadaya.MCH.UI;

import com.github.zhuaidadaya.MCH.Events.Events;
import com.github.zhuaidadaya.MCH.Events.LoadAssembly;

import javax.swing.*;

public class exit extends JFrame {
    public static JButton buttonEXIT = new JButton();
    public static JButton buttonEXITNot = new JButton();
    public static JFrame jFrame = new JFrame();
    public static JTextArea jTextArea = new JTextArea();

    public static void Ex() {
        LoadAssembly.loadAssembly("[Main Thread/INFO] Stopping MCH","",false);

        try {
            Events.closeStreamOfUPD();
        } catch (Exception ignored) {

        }

        System.exit(1);
    }

}
