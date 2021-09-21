package com.github.zhuaidadaya.MCH.Events;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.UI.*;
import com.github.zhuaidadaya.MCH.lib.Log;
import com.github.zhuaidadaya.MCH.lib.Resources;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Errors extends Throwable {
    public static JFrame jFrame = new JFrame();
    public static JTextArea jTextArea = new JTextArea();

    public static boolean CannotHandle = false;

    public static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static Dimension screenSize = toolkit.getScreenSize();
    public static int width = screenSize.width;
    public static int height = screenSize.height;

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
        errors(error, exception, cannotHandle, exceptionSource, message, 644, 466, true);
    }

    public static void errors(Error error, Exception exception, boolean cannotHandle, String exceptionSource, String message, int w, int h, boolean show) {
        try {
            if(! CannotHandle) {
                jFrame.setAlwaysOnTop(true);

                CannotHandle = cannotHandle;
                if(error != null)
                    jFrame.setTitle("Error Now");
                if(exception != null)
                    jFrame.setTitle("Exception now");
                if(exception != null & error != null)
                    jFrame.setTitle("Exception and Error now");

                jFrame.setSize(w, h);
                jFrame.add(jTextArea);

                jTextArea.setVisible(true);
                jTextArea.setBounds(0, 0, 1000, 1000);
                jTextArea.setEditable(false);

                new Thread(() -> {
                    while(true) {
                        if(CannotHandle) {
                            historyReader.BreakRead = true;
                            historyReader.history = null;

                            exit.jFrame.setVisible(false);
                            MchUI.jFrame.setVisible(false);
                            MenuUI.jFrame.setVisible(false);
                            MenuUI2.jFrame.setVisible(false);
                            Config.jFrame.setVisible(false);
                            ExtraUI.jFrame.setVisible(false);
                            loadingWindow.jFrame.setVisible(false);

                            jFrame.setVisible(true);
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                jFrame.setResizable(false);

                if(cannotHandle)
                    jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                else
                    jFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

                jFrame.setLocation(width / 2 - jFrame.getWidth() / 2, height / 2 - jFrame.getHeight() / 2);

                Object o = exception != null ? exception : error;
                String er = o + "\n" + Arrays.toString(exception.getStackTrace()).replace("  ", " ").replace("[", "    at ").replace("]", "").replace(",", "\n    at");

                if(Community.saveErrorLog) {
                    if(er.substring(er.indexOf("at")).equals("at "))
                        er = er.replace("at ", "no local");

                    FileWriter fr;
                    if(exceptionSource.equals("history")) {
                        try {
                            fr = new FileWriter(Config.path + "history.txt", false);
                            fr.write("");
                            fr.close();
                        } catch (Exception ignored) {

                        }
                    }

                    if(cannotHandle)
                        jTextArea.setText(String.format(Resources.initLanguage.lang.get("err-cannot-handle"), er));
                    else
                        jTextArea.setText(String.format(Resources.initLanguage.lang.get("err"), er));

                    jTextArea.setText(jTextArea.getText() + "\n" + String.format(Resources.initLanguage.lang.get("SourceAt"), exceptionSource) + String.format(Resources.initLanguage.lang.get("message"), message) + "\n\n" + String.format(Resources.initLanguage.lang.get("err-already-save"), Config.path + "logs/err/latest.log"));

                    File file = new File(Config.path + "logs/err/latest.log");

                    if(file.length() > 2048000) {
                        file.delete();
                    }
                    Log.writeLog(file, true, StandardCharsets.UTF_8, "[ERROR Thread/INFO] " + er + "\nSourceAt:" + exceptionSource, false);
                } else {
                    jTextArea.setText(String.format(Resources.initLanguage.lang.get("err-cannot-handle"), er));

                    Log.outLog(er,true);
                    jTextArea.setText(jTextArea.getText() + "\n" + String.format(Resources.initLanguage.lang.get("SourceAt"), exceptionSource) + String.format(Resources.initLanguage.lang.get("message"), message));
                }

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
                }

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