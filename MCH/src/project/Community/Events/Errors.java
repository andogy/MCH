package project.Community.Events;

import project.Community.Command.ini;
import project.Community.Community;
import project.Community.Times.times;
import project.Community.UI.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Errors {
    public static JFrame jFrame = new JFrame();
    public static JTextArea jTextArea = new JTextArea();

    public static boolean CannotHandle = false;

    public static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static Dimension screenSize = toolkit.getScreenSize();
    public static int width = screenSize.width;
    public static int height = screenSize.height;

    public static void errors(Error error, Exception exception, boolean cannotHandle, String exceptionSource) {
        if (!CannotHandle) {

            CannotHandle = cannotHandle;
            if (error != null) {
                jFrame.setTitle("Error Now");
            }

            if (exception != null) {
                jFrame.setTitle("Exception now");
            }

            if (exception != null & error != null) {
                jFrame.setTitle("Exception and Error now");
            }
            if (Community.saveErrorLog) {
                jFrame.setSize(300, 160);
            } else {
                jFrame.setSize(450, 300);
            }

            jFrame.add(jTextArea);

            jTextArea.setBounds(0, 0, 340, 160);
            jTextArea.setEditable(false);

            if (CannotHandle) {

                historyReader.BreakRead = true;

                historyReader.history = null;

                exit.jFrame.setVisible(false);
                MchUI.jFrame.setVisible(false);
                MenuUI.jFrame.setVisible(false);
                MenuUI2.jFrame.setVisible(false);
                ini.jFrame.setVisible(false);
                ExtraUI.jFrame.setVisible(false);

                jFrame.setVisible(true);
            }

            System.gc();

            jFrame.setResizable(false);

            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            //设置窗口位置
            jFrame.setLocation(width / 2 - jFrame.getWidth() / 2, height / 2 - jFrame.getHeight() / 2);

            String er = "";
            if (Community.saveErrorLog) {
                if (exception != null) {
                    er = Arrays.toString(exception.getStackTrace()).replace("  ", " ").replace("[", "    at ").replace("]", "").replace(",", "\n    at ");
                } else if (error != null) {
                    er = Arrays.toString(error.getStackTrace()).replace("  ", " ").replace("[", "    at ").replace("]", "").replace(",", "\n    at ");
                }
                if (er.substring(er.indexOf("at")).equals("at ")) {
                    er = er.replace("at ", "no Local");
                }

                FileWriter fr = null;
                if (exceptionSource.equals("history")) {
                    try {
                        fr = new FileWriter(ini.path + "history.txt", false);
                        fr.write("");
                        fr.close();
                    } catch (Exception ignored) {

                    }
                }

                System.out.println("[" + times.format + "]\n" + "Errors:出现错误");
                if (CannotHandle) {
                    LoadAssembly.loadAssembly("[" + times.format + "]\n" + "Errors: try restart the MCH\n");
                }

                if (exception != null) {
                    System.out.println(exception + "\n" + er);
                } else if (error != null) {
                    System.out.println(error + "\n" + er);
                }

                try {
                    File file = new File(ini.path + "errors.log");
                    FileWriter fw = new FileWriter(file, true);
                    if (file.length() > 2048000) {
                        file.delete();
                    }
                    if (exception != null) {
                        fw.write("[" + times.format + "]\n" + exception + "\n" + er + "\nSourceAt:" + exceptionSource + "\n" + "");
                    } else if (error != null) {
                        fw.write("[" + times.format + "]\n" + error + "\n" + er + "\nSourceAt:" + exceptionSource + "\n" + "");
                    }
                    fw.close();
                } catch (IOException ignored) {

                }
            } else {
                if (exception != null) {
                    er = exception + "\n" + Arrays.toString(exception.getStackTrace()).replace("  ", " ").replace("[", "    at ").replace("]", "").replace(",", "\n    at ");
                } else if (error != null) {
                    er = error + "\n" + Arrays.toString(error.getStackTrace()).replace("  ", " ").replace("[", "    at ").replace("]", "").replace(",", "\n    at ");
                }
                if (Community.LangID == 0) {
                    jTextArea.setText("""
                            MCH遇到了一个无法处理的错误
                            正在尝试重启MCH
                                                    
                            错误信息如下:

                            """);
                } else if (Community.LangID == 1) {
                    jTextArea.setText("""
                            MCH have a problem is cannot handle
                            Trying restart the MCH
                                                    
                            error info is as follows

                            """);
                }
                jTextArea.setText(jTextArea.getText() + er + "\nSourceAt:" + exceptionSource);
            }

            if (CannotHandle) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new reStart().start();

                jFrame.setSize(450, 300);
                jFrame.setLocation(width / 2 - jFrame.getWidth() / 2, height / 2 - jFrame.getHeight() / 2);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(-1);
            }
        }
    }

    public static void tips(int w,int h,String info) {
        jFrame.add(jTextArea);

        jFrame.setSize(w,h);

        jFrame.setLocation(width / 2 - jFrame.getWidth() / 2, height / 2 - jFrame.getHeight() / 2);

        jFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        jTextArea.setText(info);

        jFrame.setVisible(true);

        jFrame.setAlwaysOnTop(Community.onTop);
    }
}