package test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class test1 {

    public static void main(String[] args) {
        final JList list = new JList();

        new Thread(() -> {
            while (true) {
                try {
                    list.removeAll();
                    list.setListData(HashMapToSTRS.toSTRS(minecraftStart.pid, 1));
                } catch (Exception e) {

                }
            }
        });

        new Thread(() -> {
            minecraftStart.startMc("C:\\normal\\Minecraft\\.minecraft\\libraries\\");
        }).start();

        new Thread(() -> {
            minecraftStart.startMc("C:\\normal\\Minecraft\\.minecraft\\libraries\\");
        }).start();

        while (minecraftStart.pid.isEmpty()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Waiting...");
        }

        JFrame jf = new JFrame("测试窗口");
        jf.setSize(300, 300);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        HashMap<Integer, String> h = new HashMap<>();

        list.setPreferredSize(new Dimension(200, 100));

        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        list.setListData(HashMapToSTRS.toSTRS(minecraftStart.pid, 1));

        // 添加选项选中状态被改变的监听器
        list.addListSelectionListener(e -> {
            // 获取所有被选中的选项索引
            int[] indices = list.getSelectedIndices();
            // 获取选项数据的 ListModel
            ListModel listModel = list.getModel();
            // 输出选中的选项
            h.clear();

            for (int index : indices) {
                h.put(index, listModel.getElementAt(index).toString());
            }

            System.out.println(h);
            try {
                Runtime.getRuntime().exec("taskkill /pid " + h.toString().substring(h.toString().indexOf(":") + 1).replace("}", ""));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });

        // 设置默认选中项
        list.setSelectedIndex(0);

        // 添加到内容面板容器
        panel.add(list);

        jf.setContentPane(panel);
        jf.setVisible(true);
    }

}
