package com.github.zhuaidadaya.MCH.Events.KeyListener;

import com.github.zhuaidadaya.MCH.Command.CommandParsing;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Config.ConfigUtil;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.Events.Events;
import com.github.zhuaidadaya.MCH.Events.UPD.URLs;
import com.github.zhuaidadaya.MCH.Events.reStart;
import com.github.zhuaidadaya.MCH.Help.Helps;
import com.github.zhuaidadaya.MCH.UI.*;
import com.github.zhuaidadaya.MCH.lib.OpenInExplore;
import com.github.zhuaidadaya.MCH.lib.Resources;

import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class listener extends Thread {
    public static int point = 0;

    public static AttributeSet commandDoc = null;

    public static void setSwitchTipLine(int lines) {
        switchTip.tipLine = lines;
    }

    public void run() {
        new errorFrame();
        new inputs();
        new ex();
        new mainFrame();
        new switchTip();
        new functionEditor();
        new minecraftLauncher_instance();
        new minecraftLauncher_instanceManager();
    }

    public static class switchTip {
        static int tipLine = 0;
        private static Set<Integer> c = new HashSet<>();

        public switchTip() {
            MchUI.switchTip.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    c.add(e.getKeyCode());
                    try {
                        String s = Arrays.toString(getC().toArray()).replace("[", "|").replace("]", "|").replace(",", "|").replace(" ", "");

                        if(s.equals("|32|")) {
                            MchUI.switchTip.setText(MchUI.switchTip.getText() + " ");
                        }

                        if(! (s.equals("|38|") | s.equals("|40|"))) {
                            int point = MchUI.switchTip.getText().length();

                            //                            MchUI.input_Command.setVisible(true);

                            if(! s.contains("|8|")) {
                                //                                MchUI.input_Command.setText(MchUI.switchTip.getText().replace("\n", "").replace("\r", "").replace("\t", ""));
                                inputUI.inputArea.setText(MchUI.switchTip.getText().replace("\n", "").replace("\r", "").replace("\t", ""));
                                //                                MchUI.input_Command.setCaretPosition(point);
                                inputUI.inputArea.setCaretPosition(point);
                            }
                            setLight("", true);
                            MchUI.switchTip.setText("");
                            MchUI.switchTip.setVisible(false);

                            inputUI.inputArea.setVisible(true);
                            inputUI.inputArea.setFocusable(true);
                            inputUI.inputArea.requestFocus();

                            tipLine = 0;
                        } else if(! MchUI.switchTip.isFocusOwner()) {
                            tipLine = 0;
                        } else {
                            String String = MchUI.commandDisplay.getText();
                            int allLine = 0;
                            allLine += 1;
                            String = String.substring(String.indexOf("\n") + 1);
                            while(String.contains("\n")) {
                                allLine += 1;
                                String = String.substring(String.indexOf("\n") + 1);
                            }

                            if(inputUI.inputArea.getText().equals("")) {
                                //                                if (MchUI.input_Command.getText().equals("")) {
                                if(s.equals("|38|")) {
                                    tipLine++;

                                    if(tipLine > allLine) {
                                        tipLine = 1;
                                    }
                                }

                                if(s.equals("|40|")) {
                                    if(tipLine >= 1) {
                                        tipLine--;
                                    }

                                    if(tipLine == 0) {
                                        tipLine = allLine;
                                    }
                                }
                            } else {
                                if(s.equals("|40|")) {
                                    tipLine++;

                                    if(tipLine > allLine) {
                                        tipLine = 1;
                                    }
                                }

                                if(s.equals("|38|")) {
                                    if(tipLine >= 1) {
                                        tipLine--;
                                    }

                                    if(tipLine == 0) {
                                        tipLine = allLine;
                                    }
                                }
                            }

                            String command = MchUI.commandDisplay.getText();
                            String tips = "";
                            int useLine = tipLine;

                            if(inputUI.inputArea.getText().equals("")) {
                                //                                if (MchUI.input_Command.getText().equals("")) {
                                try {
                                    BufferedReader br = new BufferedReader(new StringReader(MchUI.commandDisplay.getText()));

                                    while((tips = br.readLine()) != null) {
                                        useLine--;
                                        if(useLine == 0) {
                                            break;
                                        }
                                    }

                                    MchUI.switchTip.setText(tips);
                                    //                                    setLight(tips, false);
                                } catch (Exception ignored) {

                                }
                            } else {

                                while(useLine > 0) {
                                    useLine--;
                                    tips = command.substring(0, command.indexOf("\n") + 1);

                                    command = command.substring(tips.length());

                                    if(command.equals("")) {
                                        command = MchUI.commandDisplay.getText();
                                        //                            tipLine = 1;
                                    }
                                }

                                try {
                                    if(tips.contains(" ")) {
                                        if(inputUI.inputArea.getText().contains(" ")) {
                                            MchUI.switchTip.setText(inputUI.inputArea.getText().substring(0, inputUI.inputArea.getText().lastIndexOf(" ") + 1) + tips.substring(0, tips.indexOf(" ")));
                                        } else {
                                            MchUI.switchTip.setText(tips.substring(0, tips.indexOf(" ")));
                                        }
                                    }
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }

                                setLight(tips, false);
                            }
                        }

                    } catch (Exception ignored) {

                    }

                    c.clear();
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    c.clear();
                }
            });
        }

        public static Set<Integer> getC() {
            return c;
        }

        public static void setC(Set<Integer> c) {
            switchTip.c = c;
        }

        public static void setLight(String tips, boolean onlyDefault) {
            Document doc = MchUI.commandDisplay.getDocument();
            AttributeSet aset = null;
            StyleContext sc = StyleContext.getDefaultStyleContext();

            if(! onlyDefault) {
                if(Community.ColorID == 0) {
                    aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 145, 70));
                    //                    aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 255, 255));
                    aset = sc.addAttribute(aset, StyleConstants.Background, new Color(210, 210, 210));
                } else if(Community.ColorID == 1) {
                    aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(203, 119, 49));
                }
            } else {
                if(Community.ColorID == 0) {
                    aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);
                } else if(Community.ColorID == 1) {
                    aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.white);
                }
            }

            AttributeSet aset_normal = null;
            StyleContext sc_normal = StyleContext.getDefaultStyleContext();

            if(Community.ColorID == 0) {
                aset_normal = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);
            } else if(Community.ColorID == 1) {
                aset_normal = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.white);
            }

            try {
                String command_text = MchUI.commandDisplay.getText();
                int start;
                if(! onlyDefault) {
                    start = command_text.indexOf(tips);
                } else {
                    start = 0;
                }

                doc.remove(0, command_text.length());
                doc.insertString(0, command_text, aset_normal);

                doc.remove(start, tips.length());
                doc.insertString(start, tips, aset);
                MchUI.commandDisplay.setCaretPosition(start);

                commandDoc = aset;
            } catch (Exception e) {

            }
        }

        public static void setLightForInput(int start, int end, boolean onlyDefault) {
            setLightForInput(start, end, onlyDefault, "red");
        }

        public static void setLightForInput(int start, int end, boolean onlyDefault, String color) {
            int selectLength = - 1;
            try {
                //                selectLength = MchUI.input_Command.getSelectedText().length();
                selectLength = inputUI.inputArea.getSelectedText().length();
            } catch (Exception ex) {

            }

            if(selectLength == - 1) {
                //                Document doc = MchUI.input_Command.getDocument();
                Document doc = inputUI.inputArea.getDocument();
                AttributeSet aset = null;
                StyleContext sc = StyleContext.getDefaultStyleContext();

                if(! onlyDefault) {
                    if(Community.ColorID == 0) {
                        if(color.equals("red"))
                            aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 65, 50));
                    } else if(Community.ColorID == 1) {
                        if(color.equals("red"))
                            aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 70, 49));
                    }
                } else {
                    if(Community.ColorID == 0) {
                        aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);
                    } else if(Community.ColorID == 1) {
                        aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.white);
                    }
                }

                AttributeSet aset_normal = null;
                StyleContext sc_normal = StyleContext.getDefaultStyleContext();

                if(Community.ColorID == 0) {
                    aset_normal = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);
                } else if(Community.ColorID == 1) {
                    aset_normal = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.white);
                }

                try {
                    //                    int caret = onlyDefault ? MchUI.input_Command.getCaretPosition() : end;
                    //                    String ins = MchUI.input_Command.getText().substring(start, end);

                    //                    int caret = onlyDefault ? inputUI.inputArea.getCaretPosition() : end;
                    int caret = inputUI.inputArea.getCaretPosition();
                    String ins = inputUI.inputArea.getText().substring(start, end);

                    String command = inputUI.inputArea.getText();

                    doc.remove(0, command.length());
                    doc.insertString(0, command, aset_normal);
                    if(! onlyDefault)
                        inputUI.inputArea.setCaretPosition(caret);

                    doc.remove(start, end - start);
                    doc.insertString(start, ins, aset);
                    inputUI.inputArea.setCaretPosition(caret);

                    commandDoc = aset;
                } catch (Exception ignored) {

                }
            }
        }
    }
}

