package project.Community.Events.UPD;

import project.Community.Community;
import project.Community.Events.Errors;
import project.Community.Times.times;
import project.Community.UI.MchUI;
import project.Community.UI.MenuUI2;

public class countTime extends Thread {
    public static boolean startDUP_count = false;

    public static boolean counting = false;

    public static boolean Suspended = false;

    public static void cannotUPD_connectFail() {
        if (Community.LangID == 0) {
            MenuUI2.checkReturn.setText("连接服务器失败:\n连接超时\n\n请等待一段时间后尝试重新连接");
        } else if (Community.LangID == 1) {
            MenuUI2.checkReturn.setText("Connect Server Fail:\nTime out\n\nPlease waiting and try again");
        }
    }

    @Override
    public void run() {
        long time_UPD = 0;

        while (!Errors.CannotHandle) {
            if (!Community.isDaemons) {
                try {

                    if (Suspended) {
                        if (Community.LangID == 0) {
                            MenuUI2.checkReturn.setText("更新功能已恢复使用\n\n确认时间:" + times.format);
                        } else if (Community.LangID == 1) {
                            MenuUI2.checkReturn.setText("update is suspended\n\ncheck time:" + times.format);
                        }

                        Suspended = false;
                    }

                    Thread.sleep(1);

                    {
                        // UPD
                        if (startDUP_count) {
                            counting = true;
                            time_UPD++;

                            if (Community.LangID == 0) {
                                MenuUI2.checkReturn.setText("尝试连接服务器:" + (double) time_UPD / 1000 + "s");
                                MchUI.tips.setText("正在检查MCH更新中");
                            } else if (Community.LangID == 1) {
                                MenuUI2.checkReturn.setText("Connecting\ntry:" + (double) time_UPD / 1000 + "s");
                                MchUI.tips.setText("checking MCH update");
                            }

                            if (time_UPD > 7000) {
                                cannotUPD_connectFail();
                                startDUP_count = false;
                            }
                        } else {
                            time_UPD = 0;
                            counting = false;
                        }
                    }
                    if (!counting) {
                        Thread.sleep(100);
                    }

                } catch (Exception ignored) {

                }
            } else {
                if (startDUP_count) {
                    startDUP_count = false;
                }
                Suspended = true;
                if (Community.LangID == 0) {
                    MenuUI2.checkReturn.setText("更新暂停中");
                } else if (Community.LangID == 1) {
                    MenuUI2.checkReturn.setText("update is suspended");
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
