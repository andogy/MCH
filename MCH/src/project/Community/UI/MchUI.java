package project.Community.UI;

import project.Community.Command.Parsing;
import project.Community.Events.LoadAssembly;
import project.Community.Times.times;
import project.Community.UI.Lang.languageSet;

import javax.swing.*;
import java.awt.*;

public class MchUI {

    public static JFrame jFrame = new JFrame();
    public static JTextArea input_Command = new JTextArea();
    public static JButton menu = new JButton();
    public static JTextPane command1 = new JTextPane();
    public static JTextArea tips = new JTextArea();
    public static JTextArea switchTip = new JTextArea();


    public static JScrollPane command_scrollPane = new JScrollPane(command1);
    public static JScrollPane input_scrollPane = new JScrollPane(input_Command);
    public static JScrollPane tip_scrollPane = new JScrollPane(switchTip);

    public MchUI() {
        //        加载语言
        System.out.println("[" + times.format + "]\n" + "language:语言就绪中");
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadingAssemble: language\n");
        new languageSet().start();

        //        解析输入
        new Parsing().start();

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
        command_scrollPane.setViewportView(command1);
        command_scrollPane.setBorder(null);

//        tips

        input_scrollPane.getHorizontalScrollBar().setValue(0);
        input_scrollPane.setViewportView(input_Command);
        input_scrollPane.setBorder(null);

        tip_scrollPane.setBorder(null);

        command1.setFont(new Font(command1.getFont().getName(), Font.PLAIN, 13));
        input_Command.setFont(new Font(command1.getFont().getName(), Font.PLAIN, 14));
        switchTip.setFont(new Font(command1.getFont().getName(), Font.PLAIN, 13));

        //设置窗口位置
        jFrame.setLocation(width / 2 - jFrame.getWidth() / 2, height / 2 - jFrame.getHeight() / 2);

        //设置退出时不响应操作,因为要使用自定义退出事件
        jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        //显示窗口
        jFrame.setVisible(true);
        System.out.println("[" + times.format + "]\n" + "MchUI:显示UI");
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "MchUI: Display UI\n");

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

                command_scrollPane.setBounds(5, 0, Width, Height - (20 + 65));

                command_scrollPane.setBorder(null);
                //                jScrollPane.setBounds(0, 0, 35, 2000);

                tips.setBounds(0, Height - 65 - 20, Width, 20);
                //                switchTip.setBounds(0, Height - 65, Width - 110, 25);
                tip_scrollPane.setBounds(0, Height - 65, Width - 110, 450);
                //                input_Command.setBounds(0, Height - 65, Width - 110, 25);
                input_scrollPane.setBounds(0, Height - 65, Width - 110, 450);
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
        jFrame.add(input_scrollPane);
        jFrame.add(tip_scrollPane);

        switchTip.setVisible(false);
        tip_scrollPane.setVisible(false);

        command1.setEditable(false);

        tips.setEditable(false);
        tips.setLineWrap(true);

        menu.addActionListener(e -> MenuUI.jFrame.setVisible(true));

        System.out.println("[" + times.format + "]\n" + "MchUI:UI就绪");
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadSucceed: MchUI\n");

    }
}
