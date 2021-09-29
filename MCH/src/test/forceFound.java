package test;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class forceFound {
    public static String javas = "";

    public static String getJavas() {
        return javas;
    }

    public static void foundJava(File f,int foundPage) {
        try {
            if(foundPage != 0) {
                for(File file : Objects.requireNonNull(f.listFiles())) {
                    if(file.isFile()) {
                        if(file.getName().equals("java.exe") || file.getName().equals("javaw.exe"))
                            javas += file.getAbsolutePath() + "\n";
                    } else if(file.isDirectory()) {
                        foundJava(file,foundPage - 1);
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}
