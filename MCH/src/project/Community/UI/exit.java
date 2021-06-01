package project.Community.UI;

import project.Community.Command.ini;
import project.Community.Community;
import project.Community.Events.Events;
import project.Community.Events.LoadAssembly;
import project.Community.lib.filesOperator;
import project.Community.Times.times;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class exit extends JFrame {
    public static JButton buttonEXIT = new JButton();
    public static JButton buttonEXITNot = new JButton();
    public static JFrame jFrame = new JFrame();
    public static JTextArea jTextArea = new JTextArea();

    public exit() {
        EXITs();
        System.out.println("[" + times.format + "]\n" + "exit:exit事件就绪");
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "LoadSucceed: exit\n");
    }

    public static void EXITs() {
//        窗口初始化设置
        //获得屏幕大小
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        //设置窗口位置
        jFrame.setLocation(width / 2 - 500 / 2, height / 2 - 300 / 2);

        //设置退出时不响应操作,因为要使用自定义退出事件
        jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        //设置窗口大小和颜色
        jFrame.setSize(500, 300);
        jFrame.getContentPane().setBackground(Color.white);

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
                jFrame.setAlwaysOnTop(Community.onTop);

//                设置位置
                jTextArea.setBounds(0, 0, 500, 210);
                buttonEXIT.setBounds(90, 210, 80, 40);
                buttonEXITNot.setBounds(180, 210, 80, 40);
            }
        };

        jFrame.setLayout(layoutManager);

//        添加显示
        jFrame.add(jTextArea);
        jFrame.add(buttonEXIT);
        jFrame.add(buttonEXITNot);

        jFrame.setResizable(false);

//        添加按钮事件
        buttonEXIT.addActionListener(e -> Ex());
        buttonEXITNot.addActionListener(e -> jFrame.setVisible(false));

        jTextArea.setEditable(false);

        jFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    public static void Ex() {
        System.out.println("[" + times.format + "]\n" + "exit:退出程序");
        LoadAssembly.loadAssembly("[" + times.format + "]\n" + "exit: exit now\n");

        try {
            Events.closeStreamOfUPD();
        } catch (Exception ignored) {

        }

        File cache = new File(ini.path + "UPD.cache");
        if (Community.saveCache) {
            File cacheTo = new File(ini.path + "save\\cache");
            filesOperator.saveCache(cache,cacheTo,"UPD");
        } else {
            cache.delete();
        }

        File run = new File(ini.path + "run.log");

        if (Community.saveRunLog) {
            File runTo = new File(ini.path + "save\\run\\");
            filesOperator.saveCache(run, runTo, "run");
        }
        run.delete();

        File err = new File(ini.path + "errors.log");

        if (Community.saveErrorLog) {
            File errTo = new File(ini.path + "save\\errors\\");
            filesOperator.saveCache(err, errTo, "error");
        }
        err.delete();

        System.exit(1);
    }

}
