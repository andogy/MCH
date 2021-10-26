package com.github.zhuaidadaya.MCH.lib;

import com.github.zhuaidadaya.MCH.UI.perf_UI;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class PTEST {
    public static AtomicInteger monthSum;
    public static boolean rabbitOver = false;
    public static long ticking = 20000;
    public static long start;

    public static int rabbits(long getM, int thread) {
        HashMap<Integer, Integer> rabbits = new HashMap<>();
        HashMap<Integer, Integer> cacheRabbits = new HashMap<>();
        HashMap<Integer, Integer> reproduction = new HashMap<>();

        rabbitOver = false;

        try {
            int grs = 0;

            rabbits.put(0, 0);
            rabbits.put(1, 0);

            monthSum = new AtomicInteger(0);

            while(true) {
                if(getM < monthSum.get())
                    break;

                start = System.currentTimeMillis();
                monthSum.addAndGet(1);
                grs = 0;

                try {
                    for(int cache : cacheRabbits.keySet())
                        rabbits.put(cache, cacheRabbits.get(cache));

                    cacheRabbits.clear();
                    reproduction.clear();

                    rabbits.keySet().forEach(rabbitID -> {
                        int month = rabbits.get(rabbitID);
                        rabbits.put(rabbitID, month + 1);
                        if(! (month < 3)) {
                            reproduction.put(rabbitID, month);
                        }
                    });

                    int rabID = 0;
                    try {
                        while(! reproduction.get(rabID).toString().equals("null")) {

                            if(! reproduction.get(rabID + 1).toString().equals("null")) {
                                grs++;
                                rabbits.put(rabbits.size(), 0);
                                rabbits.put(rabbits.size(), 0);
                                reproduction.remove(rabID);
                                reproduction.remove(rabID + 1);

                                rabID += 2;
                            }
                        }
                    } catch (Exception e) {

                    }
                } catch (Exception e) {

                } catch (OutOfMemoryError e) {
                    perf_UI.jt.setText(perf_UI.jt.getText() + "thread" + thread + ": " + e + "\n");
                }

                //                System.out.println("[Thread " + thread + "] seed:" + Math.log(rabbits.size()) + "\n[Thread " + thread + "] one tick finished in tick time(" + monthSum + "):" + (System.currentTimeMillis() - start) + "ms");

                ticking = 20000;

//                System.gc();
            }
        } catch (Error error) {
            error.printStackTrace();
            rabbitOver = true;
            return rabbits.size();
        }

        return rabbits.size();
    }

    public static void rabbitTest() {

        Scanner sc = new Scanner(System.in);

        System.out.println("how many thread are you want used in test");
        int threads = 12;
        try {
            threads = sc.nextInt();
        } catch (Exception ex) {

        }

        System.out.println("how many iterations are you want used in test");
        int getM = 600;
        try {
            getM = sc.nextInt();
        } catch (Exception ex) {

        }

        if(getM >= 1000) {
            System.out.println("we did not recommend running more than 1000 iterations \non machines with less than 128G of memory");
            System.out.println("if you have or regardless of memory size, please entry again");
            try {
                getM = sc.nextInt();
            } catch (Exception e) {

            }
        }

        System.out.println("extreme pressure?");
        boolean hurt = false;
        try {
            hurt = sc.nextBoolean();
        } catch (Exception e) {

        }

        if(hurt) {
            threads = 2099999999;
            getM = threads;
        }

        long start = System.currentTimeMillis();

        for(int i = threads; i > 0; i--) {
            int finalI = i;
            //            new Thread(() -> System.out.println("over in:" + rabbits(128, finalI))).start();
            int finalGetM = getM;
            new Thread(() -> System.out.println("over in:" + rabbits(finalGetM, finalI))).start();
        }

        int finalGetM1 = getM;
        new Thread(() -> {
            while(! rabbitOver) {

                //                if(System.currentTimeMillis() - start > ticking) {
                //                    System.out.println("tick time out of " + ticking + "ms, but it's running");
                //                    ticking += 20000;

                //                    System.out.println(monthSum);
                //                }
                System.out.println("now iterations:" + (monthSum));

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        long end;

        while(true) {

            if(finalGetM1 <= monthSum.get()) {
                end = (System.currentTimeMillis() - start);
                break;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("finished in " + end + "ms");
        System.exit(0);
    }

    public static void main(String[] args) {
        rabbitTest();
    }
}
