package test;

import java.io.BufferedReader;
import java.io.FileReader;

public class jsonToJJson {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("G:\\Code-Java\\MCH\\MCH\\src\\project\\resources\\Json\\test.json"));
            String s;
            String command = "";
            while ((s = br.readLine()) != null) {
                command += s.replace("\n","").replace(" ","");
            }
            System.out.println(command);
            cmdj.cmd(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
