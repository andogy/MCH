package project.Community.Events;

import project.Community.Community;
import project.Community.UI.ExtraUI;
import project.Community.UI.MchUI;
import project.Community.UI.MenuUI;
import project.Community.UI.MenuUI2;

public class daemons extends Thread {
    boolean setTit_MenuUI = false;
    String str_MenuUI = "";

    boolean setTit_MenuUI2 = false;
    String str_MenuUI2 = "";

    boolean setTit_extra = false;
    String str_extra = "";

    public void run() {
        while (!Errors.CannotHandle) {
            try {
                Thread.sleep(300);

                Community.isDaemons = MchUI.jFrame.getExtendedState() == 1;

                if (Community.isDaemons) {

                    //                    MenuUI
                    {
                        if (!setTit_MenuUI) {
                            str_MenuUI = MenuUI.jFrame.getTitle() + "(";
                        }
                        if (!MenuUI.jFrame.getTitle().equals(str_MenuUI)) {
                            if (Community.LangID == 0) {
                                MenuUI.jFrame.setTitle(MenuUI.jFrame.getTitle() + "(已暂停)");
                            } else if (Community.LangID == 1) {
                                MenuUI.jFrame.setTitle(MenuUI.jFrame.getTitle() + "(Suspended)");
                            }
                            setTit_MenuUI = true;
                            str_MenuUI = MenuUI.jFrame.getTitle();
                        }
                    }

                    //                    MenuUI2
                    {
                        if (!setTit_MenuUI2) {
                            str_MenuUI2 = MenuUI2.jFrame.getTitle() + "(";
                        }
                        if (!MenuUI2.jFrame.getTitle().equals(str_MenuUI2)) {
                            if (Community.LangID == 0) {
                                MenuUI2.jFrame.setTitle(MenuUI2.jFrame.getTitle() + "(已暂停)");
                            } else if (Community.LangID == 1) {
                                MenuUI2.jFrame.setTitle(MenuUI2.jFrame.getTitle() + "(Suspended)");
                            }
                            setTit_MenuUI2 = true;
                            str_MenuUI2 = MenuUI2.jFrame.getTitle();
                        }
                    }

                    //                    ExtraUI
                    {
                        if (!setTit_extra) {
                            str_extra = ExtraUI.jFrame.getTitle() + "(";
                        }
                        if (!ExtraUI.jFrame.getTitle().equals(str_extra)) {
                            if (Community.LangID == 0) {
                                ExtraUI.jFrame.setTitle(ExtraUI.jFrame.getTitle() + "(已暂停)");
                            } else if (Community.LangID == 1) {
                                ExtraUI.jFrame.setTitle(ExtraUI.jFrame.getTitle() + "(Suspended)");
                            }
                            setTit_extra = true;
                            str_extra = ExtraUI.jFrame.getTitle();
                        }
                        ExtraUI.functionEdit.setEditable(false);
                    }
                }

            } catch (Exception ignored) {

            }
        }
    }
}
