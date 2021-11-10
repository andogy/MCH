package com.github.zhuaidadaya.MCH.lib.extras;

import java.util.LinkedHashMap;
import java.util.Set;

public class ExtraVersions<N, X> extends LinkedHashMap<Object, Object> {

    public Object put(ExtraVersion exv) {
        return super.put(exv, exv);
    }

    public ExtraVersion get(ExtraVersion exv) {
        return (ExtraVersion) super.get(exv);
    }
}