class inputs {
    public static Set<Integer> c = new HashSet<>();

    public inputs() {
        try {
            //            MchUI.input_Command.addKeyListener(new KeyListener() {
            inputUI.inputArea.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    try {
                        getC().add(e.getKeyCode());

                        if(getC().size() > 1) {
                            String s = Arrays.toString(getC().toArray()).replace("[", "|").replace("]", "|").replace(",", "|").replace(" ", "");

                            if((s.contains("|18|") & s.contains("|47|")) || (s.contains("|17|") & s.contains("|47|"))) {
                                //                                if (MchUI.input_Command.getText().equals("")) {
                                //                                    MchUI.input_Command.setText("/");
                                //                                }

                                if(inputUI.inputArea.getText().equals("")) {
                                    inputUI.inputArea.setText("/");
                                }
                            }

                            normallyKeyP.p(s);

                            if((s.contains("|67|") & s.contains("|18|")) || (s.contains("|17|") & s.contains("|18|"))) {
                                if(Community.ColorID == 0) {
                                    Events.switchColor(1);
                                } else if(Community.ColorID == 1) {
                                    Events.switchColor(0);
                                }
                            }

                            if(s.contains("|76|") & s.contains("|18|")) {
                                if(Community.LangID == 0) {
                                    Events.switchLanguage(1);
                                } else if(Community.LangID == 1) {
                                    Events.switchLanguage(0);
                                }
                            }

                            if(s.contains("|112|") & s.contains("|18|")) {
                                //                                int poi = MchUI.input_Command.getCaretPosition();
                                //                                String colorCode1 = MchUI.input_Command.getText().substring(0, MchUI.input_Command.getCaretPosition());
                                //                                String colorCode2 = MchUI.input_Command.getText().substring(MchUI.input_Command.getCaretPosition());
                                //                                String colorCode = colorCode1 + "§" + colorCode2;
                                //
                                //                                MchUI.input_Command.setText(colorCode);
                                //                                MchUI.input_Command.setCaretPosition(poi + 1);

                                int poi = inputUI.inputArea.getCaretPosition();
                                String colorCode1 = inputUI.inputArea.getText().substring(0, inputUI.inputArea.getCaretPosition());
                                String colorCode2 = inputUI.inputArea.getText().substring(inputUI.inputArea.getCaretPosition());
                                String colorCode = colorCode1 + "§" + colorCode2;

                                inputUI.inputArea.setText(colorCode);
                                inputUI.inputArea.setCaretPosition(poi + 1);
                            }

                            if(s.contains("|17|") & s.contains("|87|")) {
                                CommandParsing.toWiki();
                                CommandParsing.canToWiki = false;
                            }

                        } else if(getC().size() == 1) {

                            String s = Arrays.toString(getC().toArray()).replace("[", "|").replace("]", "|").replace(",", "|").replace(" ", "");

                            //                            if ((s.equals("|38|") || s.equals("|40|")) & MchUI.input_Command.getCaretPosition() >= 1) {
                            //                                int point = MchUI.input_Command.getCaretPosition();
                            //
                            //                                MchUI.input_Command.setVisible(false);
                            //                                MchUI.input_scrollPane.setVisible(false);
                            //
                            //                                MchUI.tip_scrollPane.setVisible(true);
                            //                                MchUI.switchTip.setVisible(true);
                            //                                MchUI.switchTip.setText(MchUI.input_Command.getText());
                            //                                MchUI.switchTip.requestFocus();
                            //                                MchUI.switchTip.setCaretPosition(point);
                            //                            }

                            if((s.equals("|38|") || s.equals("|40|")) & inputUI.inputArea.getCaretPosition() >= 1) {
                                int point = inputUI.inputArea.getCaretPosition();

                                inputUI.inputArea.setFocusable(false);
                                inputUI.inputArea.setVisible(false);

                                MchUI.tip_scrollPane.setVisible(true);
                                MchUI.switchTip.setVisible(true);
                                MchUI.switchTip.setText(inputUI.inputArea.getText());
                                MchUI.switchTip.setFocusable(true);
                                MchUI.switchTip.requestFocus();
                                MchUI.switchTip.setCaretPosition(point);
                            }
                        }
                    } catch (Exception ignored) {

                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    CommandParsing.canToWiki = true;
                    c.clear();
                }
            });
        } catch (Error er) {
            Errors.errors(er, null, false, "KeyListener", "", 700, 520, false, false);
        }
    }

    public Set<Integer> getC() {
        return c;
    }

    public void setC(Set<Integer> c) {
        inputs.c = c;
    }
}

