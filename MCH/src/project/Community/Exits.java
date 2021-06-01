package project.Community;

import project.Community.Command.ini;
import project.Community.Events.LoadAssembly;
import project.Community.Times.times;
import project.Community.UI.MchUI;
import project.Community.UI.exit;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static project.Community.Exits.*;

public class Exits {
    public static boolean small = false;

    public static int h;
    public static int w;

    public Exits() {
        System.out.println("[" + times.format + "]\n" + "Exits:退出按钮事件就绪");
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadSucceed: Exits\n");
        Exit_Button();
    }

    public static void Exit_Button() {
        new MchUiEx();

        new iniEx();
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