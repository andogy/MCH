package project.Community.Events;

import project.Community.Command.ini;
import project.Community.Events.UPD.getJar;
import project.Community.Exits;
import project.Community.UI.exit;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class reStart extends Thread{

    @Override
    public void run() {
        try {
            //            运行一个新的MCH
            Runtime r = Runtime.getRuntime();
            String str = r.exec("cmd.exe /k \"" + getJar.getOldPath() + "\"").toString();
            File file = new File(ini.path + "res.cache");
            FileWriter fw = new FileWriter(file);
            fw.write(file.hashCode());
            fw.close();
            System.out.println("cmd.exe /k \"" + getJar.getOldPath() + "\"");

            //            if (Community.LangID == 0) {
            //                Errors.jTextArea.setText("""
            //                            无法完成MCH重启,请手动启动
            //                            此窗口将会在10秒后自动关闭
            //                            可以直接进行MCH重启而不需要管此窗口
            //
            //                            你也可也现在手动关闭此窗口再去重启MCH
            //
            //                            错误信息:
            //                            MCH程序名具有空格,无法进行重启
            //
            //                            位置:
            //                            """ + getJar.getOldPath());
            //            } else if (Community.LangID == 1) {
            //                Errors.jTextArea.setText("""
            //                            Cannot restart MCH,Please restart by you self
            //                            This window will close after 10 seconds
            //                            You can direct start new MCH
            //                            and not need process this window
            //
            //                            You can also close this window and go start MCH
            //
            //                            error info:
            //                            name of MCH have a or lots space,cannot restart
            //
            //                            local:
            //                            """ + getJar.getOldPath());
            //            }

            System.out.println(str);

            //       重启

            long startRestart = System.currentTimeMillis();

            do {
                FileReader fr = new FileReader(ini.path + "res.cache");
                fr.close();
                Thread.sleep(1);
            } while (System.currentTimeMillis() - startRestart <= 2000);

            boolean restart = new File(ini.path + "res.cache").delete();

//            exit.Ex();
            if (restart) {
                System.out.println("restart failed");

                Errors.tips(Errors.jFrame.getWidth(), Errors.jFrame.getHeight(), "restart failed");

                Thread.sleep(10000);

                exit.Ex();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            exit.Ex();
        }
    }
}