class ex {
    public static Set<Integer> c = new HashSet<>();

    public ex() {
        exit.jTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                c.add(e.getKeyCode());
                String s = Arrays.toString(c.toArray()).replace("[", "|").replace("]", "|");

                if(s.contains("|10|")) {
                    exit.Ex();
                }

                if(s.contains("|8|")) {
                    exit.jFrame.setVisible(false);
                }

                c.clear();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                c.clear();
            }
        });
    }
}

class functionEditor {
    public static Set<Integer> c = new HashSet<>();

    public functionEditor() {
        ExtraUI.functionEdit.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                c.add(e.getKeyCode());

                if(c.size() > 1) {
                    String s = Arrays.toString(c.toArray()).replace("[", "|").replace("]", "|").replace(",", "|").replace(" ", "");

                    c.clear();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                c.clear();
            }
        });
    }
}

class mainFrame {
    public static Set<Integer> c = new HashSet<>();

    public mainFrame() {
        MchUI.commandDisplay.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    c.add(e.getKeyCode());

                    String s = Arrays.toString(c.toArray()).replace("[", "|").replace("]", "|").replace(",", "|").replace(" ", "");

                    if(c.size() > 1) {

                        normallyKeyP.p(s);

                        if(s.contains("|76|") & s.contains("|18|")) {
                            if(Community.LangID == 0) {
                                Events.switchLanguage(1);
                            } else if(Community.LangID == 1) {
                                Events.switchLanguage(0);
                            }
                        }

                        if(s.contains("|122|") & s.contains("|123|")) {
                            System.out.println("\033[40;1m\033[32;1m" + "T" + "\033[0;1m\033[33;1m" + "h" + "\033[43;1m\033[34;1m" + "i" + "\033[40;1m\033[35;1m" + "s\033[0;0m " + "\u001B[41;1m\033[36;1m" + "a\u001B[0;0m " + "\u001B[40;1m\033[37;1m" + "c" + "\u001B[44;1m\033[38;1m" + "o" + "\u001B[46;1m\033[39;1m" + "l" + "\u001B[42;1m\033[31;1m" + "o" + "\u001B[47;1m\033[39;1m" + "r" + "\u001B[48;1m\033[31;1m" + "e" + "\u001B[43;1m\033[35;1m" + "g" + "\u001B[47;1m\033[30;1m" + "g" + "\033[0;0m");
                        }

                        if(s.contains("|17|") & s.contains("|85|")) {
                            URLs.checkUPD = true;
                        }

                        c.clear();
                    }

                } catch (Exception ignored) {

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                c.clear();
            }
        });
    }
}

