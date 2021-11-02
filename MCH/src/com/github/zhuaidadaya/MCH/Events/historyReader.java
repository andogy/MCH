package com.github.zhuaidadaya.MCH.Events;

import com.github.zhuaidadaya.MCH.Config.ConfigMain;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.UPD.URLs;
import com.github.zhuaidadaya.MCH.UI.MchUI;
import com.github.zhuaidadaya.MCH.UI.inputUI;
import com.github.zhuaidadaya.MCH.lib.Log;
import com.github.zhuaidadaya.MCH.lib.filesOperator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import static com.github.zhuaidadaya.MCH.lib.Resources.initLanguage.lang;

public class historyReader extends Thread {
    public static boolean UPD_Check = false;
    public static boolean BreakRead;

    //    这个参数用来检测外部写入历史,当文本编辑器将历史文件改变时使用这个变量确认后进行刷新
    public static long historyLength = 0;

    public static File file = new File(ConfigMain.logsPath + "history.txt");

    public static String s;

    public static String history = "";

    public static void flush() {

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader fr = new BufferedReader(fileReader);
            history = "";

            historyLength = file.length();

            if (!(historyLength > 2048000)) {

                // 使用readLine方法，一次读一行
                int lines = 0;
                int counts = 0;

                while ((s = fr.readLine()) != null) {
                    if (file.length() > 2) {
                        break;
                    }
                    //                    Thread.sleep(1);
                    if (lines > 8000) {
                        if (Community.historySaveID == 0) {
                            fr.close();
                            Log.packet(file,true);
                        }
                        FileWriter fileWriter = new FileWriter(file);
                        fileWriter.write("");
                        fileWriter.close();
                    }

                    if (s.length() > 512) {
                        s = s.substring(0, 512);
                    }

                    lines++;
                    counts++;

                    //                    if (Community.LangID == 0) {
                    //                        MchUI.tips.setText("历史记录加载中: 读取了" + lines + "行    " + (float) history.length() / 1024 + "Kb数据");
                    //                    } else if (Community.LangID == 1) {
                    //                        MchUI.tips.setText("History Loading: Read " + lines + " lines    " + (float) history.length() / 1024 + "Kb Data");
                    //                    }

//                    if(BreakRead & ! MchUI.input_Command.getText().equals("")) {
                    if (BreakRead & !inputUI.inputArea.getText().equals("")) {
                        history = "";
                        break;
                    }

                    if (s.length() >= 1)
                        history = s + "\n" + history;

                }

                if (counts == 0) {
//                    if(MchUI.input_Command.getText().equals("")) {
                    if (inputUI.inputArea.getText().equals("")) {
                        if (history.equals("") || history.equals("\n")) {
                            if (Community.LangID == 0) {
                                history = "没有历史命令记录!";
                            } else if (Community.LangID == 1) {
                                history = "There is no historical command record!";
                            }
                        }
                    }
                }

                if (!BreakRead | inputUI.inputArea.getText().equals("")) {
//                    if(! BreakRead | MchUI.input_Command.getText().equals("")) {
                    MchUI.commandDisplay.setText(history);
                }

                history = null;

            } else {
                if (Community.historySaveID == 0) {
                    fr.close();
                    filesOperator.saveCache(file, new File(ConfigMain.path + "save\\history\\"), "history");
                }

                if (Community.LangID == 0) {
                    MchUI.commandDisplay.setText("你的历史命令记录太大了! 为了内存安全,此记录无法读取");
                } else if (Community.LangID == 1) {
                    MchUI.commandDisplay.setText("The History File too big! For Memory Safe,We Cannot Read it");
                }
            }

            fileReader.close();
            fr.close();
        } catch (Exception e) {
//            if(MchUI.input_Command.getText().equals("")) {
            if (inputUI.inputArea.getText().equals("")) {
                historyLength = -1;
                MchUI.commandDisplay.setText(lang.get("cannotReadHistory"));
            }
        }
        BreakRead = true;
    }

    @Override
    public void run() {
        //        if (!BreakRead) {
        while (true) {
            if (MchUI.jFrame.isVisible()) {
                historyLength = file.length();

                if (Errors.CannotHandle) {
                    history = "";
                    break;
                }

                if (!Community.isDaemons) {
                    try {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

//                        if (MchUI.input_Command.getText().equals("")) {
                        if (inputUI.inputArea.getText().equals("")) {
                            if (!BreakRead) {
                                MchUI.commandDisplay.setText("");
                            }
                            if (file.isFile() & file.length() != historyLength) {
                                if (Community.historySaveID != 2) {
                                    flush();
                                }
                                BreakRead = true;
                            } else if (!BreakRead) {
                                if (Community.historySaveID != 2) {
                                    flush();
                                }

                                if (!UPD_Check) {

                                    if (!URLs.checkUPD()) {
                                        MchUI.tips.setText("");
                                    }

                                    UPD_Check = true;
                                } else {
                                    MchUI.tips.setText("");
                                }
                                BreakRead = true;
                            }
                        } else if (inputUI.inputArea.getText().equals("")) {
//                            } else if (MchUI.input_Command.getText().equals("")) {
                            MchUI.commandDisplay.setText("");
                        }
                    } catch (Exception e) {

                    }
                } else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
