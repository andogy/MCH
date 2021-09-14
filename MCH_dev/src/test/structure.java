package test;

import java.io.File;
import java.util.Objects;

public class structure {
    public static void main(String[] args) {
        File target = new File("G:\\zip");
        File[] list = target.listFiles();
        showFiles(list, "");
    }

    public static void showFiles(File[] list, String indent) {

        for (File f : list) {
            if (f.isFile()) {
                System.out.println(indent + f.getName());
            } else if (f.isDirectory()) {
                System.out.println(indent + f.getName());
                showFiles(Objects.requireNonNull(f.listFiles()), indent + "|_");
            }
        }

    }

}
