package project.Community.UI;

import project.Community.UI.Color.displaySets;

import javax.swing.*;
import java.awt.*;

public class loadingWindow {
    public static JTextArea loading = new JTextArea();

    public static JLabel loadingTip = new JLabel();

    public static JFrame jFrame = new JFrame("MCH Loading...");

    public static JLabel loadStatus = new JLabel();
    public static JLabel status = new JLabel();

    public static JLabel percentage = new JLabel();

    public static void ui() {
        loading.setEditable(false);

        //设置窗口大小和颜色
        jFrame.setSize(350, 520);
        jFrame.getContentPane().setBackground(Color.white);

        //        窗口初始化设置
        //获得屏幕大小
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        //设置窗口位置
        jFrame.setLocation(width / 2 - jFrame.getWidth() / 2, height / 2 - jFrame.getHeight() / 2);

        //设置退出时不响应操作,因为要使用自定义退出事件
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jFrame.setResizable(false);

        jFrame.add(loading);

        jFrame.add(loadingTip);

        jFrame.add(loadStatus);
        jFrame.add(status);

        jFrame.add(percentage);

        jFrame.setLayout(new LayoutManager() {
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

                loading.setBounds(0,70,350,450);
                loadingTip.setBounds(0,50,350,20);

                percentage.setBounds(0,30,350,20);

                loadStatus.setBounds(0,5,50,20);
                status.setBounds(50,5,200,20);

            }
        });

        jFrame.setVisible(true);
    }
}
