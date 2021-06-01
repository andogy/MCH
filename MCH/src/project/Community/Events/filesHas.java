package project.Community.Events;

import project.Community.Command.ini;
import project.Community.Community;
import project.Community.UI.MenuUI;
import project.Community.UI.MenuUI2;

import java.io.File;
import java.io.FileReader;

public class filesHas extends Thread {
    @Override
    public void run() {
        while (!Errors.CannotHandle) {
            if (!Community.isDaemons) {
                try {
                    Thread.sleep(100);
                    ini.parsing();
                    FileReader file = new FileReader(ini.path + ini.sets);
                    Community.iniHas = true;
                    file.close();
                } catch (Exception e) {
                    Community.iniHas = false;
                    MenuUI.jFrame.setVisible(false);
                    MenuUI2.jFrame.setVisible(false);
                }

                try {
                    File file = new File(ini.path);
                    if (!file.isDirectory()) {
                        file.mkdirs();
                    }
                } catch (Exception ignored) {

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
