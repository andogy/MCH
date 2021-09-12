package com.github.zhuaidadaya.MCH.UI;

import com.github.zhuaidadaya.MCH.Command.CommandParsing;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.lib.OSUtils;

import javax.swing.*;
import java.awt.*;

public class perf_UI {
    public static JFrame jFrame = new JFrame();
    public static JTextArea jt = new JTextArea();
    public static JProgressBar cpuBar = new JProgressBar();
    public static JProgressBar memBar = new JProgressBar();

    public perf_UI() {
        new Thread(() -> {
            memBar.setMaximum(OSUtils.memoryMax());
            while (!Errors.CannotHandle) {
                String used = String.valueOf(OSUtils.cpuUsed());
                cpuBar.setValue(Integer.parseInt(used.substring(0, used.indexOf("."))));
                memBar.setValue(OSUtils.memoryUsed());

                if (Community.perf & CommandParsing.command.equals(""))
                    jt.setText("CPU Used\n  -" + cpuBar.getValue() + "%" + "\n\nMemory Used\n  -max: " + memBar.getMaximum() / 1024 / 1024 + "G\n  -Used" + memBar.getValue() / 1024 + "MB");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (!Errors.CannotHandle) {
                String used = String.valueOf(OSUtils.cpuUsed());
                cpuBar.setValue(Integer.parseInt(used.substring(0, used.indexOf("."))));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        jFrame.setSize(640, 360);
        jFrame.setResizable(false);

        //        窗口初始化设置
        //获得屏幕大小
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        //设置窗口位置
        jFrame.setLocation(width / 2 - jFrame.getWidth() / 2, height / 2 - jFrame.getHeight() / 2);

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jt.setEditable(false);

        jFrame.add(jt);
        jFrame.add(cpuBar);
        jFrame.add(memBar);

        LayoutManager ly = new LayoutManager() {
            @Override
            public void addLayoutComponent(String name, Component comp) {

            }

            @Override
            public void removeLayoutComponent(Component comp) {

            }

            @Override
            public Dimension preferredLayoutSize(Container parent) {
                return null;
            }

            @Override
            public Dimension minimumLayoutSize(Container parent) {
                return null;
            }

            @Override
            public void layoutContainer(Container parent) {
                jt.setBounds(0, 50, 500, 600);
                cpuBar.setBounds(0, 0, 500, 25);
                memBar.setBounds(0, 25, 500, 25);
            }
        };

        jFrame.setLayout(ly);

        jFrame.setVisible(true);

        jFrame.setAlwaysOnTop(true);
    }
}
