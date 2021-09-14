package com.github.zhuaidadaya.MCH.lib;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.*;

public class OSUtils {
    public static float cpuUsed() {
        Map<?, ?> m = OSUtils.cpuInfo();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<?, ?> m2 = OSUtils.cpuInfo();

        long u1 = Long.parseLong(m.get("user").toString());
        long n1 = Long.parseLong(m.get("nice").toString());
        long s1 = Long.parseLong(m.get("system").toString());
        long i1 = Long.parseLong(m.get("idle").toString());

        long u2 = Long.parseLong(m2.get("user").toString());
        long n2 = Long.parseLong(m2.get("nice").toString());
        long s2 = Long.parseLong(m2.get("system").toString());
        long i2 = Long.parseLong(m2.get("idle").toString());

        long t1 = u1 + s1 + n1;
        long t2 = u2 + s2 + n2;
        float t = t2 - t1;

        long iTotal1 = t1 + i1;
        long iTotal2 = t2 + i2;
        float iTotal = iTotal2 - iTotal1;

        float cpuUse = (t / iTotal) * 100;

        return cpuUse;
    }

    public static Map<?, ?> cpuInfo() {
        InputStreamReader i = null;
        BufferedReader br = null;
        Map<String, Object> m = new HashMap<>();

        try {
            i = new InputStreamReader(new FileInputStream("/proc/stat"));
            br = new BufferedReader(i);

            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("cpu")) {
                    StringTokenizer tokenizer = new StringTokenizer(line);
                    List<String> temp = new ArrayList<>();
                    while (tokenizer.hasMoreElements()) {
                        String v = tokenizer.nextToken();
                        temp.add(v);
                    }
                    m.put("user",temp.get(1));
                    m.put("nice",temp.get(2));
                    m.put("system",temp.get(3));
                    m.put("idle",temp.get(4));
                    m.put("iowait",temp.get(5));
                    m.put("irq",temp.get(6));
                    m.put("softirq",temp.get(7));
                    m.put("stealstolen",temp.get(8));
                }
            }
        } catch (Exception e) {

        } finally {
            try {
                br.close();
                i.close();
            } catch (Exception e) {

            }
        }

        return m;
    }

    public static int memoryUsed() {
        return Integer.parseInt(String.valueOf(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() / 1024));
    }

    public static int memoryMax() {
        return Integer.parseInt(String.valueOf(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax() / 1024));
    }

        public static void main(String[] args) {

    }
}
