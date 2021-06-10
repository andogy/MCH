package project.Community.Command;

import project.Community.Community;
import project.Community.Events.Errors;
import project.Community.Events.Events;
import project.Community.Events.KeyListener.listener;
import project.Community.Events.historyReader;
import project.Community.Exits;
import project.Community.Times.times;
import project.Community.UI.Color.displaySets;
import project.Community.UI.*;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class Parsing extends Thread {
    public static String display = "";

    public static boolean flushHistory = true;

    public static boolean canSetLight = false;

    private static void parsing() {
        while (true) {
            if (!Community.isDaemons) {

                try {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (MchUI.jFrame.getWidth() > 800) {
                        //                        System.out.println("jf>8");
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

                    //          获取输入
                    //                if (MchUI.input_Command.isFocusOwner()) {
                    //                str = MchUI.input_Command.getText().substring(0, MchUI.input_Command.getCaretPosition());
                    String allStr = MchUI.input_Command.getText();
                    //            如果tip文本框失去焦点,则让文本框回归正常input
                    if (!MchUI.switchTip.isFocusOwner()) {

                        //                关闭tip显示
                        MchUI.switchTip.setVisible(false);
                        //                让input显示
                        MchUI.input_Command.setVisible(true);

                        //                同步文本
                        //                    if (!MchUI.switchTip.getText().equals("")) {
                        //                        MchUI.input_Command.setText(MchUI.switchTip.getText());
                        //                    }

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

                        Community.commandLength = MchUI.input_Command.getText().length();
                        Community.nowPoint = 0;

                        ini.jFrame.setAlwaysOnTop(Community.onTop);
                        MchUI.jFrame.setAlwaysOnTop(Community.onTop);
                        MenuUI.jFrame.setAlwaysOnTop(Community.onTop);
                        MenuUI2.jFrame.setAlwaysOnTop(Community.onTop);
                        ExtraUI.jFrame.setAlwaysOnTop(Community.onTop);

                        if (Errors.CannotHandle) {
                            break;
                        }

                        if (allStr.equals(".exit\n") || allStr.equals(".exit\r")) {
                            exit.jFrame.setSize(500, 300);
                            exit.jFrame.setVisible(true);
                            MchUI.input_Command.setText("");
                        }

                        if (allStr.equals(".function on\n") || allStr.equals(".function\r")) {
                            Community.functionEditing = true;
                            displaySets.extraDisplay();
                            MchUI.input_Command.setText("");
                            ExtraUI.functionEdit.setText("");
                        }

                        if (allStr.equals(".function off\n") || allStr.equals(".function off\r")) {
                            Community.functionEditing = false;
                            displaySets.extraDisplay();
                            MchUI.input_Command.setText("");
                        }

                        if (allStr.equals(".new exception\n") || allStr.equals(".new exception\r")) {
                            try {
                                throw new IllegalStateException();
                            } catch (Exception ex) {
                                Errors.errors(null, ex, true, "MCH test");
                            }
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

                        if (allStr.contains("/")) {
                            if (allStr.lastIndexOf("/") != 0) {
                                if (Community.LangID == 0) {
                                    Parsing.display = "第" + (allStr.lastIndexOf("/") + 1) + "位字符的斜杠是非法存在的,请删除它";
                                } else {
                                    Parsing.display = "The slash of the " + ((allStr.lastIndexOf("/")) + 1) + " character is illegal. Please delete it";
                                }
                            }
                        }

                        if (allStr.contains("\n")) {
                            if (!allStr.replace("\n", "").contains("\n")) {
                                try {
                                    Events.Copy();
                                    MchUI.input_Command.setText("");
                                    if (Community.historySaveID != 2) {
                                        historyReader.flush();
                                    }
                                } catch (IllegalStateException e) {
                                    Errors.errors(null, e, true, "copy");
                                    //                        exit.Ex();
                                }
                            }
                        }

                        allStr = allStr.replace("\r", "").replace("\n", "");

                        ini.input_command = "Input@" + allStr;
                        ini.WriteIni();
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

        System.out.println("????");
    }

    @Override
    public void run() {
        new CommandParsing().start();

        new Thread(() -> {
            while (true) {
                if (!Community.isDaemons) {
                    try {
                        if (!MchUI.switchTip.isFocusOwner()) {
                            Thread.sleep(1);
                            if (listener.commandDoc.equals(MchUI.command1.getCharacterAttributes())) {
                                System.out.println("??");
                                Document doc = MchUI.command1.getDocument();
                                AttributeSet aset = null;
                                StyleContext sc = StyleContext.getDefaultStyleContext();
                                if (Community.ColorID == 0) {
                                    aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(0,0,1));
                                } else if (Community.ColorID == 1) {
                                    aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255,255,254));
                                }
                                String command_text = MchUI.command1.getText();
                                doc.remove(0, command_text.length());
                                MchUI.command1.setText("");
                                doc.insertString(0, command_text, aset);

                                MchUI.command1.setCharacterAttributes(aset,true);
                            }
                            MchUI.command1.setCaretPosition(0);
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
