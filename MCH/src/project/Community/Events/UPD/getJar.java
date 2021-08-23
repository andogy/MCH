package project.Community.Events.UPD;

import project.Community.Events.Errors;

import java.io.File;

public class getJar {

    public static String getJarName() {
        try {
            File file = getFile();

            assert file != null;
            return file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\")).replace("\\","");
        } catch (Error e) {
            Errors.errors(e,null,false,"UPD","");
        }
        return null;
    }

    public static String getOldPath() {
        File file = getFile();

        assert file != null;
        return file.getAbsolutePath();
    }

    private static File getFile() {
        String path = getJar.class.getProtectionDomain().getCodeSource().getLocation().getFile();

        try {
            //转换处理中文及空格
            path = java.net.URLDecoder.decode(path, "UTF-8");
        } catch (Exception e) {
            return null;
        }

        return new File(path);
    }
}

