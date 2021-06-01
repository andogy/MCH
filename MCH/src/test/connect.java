package test;

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class connect extends Thread {
    public static BigDecimal bigDecimal = new BigDecimal(0);

    public static void Connect(String ip) {
        try {
            // 统一资源
            URL url = new URL(ip);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

            //设置超时
            httpURLConnection.setConnectTimeout(10000);

            //设置请求方式
            httpURLConnection.setRequestMethod("POST");

            // 设置字符编码0
            httpURLConnection.setRequestProperty("Charset", "UTF-8");

            long time = System.currentTimeMillis();

            // 打开到此 URL引用的资源的通信链接（如果尚未建立这样的连接）
            httpURLConnection.connect();

            System.out.println(httpURLConnection.getResponseMessage());

            if (httpURLConnection.getResponseMessage().equals("OK")) {
                System.out.println("connect time:" + (time - System.currentTimeMillis()));
                bigDecimal = bigDecimal.add(BigDecimal.valueOf(1));
            } else {
                System.out.println("a thread cannot connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connect("157f2e607fa2a757.natapp.cc:19132");
        //        new connect().start();
        //        int i = 1000;
        //        new countTime().start();
        //        while (i > 0) {
        //            az();
        //            System.out.println("?");
        //            i--;
        //        }
    }

    public static void az() {
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
        new connect().start();
    }

    public void run() {
        try {
            while (true) {
                Connect("");
            }
        } catch (Exception e) {
            //            e.printStackTrace();
        }
    }

    public static class countTime extends Thread {
        @Override
        public void run() {
            long time = 0;
            while (true) {
                try {
                    Thread.sleep(100);
                    System.out.println("connect:" + bigDecimal + ",times:" + time / 1000 + "s");
                    time += 100;
                } catch (Exception ignored) {

                }
            }
        }
    }
}
