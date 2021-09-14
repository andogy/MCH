package test;

import java.util.Arrays;
import java.util.HashMap;

public class HashMapToSTRS {
    public static String[] toSTRS(HashMap hashMap,int getNum) {
        String source = hashMap.toString().replace("{","").replace("}",",").replace(" ","");
        String[] str = new String[255];
        String[] str2 = new String[255];

        int i = 0;
        do {
            str[i] = source.substring(source.indexOf("=") + 1,source.indexOf(","));
            str2[i] = source.substring(0,source.indexOf("="));

            source = source.substring(source.indexOf(",") + 1);

            System.out.println(Arrays.toString(str).replace(", null",""));
            System.out.println(Arrays.toString(str2).replace(", null",""));

            i++;

        } while (source.contains(","));

        if (getNum == 1) {
            return str;
        } else if (getNum == 2) {
            return str2;
        }
        return null;
    }

    public static void main(String[] args) {
        HashMap<Integer,String> h = new HashMap<>();
        h.put(1,"az");
        h.put(114514,"114");
        System.out.println(h);
//        toSTRS(h);
    }
}
