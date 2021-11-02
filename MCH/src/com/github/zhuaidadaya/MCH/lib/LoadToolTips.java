package com.github.zhuaidadaya.MCH.lib;

import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.lib.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoadToolTips {
    public static void loadToolTips(JSONObject index) {
        System.out.println(index);
    }


    public static void load(String index) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Resources.getResource(index,Resources.class)));

            String cache;
            StringBuilder all = new StringBuilder();
            while((cache = br.readLine()) != null) {
                all.append(cache);
            }

            JSONObject indexes = new JSONObject(all.toString());

            String get = Community.LangID == 0 ? "chinese" : Community.LangID == 1 ? "english" : "chinese_tw";

            br = new BufferedReader(new InputStreamReader(Resources.getResource(indexes.get(get).toString(),Resources.class)));

            all = new StringBuilder();
            while((cache = br.readLine()) != null) {
                all.append(cache);
            }

            JSONObject list = new JSONObject(all.toString());

            loadToolTips(list);

        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        load("/com/github/zhuaidadaya/resources/resource_files/tooltip/tooltip.index");
    }
}
