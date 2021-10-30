package com.github.zhuaidadaya.MCH.UI;

import com.github.zhuaidadaya.MCH.Command.Parsing;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.Events.LoadAssembly;
import com.github.zhuaidadaya.MCH.lib.Resources;

import javax.swing.*;
import java.awt.*;

public class MchUI extends Community {

    public static JFrame jFrame = new JFrame();
//    public static JTextPane input_Command = new JTextPane();
    public static JButton menu = new JButton();
    public static JTextPane commandArea = new JTextPane();
    public static JTextPane commandDisplay = new JTextPane();
    public static JTextArea tips = new JTextArea();
    public static JTextArea switchTip = new JTextArea();

    public static JScrollPane command_scrollPane = new JScrollPane(commandDisplay);
//    public static JScrollPane input_scrollPane = new JScrollPane(input_Command);
public static JScrollPane tip_scrollPane = new JScrollPane(switchTip);

    public MchUI() {
        //        解析输入
        new Parsing().start();

        new inputUI();

        //设置窗口大小和颜色
        jFrame.setSize(800, 500);
        jFrame.getContentPane().setBackground(Color.white);

        //        窗口初始化设置
        //获得屏幕大小
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        command_scrollPane.getVerticalScrollBar().setValue(0);
        command_scrollPane.setHorizontalScrollBar(null);
        command_scrollPane.setViewportView(commandDisplay);
        command_scrollPane.setBorder(null);

//        input_scrollPane.getHorizontalScrollBar().setValue(0);
//        input_scrollPane.setViewportView(input_Command);
//        input_scrollPane.setBorder(null);

        tip_scrollPane.getHorizontalScrollBar().setValue(0);
        tip_scrollPane.setViewportView(switchTip);
        tip_scrollPane.setBorder(null);

        commandDisplay.setFont(new Font(commandDisplay.getFont().getName(), Font.PLAIN, 13));
//        input_Command.setFont(new Font(command1.getFont().getName(), Font.PLAIN, 14));
        switchTip.setFont(new Font(commandDisplay.getFont().getName(), Font.PLAIN, 13));

        //设置窗口位置
        jFrame.setLocation(width / 2 - jFrame.getWidth() / 2, height / 2 - jFrame.getHeight() / 2);

        //设置退出时不响应操作,因为要使用自定义退出事件
        jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        //显示窗口
        jFrame.setVisible(! Errors.CannotHandle);
        LoadAssembly.loadAssembly("[Main Thread/INFO] Showing UI", Resources.initLanguage.lang.get("show_UI"), false);

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
                int Height = jFrame.getHeight();
                int Width = jFrame.getWidth();

                command_scrollPane.setBounds(5, 0, Width + 100, Height - (20 + 65));
                tips.setBounds(0, Height - 65 - 20, Width, 20);
//                input_scrollPane.setBounds(0, Height - 65, Width - 110, 100);
                menu.setBounds(Width - 100, Height - 65, 80, 27);
            }
        };

        jFrame.setLayout(layoutManager);

        //        jFrame.add(input_Command);
        jFrame.add(menu);
        //        jFrame.add(command1);
        jFrame.add(tips);
        //        jFrame.add(switchTip);

        jFrame.add(command_scrollPane);
//        jFrame.add(input_scrollPane);

        switchTip.setVisible(false);
        tip_scrollPane.setVisible(false);
//        input_scrollPane.setVisible(false);

        commandArea.setEditable(false);
        commandDisplay.setEditable(false);

        tips.setEditable(false);
        tips.setLineWrap(true);

        menu.addActionListener(e -> MenuUI.jFrame.setVisible(true));
    }
}
