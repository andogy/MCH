package com.github.zhuaidadaya.MCH.UI;

import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.Events.Events;
import com.github.zhuaidadaya.MCH.UI.Color.displaySets;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;

public class ExtraUI extends Community {
    public static JFrame jFrame = new JFrame();

    public static boolean mcRunning = false;
    public static JLabel McBe = new JLabel();
    public static JButton turnOnMcBe = new JButton();
    public static JButton turnOffMcBe = new JButton();
    public static JTextArea McBeStatus = new JTextArea();

    public static JButton launchers = new JButton();
    public static JButton randomProblem = new JButton();
    public static JButton functions = new JButton();

    public static JTextArea functionEdit = new JTextArea();
    public static JButton saveFunc = new JButton();
    public static JLabel minecraftListenSpeedLevel = new JLabel();
    public static JButton Level0OfMLSL = new JButton();
    public static JButton Level1OfMLSL = new JButton();

    public static JLabel McJe = new JLabel();

    public static JTextArea McJeStatus = new JTextArea();

    public ExtraUI() {
        extraUI();
    }

    public static void show() {
        jFrame.setVisible(true);
    }

    public static void extraUI() {

        //        Launcher
        jFrame.add(McBe);
        jFrame.add(turnOnMcBe);
        jFrame.add(turnOffMcBe);
        jFrame.add(McBeStatus);
        jFrame.add(launchers);
        jFrame.add(functionEdit);
        jFrame.add(saveFunc);
        jFrame.add(randomProblem);
        jFrame.add(functions);

        jFrame.add(McJe);

        jFrame.add(minecraftListenSpeedLevel);
        jFrame.add(Level0OfMLSL);
        jFrame.add(Level1OfMLSL);

        McBeStatus.setEditable(false);

        jFrame.setSize(640, 360);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        jFrame.setResizable(false);

        jFrame.setLocation(width / 2 - ExtraUI.jFrame.getWidth() / 2, height / 2 - ExtraUI.jFrame.getHeight() / 2);

        displaySets.extraDisplay();

        {
            uiSizeMap.put(McBe, new Rectangle(5, 5, 80, 30));
            uiSizeMap.put(turnOnMcBe, new Rectangle(85, 5, 80, 30));
            uiSizeMap.put(turnOffMcBe, new Rectangle(175, 5, 80, 30));
            uiSizeMap.put(McBeStatus, new Rectangle(5, 45, 265, 180));
            uiSizeMap.put(minecraftListenSpeedLevel, new Rectangle(5, 230, 80, 30));
            uiSizeMap.put(Level0OfMLSL, new Rectangle(180, 230, 90, 30));
            uiSizeMap.put(Level1OfMLSL, new Rectangle(80, 230, 90, 30));

            uiSizeMap.put(launchers, new Rectangle(0, 290, 90, 32));
            uiSizeMap.put(randomProblem, new Rectangle(90, 290, 90, 32));
            uiSizeMap.put(functions, new Rectangle(180, 290, 90, 32));

            uiSizeMap.put(functionEdit, new Rectangle(0, 0, (360 / 4) * 3, 260));
            uiSizeMap.put(saveFunc, new Rectangle(360 - (360 / 4) + 10, 0, 100, 30));

            uiSizeMap.put(McJe, new Rectangle(5, 310, 90, 30));
        }

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
                McBe.setBounds(uiSizeMap.getRectangle(McBe));
                turnOnMcBe.setBounds(uiSizeMap.getRectangle(turnOnMcBe));
                turnOffMcBe.setBounds(uiSizeMap.getRectangle(turnOffMcBe));
                McBeStatus.setBounds(uiSizeMap.getRectangle(McBeStatus));
                minecraftListenSpeedLevel.setBounds(uiSizeMap.getRectangle(minecraftListenSpeedLevel));
                Level0OfMLSL.setBounds(uiSizeMap.getRectangle(Level0OfMLSL));
                Level1OfMLSL.setBounds(uiSizeMap.getRectangle(Level1OfMLSL));

                launchers.setBounds(uiSizeMap.getRectangle(launchers));
                randomProblem.setBounds(uiSizeMap.getRectangle(randomProblem));
                functions.setBounds(uiSizeMap.getRectangle(functions));

                functionEdit.setBounds(0, 0, (360 / 4) * 3, 260);
                saveFunc.setBounds(360 - (360 / 4) + 10, 0, 100, 30);

                McJe.setText("Java Minecraft:");
                McJe.setBounds(5, 310, 90, 30);
            }
        });

        turnOffMcBe.addActionListener(e -> {
            if(! Community.isDaemons) {
                try {
                    Runtime.getRuntime().exec("cmd.exe /k taskkill /f /im minecraft.windows.exe");
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        turnOnMcBe.addActionListener(e -> {
            if(! Community.isDaemons) {
                try {
                    Runtime.getRuntime().exec("cmd.exe /k start minecraft:");
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        launchers.addActionListener(e -> {
            if(! Community.isDaemons) {
                Community.extraDisplayID = 0;
                displaySets.extraDisplay();
            }
        });

        randomProblem.addActionListener(e -> {
            if(! Community.isDaemons) {
                Community.extraDisplayID = 1;
                displaySets.extraDisplay();
            }
        });

        functions.addActionListener(e -> {
            if(! Community.isDaemons) {
                Community.extraDisplayID = 2;
                displaySets.extraDisplay();
            }
        });

        Level0OfMLSL.addActionListener(e -> Events.switchMinecraftListenFlushSpeedLevel(0));

        Level1OfMLSL.addActionListener(e -> Events.switchMinecraftListenFlushSpeedLevel(1));

        saveFunc.addActionListener(e -> ExtraUI.saveFunction());
    }

    public static void saveFunction() {
        if(! Community.isDaemons) {
            try {
                int result;
                new File("");
                String path;
                JFileChooser fileChooser = new JFileChooser();
                FileSystemView fsv = FileSystemView.getFileSystemView();
                fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
                fileChooser.setDialogTitle("选择保存路径");
                fileChooser.setApproveButtonText("确定");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                result = fileChooser.showOpenDialog(jFrame);
                if(JFileChooser.APPROVE_OPTION == result) {
                    path = fileChooser.getSelectedFile().getPath();
                    if(new File(path).isFile()) {
                        FileWriter fileWriter = new FileWriter(path);
                    } else {
                        JTextArea fileName = new JTextArea();
                        JButton yes = new JButton();
                        JButton cancel = new JButton();

                        //获得屏幕大小
                        Toolkit toolkit = Toolkit.getDefaultToolkit();
                        Dimension screenSize = toolkit.getScreenSize();
                        int width = screenSize.width;
                        int height = screenSize.height;

                        JFrame jFrame1 = new JFrame();

                        //设置窗口位置
                        jFrame1.setLocation(width / 2 - jFrame1.getWidth() / 2, height / 2 - jFrame1.getHeight() / 2);

                        jFrame1.setAlwaysOnTop(Community.onTop);

                        jFrame1.add(fileName);
                        jFrame1.add(yes);
                        jFrame1.add(cancel);
                        jFrame1.setLayout(new LayoutManager() {
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
                                jFrame1.setSize(500, 300);

                                fileName.setBounds(30, 50, 440, 30);
                            }
                        });

                        jFrame1.setVisible(true);
                    }
                }
            } catch (Exception ignored) {

            }
        }
    }

    public static class minecraftListener extends Thread {
        @Override
        public void run() {
            if(Community.os.equals("windows")) {
                Runtime r = Runtime.getRuntime();
                while(true) {
                    try {
                        //                    Thread.sleep(10);
                        if(! Community.isDaemons & Community.extraDisplayID == 0 & ExtraUI.jFrame.isVisible()) {
                            try {
                                Process p = r.exec("cmd.exe /c tasklist");
                                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

                                String s = "";
                                StringBuilder all = new StringBuilder();
                                while((s = br.readLine()) != null) {
                                    s = s.toLowerCase();

                                    all.append(s).append("\n");

                                    StringBuilder displayString = new StringBuilder();
                                    if(Community.LangID == 0) {
                                        displayString = new StringBuilder("Minecraft状态\n\n");
                                    } else if(Community.LangID == 1) {
                                        displayString = new StringBuilder("Minecraft Status\n\n");
                                    }

                                    if(s.contains("minecraft.windows.exe")) {
                                        String s1;
                                        int c = 0;
                                        while(s.contains(" ")) {
                                            c++;
                                            s1 = s.substring(0, s.indexOf(" "));

                                            s = s.substring(s.indexOf(" "));
                                            while(s.substring(0, 1).contains(" ")) {
                                                s = s.replaceFirst(" ", "");
                                            }

                                            if(Community.LangID == 0) {
                                                switch(c) {
                                                    case 1 -> displayString.append("软件包名: ").append(s1).append("\n");
                                                    case 2 -> displayString.append("PID: ").append(s1).append("\n");
                                                    case 5 -> displayString.append("占用内存: ").append(s1).append(" KB\n");
                                                }
                                            } else if(Community.LangID == 1) {
                                                switch(c) {
                                                    case 1 -> displayString.append("minecraft name: ").append(s1).append("\n");
                                                    case 2 -> displayString.append("PID: ").append(s1).append("\n");
                                                    case 5 -> displayString.append("minecraft ram: ").append(s1).append("kb\n");
                                                }
                                            }

                                            if(c >= 5) {
                                                break;
                                            }
                                        }

                                        McBeStatus.setText(displayString.toString());

                                    }
                                    if(Community.minecraftListenFlushSpeedLevels == 0) {
                                        Thread.sleep(1);
                                    }
                                }

                                System.gc();

                                if(! all.toString().contains("minecraft.windows.exe")) {
                                    mcRunning = false;
                                    if(Community.LangID == 0) {
                                        McBeStatus.setText("Minecraft基岩版未在运行");
                                    } else if(Community.LangID == 1) {
                                        McBeStatus.setText("Minecraft Bedrock Edition is not running");
                                    }
                                } else {
                                    mcRunning = true;
                                }

                                if(Errors.CannotHandle) {
                                    break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            Thread.sleep(500);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                ExtraUI.McBeStatus.setText("Launcher cannot use");
            }
        }
    }
}