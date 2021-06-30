package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

public class utf16test {
    public static void main(String[] args) {
        try {

            BufferedReader br = new BufferedReader(new FileReader("G:\\Code-Java\\MCH\\MCH\\src\\project\\resources\\resource_files\\commands_16.json",StandardCharsets.UTF_16LE));
            String str = "";
            String buffered;

            while((buffered = br.readLine()) != null) {
                str += buffered + "\n";
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("G:\\Code-Java\\MCH\\MCH\\src\\project\\resources\\resource_files\\commands_16_.json",StandardCharsets.UTF_16LE));
            bw.write(str);
            System.out.println(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
