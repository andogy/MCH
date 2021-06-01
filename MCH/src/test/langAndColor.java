package test;

import java.util.Locale;

public class langAndColor {
    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        System.out.println(locale.getLanguage());
    }
}
