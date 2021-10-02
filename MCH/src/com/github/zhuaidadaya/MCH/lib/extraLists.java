package com.github.zhuaidadaya.MCH.lib;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static com.github.zhuaidadaya.MCH.lib.Resources.initLanguage.lang;

public class extraLists {
    public Object[] o_pub;
    JList<Object> list = new JList<>();
    JFrame jf = new JFrame("Extra Lists");
    JTextPane extraStatus = new JTextPane();
    JTextPane extraStatusSource = new JTextPane();
    JScrollPane jScrollPane = new JScrollPane();
    JLabel extraSum = new JLabel();
    HashMap<Integer, String> h = new HashMap<>();
    int infoHeight = 20;

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
            extraSum.setBounds(jf.getWidth() / 2, 0, jf.getWidth() / 2, infoHeight);
            jScrollPane.setBounds(jf.getWidth() / 2, infoHeight, jf.getWidth() + 100, jf.getHeight() / 2);
            extraStatus.setBounds(jf.getWidth() / 2, jf.getHeight() / 2 + infoHeight, jf.getWidth() / 2, jf.getHeight() / 2);
            extraStatusSource.setBounds(0, jf.getHeight() / 2 + infoHeight, jf.getWidth() / 2, jf.getHeight() / 2);

            boolean load;
            boolean reg;
            try {
                load = ExtraLoader.extras.get(list.getModel().getElementAt(list.getSelectedIndex()).toString()).get("loaded").equals("true");
                reg = ExtraLoader.extras.get(list.getModel().getElementAt(list.getSelectedIndex()).toString()).get("reg-status").equals("reg");
                if (!load)
                    list.setSelectionBackground(new Color(255, 97, 78));
                else if (!reg)
                    list.setSelectionBackground(new Color(252, 148, 42));
                else
                    list.setSelectionBackground(new Color(72, 210, 82));
            } catch (Exception e) {

            }
        }
    };

    public extraLists() {
        extras();
    }

    public void uploadList(Object[] o) {

        int selected = -1;
        String sel = "";
        try {
            selected = list.getSelectedIndices().length > 1 ? -1 : list.getSelectedIndex();
            sel = list.getSelectedValue().toString();
        } catch (Exception e) {

        }

        list.setListData(o_pub = o);
        extraSum.setText("extras sum:" + o.length);
        //        extraSum.setText(String.format(lang.get("extra-sum"),o.length));

        if (selected == -1)
            list.setSelectedIndex(0);
        else
            list.setSelectedValue(sel, true);
    }

    public void extras() {
        jScrollPane.getVerticalScrollBar().setValue(0);
        jScrollPane.getHorizontalScrollBar().setValue(0);
        jScrollPane.setViewportView(list);

        extraStatus.setEditable(false);
        extraStatusSource.setEditable(false);

        jf.setSize(500, 540);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        list.addListSelectionListener(e -> {
            int[] indices = list.getSelectedIndices();
            ListModel<?> listModel = list.getModel();
            h.clear();

            int ind = 0;
            for (int index : indices) {
                h.put(ind = index, listModel.getElementAt(index).toString());
                extraStatusSource.setText(ExtraLoader.extras.get(h.get(index)).toString().replace("{", "{\n    ").replace(", ", "\n    ").replace("}", "\n}"));
            }

            extraSum.setText("extras sum:" + o_pub.length + "(" + ind + ")");

            extraStatus.setText(listModel.getElementAt(ind).toString() + "\n");
            try {
                String key;
                String value;
                for (Object obj : ExtraLoader.extras.get(h.get(ind)).keySet()) {
                    key = lang.get(obj.toString()) != null ? lang.get(obj.toString()) : obj.toString();
                    value = lang.get(ExtraLoader.extras.get(h.get(ind)).get(obj.toString()).toString()) != null ? lang.get(ExtraLoader.extras.get(h.get(ind)).get(obj.toString()).toString()) : ExtraLoader.extras.get(h.get(ind)).get(obj.toString()).toString();
                    extraStatus.setText(extraStatus.getText() + "\n" + key + " = " + value);
                }
            } catch (Exception var1) {

            }
        });

        list.setSelectedIndex(0);

        jf.add(extraSum);
        jf.add(jScrollPane);
        jf.add(extraStatus);
        jf.add(extraStatusSource);

        jf.setLayout(layoutManager);
    }

    public void showWindow() {
        jf.setVisible(true);
    }
}
