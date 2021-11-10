package Mex;

import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.UPD.getJar;
import com.github.zhuaidadaya.MCH.Logger;
import com.github.zhuaidadaya.MCH.UI.ExtraUI;
import com.github.zhuaidadaya.MCH.lib.Resources;

import java.awt.*;

public class Declared extends Community {
    Logger logger = new Logger("Test Extra");

    public void onLoad() {
        Resources.initLanguage.initFromSelf("/resources/ex_lang.json", "", "testLang",this.getClass());
        uiSizeMap.put(ExtraUI.launchers, new Rectangle(0, 290, 150, 32));
        uiSizeMap.put(ExtraUI.randomProblem, new Rectangle(150, 290, 90, 32));
        uiSizeMap.put(ExtraUI.functions, new Rectangle(210, 290, 90, 32));

        logger.info(new getJar().getJarName(this.getClass()) + "/");

    }

    public String r() {
        return new getJar().getOldPath(this.getClass());
    }
}
