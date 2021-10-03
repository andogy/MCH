package com.github.zhuaidadaya.MCH.Events;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.Events.UPD.getJar;
import com.github.zhuaidadaya.MCH.UI.exit;
import com.github.zhuaidadaya.MCH.lib.Resources;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class reStart {
    public static String jvmConf = "-Xmx120M -Xms120M";

    public static void setJvmConf(String conf) {
        jvmConf = conf;
    }

    public static void addJvmConf(String conf) {
        jvmConf += " " + conf;
    }

    public void restart(String... args) {
        try {
            Runtime r = Runtime.getRuntime();
            String str = r.exec("%JAVA_HOME%\\bin\\java.exe " + jvmConf + " -jar \"" + new getJar().getOldPath(this.getClass()) + "\" " + Arrays.toString(args)).toString();
            File file = new File(Config.path + "res.cache");
            FileWriter fw = new FileWriter(file);
            fw.write(file.hashCode());
            fw.close();

            long startRestart = System.currentTimeMillis();

            do {
                FileReader fr = new FileReader(Config.path + "res.cache");
                fr.close();
                Thread.sleep(1);
            } while (System.currentTimeMillis() - startRestart <= 2000);

            boolean restart = new File(Config.path + "res.cache").delete();

//            exit.Ex();
            if (restart) {
                Errors.tips(400, 220,  Resources.initLanguage.lang.get("restart-failed"),Resources.initLanguage.lang.get("restart-failed-title"));

                Thread.sleep(10000);

                exit.Ex();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
//            Errors.tips(400, 220,  Resources.initLanguage.lang.get("restart-failed"),Resources.initLanguage.lang.get("restart-failed-title"));
            Errors.errors(null,e,false, Resources.initLanguage.lang.get("restart"), Resources.initLanguage.lang.get("restart-failed"),700,1080,true,false);
        }
    }
}
