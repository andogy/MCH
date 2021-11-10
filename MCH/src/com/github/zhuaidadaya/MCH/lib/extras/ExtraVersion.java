package com.github.zhuaidadaya.MCH.lib.extras;

import java.util.LinkedHashMap;

public class ExtraVersion {
    LinkedHashMap<Object,Object> extraInfo = new LinkedHashMap<>();

    public ExtraVersion(String extraName, String extraVersion, String extraID, String extraUpdateID,String extraLoadLocation) {
        extraInfo.put("extraName", extraName);
        extraInfo.put("extraVersion",extraVersion);
        extraInfo.put("extraID", extraID);
        extraInfo.put("extraUpdateID",extraUpdateID);
        extraInfo.put("extraLoadLocation",extraLoadLocation);
    }

    public ExtraVersion() {
    }

    public String getName() {
        return extraInfo.get("extraName").toString();
    }

    public String getID() {
        return extraInfo.get("extraID").toString();
    }

    public String getUpdateID() {
        return extraInfo.get("extraUpdateID").toString();
    }

    public String getVersion() {
        return extraInfo.get("extraVersion").toString();
    }

    public String getLoadLocation() {
        return extraInfo.get("extraLoadLocation").toString();
    }

    public LinkedHashMap<Object,Object> getInfo() {
        return extraInfo;
    }

    public Object get(Object get) {
        return extraInfo.get(get);
    }

    public void uploadCustomInfo(Object infoName,Object infoValue) {
        extraInfo.put(infoName,infoValue);
    }
}
