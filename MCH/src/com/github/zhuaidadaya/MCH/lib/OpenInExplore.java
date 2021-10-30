package com.github.zhuaidadaya.MCH.lib;

import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Config.ConfigUtil;
import com.github.zhuaidadaya.MCH.Events.Errors;

public class OpenInExplore {
    public static void open(String s) {
        if(Community.os.equals("Linux")) {
            try {
                Runtime.getRuntime().exec("nautilus " + s);
            } catch (Exception exception) {
                Errors.tips(500, 300, "open-files-cannot", "");
            }
        } else {
            try {
                Runtime.getRuntime().exec("explorer.exe \"" + s.replace("/", "\\") + "\"");
            } catch (Exception exception) {
                Errors.tips(500, 300, "open-files-cannot", "");
            }
        }
    }
}
