package test;

import java.io.IOException;

public class fileSe {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("explorer /select, C:\\.MCH\\settings.ini");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
