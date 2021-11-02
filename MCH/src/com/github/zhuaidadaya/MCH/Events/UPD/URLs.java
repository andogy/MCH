package com.github.zhuaidadaya.MCH.Events.UPD;

import com.github.zhuaidadaya.MCH.Config.ConfigMain;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.Times.times;
import com.github.zhuaidadaya.MCH.UI.MchUI;
import com.github.zhuaidadaya.MCH.UI.MenuUI2;
import com.github.zhuaidadaya.MCH.lib.filesOperator;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import static com.github.zhuaidadaya.MCH.lib.Resources.initLanguage.lang;

public class URLs extends Thread {
    public static boolean UPD = false;
    public static boolean nowUPD = false;

    public static boolean timeOut = false;

    public static boolean checkUPD = false;
    public static OutputStream out;
    static File file = null;

    public static boolean checkUPD() {
        StringBuilder result = new StringBuilder();
        try {
            //                从Github获取下载代码需要下载代码缓存
            new URLs().UPD(true, false);

            //                读取缓存
            BufferedReader fr = new BufferedReader(new FileReader("C:/.MCH/UPD.cache"));
            String s;
            while((s = fr.readLine()) != null) {
                result.append(s);
            }

            //                关闭流
            fr.close();

            //                删除缓存
            File file = new File("C:/.MCH/UPD.cache");
            File file1 = new File("C:/.MCH/logs/cache/cache");
            if(Community.saveCache) {
                filesOperator.saveCache(file, file1, "UPD");
            } else {
                file.delete();
            }
        } catch (Exception e) {
            return false;
        }

        //        如果获取不到符合规格的更新代码,则返回false,拒绝下载更新包
        if (result.toString().length() < 3) {
            return false;
        }

        timeOut = false;
        //        如果本地ID和网络ID不一致,则有更新
        //        因为网络ID始终是最新版,本地ID除非是开发人员,不然不可能比网络ID更新
        return !result.toString().equals(Community.UPD_ID);
    }

