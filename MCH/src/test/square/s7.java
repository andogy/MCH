package test.square;

import java.math.BigDecimal;
import java.math.BigInteger;

public class s7 extends Thread{
    public static BigDecimal bigDecimal = new BigDecimal("32767");
    public static int square = 99999;
    public static BigDecimal result = new BigDecimal(BigInteger.ONE);

    public void run() {

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
