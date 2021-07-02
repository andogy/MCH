package project.Community.lib;

import project.Community.Events.LoadAssembly;
import project.Community.Times.times;
import project.Community.UI.Lang.initLanguage;
import project.Community.UI.loadingWindow;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

import static project.Community.UI.Lang.initLanguage.lang;

public class Resources extends Thread {

    public static void fixResource(String resource, String fixTarget, boolean lineWrap) {
        try {
            LoadAssembly.badLoadAssembly("[" + times.format + "]" + "\n" + "fixing resource:" + fixTarget + "\n", lang.get("fixing_resource"));

            URL resourceURL = initLanguage.class.getResource(resource);
            BufferedReader br = new BufferedReader(new InputStreamReader(getResource(resource), StandardCharsets.UTF_8));

            FileWriter writer = new FileWriter(fixTarget);
            String out;

            int interval_gc_cache = 300;
            int interval_gc = interval_gc_cache;
            int gcCount = 0;

            long length = 0;

            while((out = br.readLine()) != null) {
                length += out.length();

                String replace = out.replace("  ", "").replace(" \"", "\"").replace(" [", "[").replace(" {", "{");
                if(! lineWrap) {
                    writer.write(replace);
                } else {
                    writer.write(replace + "\n");
                }
                writer.flush();
                interval_gc -= 1;
                if(interval_gc <= 0) {
                    interval_gc = interval_gc_cache;
                    gcCount += 1;
                    System.gc();
                }
                Thread.sleep(1);

                loadingWindow.percentage.setText(length + "/" + new File(resourceURL.toURI()).length());
            }

            br.close();
            writer.close();

            loadingWindow.percentage.setText("");
        } catch (Exception ignored) {

        }
    }

    public static InputStream getResource(String resource) {
        InputStream in = Resources.class.getResourceAsStream(resource);
//        return new File(Objects.requireNonNull(initLanguage.class.getResource(resource)).getFile());
        return in;
    }
}