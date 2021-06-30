package project.Community;

import project.Community.Command.ini;
import project.Community.Events.LoadAssembly;
import project.Community.Times.times;
import project.Community.UI.MchUI;
import project.Community.UI.MenuUI2;
import project.Community.UI.exit;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static project.Community.Exits.*;
import static project.Community.UI.Lang.initLanguage.lang;

public class Exits {
    public static boolean small = false;

    public static int h;
    public static int w;

    public Exits() {
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadSucceed: Exits\n",lang.get("loading_exButtons_succeed"), new Color(99,128,87));
        Exit_Button();
    }

    public static void Exit_Button() {
        new MchUiEx();

        new iniEx();

        new MenuUi2();
    }
}

class iniEx {
    public iniEx() {
        ini.jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!MchUI.jFrame.isVisible()) {
                    exit.Ex();
                } else {
                    ini.jFrame.setVisible(false);
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
                if (ini.settingIni) {
                    exit.Ex();
                } else {
                    MenuUI2.jFrame.setVisible(false);
                }
            }
        });
    }
}