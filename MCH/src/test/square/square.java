package test.square;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class square {
    public static BigDecimal bigDecimal = new BigDecimal("32767");
    public static int square = 99999;
    public static BigDecimal result = new BigDecimal(BigInteger.ONE);
    public static String times;

    public static void main(String[] args) {
        times = "开始时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        new end();

        new square1().start();
        new s2().start();
        new s3().start();
        new s4().start();
        new s5().start();
        new s6().start();
        new s7().start();
        new s8().start();
        new s9().start();
        new Gc().start();

        while (square != 0) {
            square--;
            result = result.multiply(bigDecimal);
//            System.out.println(result);
//            System.out.println("长度:" + result.toString().length());
//            System.out.println("余幂:" + square);
        }

        System.out.println("结果:" + result);
        System.out.println("长度:" + result.toString().length());
    }
}

class end {
    end() {
        Thread t = new Thread(() -> {
            System.out.println(square.times);
            System.out.println("结束时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        });
        Runtime.getRuntime().addShutdownHook(t);
    }
}
