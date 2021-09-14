package com.github.zhuaidadaya.MCH.UI;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class UiSizeMap {
    public HashMap<Object, Object> sizes = new HashMap<>();

    public Object get(Object o) {
        return sizes.get(o.hashCode());
    }

    public Rectangle getRectangle(Object o) {
        return (Rectangle) sizes.get(o.hashCode());
    }

    public Dimension getDimension(Object o) {
        return (Dimension) sizes.get(o.hashCode());
    }

    public void put(Object object, Object value) {
        sizes.put(object.hashCode(), value);
    }

    public void putAll(Component[] keys) {
        for(int i = 0;i < keys.length - 1;i ++)
            sizes.put(keys[i].hashCode(), keys[i].getBounds());
    }
}