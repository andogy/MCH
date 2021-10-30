package com.github.zhuaidadaya.MCH.Help;

import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Errors;

import java.awt.*;
import java.net.URI;

import static com.github.zhuaidadaya.MCH.lib.Resources.initLanguage.lang;

public class Helps {
    public static void openInBrowse(String url) {
        try {
            URI uri = URI.create(url);
            Desktop dp = Desktop.getDesktop();
            if(dp.isSupported(Desktop.Action.BROWSE)) {
                dp.browse(uri);
            }
        } catch (Exception e) {
            Errors.errors(null, e, false, "Helps", "cannot open url", 700, 850, true, false);
        }
    }

    public static void Helps() {
        try {
            String url = "";
            if(Community.LangID == 0) {
                url = "https://github.com/andogy/MCH/tree/main/%E4%B8%AD%E6%96%87/%E5%B8%AE%E5%8A%A9/%E4%BD%BF%E7%94%A8%E5%B8%AE%E5%8A%A9";
            } else if(Community.LangID == 1) {
                url = "https://github.com/andogy/MCH/tree/main/English/Helps/Help";
            }
            openInBrowse(url);
        } catch (Exception e) {
            Errors.errors(null, e, false, "Helps", "", 700, 520, true, false);
        }
    }

    public static void agreement() {
        String url = "";

        try {
            if(Community.LangID == 0) {
                url = "https://github.com/andogy/MCH/tree/main/%E4%B8%AD%E6%96%87/%E5%B8%AE%E5%8A%A9/%E7%94%A8%E6%88%B7%E5%8D%8F%E8%AE%AE";
            } else if(Community.LangID == 1) {
                url = "https://github.com/andogy/MCH/tree/main/English/Helps/agreement";
            }
            openInBrowse(url);
        } catch (Exception e) {
            Errors.errors(null, e, false, "Helps", "", 700, 520, true, false);
        }
    }

    public static void gayhub() {
        try {
            String url = "";
            if(Community.LangID == 0) {
                url = "https://github.com/andogy/MCH/tree/main/%E4%B8%AD%E6%96%87";
            } else if(Community.LangID == 1) {
                url = "https://github.com/andogy/MCH/tree/main/English";
            }
            openInBrowse(url);
        } catch (Exception e) {
            Errors.errors(null, e, false, "Helps", "", 700, 520, true, false);
        }
    }

    public static void feedback() {
        try {
            String url = "https://github.com/zhuaidadaya/MCH/issues";
            openInBrowse(url);
        } catch (Exception e) {
            Errors.errors(null, e, false, "Helps", "", 700, 520, true, false);
        }
    }

    public static void iniHelps() {
        try {
            String url = lang.get("iniHelpLink");
            openInBrowse(url);
        } catch (Exception e) {
            Errors.errors(null, e, false, "Helps", "", 700, 520, true, false);
        }
    }
}
