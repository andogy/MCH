package test;

import java.util.Arrays;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("entry numbers");
        String input = sc.next();

        Object[] objects = new Object[10];

        try {
            while (true) {
                String cache = String.valueOf(input.charAt(0));
                objects[getNull(objects)] = cache;
                input = input.substring(1);
            }
        } catch (Exception e) {

        }

        System.out.println(Arrays.toString(objects));
    }

    public static int getNull(Object[] objects) {
        try {
            for (int i = 0; ; i++) {
                if (objects[i] == null)
                    return i;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return objects.length;
        }
    }
}
