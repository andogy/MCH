package com.github.zhuaidadaya.MCH;

import com.github.zhuaidadaya.MCH.lib.Log;

public class exOut {
    private Object exID = null;

    public void println(Object o) {
        if(exID != null)
            Log.writeLog(null, o, false, exID);
    }

    public exOut setExID(Object exID) {
        this.exID = exID;

        return this;
    }
}
