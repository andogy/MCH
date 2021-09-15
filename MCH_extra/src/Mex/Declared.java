package Mex;

import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.Events.UPD.getJar;
import com.github.zhuaidadaya.MCH.UI.ExtraUI;
import com.github.zhuaidadaya.MCH.exOut;
import com.github.zhuaidadaya.MCH.lib.Resources;

import java.awt.*;

public class Declared extends Community {
    exOut out = new exOut().setExID("");

    public void onLoad() {
//        out.setExID("Name");
//
//        Resources.initLanguage.initLang("ex_lang.json", "", "testLang");
//        uiSizeMap.put(ExtraUI.launchers, new Rectangle(0, 290, 150, 32));
//        uiSizeMap.put(ExtraUI.randomProblem, new Rectangle(150, 290, 90, 32));
//        uiSizeMap.put(ExtraUI.functions, new Rectangle(210, 290, 90, 32));

        try {
            throw new IllegalStateException();
//            out.println(new getJar().getJarName(this.getClass()) + "/");
        } catch (Exception e) {
            Errors.errors(e,true,"e");
        }
    }
    public String r() {
        return new getJar().getOldPath(this.getClass());
    }
}
