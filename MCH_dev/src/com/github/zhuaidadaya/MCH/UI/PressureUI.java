package com.github.zhuaidadaya.MCH.UI;

import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.Events.reStart;
import com.github.zhuaidadaya.MCH.lib.Resources;

import javax.swing.*;
import java.awt.*;

public class PressureUI {
    public static JFrame jFrame = new JFrame();
    public static JButton rabbit = new JButton();
    public static boolean checking = false;

    public static void pressureUI() {
        jFrame.setSize(640, 360);
        jFrame.setResizable(false);

        jFrame.setTitle("PTEST");

        //        窗口初始化设置
        //获得屏幕大小
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        //设置窗口位置
        jFrame.setLocation(width / 2 - jFrame.getWidth() / 2, height / 2 - jFrame.getHeight() / 2);

        jFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        jFrame.setVisible(true);

        LayoutManager layoutManager = new LayoutManager() {
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
                rabbit.setBounds(10, 10, 120, 30);
            }
        };

        rabbit.addActionListener(e -> {
            if(! checking) {
                Errors.tips(450, 240, Resources.initLanguage.lang.get("PTEST-Rabit"), Resources.initLanguage.lang.get("WARN-PTEST-Title"));
            } else {
                reStart.setJvmConf("-Xmx4096G -XX:+UnlockExperimentalVMOptions -XX:+ZUncommit -XX:+UseZGC -server");
                new reStart().restart("PTEST-rabbit", "");
            }
            checking = true;
        });

        jFrame.setLayout(layoutManager);

        jFrame.add(rabbit);
    }
}
