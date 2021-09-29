package com.github.zhuaidadaya.MCH.UI;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Events;
import com.github.zhuaidadaya.MCH.Events.LoadAssembly;
import com.github.zhuaidadaya.MCH.Events.reStart;
import com.github.zhuaidadaya.MCH.Help.Helps;
import com.github.zhuaidadaya.MCH.lib.Log;

import javax.swing.*;
import java.awt.*;

public class MenuUI extends Community {
    public static JFrame jFrame = new JFrame();

    public static boolean OpenMenu = false;
    //    public static boolean OpeningMenu = false;

    public static JButton feedback = new JButton();

    public static JButton helps = new JButton();
    public static JButton user = new JButton();
    public static JButton gayhub = new JButton("Github");

    public static JButton restart = new JButton();

    public static JButton settings = new JButton();
    public static JButton functions = new JButton();

    public static JTextArea announcement = new JTextArea();
    public static JButton flushAnnouncement = new JButton();

    public static JButton deleteMCH = new JButton();

    public MenuUI() {
        Log.writeLog("[Main Thread/INFO] Reloading configs by MenuUI");
        OpenMenu = true;
        menuUI();
    }

    public static void show() {
        jFrame.setVisible(true);
    }

    public static void menuUI() {
        Config.parsing(false);

        OpenMenu = false;

        jFrame.setResizable(false);

        jFrame.setSize(640, 360);

        //        窗口初始化设置
        //获得屏幕大小
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        //设置窗口位置
        jFrame.setLocation(width / 2 - jFrame.getWidth() / 2, height / 2 - jFrame.getHeight() / 2);

        jFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        jFrame.setVisible(false);

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
                announcement.setBounds(5, 5, 630, 170);

                restart.setBounds(0, 40 + 35 + 100 + 30 + 40 + 30 + 10, 110, 30);

                helps.setBounds(120, 40 + 35 + 100 + 30 + 40, 80, 30);

                user.setBounds(210, 40 + 35 + 100 + 30 + 40, 100, 30);

                functions.setBounds(0, 245, 110, 30);

                feedback.setBounds(320, 285, 90, 30);

                settings.setBounds(210, 40 + 35 + 100 + 30 + 40 + 30 + 10, 100, 30);

                gayhub.setBounds(120, 40 + 35 + 100 + 30 + 40 + 30 + 10, 80, 30);

                deleteMCH.setBounds(490, 285, 120, 30);
            }
        };

        jFrame.setLayout(layoutManager);

        jFrame.add(helps);
        jFrame.add(user);
        jFrame.add(gayhub);
        jFrame.add(restart);
        jFrame.add(settings);
        jFrame.add(feedback);
        jFrame.add(functions);
        jFrame.add(announcement);
        jFrame.add(flushAnnouncement);
        jFrame.add(deleteMCH);

        helps.addActionListener(e -> Helps.Helps());
        user.addActionListener(e -> Helps.agreement());
        gayhub.addActionListener(e -> Helps.gayhub());

        restart.addActionListener(e -> new reStart().restart());

        settings.addActionListener(e -> {
            if(! Community.isDaemons) {
                MenuUI2.jFrame.setVisible(true);
                MenuUI.jFrame.setVisible(false);
            }
        });

        feedback.addActionListener(e -> Helps.feedback());

        functions.addActionListener(e -> {
            if(! Community.isDaemons) {
                ExtraUI.jFrame.setVisible(true);
            }
        });
    }
}