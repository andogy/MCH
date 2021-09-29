package com.github.zhuaidadaya.MCH.lib;

import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.LoadAssembly;
import com.github.zhuaidadaya.MCH.Times.times;
import com.github.zhuaidadaya.MCH.UI.loadingWindow;

import java.io.File;

public class filesOperator {
    public static long fileSize = 0;
    public static long fileCount = 0;

    public static void countFiles(File[] files) {
        for (File f : files) {
            if (f.isDirectory())
                countFiles(f.listFiles());
            fileCount++;
        }
    }

    public static void DeleteFiles(File[] path) {
        try {
            for (File f : path) {
                if (f.isFile()) {
                    if (!f.getName().equals("settings.ini") & !f.getName().equals("run.log") & !f.getName().equals("languages.json") & !f.getName().equals("commands.json")) {
                        LoadAssembly.loadAssembly("[Main Thread/INFO] Delete File: " + f.getName(), "", false);
                        f.delete();
                        loadingWindow.progress.setValue(loadingWindow.progress.getValue() + 1);
                    }
                }
                if (f.isDirectory()) {
                    DeleteFiles(f.listFiles());
                    f.delete();
                    loadingWindow.progress.setValue(loadingWindow.progress.getValue() + 1);
                }
            }
        } catch (Exception ignored) {

        }
    }

    public static long ReadFiles(File[] path, String slow, boolean out) {
        fileSize = READ(path, slow, out);
        long size = fileSize;
        fileSize = 0;
        return size;
    }

    public static long READ(File[] path, String slow, boolean out) {
        try {
            for (File f : path) {
                if (! Community.isDaemons) {
                    if (slow.equals("slow")) {
                        Thread.sleep(1);
                        if (!Community.fastLoad) {
                            Thread.sleep(1);
                        }
                    }
                    if (f.isFile()) {
                        fileSize += f.length();
                        if (out) {
                            System.out.println(f.getPath() + ";");
                        }
                    } else if (f.isDirectory()) {
                        READ(f.listFiles(), slow, out);
                    }
                } else {
                    break;
                }
            }
        } catch (Exception ignored) {
            return 0;
        }
        return fileSize;
    }

    public static void saveCache(File file, File toFile, String name) {
        toFile.mkdirs();
        file.getName();
        file.renameTo(new File(toFile + "\\" + name + "_" + times.format.replace(" ", "_").replace(":", "-") + ".cache"));
    }
}
