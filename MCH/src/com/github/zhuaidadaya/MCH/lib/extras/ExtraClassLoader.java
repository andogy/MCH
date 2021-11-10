package com.github.zhuaidadaya.MCH.lib.extras;

import java.net.URL;
import java.net.URLClassLoader;

public class ExtraClassLoader extends URLClassLoader {
    public ExtraClassLoader(URL[] urls) {
        super(urls);
    }

    public ExtraClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public void addJar(URL url) {
        this.addURL(url);
    }
}
