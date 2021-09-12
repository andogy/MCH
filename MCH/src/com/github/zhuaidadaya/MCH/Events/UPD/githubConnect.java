package com.github.zhuaidadaya.MCH.Events.UPD;

import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.UI.MenuUI2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class githubConnect {
    public static void main(String[] args) {
        writeHosts();
    }
    public static void writeHosts() {
        String hosts = "C:/Windows/System32/drivers/etc/hosts";
        FileWriter fileWriter;
        try {
            BufferedReader bf = new BufferedReader(new FileReader(hosts));

            String s;
            StringBuilder all = new StringBuilder();
            while ((s = bf.readLine()) != null) {
                all.append(s).append("\n");
            }

            if (!all.toString().contains("199.232.68.133  raw.githubusercontent.com")) {
                if (Community.LangID == 0) {
                    MenuUI2.checkReturn.setText("尝试添加Hosts中");
                } else {
                    MenuUI2.checkReturn.setText("trying add Hosts");
                }
                fileWriter = new FileWriter(hosts,true);
                fileWriter.write("\n199.232.68.133  raw.githubusercontent.com");
                fileWriter.close();
                Thread.sleep(1500);
            }

        } catch (Exception e) {

        }
    }
}