    public void UPD(boolean check, boolean getUpdInfo) {
        String downloadDir = "C:/.MCH/";
        String fileFullName = "UPD.cache";

        try {
            String urlPath;
//            githubConnect.writeHosts();
            urlPath = "https://raw.githubusercontent.com/andogy/MCH/main/Public/MCH.jar";

            //            使用下载方式检查代码防止被github拒绝连接
            if(check) {
                urlPath = "https://raw.githubusercontent.com/andogy/MCH/main/Public/atudpc.code";
            }

            if(check & getUpdInfo) {
                urlPath = "https://raw.githubusercontent.com/andogy/MCH/main/Public/test.txt";
                if(Community.LangID == 0) {
                    urlPath = "https://raw.githubusercontent.com/andogy/MCH/main/Public/upd/upd_zh.txt";
                } else if(Community.LangID == 1) {
                    urlPath = "https://raw.githubusercontent.com/andogy/MCH/main/Public/upd/upd_en.txt";
                }
            }

            //            urlPath = "http://speed.hetzner.de/1GB.bin";

            // 统一资源
            URL url = new URL(urlPath);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

            //设置超时
            httpURLConnection.setConnectTimeout(10000);

            //设置请求方式
            httpURLConnection.setRequestMethod("GET");

            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");


            countTime.startDUP_count = true;

            // 打开到此 URL引用的资源的通信链接（如果尚未建立这样的连接）
            httpURLConnection.connect();

            if(countTime.startDUP_count) {

                countTime.startDUP_count = false;

                // 获取文件大小
                int fileLength = httpURLConnection.getContentLength();

                // 建立链接从请求中获取数据
                url.openConnection();
                BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());

                // 指定存放位置
                String path = downloadDir + File.separatorChar + fileFullName;
                file = new File(path);

                // 校验文件夹目录是否存在，不存在就创建一个目录
                if(! file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                if(! check) {
                    if(Community.LangID == 0) {
                        MenuUI2.checkReturn.setText("检查下载请求中");
                    } else if(Community.LangID == 1) {
                        MenuUI2.checkReturn.setText("Checking Download request");
                    }
                    //                    Thread.sleep(1000);

                    if(Community.LangID == 0) {
                        MenuUI2.checkReturn.setText("尝试下载中");
                    } else if(Community.LangID == 1) {
                        MenuUI2.checkReturn.setText("trying Download");
                    }

                    //                    Thread.sleep(1000);
                }

                //            System.out.println(fileFullName.length());
                //            if (file.length() > 10240) {
                out = new FileOutputStream(file);
                int size = 0;
                int len = 0;
                byte[] buf = new byte[8192];
                while((size = bin.read(buf)) != - 1) {

                    if(Errors.CannotHandle) {
                        break;
                    }

                    //                    Thread.sleep(1);
                    len += size;
                    out.write(buf, 0, size);
                    // 控制台打印文件下载的百分比情况
                    if(! check) {
                        String download = String.valueOf((float) (len) / 1024 / 1024);
                        String downloadFilePercentage = String.valueOf((float) (len) / fileLength * 100);
                        String downloadSource = String.valueOf((float) (fileLength) / 1024 / 1024);
                        download = download.substring(0, download.substring(0, download.indexOf(".")).length() + download.substring(download.indexOf(".")).length() / 2);
                        downloadFilePercentage = downloadFilePercentage.substring(0, downloadFilePercentage.substring(0, downloadFilePercentage.indexOf(".")).length() + downloadFilePercentage.substring(downloadFilePercentage.indexOf(".")).length() / 2);
                        downloadSource = downloadSource.substring(0, downloadSource.substring(0, downloadSource.indexOf(".")).length() + downloadSource.substring(downloadSource.indexOf(".")).length() / 2);
                        if(Community.LangID == 0) {
                            MenuUI2.checkReturn.setText("下载中:\n" + download + "MB / " + downloadSource + "MB\n" + downloadFilePercentage + "%");
                        } else if(Community.LangID == 1) {
                            MenuUI2.checkReturn.setText("Downloading:\n" + download + "MB / " + downloadSource + "MB\n" + downloadFilePercentage + "%");
                        }
                    }
                }

                // 关闭资源
                bin.close();
                out.close();

                if(! check) {
                    //                    Thread.sleep(500);

                    if(Community.LangID == 0) {
                        MenuUI2.checkReturn.setText("下载完成");
                    } else if(Community.LangID == 1) {
                        MenuUI2.checkReturn.setText("Download Finished");
                    }

                    //                    Thread.sleep(500);

                    if(Community.LangID == 0) {
                        MenuUI2.checkReturn.setText("尝试复制文件中");
                    } else if(Community.LangID == 1) {
                        MenuUI2.checkReturn.setText("trying copy file...");
                    }

                    //                    Thread.sleep(1000);

                    try (InputStream input = new FileInputStream("C:/.MCH/UPD.cache"); OutputStream output = new FileOutputStream(new getJar().getOldPath(this.getClass()))) {
                        //                        Thread.sleep(1);
                        byte[] buff = new byte[128];
                        int bytesRead;
                        while((bytesRead = input.read(buff)) > 0) {
                            if(Errors.CannotHandle) {
                                break;
                            }

                            output.write(buff, 0, bytesRead);

                            if(Community.LangID == 0) {
                                MenuUI2.checkReturn.setText("复制了:\n" + new File(new getJar().getOldPath(this.getClass())).length() + "Bytes/" + new File("C:/.MCH/UPD.cache").length() + "Bytes");
                            } else if(Community.LangID == 1) {
                                MenuUI2.checkReturn.setText("copied\n" + new File(new getJar().getOldPath(this.getClass())).length() + "Bytes/" + new File("C:/.MCH/UPD.cache").length() + "Bytes");
                            }
                        }
                    }

                    //                    Thread.sleep(1000);


                    File file = new File(downloadDir + fileFullName);
                    File file1 = new File("C:/.MCH/logs/cache/cache");
                    if(Community.saveCache) {
                        filesOperator.saveCache(file, file1, "UPD");
                    } else {
                        file.delete();
                    }
                }

                if(! check) {
                    if(Community.LangID == 0) {
                        MenuUI2.checkReturn.setText("安装中...");
                    } else if(Community.LangID == 1) {
                        MenuUI2.checkReturn.setText("installing...");
                    }

                    //                    Thread.sleep(1500);

                    if(Community.LangID == 0) {
                        MchUI.tips.setText("MCH更新完成,即将重启");
                    } else if(Community.LangID == 1) {
                        MchUI.tips.setText("MCH update finished,about to restart");
                    }

                    if(Community.LangID == 0) {
                        MenuUI2.checkReturn.setText("尝试重启中...");
                    } else if(Community.LangID == 1) {
                        MenuUI2.checkReturn.setText("trying restart...");
                    }

                    Thread.sleep(1000);

                    Runtime.getRuntime().exec("cmd.exe /k java -Xmx100M -Xms100M -jar " + new getJar().getOldPath(this.getClass()));

                    System.exit(0);
                }

                System.gc();
            }
        } catch (SocketTimeoutException e) {
            countTime.cannotUPD_connectTimeOut();
        } catch (SocketException e) {
            countTime.cannotUPD_connectReset();
        } catch (Exception e) {
            File file = new File(downloadDir + fileFullName);
            File file1 = new File("C:/.MCH/cache/cache");
            if(Community.saveCache) {
                filesOperator.saveCache(file, file1, "UPD");
            } else {
                file.delete();
            }
            countTime.startDUP_count = false;
            if(Community.LangID == 0) {
                MenuUI2.checkReturn.setText("更新失败:\n" + "更新行为被拒绝");
            } else if(Community.LangID == 1) {
                MenuUI2.checkReturn.setText("update Fail :\n" + "access denied");
            }
        }

        if(! check) {
            if(Community.LangID == 0) {
                MchUI.tips.setText("MCH更新失败");
            } else if(Community.LangID == 1) {
                MchUI.tips.setText("MCH update fail");
            }
        }

        System.gc();
    }

