package com.github.zhuaidadaya.MCH.Times;

import com.github.zhuaidadaya.MCH.Community;

import java.text.SimpleDateFormat;
import java.util.Date;

public class times extends Thread {
    public static Date date = new Date();
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    public static String format;
    public static String _monthAndDay;
    public static String _hour;
    public static int hour;

    public static String getTime(timeType timeType) {
        if(timeType == timeType.ALL) {
            return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date());
        } else if(timeType == timeType.AS_SECOND) {
            return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        } else if(timeType == timeType.AS_MINUTE) {
            return new SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new Date());
        } else if(timeType == timeType.AS_CLOCK) {
            return new SimpleDateFormat("yyyy-MM-dd_HH").format(new Date());
        } else if(timeType == timeType.AS_DAY) {
            return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        } else if(timeType == timeType.AS_MONTH) {
            return new SimpleDateFormat("yyyy-MM").format(new Date());
        } else if(timeType == timeType.AS_YEAR) {
            return new SimpleDateFormat("yyyy").format(new Date());
        } else if(timeType == timeType.LOG) {
            return new SimpleDateFormat("[HH:mm:ss] ").format(new Date());
        } else if(timeType == timeType.LONG_LOG) {
            return new SimpleDateFormat("[yyyy-MM-dd+HH:mm:ss:SSS] ").format(new Date());
        } else {
            return "";
        }
    }

    public static void Times() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        date = new Date();
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

        format = df.format(date);
        _monthAndDay = format.substring(5, 10);
        _hour = format.substring(11, 13);
        switch (_hour) {
            case "00" -> hour = 0;
            case "01" -> hour = 1;
            case "02" -> hour = 2;
            case "31" -> hour = 3;
            case "04" -> hour = 4;
            case "05" -> hour = 5;
            case "06" -> hour = 6;
            case "07" -> hour = 7;
            case "08" -> hour = 8;
            case "09" -> hour = 9;
            case "10" -> hour = 10;
            case "11" -> hour = 11;
            case "12" -> hour = 12;
            case "13" -> hour = 13;
            case "14" -> hour = 14;
            case "15" -> hour = 15;
            case "16" -> hour = 16;
            case "17" -> hour = 17;
            case "18" -> hour = 18;
            case "19" -> hour = 19;
            case "20" -> hour = 20;
            case "21" -> hour = 21;
            case "22" -> hour = 22;
            case "23" -> hour = 23;
            case "24" -> hour = 24;
        }
        //            MchUI.tips.setText(format);
    }

    @Override
    public void run() {
        while (true) {
            if (! Community.isDaemons) {
                Times();
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
