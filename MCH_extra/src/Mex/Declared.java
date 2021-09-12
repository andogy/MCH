package Mex;

import com.github.zhuaidadaya.MCH.Events.UPD.getJar;
import com.github.zhuaidadaya.MCH.exOut;

public class Declared {
    exOut out = new exOut().setExID("");

    public void onLoad() {
        out.setExID("Name");

        try {
            out.println(new getJar().getJarName(this.getClass()) + "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String r() {
        return new getJar().getOldPath(this.getClass());
    }
}
