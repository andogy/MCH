package project.Community.Events.mchDir;

import project.Community.Command.ini;
import project.Community.Community;
import project.Community.Events.Errors;
import project.Community.UI.MenuUI2;
import project.Community.lib.filesOperator;

import java.awt.*;
import java.io.File;

public class dirSize extends Thread {
    String slow = "";

    @Override
    public void run() {
        while (!Errors.CannotHandle) {
            if (slow.equals("")) {
                if (Community.ColorID == 0) {
                    MenuUI2.mchDirSize.setForeground(Color.black);
                } else if (Community.ColorID == 1) {
                    MenuUI2.mchDirSize.setForeground(Color.white);
                }
            }
            if (!Community.isDaemons & MenuUI2.jFrame.isVisible() & Community.setsDisplayID == 3) {

                try {
                    Thread.sleep(100);

                    File[] caches = new File(ini.path).listFiles();
                    File[] UPD_caches = new File(ini.path + "save\\").listFiles();
                    long Size = filesOperator.ReadFiles(caches, slow,false);
                    long updCacheSize = filesOperator.ReadFiles(UPD_caches, slow,false);

                    if (Community.LangID == 0) {
                        MenuUI2.mchDirSize.setText("MCH文件占用: " + Size / 1024 + "KB         缓存: " + updCacheSize / 1024 + "KB");
                    } else if (Community.LangID == 1) {
                        MenuUI2.mchDirSize.setText("MCH File Size: " + Size / 1024 + "KB          cache: " + updCacheSize / 1024 + "KB");
                    }

                    if (Size / 1024 / 1024 > 150) {
                        MenuUI2.mchDirSize.setForeground(new Color(181, 61, 58, 200));
                    } else if (Size / 1024 / 1024 > 100) {
                        MenuUI2.mchDirSize.setForeground(new Color(240, 167, 50, 200));
                    } else {
                        MenuUI2.mchDirSize.setForeground(new Color(73, 165, 84, 200));
                    }

                    slow = "slow";

                    if (!Community.fastLoad) {
                        System.gc();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
