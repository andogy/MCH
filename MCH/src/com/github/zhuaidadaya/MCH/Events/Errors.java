package com.github.zhuaidadaya.MCH.Events;

import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Config.ConfigMain;
import com.github.zhuaidadaya.MCH.UI.*;
import com.github.zhuaidadaya.MCH.Logger;
import com.github.zhuaidadaya.MCH.lib.Log;
import com.github.zhuaidadaya.MCH.lib.Resources;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

public class Errors extends Throwable {
    public static JFrame jFrame = new JFrame();
    public static JTextArea jTextArea = new JTextArea();

    public static boolean CannotHandle = false;

    public static String latestErrorReport = "";

    public static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static Dimension screenSize = toolkit.getScreenSize();
    public static int width = screenSize.width;
    public static int height = screenSize.height;

    public static boolean reportCrash_onceOnly = false;
    public static boolean reportCrash = false;

    public static Logger logger = new Logger("Error Thread");

    public Errors() {

    }

    public static void errors(Exception exception, boolean cannotHandle, String message) {
        errors(exception, cannotHandle, "Unknow", message);
    }

    public static void errors(Error error, boolean cannotHandle, String message) {
        errors(error, cannotHandle, "Unknow", message);
    }

    public static void errors(Error error, boolean cannotHandle, String exceptionSource, String message) {
        errors(error, null, cannotHandle, exceptionSource, message);
    }

    public static void errors(Exception exception, boolean cannotHandle, String exceptionSource, String message) {
        errors(null, exception, cannotHandle, exceptionSource, message);
    }

    public static void errors(Error error, Exception exception, boolean cannotHandle, String exceptionSource, String message) {
        errors(error, exception, cannotHandle, exceptionSource, message, 644, 466, true, false);
    }

    public static void errors(Error error, Exception exception, boolean cannotHandle, String exceptionSource, String message, int w, int h, boolean show, boolean showNow) {
        try {
            jFrame.setAlwaysOnTop(true);

            reportCrash = false;
            reportCrash_onceOnly = false;

            //                jTextArea.requestFocus();

            CannotHandle = cannotHandle;
            if(error != null) {
                jFrame.setTitle("Error Now");
                if(showNow) {
                    jTextArea.setText(Arrays.toString(error.getStackTrace()).replace("[", "").replace("]", "").replace(", ", "\n    "));
                    Log.writeErr("[Error Thread/ERROR] " + error.getMessage());
                }
            }
            if(exception != null)
                jFrame.setTitle("Exception now");
            if(exception != null & error != null)
                jFrame.setTitle("Exception and Error now");

            exit.jFrame.setVisible(false);
            MchUI.jFrame.setVisible(false);
            MenuUI.jFrame.setVisible(false);
            MenuUI2.jFrame.setVisible(false);
            ConfigMain.jFrame.setVisible(false);
            ExtraUI.jFrame.setVisible(false);
            inputUI.jFrame.setVisible(false);
            loadingWindow.jFrame.setVisible(false);
            MinecraftLauncher.mainFrame.setVisible(false);
            MinecraftLauncher.logsFrame.setVisible(false);

            jFrame.setVisible(true);

            jFrame.setSize(w, h);
            jFrame.add(jTextArea);

            jTextArea.setVisible(true);
            jTextArea.setBounds(0, 0, 1000, 1000);
            jTextArea.setEditable(false);

            historyReader.BreakRead = true;
            historyReader.history = null;

            jFrame.setResizable(false);

            if(cannotHandle)
                jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            else
                jFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

            jFrame.setLocation(width / 2 - jFrame.getWidth() / 2, height / 2 - jFrame.getHeight() / 2);

            Object o = exception != null ? exception : error;

            String er;
            if(exception != null)
                er = o + "\n" + Arrays.toString(exception.getStackTrace()).replace("  ", " ").replace("[", "    at ").replace("]", "").replace(",", "\n    at");
            else if(error != null)
                er = o + "\n" + Arrays.toString(error.getStackTrace()).replace("  ", " ").replace("[", "    at ").replace("]", "").replace(",", "\n    at");
            else
                er = "Unknown Error/Exception Source";

            if(Community.saveErrorLog) {
                if(er.substring(er.indexOf("at")).equals("at "))
                    er = er.replace("at ", "no local");

                FileWriter fr;
                if(exceptionSource.equals("history")) {
                    try {
                        fr = new FileWriter(ConfigMain.path + "history.txt", false);
                        fr.write("");
                        fr.close();
                    } catch (Exception ignored) {

                    }
                }
                if(cannotHandle)
                    jTextArea.setText(String.format(Resources.initLanguage.lang.get("err-cannot-handle"), er));
                else
                    jTextArea.setText(String.format(Resources.initLanguage.lang.get("err"), er));

                jTextArea.setText(jTextArea.getText() + "\n" + String.format(Resources.initLanguage.lang.get("SourceAt"), exceptionSource) + String.format(Resources.initLanguage.lang.get("message"), message) + "\n\n" + String.format(Resources.initLanguage.lang.get("err-already-save"), ConfigMain.path + "logs/err/latest.log"));

                File file = new File(ConfigMain.path + "logs/err/latest.log");

                if(file.length() > 2048000) {
                    file.delete();
                }
                if(! showNow)
                    logger.error(er + "\nSourceAt:" + exceptionSource);
            } else {
                if(! showNow)
                    logger.error(er);
                if(cannotHandle)
                    jTextArea.setText(String.format(Resources.initLanguage.lang.get("err-cannot-handle"), er));
                else
                    jTextArea.setText(String.format(Resources.initLanguage.lang.get("err"), er));

                jTextArea.setText(jTextArea.getText() + "\n" + String.format(Resources.initLanguage.lang.get("SourceAt"), exceptionSource) + String.format(Resources.initLanguage.lang.get("message"), message));
            }

            latestErrorReport = "---------ERROR REPORT----------\nError Level: " + (cannotHandle ? "2" : "1") + "\nError From: " + exceptionSource + "\nError Trace:\n" + er;

            jTextArea.append("\n\n");
            jTextArea.append(Resources.initLanguage.lang.get("press_ctrl_r_to_report"));
            jTextArea.append("\n");
            jTextArea.append(Resources.initLanguage.lang.get("press_ctrl_o_to_exit-ctrl_s_to_restart"));

            if(CannotHandle) {
                LoadAssembly.loadAssembly("[Main Thread/ERROR] Trying restart MCH", "", false);

                try {
                    Thread.sleep(6000);

                    new reStart().restart();

                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(- 1);

                jFrame.setVisible(show);
            }
        } catch (Exception | Error er) {
            //            Log.writeLog("[Error Thread/ERROR] Stopping MCH");
            //            System.exit(- 1);
        }
    }

    public static void tips(int w, int h, String info, String title) {
        jTextArea.setEditable(false);

        jFrame.add(jTextArea);

        jFrame.setTitle(title);

        jFrame.setSize(w, h);

        jFrame.setLocation(width / 2 - jFrame.getWidth() / 2, height / 2 - jFrame.getHeight() / 2);

        jFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        jTextArea.setText(info);

        jFrame.setVisible(true);

        jFrame.setAlwaysOnTop(true);
    }
}