class errorFrame {
    public static Set<Integer> c = new HashSet<>();

    public errorFrame() {
        Errors.jTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    c.add(e.getKeyCode());

                    String s = Arrays.toString(c.toArray()).replace("[", "|").replace("]", "|").replace(",", "|").replace(" ", "");

                    if(c.size() > 1) {
                        normallyKeyP.p(s);

                        if(s.contains("|83|") & s.contains("|17|")) {
                            new reStart().restart();
                        }

                        if(s.contains("|79|") & s.contains("|17|")) {
                            System.exit(- 1);
                        }

                        if(s.contains("|82|") & s.contains("|17|")) {
                            if(Errors.CannotHandle) {
                                if(! Errors.reportCrash_onceOnly) {
                                    Errors.reportCrash_onceOnly = true;
                                }
                            } else {
                                if(! Errors.reportCrash) {
                                    Errors.reportCrash = true;
                                }
                            }

                            String reportFile = ConfigUtil.path + "logs/reports/report" + Errors.latestErrorReport.hashCode() + "/report.log";
                            File parent = new File(new File(reportFile).getParent());
                            parent.mkdirs();
                            BufferedWriter bw = new BufferedWriter(new FileWriter(reportFile));

                            bw.write(Errors.latestErrorReport);

                            bw.close();

                            Helps.openInBrowse("https://github.com/zhuaidadaya/MCH/issues/new?title=Error_Report_" + Errors.latestErrorReport.hashCode() + "&body=Crash Report<br><br>Some errors were encountered, here is errors detailed information:<br><>".replace(" ", "%20").replace("<", "%3C").replace(">", "%3E"));
                            //                            Helps.openInBrowse("https://github.com/zhuaidadaya/MCH/issues/new?title=Error_Report_" + Errors.latestErrorReport.hashCode() + "&body=" + Errors.latestErrorReport.replace("\n", "<br>").replace(" ", "%20").replace("<", "%3C").replace(">", "%3E").replace("/","%2F").replace("\\","%5C"));

                            OpenInExplore.open(parent.toString());

                            Errors.jTextArea.setText(Errors.jTextArea.getText().replace(Resources.initLanguage.lang.get("press_ctrl_r_to_report"), Resources.initLanguage.lang.get("please_drop_logs")));

                            Thread.sleep(500);

                            Errors.jFrame.setAlwaysOnTop(false);
                        }

                        if(s.contains("|69|") & s.contains("|17|")) {
                            System.exit(- 1);
                        }

                        c.clear();
                    }

                } catch (Exception ignored) {

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                c.clear();
            }
        });
    }
}

