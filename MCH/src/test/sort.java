package test;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Comparator;

public class sort {
    public static void main(String[] args) {
        //        排序
        String[] st = "a44adf,sfa,azwad".split(",");
        Arrays.sort(st, new Comparator<String>()
        {
            @Override
            public int compare(String o1, String o2) {
                char s1 = o1.charAt(0);
                char s2 = o2.charAt(0);
                if(s1 == s2){
                    Integer i1 = Integer.valueOf(o1.substring(1));
                    Integer i2 = Integer.valueOf(o2.substring(1));
                    return i1.compareTo(i2);
                }else{
                    return o1.compareTo(o2);
                }
            }
        });
        String allKeys = StringUtils.join(st, ",");

        System.out.println(allKeys);
    }
}
