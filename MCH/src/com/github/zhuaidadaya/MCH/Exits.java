package com.github.zhuaidadaya.MCH;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.UI.MenuUI2;
import com.github.zhuaidadaya.MCH.UI.exit;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.UI.MchUI;
import com.github.zhuaidadaya.MCH.UI.PressureUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.github.zhuaidadaya.MCH.Exits.*;

public class Exits {
    public static boolean small = false;

    public static int h;
    public static int w;

    public Exits() {
        Exit_Button();
    }

    public static void Exit_Button() {
        new MchUiEx();

        new iniEx();

        new MenuUi2();

        new p_test();

        new ErrorsEx();
    }
}

class ErrorsEx {
    public ErrorsEx() {
        Errors.jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!MchUI.jFrame.isVisible()) {
                    exit.Ex();
                } else {
                    Errors.jFrame.setVisible(false);
                }
            }
        });
    }
}

class p_test {
    public p_test() {
        PressureUI.jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!MchUI.jFrame.isVisible()) {
                    exit.Ex();
                } else {
                    PressureUI.jFrame.setVisible(false);
                }
            }
        });
    }
}

class iniEx {
    public iniEx() {
        Config.jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!MchUI.jFrame.isVisible()) {
                    exit.Ex();
                } else {
                    Config.jFrame.setVisible(false);
                }
            }
        });
    }
}

class MchUiEx {
    public MchUiEx() {
        MchUI.jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (Community.exitButtonWillExit) {
                    exit.Ex();
                } else {
                    if (small) {
                        MchUI.jFrame.setSize(w, h);
                        small = false;
                    } else {
                        h = MchUI.jFrame.getHeight();
                        w = MchUI.jFrame.getWidth();
                        MchUI.jFrame.setSize(MchUI.jFrame.getWidth(), 0);
                        small = true;
                    }

                }
            }
        });
    }
}

class MenuUi2 {
    public MenuUi2() {
        MenuUI2.jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (Config.settingIni) {
                    exit.Ex();
                } else {
                    MenuUI2.jFrame.setVisible(false);
                }
            }
        });
    }
}