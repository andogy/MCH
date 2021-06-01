package project.Community.Events;

import project.Community.Command.ini;
import project.Community.Community;
import project.Community.Events.UPD.URLs;
import project.Community.Times.times;
import project.Community.UI.MchUI;
import project.Community.lib.filesOperator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class historyReader extends Thread {
    public static boolean UPD_Check = false;
    public static boolean BreakRead;

    //    这个参数用来检测外部写入历史,当文本编辑器将历史文件改变时使用这个变量确认后进行刷新
    public static long historyLength = 0;

    public static File file = new File(ini.path + "history.txt");

    public static String s;

    public static String history = "";

    public static void flush() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader fr = new BufferedReader(fileReader);
            history = "";

            historyLength = file.length();

            if (!(historyLength > 2048000)) {

                // 使用readLine方法，一次读一行
                int lines = 0;
                int counts = 0;

                while ((s = fr.readLine()) != null) {
                    if (file.length() > 2048000) {
                        break;
                    }
                    //                    Thread.sleep(1);
                    if (lines > 800) {
                        if (Community.historySaveID == 0) {
                            fr.close();
                            filesOperator.saveCache(file, new File(ini.path + "save\\history\\"), "history");
                        }
                        FileWriter fileWriter = new FileWriter(file);
                        fileWriter.write("");
                        fileWriter.close();
                    }

                    if (s.length() > 512) {
                        s = s.substring(0, 512);
                    }

                    lines++;
                    counts++;

//                    if (Community.LangID == 0) {
//                        MchUI.tips.setText("历史记录加载中: 读取了" + lines + "行    " + (float) history.length() / 1024 + "Kb数据");
//                    } else if (Community.LangID == 1) {
//                        MchUI.tips.setText("History Loading: Read " + lines + " lines    " + (float) history.length() / 1024 + "Kb Data");
//                    }

                    if (BreakRead & !MchUI.input_Command.getText().equals("")) {
                        history = "";
                        break;
                    }

                    if (s.length() >= 1) {
                        history = String.format("%s\n%s", s, history);
                    }

                    if (!Community.fastLoad) {
                        Runtime.getRuntime().gc();
                    }
                }

                if (counts == 0) {
                    if (MchUI.input_Command.getText().equals("")) {
                        if (history.equals("") || history.equals("\n")) {
                            if (Community.LangID == 0) {
                                history = "没有历史命令记录!";
                            } else if (Community.LangID == 1) {
                                history = "There is no historical command record!";
                            }
                        }
                    }
                }

                if (!BreakRead | MchUI.input_Command.getText().equals("")) {
                    MchUI.command1.setText(history);
                }

                history = null;

                Runtime.getRuntime().gc();

            } else {
                if (Community.historySaveID == 0) {
                    fr.close();
                    filesOperator.saveCache(file, new File(ini.path + "save\\history\\"), "history");
                }

                if (Community.LangID == 0) {
                    MchUI.command1.setText("你的历史命令记录太大了! 为了内存安全,此记录无法读取");
                } else if (Community.LangID == 1) {
                    MchUI.command1.setText("The History File too big! For Memory Safe,We Cannot Read it");
                }
            }

            fileReader.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (MchUI.input_Command.getText().equals("")) {
                historyLength = -1;
                if (Community.LangID == 0) {
                    MchUI.command1.setText("""
                            出了一点小问题,因此暂时无法读取历史命令
                            可能因为找不见历史记录的文件或程序出现问题

                            你可以尝试重启MCH或者在输入栏按下回车
                            如果以上操作无法修复,请尝试使用更新版本
                            若错误依旧,请联系开发人员""");
                } else if (Community.LangID == 1) {
                    MchUI.command1.setText("""
                            There's a Small Problem,So we Cannot Read the History Command right now
                            There Maybe a Problem With the File or Program

                            You Can Try to Restart MCH or Press enter Key
                            if The Above Operation Cannot be Repaired,Please Try to Use The Updated Version
                            if The Error Persists,Please Contact the Developer""");
                }
            }
        }
        BreakRead = true;
    }

    public static void randomTips() {
        if (Community.LangID == 0) {
            String tips = "";

            int tipID = new Random(new Random().nextInt()).nextInt(15);

            switch (tipID) {
                case 0 -> tips = "你知道吗,在输入框输入超过340万个字符有几率导致电脑死机哦";
                case 1 -> tips = "今天又是鸽着的一天";
                case 2 -> tips = "为什么你会看到这条tip";
                case 3 -> tips = "据说随机问题里面的问题都非常难,要不要试试看?";
                case 4 -> tips = "挺不正经一程序";
                case 5 -> tips = "苦力怕被小白射死后可能会掉落唱片,要不试试?";
                case 6 -> tips = "听说MCH的开发者都是自学编程,不知道真的假的";
                case 7 -> tips = "草";
                case 8 -> tips = "MCH的命令提示体系不咋,但彩蛋可不少(x";
                case 9 -> tips = "哦豁";
                case 10 -> {
                    if (times.hour < 5) {
                        tips = "快去睡觉";
                    } else {
                        tips = "恭喜你刷到了一个空的tip";
                    }
                }
                case 11 -> tips = "tip重复显示的几率很高";
                case 12 -> tips = "真当tip看不完啊";
                case 13 -> tips = "我猜你一定连续看过几次一样的tip";
                case 14 -> tips = "在提示命令时,MCH只会截取光标之前的内容喔";
                case 15 -> tips = "";
            }

            MchUI.tips.setText(tips);
        }
    }

    @Override
    public void run() {
        //        if (!BreakRead) {
        while (true) {
            if (MchUI.jFrame.isVisible()) {
                historyLength = file.length();

                if (Errors.CannotHandle) {
                    history = "";
                    break;
                }

                if (!Community.isDaemons) {
                    try {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (MchUI.input_Command.getText().equals("")) {
                            if (!BreakRead) {
                                MchUI.command1.setText("");
                            }
                            if (file.isFile() & file.length() != historyLength) {
                                if (Community.historySaveID != 2) {
                                    flush();
                                    Runtime.getRuntime().gc();
                                }
                                BreakRead = true;
                                randomTips();
                            } else if (!BreakRead) {
                                if (Community.historySaveID != 2) {
                                    flush();
                                    Runtime.getRuntime().gc();
                                }

                                if (!UPD_Check) {

                                    if (!URLs.checkUPD()) {
                                        randomTips();
                                    }

                                    UPD_Check = true;
                                } else {
                                    randomTips();
                                }
                                BreakRead = true;
                            }
                        }
                    } catch (Exception e) {
                        Errors.errors(null, e, false, "historyRead");
                    }

                    Runtime.getRuntime().gc();
                } else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //        }
    }
}
