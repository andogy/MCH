package com.github.zhuaidadaya.MCH.UI;

import com.github.zhuaidadaya.MCH.Community;

import javax.swing.*;
import java.awt.*;

public class inputUI extends Community {
    public static JFrame jFrame = new JFrame();
    public static JTextPane inputArea = new JTextPane();

    public inputUI() {
        uiSizeMap.put(jFrame, new Dimension(400, 570));
        uiSizeMap.put(inputArea, new Rectangle(0, 0, 400, 600));

        InputUI();
    }

    public static void InputUI() {
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
                inputArea.setBounds(uiSizeMap.getRectangle(inputArea));
                MchUI.tip_scrollPane.setBounds(uiSizeMap.getRectangle(inputArea));
            }
        };

        jFrame.setSize(uiSizeMap.getDimension(jFrame));

        jFrame.setLayout(layoutManager);

        jFrame.add(MchUI.tip_scrollPane);

        jFrame.add(inputArea);

        jFrame.setVisible(true);
    }
}
