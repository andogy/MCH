package project.Community.lib;

import project.Community.Community;
import project.Community.Events.LoadAssembly;
import project.Community.Times.times;

import java.io.File;

public class filesOperator {
    public static long fileSize = 0;

    public static void DeleteFiles(File[] path) {
        try {
            for (File f : path) {
                if (f.isFile()) {
                    if (!f.getName().equals("settings.ini") & !f.getName().equals("run.log") & !f.getName().equals("languages.json") & !f.getName().equals("commands.json")) {
                        LoadAssembly.loadAssembly("delete: " + f.getName() + "\n","");
                        f.delete();
                    }
                }
                if (f.isDirectory()) {
                    DeleteFiles(f.listFiles());
                    f.delete();
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
                if (!Community.isDaemons) {
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
