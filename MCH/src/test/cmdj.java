package test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class cmdj {
    /*
     本解析系统制作者:警告！本系统为无限嵌套解析器，如修改任何一参数，本系统将会全体报废！请勿随意更改参数！！
     */
    public static HashMap cmd(String pkg_text) throws JSONException, IOException {
            JSONObject pkg = new JSONObject(pkg_text);
            String bt = pkg.getString("title");//获取标题
            String des = pkg.getString("description");//获取描述
            String au = pkg.getString("author");//获取作者
            String ver = pkg.getString("ver");//获取版本号
            HashMap<Integer, String> cmda = new HashMap<Integer, String>();//哈市马屁
            cmda.clear();//刷新哈市马屁
            for (int nc = 4; nc > 0; nc--) {
                switch (nc) {
                    case 1 -> cmda.put((cmda.size() + 1), ver);
                    case 2 -> cmda.put((cmda.size() + 1), bt);
                    case 3 -> cmda.put((cmda.size() + 1), des);
                    case 4 -> cmda.put((cmda.size() + 1), au);
                }
            }
            JSONObject item = pkg.getJSONObject("item");//获取项目jsonob
            JSONArray command = item.getJSONArray("commands");//获取command项目（使用jsonarray是因为简易遍历性）
            System.out.println(pkg);
            if (command.length() > 0) {
                int cl = command.length();
                int i = 0;
                for (; i < cl; i++) {
                    JSONObject commandtree = command.getJSONObject(i);//遍历指令
                    Iterator<String> Iterator = commandtree.keys();//检测器
                    while (Iterator.hasNext()) //检测是否有下一个json阿伟
                    {
                        String key = Iterator.next();//指令
                        Object vals;
                        try {
                            vals = commandtree.getJSONObject(key);//tree结构
                        } catch (Exception e) {
                            vals = commandtree.getJSONArray(key);
                        }
                        String val = vals.toString();
                        System.out.println(key + ":");
                        System.out.println(val);
                        System.out.println("usage:");
                        System.out.println(new JSONObject(val).getString("description"));
                        Object jsonTn = new JSONTokener(val).nextValue();//解析json类型
                        if (jsonTn instanceof JSONObject) {//jsonob解析
                            JSONObject a = new JSONObject(val);//动态解析为jsonob
                            String b = a.getJSONArray("usage").toString();
                            System.out.println(b);
                            cmda.put((cmda.size() + 1), b);
                        } else if (jsonTn instanceof JSONArray) {//json阿伟解析
                            JSONArray a = new JSONArray(val);//动态解析为json阿伟
                            int b = a.length();
                            if (b > 0) {//再度进行遍历解析
                                for (int c = 0; c < b; c++) {
                                    JSONObject d = a.getJSONObject(c);//遍历tree结构
                                    Iterator<String> e = d.keys();//检测器
                                    while (e.hasNext()) {
                                        String f = d.getString(e.next());
                                        System.out.println(f);
                                        cmda.put((cmda.size() + 1), f);
                                    }
                                }
                            }
                        }

                        System.out.println("--------------------------");
                    }
                }
            }
        return cmda;
    }
}