    public void run() {
        while(true) {
            try {
                Thread.sleep(50);

                if(Errors.CannotHandle) {
                    break;
                }

                if(! Community.isDaemons) {

                    if(checkUPD) {
                        //                检查更新代码,判断是否可以更新
                        UPD = checkUPD();

                        countTime.startDUP_count = false;

                        //                if (!timeOut) {
                        String Return = "";
                        if(Community.LangID == 0) {
                            if(UPD) {
                                countTime.counting = false;
                                Return = "MCH有版本更新可以下载";
                                Community.canUPD = true;
                                new URLs().UPD(true, true);
                            } else {
                                countTime.counting = false;
                                Return = "MCH没有可用更新";
                                Community.canUPD = false;
                            }

                            Return += "\n\n检查时间:\n" + times.format;
                        } else if(Community.LangID == 1) {
                            if(UPD) {
                                Return = "MCH Can update now";
                                Community.canUPD = true;
                                new URLs().UPD(true, true);
                            } else {
                                Return = "Cannot update MCH now";
                                Community.canUPD = false;
                            }

                            Return += "\n\ncheck Time:\n" + times.format;
                        }

                        if(UPD) {
                            BufferedReader br = new BufferedReader(new FileReader(ConfigMain.path + "UPD.cache", StandardCharsets.UTF_8));
                            String string;
                            StringBuilder newly = new StringBuilder();
                            int line = 0;
                            while((string = br.readLine()) != null) {
                                newly.append(string).append("\n");

                                if(line == 0) {
                                    if(Community.LangID == 0) {
                                        newly.insert(0, "新版本:");
                                    } else if(Community.LangID == 1) {
                                        newly.insert(0, "new version:");
                                    }
                                }

                                System.out.println(string);

                                line++;

                                if(line == 1) {
                                    if(Community.LangID == 0) {
                                        newly.append("当前版本:").append(Community.verID).append("(").append(Community.ver).append(")\n\n");
                                    } else if(Community.LangID == 1) {
                                        newly.append("now version:").append(Community.verID).append("(").append(Community.ver).append(")\n\n");
                                    }
                                }
                            }

                            br.close();

                            MenuUI2.updateInfo.setText(newly.toString());

                            if(Community.saveCache) {
                                filesOperator.saveCache(new File(ConfigMain.path + "UPD.cache"), new File(ConfigMain.path + "save/cache/"), "UPD_View");
                            } else {
                                new File(ConfigMain.path + "UPD.cache").delete();
                            }

                            MchUI.tips.setText(lang.get("MchHaveNewVer"));

                            if(Community.autoUPD) {
                                MchUI.tips.setText(lang.get("autoUpdating"));

                                new URLs().UPD(false, false);
                            }
                        } else {
                            MenuUI2.updateInfo.setText(lang.get("noNewVersion") + Community.verID + "(" + Community.ver + ")" + "\n" + "\n" + lang.get("nowFeature"));
                        }
                        //                输出提示
                        MenuUI2.checkReturn.setText(Return);

                        Return = "";

                        checkUPD = false;

                        System.gc();
                    }

                    if(nowUPD) {
                        UPD(false, false);
                        nowUPD = false;
                    }
                } else {
                    Thread.sleep(500);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}