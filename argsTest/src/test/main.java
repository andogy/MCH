package test;

import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
