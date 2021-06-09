package project.Community.Events.KeyListener;

import project.Community.Command.ini;
import project.Community.Community;
import project.Community.Events.Errors;
import project.Community.Events.Events;
import project.Community.Events.UPD.URLs;
import project.Community.Events.reStart;
import project.Community.Help.Helps;
import project.Community.UI.*;

import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class listener extends Thread {
    public static void setSwitchTipLine(int lines) {
        switchTip.tipLine = lines;
    }

    public void run() {
        new inputs();
        new ex();
        new mainFrame();
        new switchTip();
        new functionEditor();
    }
}

class inputs {
    public static Set<Integer> c = new HashSet<>();

    public inputs() {
        try {
            MchUI.input_Command.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    try {
                        getC().add(e.getKeyCode());
                        if (getC().size() > 1) {
                            //                        System.out.println(c);
                            String s = Arrays.toString(getC().toArray()).replace("[", "|").replace("]", "|").replace(",", "|").replace(" ", "");

                            if ((s.contains("|18|") & s.contains("|47|")) || (s.contains("|17|") & s.contains("|47|"))) {
                                if (MchUI.input_Command.getText().equals("")) {
                                    MchUI.input_Command.setText("/");
                                }
                            }

                            if (s.contains("|127|") & s.contains("|17|")) {
                                MchUI.input_Command.setText("");
                            }

                            if (s.contains("|27|") & s.contains("|18|")) {
                                exit.Ex();
                            }

                            if ((s.contains("|82|") & s.contains("|17|")) || (s.contains("|82|") & s.contains("|18|"))) {
                                new reStart().start();
                            }

                            if ((s.contains("|67|") & s.contains("|18|")) || (s.contains("|17|") & s.contains("|18|"))) {
                                if (Community.ColorID == 0) {
                                    Events.switchColor(1);
                                } else if (Community.ColorID == 1) {
                                    Events.switchColor(0);
                                }
                            }

                            if (s.contains("|76|") & s.contains("|18|")) {
                                if (Community.LangID == 0) {
                                    Events.switchLanguage(1);
                                } else if (Community.LangID == 1) {
                                    Events.switchLanguage(0);
                                }
                            }

                            if (s.contains("|112|") & s.contains("|18|")) {
                                int poi = MchUI.input_Command.getCaretPosition();
                                String colorCode1 = MchUI.input_Command.getText().substring(0, MchUI.input_Command.getCaretPosition());
                                String colorCode2 = MchUI.input_Command.getText().substring(MchUI.input_Command.getCaretPosition());
                                String colorCode = colorCode1 + "§" + colorCode2;

                                MchUI.input_Command.setText(colorCode);
                                MchUI.input_Command.setCaretPosition(poi + 1);
                            }

                            getC().clear();
                        } else if (getC().size() == 1) {
                            String s = Arrays.toString(getC().toArray()).replace("[", "|").replace("]", "|").replace(",", "|").replace(" ", "");

                            if (s.equals("|38|") || s.equals("|40|") || (s.contains("|9|") & MchUI.input_Command.getCaretPosition() >= 1)) {
                                int point = MchUI.input_Command.getCaretPosition();

                                MchUI.switchTip.setText(MchUI.input_Command.getText());
                                MchUI.switchTip.setVisible(true);
                                MchUI.switchTip.requestFocus();
                                MchUI.switchTip.setCaretPosition(point);
                                MchUI.input_Command.setVisible(false);
                            }
                        }
                    } catch (Exception ignored) {
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    c.clear();
                    //                    System.out.println(e.getKeyCode() + ":" + e.getKeyCode());

                }
            });
        } catch (Error er) {
            Errors.errors(er, null, false, "KeyListener");
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

                if (s.contains("|10|")) {
                    exit.Ex();
                }

                if (s.contains("|8|")) {
                    exit.jFrame.setVisible(false);
                }

                c.clear();
            }

            @Override
            public void keyReleased(KeyEvent e) {
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

                if (c.size() > 1) {

                    String s = Arrays.toString(c.toArray()).replace("[", "|").replace("]", "|").replace(",", "|").replace(" ", "");

//                    if (s.contains("|17|")  & s.contains("|47|")) {
//                        System.out.println("???");
//                        int poi = ExtraUI.functionEdit.getCaretPosition();
//                        String functions = ExtraUI.functionEdit.getText();
//                        functions = functions.substring(functions.indexOf(""));
//                        ExtraUI.functionEdit.setFocusable(true);
//                        System.out.println(functions);
//                    }

//                    if (s.contains("|8|")) {
//                        exit.jFrame.setVisible(false);
//                    }

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
        MchUI.command1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    c.add(e.getKeyCode());

                    String s = Arrays.toString(c.toArray()).replace("[", "|").replace("]", "|").replace(",", "|").replace(" ", "");

                    if (c.size() > 1) {
                        if (s.contains("|27|") & s.contains("|17|")) {
                            exit.Ex();
                        }

                        if (s.contains("|77|") & s.contains("|17|")) {
                            MenuUI.jFrame.setVisible(true);
                        }

                        if (s.contains("|71|") & s.contains("17")) {
                            Helps.gayhub();
                        }

                        if (s.contains("|127|") & s.contains("|17|")) {
                            MchUI.input_Command.setText("");
                        }

                        if (s.contains("|72|") & s.contains("|17|")) {
                            new MenuUI2();
                            MenuUI2.jFrame.setVisible(true);
                        }

                        if ((s.contains("|82|") & s.contains("|17|")) || (s.contains("|82|") & s.contains("|18|"))) {
                            new reStart().start();
                        }

                        if ((s.contains("|67|") & s.contains("|18|")) || (s.contains("|17|") & s.contains("|18|"))) {
                            if (Community.ColorID == 0) {
                                Events.switchColor(1);
                            } else if (Community.ColorID == 1) {
                                Events.switchColor(0);
                            }
                        }

                        if (s.contains("|76|") & s.contains("|18|")) {
                            if (Community.LangID == 0) {
                                Events.switchLanguage(1);
                            } else if (Community.LangID == 1) {
                                Events.switchLanguage(0);
                            }
                        }

                        if (s.contains("|122|") & s.contains("|123|")) {
                            System.out.println("\033[40;1m\033[32;1m" + "T" + "\033[0;1m\033[33;1m" + "h" + "\033[43;1m\033[34;1m" + "i" + "\033[40;1m\033[35;1m" + "s\033[0;0m " + "\u001B[41;1m\033[36;1m" + "a\u001B[0;0m " + "\u001B[40;1m\033[37;1m" + "c" + "\u001B[44;1m\033[38;1m" + "o" + "\u001B[46;1m\033[39;1m" + "l" + "\u001B[42;1m\033[31;1m" + "o" + "\u001B[47;1m\033[39;1m" + "r" + "\u001B[48;1m\033[31;1m" + "e" + "\u001B[43;1m\033[35;1m" + "g" + "\u001B[47;1m\033[30;1m" + "g" + "\033[0;0m");
                        }

                        if (s.contains("|17|") & s.contains("|85|")) {
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

class switchTip {
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

                    if (s.equals("|32|")) {
                        MchUI.switchTip.setText(MchUI.switchTip.getText() + " ");
                    }

                    if (!(s.equals("|38|") | s.equals("|40|") | s.equals("|9|"))) {
                        int point = MchUI.switchTip.getCaretPosition();

                        MchUI.input_Command.setVisible(true);
                        if (!s.contains("|8|")) {
                            MchUI.input_Command.setText(MchUI.switchTip.getText().replace("\n", "").replace("\r", "").replace("\t", ""));
                            MchUI.input_Command.setCaretPosition(point);
                        }
                        MchUI.switchTip.setText("");
                        MchUI.switchTip.setVisible(false);
                        setLight("");
                        tipLine = 0;
                        MchUI.input_Command.requestFocus();
                    } else if (!MchUI.switchTip.isFocusOwner()) {
                        tipLine = 0;
                    } else {
                        String String = MchUI.command1.getText();
                        int allLine = 0;
                        do {
                            allLine += 1;
                            String = String.substring(String.indexOf("\n") + 1);
                        } while (String.contains("\n"));

                        if (MchUI.input_Command.getText().equals("")) {
                            if (s.equals("|38|")) {
                                tipLine++;

                                if (tipLine > allLine) {
                                    tipLine = 1;
                                }
                            }

                            if (s.equals("|40|")) {
                                if (tipLine >= 1) {
                                    tipLine--;
                                }

                                if (tipLine == 0) {
                                    tipLine = allLine;
                                }
                            }

                            if (s.contains("|9|") || MchUI.switchTip.getText().contains("\t")) {
                                MchUI.switchTip.setText(MchUI.switchTip.getText().replace("\t", ""));

                                tipLine++;

                                if (tipLine > allLine) {
                                    tipLine = 1;
                                }
                            }
                        } else {
                            if (s.equals("|40|")) {
                                tipLine++;

                                if (tipLine > allLine) {
                                    tipLine = 1;
                                }
                            }

                            if (s.contains("|9|") || MchUI.switchTip.getText().contains("\t")) {
                                MchUI.switchTip.setText(MchUI.switchTip.getText().replace("\t", ""));

                                tipLine++;

                                if (tipLine > allLine) {
                                    tipLine = 1;
                                }
                            }

                            if (s.equals("|38|")) {
                                if (tipLine >= 1) {
                                    tipLine--;
                                }

                                if (tipLine == 0) {
                                    tipLine = allLine;
                                }
                            }
                        }

                        String command = MchUI.command1.getText();
                        String tips = "";
                        int useLine = tipLine;


                        if (MchUI.input_Command.getText().equals("")) {
                            try {
                                BufferedReader br = new BufferedReader(new FileReader(ini.path + "history.txt"));

                                while ((tips = br.readLine()) != null) {
                                    useLine--;
                                    if (useLine == 0) {
                                        break;
                                    }
                                }

                                MchUI.switchTip.setText(tips);
                            } catch (Exception ignored) {

                            }
                        } else {

                            while (useLine > 0) {
                                useLine--;
                                tips = command.substring(0, command.indexOf("\n") + 1);

                                command = command.substring(tips.length());

                                if (command.equals("")) {
                                    command = MchUI.command1.getText();
                                    //                            tipLine = 1;
                                }
                            }

                            try {
                                if (tips.contains("*")) {
                                    if (MchUI.input_Command.getText().contains(" ")) {
                                        if (MchUI.input_Command.getText().contains("[") & !MchUI.input_Command.getText().contains(",")) {
                                            MchUI.switchTip.setText(MchUI.input_Command.getText().substring(0, MchUI.input_Command.getText().replace("[", " [").replace(",", " ,").lastIndexOf(" ") + 1) + tips.substring(0, tips.indexOf(" ")));
                                        } else if (MchUI.input_Command.getText().contains(",")) {
                                            MchUI.switchTip.setText(MchUI.input_Command.getText().substring(0, MchUI.input_Command.getText().replace("[", " [").replace(",", " ,").lastIndexOf(" ")) + tips.substring(0, tips.indexOf(" ")));
                                        } else {
                                            MchUI.switchTip.setText(MchUI.input_Command.getText().substring(0, MchUI.input_Command.getText().replace("[", " [").replace(",", " ,").lastIndexOf(" ") + 1) + tips.substring(0, tips.indexOf(" ")));
                                        }
                                    } else {
                                        MchUI.switchTip.setText(tips.substring(0, tips.indexOf("*")));
                                    }
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }

                            setLight(tips);
                        }
                    }

                } catch (Exception ignored) {

                }

                c.clear();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public static Set<Integer> getC() {
        return c;
    }

    public static void setC(Set<Integer> c) {
        switchTip.c = c;
    }

    public static void setLight(String tips) {
        Document doc = MchUI.command1.getDocument();
        StyleContext sc = StyleContext.getDefaultStyleContext();
        StyleContext sc_normal = StyleContext.getDefaultStyleContext();
        AttributeSet aset = null;
        if (Community.ColorID == 0) {
            aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(239, 126, 32));
        } else if (Community.ColorID == 1) {
            aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(203, 119, 49));
        }

        AttributeSet aset_normal = null;

        if (Community.ColorID == 0) {
            aset_normal = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);
        } else if (Community.ColorID == 1) {
            aset_normal = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.white);
        }

        try {
            String command_text = MchUI.command1.getText();
            doc.remove(0, command_text.length());
            MchUI.command1.setText("");
            doc.insertString(0, command_text, aset_normal);

            int points = MchUI.command1.getText().indexOf(tips);

            doc.remove(points, tips.length());
            doc.insertString(points, tips, aset);

            MchUI.command1.setCaretPosition(points);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}