class minecraftLauncher_instance {
    public static Set<Integer> c = new HashSet<>();

    public minecraftLauncher_instance() {
        MinecraftLauncher.logsFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    c.add(e.getKeyCode());

                    String s = Arrays.toString(c.toArray()).replace("[", "|").replace("]", "|").replace(",", "|").replace(" ", "");

                    normallyKeyP.p(s);

                    if(s.contains("|76|") & s.contains("|18|")) {

                    }


                } catch (Exception ignored) {

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                c.clear();
            }
        });
    }
}

class minecraftLauncher_instanceManager {
    public static Set<Integer> c = new HashSet<>();

    public minecraftLauncher_instanceManager() {
        MinecraftLauncher.runningList.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    c.add(e.getKeyCode());

                    String s = Arrays.toString(c.toArray()).replace("[", "|").replace("]", "|").replace(",", "|").replace(" ", "");

                    if(s.equals("|127|")) {
                        MinecraftLauncher.removeMinecraftInstance();
                    }

                    normallyKeyP.p(s);

                } catch (Exception ignored) {

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                c.clear();
            }
        });
    }
}

class normallyKeyP {
    public static void p(String s) {
        if((s.contains("|67|") & s.contains("|18|")) || (s.contains("|17|") & s.contains("|18|"))) {
            if(Community.ColorID == 0) {
                Events.switchColor(1);
            } else if(Community.ColorID == 1) {
                Events.switchColor(0);
            }
        }

        if((s.contains("|82|") & s.contains("|17|")) || (s.contains("|82|") & s.contains("|18|"))) {
            new reStart().restart();
        }

        if(s.contains("|72|") & s.contains("|17|")) {
            new MenuUI2();
            MenuUI2.jFrame.setVisible(true);
        }

        if(s.contains("|127|") & s.contains("|17|")) {
            //            MchUI.input_Command.setText("");
            inputUI.inputArea.setText("");
        }

        if(s.contains("|71|") & s.contains("17")) {
            Helps.gayhub();
        }

        if(s.contains("|77|") & s.contains("|17|")) {
            MenuUI.jFrame.setVisible(true);
        }

        if(s.contains("|27|") & s.contains("|17|")) {
            exit.Ex();
        }

        if(s.contains("|17|") & s.contains("|87|")) {
            CommandParsing.toWiki();
            CommandParsing.canToWiki = false;
        }
    }
}