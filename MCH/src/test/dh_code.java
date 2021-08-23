package test;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class dh_code {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap<String, Integer>();

        long spawnTime = System.currentTimeMillis();

        long count = 150000;
        while(count > 0) {
            hashMap.put(String.valueOf(new Random().nextLong()), new Random().nextInt(3000));

            count -= 1;

        }

        System.gc();

        System.out.println("列表:\n" + hashMap);

        System.out.println("生成时间:" + (System.currentTimeMillis() - spawnTime) + "ms");

        long count2 = 100000000;
        long findCount = 0;
        HashMap trueCode = new HashMap<String,String>();

        while(count2 > 0) {
            count2 -= 1;
            Scanner sc = new Scanner(System.in);

            String code = String.valueOf(new Random().nextInt());
            //                    sc.next();

            long time = System.currentTimeMillis();

            System.out.println("目标:" + code);

            System.out.println(hashMap.get(code));

            if(hashMap.get(code) != null) {
                System.out.println("存在");
                trueCode.put("第" + findCount + "次",hashMap.get(code));
                hashMap.remove(code);
            } else {
                System.out.println("没有或已失效");
            }

            System.out.println("\033[33;1m搜寻时间:" + (System.currentTimeMillis() - time) + "ms");
            System.out.println("搜寻数量:" + hashMap.size() + "\033[0;0m");
            System.out.println("搜寻次数:" + findCount);
            System.out.println("成功搜寻:\n" + trueCode);

            System.out.println("___________________________");

            findCount += 1;
        }
    }
}
