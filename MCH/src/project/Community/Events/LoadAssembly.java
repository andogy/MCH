package project.Community.Events;

import project.Community.Command.ini;
import project.Community.Community;
import project.Community.UI.loadingWindow;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;

public class LoadAssembly {

    public static void loadAssembly(String Assembly, String status, Color statusColor) {
        File f = new File(ini.path + "run.log");

        loadingWindow.loading.setText(Assembly + loadingWindow.loading.getText());
        loadingWindow.status.setText(status);
        loadingWindow.status.setForeground(statusColor);

        if(Community.saveRunLog) {

            if(f.length() > 2048000) {
                f.delete();
            }

            try {

                FileWriter fw = new FileWriter(f, true);

                fw.write(Assembly);

                fw.close();
            } catch (Exception e) {
                Errors.errors(null, e, false, "loadAssembly");
            }

            if(f.length() > 10240000) {
                try {
                    FileWriter fileWriter = new FileWriter(f, false);
                    fileWriter.write("");
                } catch (Exception ignored) {

                }
            }
        } else {
            f.delete();
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void loadAssembly(String Assembly, String status) {
        loadAssembly(Assembly, status, new Color(232, 107, 22));
    }
}