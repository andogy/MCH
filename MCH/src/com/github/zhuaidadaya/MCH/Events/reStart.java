package com.github.zhuaidadaya.MCH.Events;

import com.github.zhuaidadaya.MCH.Config.ConfigMain;
import com.github.zhuaidadaya.MCH.Events.UPD.getJar;
import com.github.zhuaidadaya.MCH.UI.exit;
import com.github.zhuaidadaya.MCH.lib.Resources;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class reStart {
    public static String jvmConf = "-Xmx200M -Xms200M";

    public static void setJvmConf(String conf) {
        jvmConf = conf;
    }

    public static void addJvmConf(String conf) {
        jvmConf += " " + conf;
    }

    public void restart(String... args) {
        try {
            Runtime r = Runtime.getRuntime();
            String str = r.exec("java " + jvmConf + " -jar \"" + new getJar().getOldPath(this.getClass()) + "\" " + Arrays.toString(args)).toString();
            File file = new File(ConfigMain.path + "res.cache");
            FileWriter fw = new FileWriter(file);
            fw.write(file.hashCode());
            fw.close();

            long startRestart = System.currentTimeMillis();

            do {
                FileReader fr = new FileReader(ConfigMain.path + "res.cache");
                fr.close();
                Thread.sleep(1);
            } while (System.currentTimeMillis() - startRestart <= 2000);

            boolean restart = file.isFile();

            
//            exit.Ex();
            if (restart) {
                file.delete();

                Errors.tips(400, 220,  Resources.initLanguage.lang.get("restart-failed"),Resources.initLanguage.lang.get("restart-failed-title"));

                Thread.sleep(10000);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Errors.errors(null,e,false, "restart", Resources.initLanguage.lang.get("restart-failed"),700,800,true,false);

        }
    }
}
