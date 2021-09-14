package test;

import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.UI.perf_UI;

import java.util.HashMap;

public class e3x_1 {
    public static int thread = 0;
    public static Integer count = 0;
    public static Integer count_once = 0;
    public static Integer b = 0;

    public static HashMap<Integer,Long> times = new HashMap<>();

    public static void e3x_p1(int base,int thread) {
        int num;

        while(true) {
            long start = System.currentTimeMillis();
            num = base;
            while(! (num == 1) & ! (num == 0)) {
                if(num % 2 == 1) {
                    num *= 3;
                    num++;
                } else {
                    num /= 2;
                }

                count_once++;
            }

            long time = System.currentTimeMillis() - start;
            System.out.println("[Thread" + thread + "]n:" + base + "\n[Thread" + thread + "] Iterator times:" + count_once + "\n[Thread" + thread + "] tick times:" + time + "ms" + "\n------------------");

            times.put(base,time);

            count_once = 0;
            count++;
            base += thread;
        }
    }

    public static void main(String[] args) {
        Community.perf = true;
        new perf_UI();

        thread = 12;

        for(int i = 0; i <= thread - 1;i++) {
            int finalI = i;
            new Thread(() -> e3x_p1(finalI, finalI)).start();
        }

        while(true) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
