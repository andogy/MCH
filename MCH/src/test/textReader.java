package test;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

public class textReader {
    public static void main(String[] args) {
        System.out.println((char) new Random().nextInt(10000));
//        for(Object o : "FK\n".chars().toArray())
//            System.out.println(o);
    }
}
