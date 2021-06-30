package project.Community.Help;

import project.Community.Community;
import project.Community.Events.Errors;

import java.awt.*;
import java.net.URI;

public class Helps {
    public static void open(String url) {
        try {
            URI uri = URI.create(url);
            // 获取当前系统桌面扩展
            Desktop dp = Desktop.getDesktop();
            // 判断系统桌面是否支持要执行的功能
            if (dp.isSupported(Desktop.Action.BROWSE)) {
                dp.browse(uri);// 获取系统默认浏览器打开链接
            }
        } catch (Exception e) {
            Errors.errors(null, e, false, "Helps");
        }
    }

    public static void Helps() {
        try {
            String url = "";
            if (Community.LangID == 0) {
                url = "https://github.com/andogy/MCH/tree/main/%E4%B8%AD%E6%96%87/%E5%B8%AE%E5%8A%A9/%E4%BD%BF%E7%94%A8%E5%B8%AE%E5%8A%A9";
            } else if (Community.LangID == 1) {
                url = "https://github.com/andogy/MCH/tree/main/English/Helps/Help";
            }
            open(url);
        } catch (Exception e) {
            Errors.errors(null, e, false, "Helps");
        }
    }

    public static void agreement() {
        String url = "";

        try {
            if (Community.LangID == 0) {
                url = "https://github.com/andogy/MCH/tree/main/%E4%B8%AD%E6%96%87/%E5%B8%AE%E5%8A%A9/%E7%94%A8%E6%88%B7%E5%8D%8F%E8%AE%AE";
            } else if (Community.LangID == 1) {
                url = "https://github.com/andogy/MCH/tree/main/English/Helps/agreement";
            }
            open(url);
        } catch (Exception e) {
            Errors.errors(null, e, false, "Helps");
        }
    }

    public static void gayhub() {
        try {
            String url = "";
            if (Community.LangID == 0) {
                url = "https://github.com/andogy/MCH/tree/main/%E4%B8%AD%E6%96%87";
            } else if (Community.LangID == 1) {
                url = "https://github.com/andogy/MCH/tree/main/English";
            }
            open(url);
        } catch (Exception e) {
            Errors.errors(null, e, false, "Helps");
        }
    }

    public static void feedback() {
        try {
            String url = "mailto:1501917367@qq.com";
            URI uri = URI.create(url);
            open(url);
        } catch (Exception e) {
            Errors.errors(null, e, false, "Helps");
        }
    }

    public static void iniHelps() {
        try {
            String url = "";
            if (Community.LangID == 0) {
                url = "https://github.com/andogy/MCH/blob/main/%E4%B8%AD%E6%96%87/%E5%B8%AE%E5%8A%A9/%E8%AE%BE%E5%AE%9A%E5%B8%AE%E5%8A%A9/README.md";
            } else if (Community.LangID == 1) {
                url = "https://github.com/andogy/MCH/blob/main/English/Helps/settings/README.md";
            }
            open(url);
        } catch (Exception e) {
            Errors.errors(null, e, false, "Helps");
        }
    }
}
