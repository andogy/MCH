package com.github.zhuaidadaya.MCH.Command;

import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.Events.Events;
import com.github.zhuaidadaya.MCH.Events.historyReader;
import com.github.zhuaidadaya.MCH.Events.reStart;
import com.github.zhuaidadaya.MCH.UI.Color.displaySets;
import com.github.zhuaidadaya.MCH.lib.ExtraLoader;
import com.github.zhuaidadaya.MCH.Events.KeyListener.listener;
import com.github.zhuaidadaya.MCH.Exits;
import com.github.zhuaidadaya.MCH.Times.times;
import com.github.zhuaidadaya.MCH.UI.*;

import javax.swing.text.*;
import java.awt.*;

import static com.github.zhuaidadaya.MCH.lib.Resources.initLanguage.lang;

public class Parsing extends Thread {
    public static String display = "";

    public static boolean canSetLight = false;

    public static boolean inv = false;

    private static void parsing() {
        while (! Errors.CannotHandle & !inv) {
            if (! Community.isDaemons) {
                try {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (MchUI.jFrame.getWidth() > 800) {

                    }

                    if (!Exits.small) {
                        if (MchUI.jFrame.getWidth() < 500) {
                            MchUI.jFrame.setSize(500, MchUI.jFrame.getHeight());
                        }
                        if (MchUI.jFrame.getHeight() < 300) {
                            MchUI.jFrame.setSize(MchUI.jFrame.getWidth(), 300);
                        }
                    }

                    if (Exits.small) {
                        if (MchUI.jFrame.getHeight() > 50) {
                            Exits.small = false;
                        }
                    }

                    String allStr = MchUI.input_Command.getText();
                    //            如果tip文本框失去焦点,则让文本框回归正常input
                    if (!MchUI.switchTip.isFocusOwner()) {
                        //                关闭tip显示
                        MchUI.switchTip.setVisible(false);
                        MchUI.tip_scrollPane.setVisible(false);

                        //                让input显示
                        MchUI.input_Command.setVisible(true);
                        MchUI.input_scrollPane.setVisible(true);

                        MchUI.input_scrollPane.setFocusable(true);

                        listener.setSwitchTipLine(0);
                    } else {
                        canSetLight = true;
                        MchUI.input_Command.setVisible(false);
                    }

                    if (allStr.equals("coloregg")) {
                        displaySets.hadesColor();
                    }

                    //            愚人节彩蛋
                    if (times._monthAndDay.equals("04-01")) {
                        if (allStr.equals("coloreggs")) {
                            Events.switchColor(2);
                            displaySets.hadesColor();
                        }
                    }
                    if (allStr.length() > 511) {
                        if (allStr.length() > 512) {
                            allStr = allStr.substring(0, 512);
                            int poi = MchUI.input_Command.getCaretPosition();
                            MchUI.input_Command.setText(allStr);
                            MchUI.input_Command.setCaretPosition(poi);
                        }
                        if (Community.LangID == 0) {
                            MchUI.tips.setText("到达长度上限了,无法再进行输入");
                        } else if (Community.LangID == 1) {
                            MchUI.tips.setText("This Command is the Maximum Length,You Cannot input Any More\n");
                        }
                    } else {
                        if (MchUI.tips.getText().equals("到达长度上限了,无法再进行输入")) {
                            MchUI.tips.setText("");
                        }
                        Community.commandLength = MchUI.input_Command.getText().length();
                        Community.nowPoint = 0;

                        Config.jFrame.setAlwaysOnTop(Community.onTop);
                        MchUI.jFrame.setAlwaysOnTop(Community.onTop);
                        MenuUI.jFrame.setAlwaysOnTop(Community.onTop);
                        MenuUI2.jFrame.setAlwaysOnTop(Community.onTop);
                        ExtraUI.jFrame.setAlwaysOnTop(Community.onTop);

                        if (Errors.CannotHandle) {
                            break;
                        }

                        if (allStr.equals(".perf")) {
                            new reStart().restart("perf-cli");
                        }

                        if (allStr.equals(".p-test")) {
                            PressureUI.pressureUI();

                            Errors.tips(400, 340, lang.get("WARN-PTEST"), lang.get("WARN-PTEST-Title"));

                            MenuUI.jFrame.setVisible(false);
                            MenuUI2.jFrame.setVisible(false);
                            ExtraUI.jFrame.setVisible(false);
                            MchUI.jFrame.setVisible(false);

                            MchUI.input_Command.setText("");
                        }

                        if (allStr.equals(".reload")) {
                            loadingWindow.ui();
                            new ExtraLoader().LoadExtra();
                            MchUI.input_Command.setText("");
                            loadingWindow.jFrame.setVisible(false);
                        }

                        if (allStr.equals(".exit")) {
                            MchUI.input_Command.setText("");
                            exit.Ex();
                        }

                        if (allStr.equals(".function on") || allStr.equals(".function\n")) {
                            Community.functionEditing = true;
                            displaySets.extraDisplay();
                            MchUI.input_Command.setText("");
                            ExtraUI.functionEdit.setText("");
                        }

                        if (allStr.equals(".function off")) {
                            Community.functionEditing = false;
                            displaySets.extraDisplay();
                            MchUI.input_Command.setText("");
                        }

                        if (allStr.equals(".new exception")) {
                            try {
                                throw new IllegalStateException();
                            } catch (Exception ex) {
                                Errors.errors(null, ex, true, "MCH Crash Test", lang.get("crash-by-user"), 700, 520, true);
                            }
                        }

                        if (allStr.equals(".tick inv")) {
                            inv = true;
                            allStr = "";
                        }

                        if (allStr.contains("  ")) {
                            int poi = MchUI.input_Command.getCaretPosition();
                            allStr = allStr.replace("  ", " ");
                            MchUI.input_Command.setText(allStr);
                            if (MchUI.input_Command.getText().length() < poi) {
                                poi = MchUI.input_Command.getText().length() - 1;
                            }
                            MchUI.input_Command.setCaretPosition(poi);
                        }
                        if (allStr.indexOf(" ") == 0) {
                            allStr = allStr.substring(1);
                            MchUI.input_Command.setText(allStr);
                        }

                        if (MchUI.switchTip.getText().contains("  ")) {
                            int poi = MchUI.switchTip.getCaretPosition();
                            String str1 = MchUI.switchTip.getText().replace("  ", " ");
                            MchUI.switchTip.setText(str1);
                            if (MchUI.switchTip.getText().length() < poi) {
                                poi = MchUI.switchTip.getText().length() - 1;
                            }
                            MchUI.switchTip.setCaretPosition(poi);
                        }
                        display = "";

                        //            空输入时键入tab快捷输入斜杠
                        if (allStr.equals("\t")) {
                            MchUI.input_Command.setText("/");
                            MchUI.input_Command.setCaretPosition(1);
                        } else if (allStr.contains("\t")) {
                            allStr = allStr.replace("\t", "");
                            int poi = MchUI.input_Command.getCaretPosition();
                            MchUI.input_Command.setText(allStr);
                            MchUI.input_Command.setCaretPosition(allStr.length());
                            MchUI.input_Command.setCaretPosition(poi);
                        }

                        if (allStr.contains("\n") | allStr.contains("\r")) {
                            if (!allStr.replace("\n", "").contains("\n")) {
                                try {
                                    Events.Copy();
                                    MchUI.input_Command.setText("");
                                    if (Community.historySaveID != 2) {
                                        historyReader.flush();
                                    }
                                } catch (Exception e) {
                                    Errors.errors(null, e, true, "copy", "", 700, 520, true);
                                    //                        exit.Ex();
                                }
                            }
                        }

                        allStr = allStr.replace("\r", "").replace("\n", "");

                        Config.input_command = "Input@" + allStr;
                        Config.WriteIni();
                    }
                } catch (Exception ex) {
                    //                ex.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        Errors.errors(new TickThreadInvalidError(), null, true, "TickThread", lang.get("tick-invalid"), 700, 520, true);
    }

    @Override
    public void run() {
        new CommandParsing().start();

        new Thread(() -> {
            while (!Errors.CannotHandle) {
                if (!Community.isDaemons) {
                    try {
                        if (!MchUI.switchTip.isFocusOwner()) {
                            Thread.sleep(20);
                            if (listener.commandDoc.equals(MchUI.command1.getCharacterAttributes())) {
                                Document doc = MchUI.command1.getDocument();
                                AttributeSet aset = null;
                                StyleContext sc = StyleContext.getDefaultStyleContext();
                                if (Community.ColorID == 0) {
                                    aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(0, 0, 1));
                                } else if (Community.ColorID == 1) {
                                    aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 255, 254));
                                }
                                String command_text = MchUI.command1.getText();
                                doc.remove(0, command_text.length());
                                MchUI.command1.setText("");
                                doc.insertString(0, command_text, aset);

                                MchUI.command1.setCharacterAttributes(aset, true);
                            }
                            MchUI.command1.setCaretPosition(MchUI.switchTip.getCaretPosition());

                            if (!MenuUI.jFrame.isVisible() & !MenuUI2.jFrame.isVisible() & !ExtraUI.jFrame.isVisible()) {
                                MchUI.input_Command.setFocusable(true);
                                MchUI.input_Command.requestFocus();
                            }
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
            }
        }).start();

        new Thread(() -> {
            while (!Errors.CannotHandle) {
                if (!Community.isDaemons) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        MchUI.switchTip.setText(MchUI.switchTip.getText().replace("\t", ""));


                        if (MchUI.input_Command.getText().contains("//")) {
                            int point = MchUI.input_Command.getCaretPosition();
                            MchUI.input_Command.setText(MchUI.input_Command.getText().replace("//", "/"));
                            MchUI.input_Command.setCaretPosition(point - 1);
                        }
                        if (MchUI.input_Command.getText().contains("\t")) {
                            int point = MchUI.input_Command.getCaretPosition();
                            MchUI.input_Command.setText(MchUI.input_Command.getText().replace("\t", ""));
                            MchUI.input_Command.setCaretPosition(point);
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
            }
        }).start();

        parsing();
    }
}