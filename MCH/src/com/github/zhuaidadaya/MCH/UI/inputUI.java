package com.github.zhuaidadaya.MCH.UI;

import com.github.zhuaidadaya.MCH.Community;

import javax.swing.*;
import java.awt.*;

public class inputUI extends Community {
    public static JFrame jFrame = new JFrame();
    public static JTextPane inputArea = new JTextPane();
    public static JScrollPane inputArea_scrollPane = new JScrollPane(inputArea);

    public inputUI() {
        InputUI();
    }

    public static void InputUI() {

        inputArea_scrollPane.getVerticalScrollBar().setValue(0);
        inputArea_scrollPane.setHorizontalScrollBar(null);
        inputArea_scrollPane.setViewportView(inputArea);
        inputArea_scrollPane.setBorder(null);

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
                inputArea_scrollPane.setBounds(0,0,jFrame.getWidth() - 10,jFrame.getHeight() - 20);
                MchUI.tip_scrollPane.setBounds(inputArea.getBounds());
            }
        };

        jFrame.setSize(400, 570);

        jFrame.setLayout(layoutManager);

        jFrame.add(MchUI.tip_scrollPane);

        jFrame.add(inputArea_scrollPane);

        jFrame.setVisible(true);
    }
}
