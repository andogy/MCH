package com.github.zhuaidadaya.MCH.Events.UPD;

import com.github.zhuaidadaya.MCH.Events.Errors;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class getJar {

    public String getJarName(Class<?> c) {
        try {
            File file = getFile(c);

            assert file != null;

            String full = file.getAbsolutePath();

            if(full.contains("\\"))
                return full.substring(full.lastIndexOf("\\") + 1);
            else
                return full.substring(full.lastIndexOf("/") + 1);
        } catch (Error var1) {
            Errors.errors(var1, null, false, "UPD", "", 700, 520, false);
            return null;
        }
    }

    public String getOldPath(Class<?> c) {
        File file = getFile(c);

        assert file != null;

        return file.getAbsolutePath();
    }

    private File getFile(Class<?> c) {
        String path = c.getProtectionDomain().getCodeSource().getLocation().getFile();

        try {
            path = URLDecoder.decode(path, StandardCharsets.UTF_8);
        } catch (Exception var2) {
            return null;
        }

        return new File(path);
    }
}

