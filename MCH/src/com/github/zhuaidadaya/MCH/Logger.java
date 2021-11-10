package com.github.zhuaidadaya.MCH;

import com.github.zhuaidadaya.MCH.lib.Log;

public class Logger {
    private Object loggerName = null;

    public Logger() {

    }

    public Logger(Object loggerName){
        setLoggerName(loggerName);
    }

    public void info(Object o) {
        if(loggerName != null)
            Log.writeLog(null, o, false, loggerName,"INFO");
    }

    public void warn(Object o) {
        if(loggerName != null)
            Log.writeLog(null, o, true, loggerName,"WARN");
    }

    public void error(Object o) {
        if(loggerName != null)
            Log.writeLog(null, o, true, loggerName,"ERROR");
    }

    public String getLoggerName() {
        return loggerName.toString();
    }

    public Logger setLoggerName(Object loggerName) {
        this.loggerName = loggerName;

        return this;
    }
}
