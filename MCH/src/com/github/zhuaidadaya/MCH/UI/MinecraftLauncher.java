package com.github.zhuaidadaya.MCH.UI;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.Events.Events;
import com.github.zhuaidadaya.MCH.Events.LoadAssembly;
import com.github.zhuaidadaya.MCH.lib.json.JSONArray;
import com.github.zhuaidadaya.MCH.lib.json.JSONObject;
import test.forceFound;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.github.zhuaidadaya.MCH.lib.Resources.initLanguage.lang;

public class MinecraftLauncher {
    public static JFrame jFrame = new JFrame();
    public static JButton launch = new JButton();
    public static JButton deleteVersion = new JButton();
    public static JTextPane deleteWarning = new JTextPane();
    public static JLabel shortInfo = new JLabel();
    public static JProgressBar loadingProgress = new JProgressBar();
    public static JTextPane loadingStatus = new JTextPane();
    public static JList<Object> verList = new JList<>();
    public static JScrollPane verScrollPane = new JScrollPane();
    public static JLabel stepNow = new JLabel();
    public static JPanel launchPanel = new JPanel();

    public static JPanel downloadPanel = new JPanel();
    public static JList<Object> verList_download = new JList<>();
    public static JScrollPane downloadScrollPane = new JScrollPane();
    public static JLabel showSelect = new JLabel();
    public static JButton showAll = new JButton();
    public static JButton showRelease = new JButton();
    public static JButton showSnapshot = new JButton();
    public static JButton showAlpha = new JButton();
    public static JButton freshList = new JButton();
    public static JProgressBar downloadProgress = new JProgressBar();
    public static JTextPane downloadStatus = new JTextPane();
    public static JButton startDownload = new JButton();
    public static JButton downloadAndLaunch = new JButton();
    public static JButton downloadAndFastLaunch = new JButton();
    //    public static JButton downloadAll = new JButton();
    public static JLabel downloadName_label = new JLabel();
    public static JTextPane downloadName = new JTextPane();
    public static JTextPane verInfo_download = new JTextPane();

    public static JPanel settingsPanel = new JPanel();
    public static JLabel checkResOption = new JLabel();
    public static JButton checkRes = new JButton();
    public static JButton noCheckRes = new JButton();

    public static JPanel javaPanel = new JPanel();
    public static JList<Object> javaList = new JList<>();
    public static JLabel usingJava = new JLabel();
    public static JScrollPane javaScrollPane = new JScrollPane();
    public static JButton setUsingJava = new JButton();
    public static JButton importJava = new JButton();
    public static JTextPane javaStatus = new JTextPane();

    public static JPanel runningPanel = new JPanel();
    public static JList<Object> runningList = new JList<>();
    public static JScrollPane runningScrollPane = new JScrollPane();
    public static JTextPane runningMinecraftStatus = new JTextPane();

    public static JPanel minecraftAreaPanel = new JPanel();
    public static JList<Object> minecraftAreaList = new JList<>();
    public static JScrollPane minecraftAreaScrollPane = new JScrollPane();
    public static JTextPane minecraftAreaDescription = new JTextPane();
    public static JTextPane minecraftAreaStatus = new JTextPane();

    public static JButton switchDownloadPanel = new JButton();
    public static JButton switchLauncherPanel = new JButton();
    public static JButton switchSettingsPanel = new JButton();
    public static JButton switchJavaPanel = new JButton();
    public static JButton switchRunningPanel = new JButton();
    public static JButton switchMinecraftAreaPanel = new JButton();
    public static JPanel menuPanel = new JPanel();

    //-1 = all, 0 = release, 1 = snapshot, 2 = old alpha/beta
    public static int downloadListDisplay = - 1;
    public static String versionPath = Config.path + "minecraft/versions/";

    public static LinkedHashMap<Object, Object> runningMinecraft = new LinkedHashMap<>();
    public static LinkedHashMap<Object, Object> runningLogs = new LinkedHashMap<>();
    public static LinkedHashMap<Object, Object> minecraftDownloadVersions = getDownloadVersions("");
    public static LinkedHashMap<Object, Object> downloadingMinecraft = new LinkedHashMap<>();
    public static LinkedHashMap<Object, Object> minecraftVersions = getVersions(versionPath);
    public static LinkedHashMap<Object, Object> downloads = new LinkedHashMap<>();
    public static LinkedHashMap<Object, Object> javaPaths = new LinkedHashMap<>();
    public static LinkedHashMap<Object, Object> minecraftAreas = new LinkedHashMap<>();

    public static LinkedHashSet<Object> minecraftVersionsList_fresh = new LinkedHashSet<>();
    public static LinkedHashSet<Object> minecraftVersionsList_use = new LinkedHashSet<>();
    public static LinkedHashSet<Object> deletingMinecraft = new LinkedHashSet<>();
    public static LinkedHashSet<Object> JavasList = new LinkedHashSet<>();

    public static boolean downloading = false;

    public static boolean launching = false;

    public static LinkedHashSet<Object> breakLaunch = new LinkedHashSet<>();

    public static boolean fastLauncher = false;

    public static int selectionIndex = - 1;

    public static String unSupportConfig = "";
    public static String checkResource_config = "checkResource@check";
    public static boolean checkResource = true;
    public static JSONObject minecraftAreasConfig = new JSONObject();

    public static String javaVersion = "";
    public static String java = "java";

    public MinecraftLauncher() {
    }

    public static void selectJava(String title) {
        try {
            int result;
            new File("");
            String path;

            UIManager.put("FileChooser.cancelButtonText", lang.get("cancel"));
            UIManager.put("FileChooser.saveButtonText", lang.get("confirm"));
            UIManager.put("FileChooser.openButtonText", lang.get("confirm"));

            JFileChooser fileChooser = new JFileChooser();
            FileSystemView fsv = FileSystemView.getFileSystemView();
            fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
            fileChooser.setDialogTitle(title);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());

            if(Community.os.contains("Windows")) {
                fileChooser.setFileFilter(new javaFileFilter("java.exe"));
                fileChooser.addChoosableFileFilter(new javaFileFilter("javaw.exe"));
            } else {
                fileChooser.setFileFilter(new javaFileFilter("java"));
                fileChooser.addChoosableFileFilter(new javaFileFilter("javaw"));
            }

            result = fileChooser.showOpenDialog(jFrame);
            if(JFileChooser.APPROVE_OPTION == result) {
                path = fileChooser.getSelectedFile().getPath();
                if(new File(path).isFile()) {
                    javaPaths.put(new File(path).getAbsolutePath(), "{\"path\":\"" + path + "\"}");
                } else if(new File(path).isDirectory()) {
                    forceFound.foundJava(new File(path), 3);
                    if(forceFound.getJavas().equals("")) {
                        selectJava(lang.get("cannot_found_java_and_retry"));
                    } else {
                        for(Object o : forceFound.getJavas().split("\n"))
                            javaPaths.put(o.toString(), "{\"path\":\"" + o + "\"}");
                    }
                }

                uploadConfig();

                Config.WriteIni();
            }
        } catch (Exception ignored) {

        }
    }

    public static void freshDownloadList() {
        new Thread(() -> {
            downloading = true;

            verList_download.clearSelection();

            if(downloadListDisplay == - 1)
                minecraftDownloadVersions = getDownloadVersions("");
            else if(downloadListDisplay == 0)
                minecraftDownloadVersions = getDownloadVersions("release");
            else if(downloadListDisplay == 1)
                minecraftDownloadVersions = getDownloadVersions("snapshot");
            else if(downloadListDisplay == 2)
                minecraftDownloadVersions = getDownloadVersions("old");

            verList_download.setListData(minecraftDownloadVersions.keySet().toArray());

            downloading = false;
        }).start();
    }

    public static boolean customDownLoad(String res, String toFile, boolean showProgress, long length) {
        return customDownLoad(res, toFile, showProgress, length, false);
    }

    public static boolean customDownLoad(String res, String toFile, boolean showProgress, long length, boolean launchPro) {
        return customDownLoad(res, toFile, showProgress, length, launchPro, 0);
    }

    public static void setTextByCustomDownload(File file, long length, boolean launchPro, String res) {
        if(file.isFile() & file.length() == length) {
            if(! launchPro)
                downloadStatus.setText(lang.get("skip") + ": " + res + "\n" + downloadProgress.getValue() / 2 + " / " + downloadProgress.getMaximum() / 2 + "\n");
            else
                loadingStatus.setText(lang.get("skip") + ": " + res + "\n" + loadingProgress.getValue() / 2 + " / " + loadingProgress.getMaximum() / 2 + "\n");
        } else {
            if(! launchPro)
                downloadStatus.setText(lang.get("downloads") + ": " + res + "\n" + downloadProgress.getValue() / 2 + " / " + downloadProgress.getMaximum() / 2 + "\n");
            else
                loadingStatus.setText(lang.get("downloads") + ": " + res + "\n" + loadingProgress.getValue() / 2 + " / " + loadingProgress.getMaximum() / 2 + "\n");
        }
    }

    public static boolean customDownLoad(String res, String toFile, boolean showProgress, long length, boolean launchPro, int reTryCount) {
        try {
            File file = new File(toFile);

            setTextByCustomDownload(file, length, launchPro, res);
            if(file.isFile() & file.length() == length) {
                return true;
            }

            URL url = new URL(res);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();
            int fileLength = httpURLConnection.getContentLength();

            if(showProgress)
                downloadProgress.setMaximum(fileLength);

            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            if(! file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file, false));
            int size;
            int len = 0;
            byte[] buf = new byte[8192];
            while((size = bis.read(buf)) != - 1) {
                if(Errors.CannotHandle)
                    break;
                len += size;
                out.write(buf, 0, size);
                //                String download = String.valueOf((float) (len) / 1024 / 1024);
                //                String downloadFilePercentage = String.valueOf((float) (len) / fileLength * 100);
                //                String downloadSource = String.valueOf((float) (fileLength) / 1024 / 1024);
                //                download = download.substring(0, download.substring(0, download.indexOf(".")).length() + download.substring(download.indexOf(".")).length() / 2);
                //                downloadFilePercentage = downloadFilePercentage.substring(0, downloadFilePercentage.substring(0, downloadFilePercentage.indexOf(".")).length() + downloadFilePercentage.substring(downloadFilePercentage.indexOf(".")).length() / 2);
                //                downloadSource = downloadSource.substring(0, downloadSource.substring(0, downloadSource.indexOf(".")).length() + downloadSource.substring(downloadSource.indexOf(".")).length() / 2);
                //                    downloadingObj.put(res,String.format(lang.get("downloading"),res,download,downloadSource,downloadFilePercentage + "%"));

                if(showProgress)
                    downloadProgress.setValue(len);
            }

            bis.close();
            out.close();
            httpURLConnection.disconnect();

            if(showProgress)
                downloadProgress.setMaximum(0);

            return true;
        } catch (Exception e) {
            if(reTryCount > 10)
                return false;
            customDownLoad(res, toFile, showProgress, length, launchPro, reTryCount + 1);
        }
        return false;
    }

    public static void show() {
        jFrame.setVisible(true);
    }

    public static LinkedHashMap<Object, Object> getVersions(String verPath) {
        LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();

        try {
            for(File f : Objects.requireNonNull(new File((verPath)).listFiles())) {
                if(f.isDirectory()) {
                    JSONObject addTo = new JSONObject();

                    try {
                        String path = f.getAbsolutePath().replace("\\", "/");
                        String version = path.substring(path.lastIndexOf("/") + 1);
                        if(new File(path + "/DELETING").isFile())
                            throw new Exception();

                        BufferedReader ver = new BufferedReader(new FileReader(path + "/" + version + ".json"));
                        String s;

                        BufferedReader status;

                        StringBuilder statusJson = new StringBuilder();

                        status = new BufferedReader(new FileReader(path + "/" + version + "_status.json"));

                        while((s = status.readLine()) != null) {
                            statusJson.append(s);
                        }

                        status.close();

                        StringBuilder verJson = new StringBuilder();
                        while((s = ver.readLine()) != null)
                            verJson.append(s);

                        ver.close();

                        try {
                            JSONObject source_status = new JSONObject(statusJson.toString());

                            downloadingMinecraft.put(version, source_status);

                            addTo.put("status", "#" + source_status.get("status").toString());
                            addTo.put("progress", source_status.get("progress").toString());
                        } catch (Exception e) {
                            addTo.put("status", "Unknow");
                            addTo.put("progress", "NaN");
                        }
                        JSONObject source = new JSONObject(verJson.toString());

                        try {
                            addTo.put("path", f.getAbsolutePath());
                            addTo.put("type", source.get("type").toString());
                            addTo.put("id", source.get("id").toString());
                            addTo.put("java_version_need", new JSONObject(source.get("javaVersion").toString()).get("majorVersion").toString());
                        } catch (Exception e) {

                        }

                        //                    addTo.put()
                        hashMap.put(f.getName(), addTo);
                    } catch (Exception e) {
                        addTo.put("status", "#invalid");
                        addTo.put("path", f.getAbsolutePath());
                        hashMap.put(f.getName(), addTo);
                    }
                }
            }
        } catch (Exception e) {

        }

        if(hashMap.size() == 0) {
            hashMap.put(lang.get("game_not_found"), lang.get("game_are_not_found_here"));
        }

        return hashMap;
    }

    public static LinkedHashMap<Object, Object> getJavaVersions() {
        configReads();

        LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();

        try {
            for(Object o : Community.launcherConf.get("javaPaths").toString().split(";")) {
                if(! o.equals("")) {
                    hashMap.put(o.toString(), "");
                }
            }
        } catch (Exception e) {

        }

        if(hashMap.size() == 0) {
            hashMap.put(lang.get("java_not_found"), lang.get("java_are_not_found_here"));
        }

        return hashMap;
    }

    public static void runMinecraft(String gamePath, String gameVersionName, boolean fastLaunch) {

        boolean check1 = false;

        try {
            Process p = Runtime.getRuntime().exec(java);

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream(), Charset.forName("GBk")));

            while(br.readLine() != null) {
                check1 = true;
            }

            Runtime.getRuntime().exec("taskkill /F /PID " + p.pid());
        } catch (Exception e) {

        }

        boolean check = false;
        try {
            check = new File(java).isFile();
        } catch (Exception ignored) {

        }

        if(check | check1) {
            gamePath = gamePath.replace("\\", "/");
            boolean canUse = true;

            stepNow.setText(lang.get("check_java"));

            try {
                Process p = Runtime.getRuntime().exec("\"" + java + "\" -cp \"" + gamePath + "/client.jar" + "\" net.minecraft.client.main.Main");

                BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream(), Charset.forName("GBk")));
                String info;

                while((info = br.readLine()) != null) {
                    if(info.replace(" ", "").replace("\t", "").startsWith("java.lang.UnsupportedClassVersionError")) {
                        canUse = false;
                    }
                }

                Runtime.getRuntime().exec("taskkill /F /PID " + p.pid());
            } catch (Exception e) {

            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(canUse) {
                String nativePath = gamePath + "/natives";
                String nativeArg = "-Djava.library.path=\"" + nativePath + "\"";

                StringBuilder cpPath = new StringBuilder();

                try {
                    BufferedReader versionReader = new BufferedReader(new FileReader(gamePath + "/" + gamePath.substring(gamePath.lastIndexOf("/") + 1) + ".json"));

                    StringBuilder json = new StringBuilder();
                    String readVer;
                    while((readVer = versionReader.readLine()) != null)
                        json.append(readVer);

                    JSONObject source = new JSONObject(json.toString());
                    JSONArray libs = new JSONArray(source.getJSONArray("libraries").toString());
                    JSONObject assetIndex = new JSONObject(source.get("assetIndex").toString());

                    stepNow.setText(lang.get("check_res"));

                    if(! fastLaunch) {
                        new downloadMinecraft().startOneMinecraftDownload(gamePath.substring(gamePath.lastIndexOf("/") + 1), source.get("id").toString(), true);
                    }

                    loadingProgress.setMaximum(libs.length() * 2);

                    stepNow.setText(lang.get("format_files"));

                    for(Object o : libs) {
                        if(breakLaunch.contains(gameVersionName)) {
                            launching = false;
                            return;
                        }

                        loadingStatus.setText(o.toString());

                        loadingProgress.setValue(loadingProgress.getValue() + 1);

                        JSONObject each = new JSONObject(o.toString());

                        JSONObject lib = new JSONObject(each.get("downloads").toString());

                        boolean breakOnce = false;
                        try {
                            for(Object o1 : each.getJSONArray("rules")) {
                                if(Community.os.contains("Windows")) {
                                    if(new JSONObject(new JSONObject(o1.toString()).get("os").toString()).get("name").toString().equals("osx") & new JSONObject(o1.toString()).get("action").toString().equals("allow"))
                                        breakOnce = true;
                                }
                            }
                        } catch (Exception e) {

                        }

                        loadingProgress.setValue(loadingProgress.getValue() + 1);

                        if(! breakOnce) {
                            try {
                                JSONObject natives = new JSONObject(lib.get("classifiers").toString());
                                try {
                                    JSONObject nativeJson = new JSONObject(natives.get("natives-windows").toString());

                                    unZipFiles(Config.path + "minecraft/libs/" + nativeJson.get("path").toString(), nativePath + "/");
                                } catch (Exception e) {

                                }
                            } catch (Exception e) {
                                try {
                                    JSONObject artifact = new JSONObject(lib.get("artifact").toString());

                                    cpPath.append(Config.path).append("minecraft/libs/").append(artifact.get("path").toString()).append(";");
                                } catch (Exception ex) {

                                }
                            }
                        } else {

                        }
                    }

                    stepNow.setText(lang.get("generate_starter"));

                    cpPath.append(gamePath).append("/client.jar");

                    String CompleteLauncher = "\"" + java + "\" -XX:+UseG1GC -XX:-UseAdaptiveSizePolicy -XX:-OmitStackTraceInFastThrow -Dfml.ignoreInvalidMinecraftCertificates=True -Dfml.ignorePatchDiscrepancies=True -XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump " + nativeArg + " -Dminecraft.launcher.brand=MCH -Dminecraft.launcher.version=10 -cp \"" + cpPath + "\" " + source.get("mainClass").toString() + " --username zhuaidada --version \"" + gameVersionName + "\" --gameDir \"" + gamePath + "\" --assetsDir \"" + Config.path + "minecraft/assets\" --assetIndex " + assetIndex.get("id").toString() + " --uuid 0000000000000009AB3A8C5C95DDD7C8 --accessToken 0000000000000009AB3A8C5C95DDD7C8 --userProperties {} --userType Legacy --width 854 --height 480";

                    loadingStatus.setText(CompleteLauncher);

                    Thread.sleep(500);

                    try {
                        if(breakLaunch.contains(gameVersionName)) {
                            launching = false;
                            return;
                        }

                        Process process = Runtime.getRuntime().exec(CompleteLauncher);

                        JSONObject runningStatus = new JSONObject();
                        runningStatus.put("pid", process.pid());
                        runningStatus.put("name", gameVersionName);
                        runningStatus.put("version", source.get("id").toString());
                        runningStatus.put("status", "running");
                        runningStatus.put("type", source.get("type").toString());
                        runningStatus.put("path", gamePath);

                        runningMinecraft.put(process.pid(), runningStatus);

                        String finalGamePath = gamePath;
                        new Thread(() -> {
                            try {

                                new Thread(() -> {
                                    while(new JSONObject(runningMinecraft.get(process.pid()).toString()).get("status").toString().equals("running")) {
                                        try {
                                            if(breakLaunch.contains(gameVersionName)) {
                                                exec("taskkill /F /PID " + process.pid());
                                                return;
                                            }
                                            Thread.sleep(100);
                                        } catch (Exception e) {

                                        }
                                    }
                                }).start();

                                downloadingMinecraft.remove(gameVersionName);
                                downloads.remove(gameVersionName);

                                BufferedReader minecraftListener = new BufferedReader(new InputStreamReader(process.getInputStream()));

                                String readLog;
                                StringBuilder log = new StringBuilder();

                                while((readLog = minecraftListener.readLine()) != null) {
                                    log.insert(0, readLog);
                                    runningLogs.put(process.pid(), log.toString());
                                }

                                JSONObject nowSelectMinecraft = new JSONObject(minecraftVersions.get(verList.getSelectedValue()).toString());
                                if(nowSelectMinecraft.get("path").toString().replace("\\", "/").equals(finalGamePath)) {
                                    runningStatus.put("status", "stopped");
                                    runningMinecraft.put(process.pid(), runningStatus);
                                }

                            } catch (Exception ignored) {

                            }
                        }).start();

                        Thread.sleep(6000);
                        if(new JSONObject(runningMinecraft.get(process.pid()).toString()).get("status").toString().equals("running"))
                            stepNow.setText(lang.get("launched"));
                        else
                            stepNow.setText(lang.get("launch_fail"));
                    } catch (Exception e) {
                        stepNow.setText(lang.get("launch_fail"));
                    }
                } catch (Exception e) {
                    stepNow.setText(lang.get("launch_fail"));
                }
            } else {
                stepNow.setText(lang.get("java_cannot_use"));
            }
        } else {
            stepNow.setText(lang.get("java_not_found"));
        }

        launching = false;
    }

    public static void deleteFile(String filePath) {
        try {
            for(File f : new File(filePath).listFiles()) {
                if(f.isFile())
                    f.delete();
                else if(f.isDirectory())
                    deleteFile(f.getAbsolutePath());
            }

            new File(filePath).delete();
        } catch (Exception ignored) {

        }
    }

    public static LinkedHashMap<Object, Object> getDownloadVersions(LinkedHashMap<Object, Object> hashMap, String type) {
        StringBuilder json = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(Config.path + "/minecraft/versions/ver.json"));

            String s;
            while((s = br.readLine()) != null)
                json.append(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject verJson = new JSONObject(json.toString());
        JSONArray versions = new JSONArray(verJson.getJSONArray("versions").toString());

        downloadProgress.setMaximum(versions.length());

        int skips = 0;

        for(Object o : versions) {
            downloadProgress.setValue(downloadProgress.getValue() + 1);

            JSONObject version = new JSONObject(new JSONObject(o.toString()).toString());

            boolean skip = true;

            if(! type.equals("old")) {
                if(version.get("type").toString().equals(type) || type.equals("")) {
                    hashMap.put(version.get("id"), new JSONObject(version.toString()));
                    skip = false;
                }
            } else {
                if(version.get("type").toString().contains(type)) {
                    hashMap.put(version.get("id"), new JSONObject(version.toString()));
                    skip = false;
                }
            }

            if(skip)
                skips++;

            try {
                downloadStatus.setText(String.format(lang.get("loading_versions"), version.get("id"), downloadProgress.getValue(), versions.length(), skips));
            } catch (Exception e) {

            }
        }

        downloadProgress.setMaximum(0);

        downloadStatus.setText("");

        return hashMap;
    }

    public static LinkedHashMap<Object, Object> getDownloadVersions(String type) {
        LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();

        File f = new File(Config.path + "/minecraft/versions/ver.json");
        if(! f.isFile()) {
            if(! customDownLoad("https://launchermeta.mojang.com/mc/game/version_manifest.json", Config.path + "/minecraft/versions/ver.json", true, - 1)) {
                hashMap.put("error", String.format(lang.get("get_ver_error_internet"), """

                        =========
                        475 Request not FeedBack
                        ========="""));
                if(f.isFile()) {
                    hashMap = getDownloadVersions(hashMap, type);
                } else {
                    hashMap.put("error", String.format(lang.get("get_ver_error"), "\n=========\n475 Request not FeedBack\n684 URL are not existent\n684 URL: file://" + f.getAbsolutePath().replace("\\", "/") + "\n========="));
                }
                verList_download.setSelectedIndex(0);
                downloadStatus.setText(hashMap.get("error").toString().substring(14));
            } else {
                hashMap = getDownloadVersions(hashMap, type);
            }
        } else {
            hashMap = getDownloadVersions(hashMap, type);
        }
        return hashMap;
    }

    public static void uploadConfig() {
        StringBuilder javas = new StringBuilder();
        try {
            for(Object o : JavasList) {
                if(new File(o.toString()).isFile()) {
                    javas.append(o).append(";");
                }
            }
        } catch (Exception e) {

        }

        javas.insert(0, "javaPaths@");
        String usingJava = "java@" + java;

        for(String s : Arrays.asList(checkResource_config, javas.toString(), usingJava)) {
            Community.launcherConf.put(s.substring(0, s.indexOf("@")), s.substring(s.indexOf("@") + 1));
        }
    }

    public static void UI() {
        configReads();

        java = Community.launcherConf.get("java") == null ? "java" : Community.launcherConf.get("java").toString();
        javaPaths = getJavaVersions();

        uploadConfig();

        try {
            Config.WriteIni();
        } catch (Exception e) {

        }

        downloadStatus.setEditable(false);
        loadingStatus.setEditable(false);
        javaStatus.setEditable(false);

        deleteWarning.setEditable(false);

        minecraftAreaDescription.setEditable(false);

        //        jFrame.setResizable(false);

        new Thread(() -> {
            for(Object o : downloadingMinecraft.keySet()) {
                JSONObject status = new JSONObject(downloadingMinecraft.get(o.toString()).toString());
                String downloadStatus = status.get("status").toString();
                String download_Name = status.get("name").toString();
                String downloadTarget = status.get("target").toString();

                if(downloadStatus.equals("downloading")) {
                    new Thread(() -> {
                        new downloadMinecraft().startOneMinecraftDownload(download_Name, downloadTarget, false);
                    }).start();
                }

                if(downloadStatus.equals("checking")) {
                    new Thread(() -> {
                        try {
                            status.put("status", "ready");
                            status.put("progress", "1/1");

                            downloadingMinecraft.put(download_Name, status);

                            FileOutputStream statusWriter = new FileOutputStream(versionPath + download_Name + "/" + download_Name + "_status.json", false);

                            statusWriter.write(status.toString().getBytes());
                        } catch (Exception e) {

                        }
                    }).start();
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(MinecraftLauncher :: manager).start();
        new Thread(() -> {
            while(! Errors.CannotHandle) {
                try {
                    if(downloads.size() > 0) {
                        for(Object o : downloads.keySet()) {
                            downloadProgress.setMaximum(Integer.parseInt(String.valueOf(((LinkedHashMap) downloads.get(o.toString())).get("max"))));
                            downloadProgress.setValue(Integer.parseInt(String.valueOf(((LinkedHashMap) downloads.get(o.toString())).get("value"))));
                        }
                    } else {
                        Thread.sleep(100);
                    }
                } catch (Exception e) {

                }

                try {
                    minecraftVersions = getVersions(versionPath);
                    int select = Math.max(verList.getSelectedIndex(), 0);
                    verList.setListData(minecraftVersions.keySet().toArray());
                    verList.setSelectedIndex(select);
                    try {
                        new JSONObject(minecraftVersions.get(verList.getSelectedValue().toString()).toString());
                        if(verList.getSelectedIndex() != - 1) {
                            deleteVersion.setVisible(true);
                            deleteWarning.setVisible(true);
                        } else {
                            deleteVersion.setVisible(false);
                            deleteWarning.setVisible(false);
                        }
                    } catch (Exception e) {
                        deleteVersion.setVisible(false);
                        deleteWarning.setVisible(false);
                    }
                } catch (Exception e) {

                }

                try {
                    minecraftVersionsList_fresh.clear();
                    for(Object o : minecraftVersions.keySet()) {
                        try {
                            new JSONObject(minecraftVersions.get(o.toString()).toString()).get("path");
                            minecraftVersionsList_fresh.add(o.toString());
                        } catch (Exception e) {

                        }
                    }

                    minecraftVersionsList_use = minecraftVersionsList_fresh;
                } catch (Exception e) {

                }

                String status = "";
                try {
                    status = new JSONObject(minecraftVersions.get(verList.getSelectedValue().toString()).toString()).get("status").toString();
                } catch (Exception e) {

                }

                launch.setVisible(minecraftVersionsList_use.size() > 0 & verList.getSelectedIndex() != - 1 & ! status.equals("#downloading"));
                stepNow.setVisible(launch.isVisible());
                if(selectionIndex != verList.getSelectedIndex()) {
                    stepNow.setText("");
                    selectionIndex = verList.getSelectedIndex();
                    if(verList.getModel().getSize() < selectionIndex)
                        verList.setSelectedIndex(selectionIndex);
                }

                setLoadingStatus(launching);
                setJavaStatus();

                try {
                    javaPaths = getJavaVersions();
                    int selected = Math.max(javaList.getSelectedIndex(), 0);
                    if(JavasList.size() != 0) {
                        javaList.setListData(JavasList.toArray());
                    } else {
                        javaList.setListData(javaPaths.keySet().toArray());
                    }
                    javaList.setSelectedIndex(selected);
                } catch (Exception e) {

                }

                try {
                    if(! java.equals("java")) {
                        usingJava.setText(lang.get("used_java") + ": " + java);
                    } else {
                        usingJava.setText(lang.get("used_java") + ": " + lang.get("default"));
                    }
                } catch (Exception e) {

                }

                Runtime.getRuntime().gc();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
        }).start();

        verList_download.setListData(minecraftDownloadVersions.keySet().toArray());

        jFrame.setSize(796, 488);

        jFrame.getContentPane().setBackground(Color.white);

        verScrollPane.getVerticalScrollBar().setValue(0);
        verScrollPane.getHorizontalScrollBar().setValue(0);
        verScrollPane.setViewportView(verList);

        runningScrollPane.getVerticalScrollBar().setValue(0);
        runningScrollPane.getHorizontalScrollBar().setValue(0);
        runningScrollPane.setViewportView(runningList);

        downloadScrollPane.getVerticalScrollBar().setValue(0);
        downloadScrollPane.getHorizontalScrollBar().setValue(0);
        downloadScrollPane.setViewportView(verList_download);

        javaScrollPane.getVerticalScrollBar().setValue(0);
        javaScrollPane.getHorizontalScrollBar().setValue(0);
        javaScrollPane.setViewportView(javaList);

        minecraftAreaScrollPane.getVerticalScrollBar().setValue(0);
        minecraftAreaScrollPane.getHorizontalScrollBar().setValue(0);
        minecraftAreaScrollPane.setViewportView(minecraftAreaList);

        //        窗口初始化设置
        //获得屏幕大小
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        jFrame.setLocation(width / 2 - jFrame.getWidth() / 2, height / 2 - jFrame.getHeight() / 2);

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
                verScrollPane.setBounds(jFrame.getWidth() - jFrame.getWidth() / 3 + 10, 0, jFrame.getWidth(), launchPanel.getHeight());
                loadingProgress.setBounds(150, 0, jFrame.getWidth() / 3 + 120, 20);
                loadingStatus.setBounds(150, 20, jFrame.getWidth() / 3 + 120, jFrame.getHeight());

                runningScrollPane.setBounds(jFrame.getWidth() - jFrame.getWidth() / 3 + 10, 0, jFrame.getWidth() / 3, runningPanel.getHeight());
                javaScrollPane.setBounds(0, - 2, jFrame.getWidth() + 10, javaPanel.getHeight() / 2);
                javaStatus.setBounds(jFrame.getWidth() / 2, javaPanel.getHeight() / 2, jFrame.getWidth() / 2, javaPanel.getHeight() / 2);
                usingJava.setBounds(0, javaPanel.getHeight() / 2, jFrame.getWidth() / 2, 30);
                setUsingJava.setBounds(5, javaPanel.getHeight() / 2 + 100, 122, 30);
                importJava.setBounds(5, javaPanel.getHeight() / 2 + 140, 122, 30);

                minecraftAreaList.setBounds(jFrame.getWidth() - jFrame.getWidth() / 3 + 10, 0, jFrame.getWidth(), minecraftAreaPanel.getHeight());
                minecraftAreaDescription.setBounds(0, (jFrame.getHeight() / 3) * 2, jFrame.getWidth() / 3, minecraftAreaPanel.getHeight());

                checkResOption.setBounds(5, 5, 100, 30);
                checkRes.setBounds(110, 5, 100, 30);
                noCheckRes.setBounds(220, 5, 100, 30);

                stepNow.setBounds(10, 70, 140, 30);
                launch.setBounds(10, 100, 122, 40);

                deleteVersion.setBounds(10, 250, 122, 40);
                deleteWarning.setBounds(10, 300, 150, 150);

                downloadName_label.setBounds(2, 40, (jFrame.getWidth() / 3) / 2 + 60, 20);
                downloadName.setBounds(2, 60, (jFrame.getWidth() / 3) / 2 + 60, 20);

                startDownload.setBounds(10, 100, 170, 40);
                downloadAndLaunch.setBounds(10, 150, 170, 40);
                downloadAndFastLaunch.setBounds(10, 200, 170, 40);
                //                downloadAll.setBounds(10, 350, 170, 40);
                downloadProgress.setBounds(jFrame.getWidth() - (jFrame.getWidth() / 3) * 2 - 60, 0, jFrame.getWidth() / 3 + 70, 20);

                downloadStatus.setBounds(jFrame.getWidth() - (jFrame.getWidth() / 3) * 2 - 60, 20, jFrame.getWidth() / 3 + 70, jFrame.getHeight());

                downloadScrollPane.setBounds(jFrame.getWidth() - jFrame.getWidth() / 3 + 10, 0, jFrame.getWidth() / 3 + 100, jFrame.getHeight() - 150);
                showSelect.setBounds(jFrame.getWidth() - jFrame.getWidth() / 3 + 10, jFrame.getHeight() - 155, (jFrame.getWidth() / 3), 30);
                showRelease.setBounds(jFrame.getWidth() - jFrame.getWidth() / 3 + 10, jFrame.getHeight() - 150 + 20, (jFrame.getWidth() / 3) / 3 - 10, 30);
                showSnapshot.setBounds(jFrame.getWidth() - jFrame.getWidth() / 3 + (jFrame.getWidth() / 3) / 3, jFrame.getHeight() - 150 + 20, (jFrame.getWidth() / 3) / 3, 30);
                showAlpha.setBounds(jFrame.getWidth() - jFrame.getWidth() / 3 + ((jFrame.getWidth() / 3) / 3) * 2, jFrame.getHeight() - 150 + 20, (jFrame.getWidth() / 3) / 3, 30);
                showAll.setBounds(jFrame.getWidth() - jFrame.getWidth() / 3 + 10, jFrame.getHeight() - 120 + 20, (jFrame.getWidth() / 3) / 2 - 10, 30);
                freshList.setBounds(jFrame.getWidth() - jFrame.getWidth() / 3 + (jFrame.getWidth() / 3) / 2, jFrame.getHeight() - 120 + 20, (jFrame.getWidth() / 3) / 2, 30);

                switchLauncherPanel.setBounds(0, 1, 100, 31);
                switchDownloadPanel.setBounds(100, 1, 100, 31);
                switchSettingsPanel.setBounds(200, 1, 100, 31);
                switchJavaPanel.setBounds(300, 1, 100, 31);
                switchRunningPanel.setBounds(400, 1, 100, 31);
                switchMinecraftAreaPanel.setBounds(500, 1, 100, 31);

                launchPanel.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight() - 70);
                downloadPanel.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight() - 70);
                settingsPanel.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight() - 70);
                javaPanel.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight() - 70);
                runningPanel.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight() - 70);
                minecraftAreaPanel.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight() - 70);

                menuPanel.setBounds(0, jFrame.getHeight() - 70, jFrame.getWidth(), 70);
            }
        };

        verList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        verList_download.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        javaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        runningList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        launch.addActionListener(e -> {
            ListModel<?> listModel = verList.getModel();

            try {
                launchMinecraft(listModel.getElementAt(verList.getSelectedIndex()).toString());
            } catch (Exception e1) {

            }
        });

        verList_download.addListSelectionListener(e -> {
            try {
                ListModel<?> listModel = verList_download.getModel();

                StringBuilder des = new StringBuilder();
                String source = minecraftDownloadVersions.get(listModel.getElementAt(verList_download.getSelectedIndex()).toString()).toString();

                JSONObject json = new JSONObject();
                try {
                    json = new JSONObject(source);
                } catch (Exception v1) {

                }

                boolean err = false;

                if(source.startsWith("error-internet")) {
                    err = true;
                    des = new StringBuilder(source.substring(14));
                } else {

                    for(Object o : json.keySet()) {
                        des.append(lang.get(o.toString()) == null ? o : lang.get(o.toString())).append(": ").append(lang.get(json.get(o.toString()).toString()) == null ? json.get(o.toString()).toString() : lang.get(json.get(o.toString()).toString())).append("\n");
                    }
                }

                if(! err) {
                    downloadName.setText(json.get("id").toString());
                }
                downloadStatus.setText(des.toString());
            } catch (Exception v2) {

            }
        });

        launchPanel.add(verScrollPane);
        launchPanel.add(launch);
        launchPanel.add(deleteVersion);
        launchPanel.add(deleteWarning);
        launchPanel.add(shortInfo);
        launchPanel.add(loadingProgress);
        launchPanel.add(loadingStatus);
        launchPanel.add(stepNow);
        jFrame.add(launchPanel);

        downloadPanel.add(verInfo_download);
        downloadPanel.add(downloadScrollPane);
        downloadPanel.add(showSelect);
        downloadPanel.add(showAll);
        downloadPanel.add(showRelease);
        downloadPanel.add(showSnapshot);
        downloadPanel.add(showAlpha);
        downloadPanel.add(freshList);
        downloadPanel.add(downloadProgress);
        downloadPanel.add(downloadStatus);
        downloadPanel.add(startDownload);
        downloadPanel.add(downloadAndLaunch);
        downloadPanel.add(downloadAndFastLaunch);
        //        downloadPanel.add(downloadAll);
        downloadPanel.add(downloadName);
        jFrame.add(downloadPanel);

        menuPanel.add(switchLauncherPanel);
        menuPanel.add(switchDownloadPanel);
        menuPanel.add(switchSettingsPanel);
        menuPanel.add(switchJavaPanel);
        menuPanel.add(switchRunningPanel);
        menuPanel.add(switchMinecraftAreaPanel);
        jFrame.add(menuPanel);

        settingsPanel.add(checkResOption);
        settingsPanel.add(checkRes);
        settingsPanel.add(noCheckRes);
        jFrame.add(settingsPanel);

        javaPanel.add(javaScrollPane);
        javaPanel.add(usingJava);
        javaPanel.add(javaStatus);
        javaPanel.add(setUsingJava);
        javaPanel.add(importJava);
        jFrame.add(javaPanel);

        runningPanel.add(runningScrollPane);
        runningPanel.add(runningMinecraftStatus);
        jFrame.add(runningPanel);

        minecraftAreaPanel.add(minecraftAreaList);
        minecraftAreaPanel.add(minecraftAreaScrollPane);
        minecraftAreaPanel.add(minecraftAreaDescription);
        minecraftAreaPanel.add(minecraftAreaStatus);
        jFrame.add(minecraftAreaPanel);

        launchPanel.setVisible(true);
        downloadPanel.setVisible(false);
        settingsPanel.setVisible(false);
        javaPanel.setVisible(false);
        runningPanel.setVisible(false);
        minecraftAreaPanel.setVisible(false);

        launch.setVisible(false);

        menuPanel.setLayout(layoutManager);
        downloadPanel.setLayout(layoutManager);
        launchPanel.setLayout(layoutManager);
        runningPanel.setLayout(layoutManager);
        jFrame.setLayout(layoutManager);
        settingsPanel.setLayout(layoutManager);
        javaPanel.setLayout(layoutManager);
        minecraftAreaPanel.setLayout(layoutManager);

        setUsingJava.addActionListener(e -> {
            try {
                if(javaList.getSelectedIndex() != - 1)
                    java = javaList.getSelectedValue().toString();
            } catch (Exception v1) {

            }
        });

        switchDownloadPanel.addActionListener(e -> {
            visible("download");
        });

        switchLauncherPanel.addActionListener(e -> {
            visible("launcher");
        });

        switchSettingsPanel.addActionListener(e -> {
            visible("settings");
        });

        switchJavaPanel.addActionListener(e -> {
            visible("java");
            if(JavasList.size() == 0) {
                selectJava(lang.get("select_java"));
            }
        });

        switchRunningPanel.addActionListener(e -> {
            visible("running");
        });

        switchMinecraftAreaPanel.addActionListener(e -> {
            visible("areas");
        });

        showAll.addActionListener(e -> {
            downloadListDisplay = - 1;
            freshDownloadList();
        });

        showRelease.addActionListener(e -> {
            downloadListDisplay = 0;
            freshDownloadList();
        });

        showSnapshot.addActionListener(e -> {
            downloadListDisplay = 1;
            freshDownloadList();
        });

        showAlpha.addActionListener(e -> {
            downloadListDisplay = 2;
            freshDownloadList();
        });

        freshList.addActionListener(e -> {
            freshDownloadList();
        });

        startDownload.addActionListener(e -> {
            new Thread(() -> {
                new downloadMinecraft().startOneMinecraftDownload();
            }).start();
        });

        downloadAndLaunch.addActionListener(e -> {
            new Thread(() -> {
                String gameVersion = verList_download.getModel().getElementAt(verList_download.getSelectedIndex()).toString();
                String gameName = downloadName.getText();
                String gamePath = Config.path + "minecraft/versions/" + gameName;
                new downloadMinecraft().startOneMinecraftDownload(gameName, gameVersion, false, true);
                if(! (downloadingMinecraft.get(gameName) == null))
                    runMinecraft(gamePath, gameVersion, false);
            }).start();
        });

        downloadAndFastLaunch.addActionListener(e -> {
            new Thread(() -> {
                String gameVersion = verList_download.getModel().getElementAt(verList_download.getSelectedIndex()).toString();
                String gameName = downloadName.getText();
                String gamePath = Config.path + "minecraft/versions/" + gameName;
                new downloadMinecraft().startOneMinecraftDownload(gameName, gameVersion, false, true);
                if(! (downloadingMinecraft.get(gameName) == null))
                    runMinecraft(gamePath, gameVersion, true);
            }).start();
        });

        deleteVersion.addActionListener(e -> {
            new Thread(() -> {
                String deleteVersion = verList.getSelectedValue().toString();

                try {
                    new File(new JSONObject(minecraftVersions.get(deleteVersion).toString()).get("path").toString() + "/DELETING").createNewFile();
                } catch (Exception v2) {

                }

                try {
                    String status = new JSONObject(downloadingMinecraft.get(deleteVersion).toString()).get("status").toString();
                    boolean needStop = false;
                    if(status.equals("checking")) {
                        needStop = true;
                        breakLaunch.add(deleteVersion);
                    }
                    if(status.equals("downloading")) {
                        needStop = true;
                    }

                    if(needStop) {
                        deletingMinecraft.add(deleteVersion);

                        downloadingMinecraft.remove(deleteVersion);
                        downloads.remove(deleteVersion);

                        Thread.sleep(2500);
                    }
                } catch (Exception v1) {

                }

                try {
                    while(true) {
                        deleteFile(new JSONObject(minecraftVersions.get(deleteVersion).toString()).get("path").toString());
                    }
                } catch (Exception v3) {

                }

                try {
                    minecraftVersions.remove(deleteVersion);
                } catch (Exception v4) {

                }

                try {
                    deletingMinecraft.remove(deleteVersion);
                } catch (Exception v6) {

                }
            }).start();
        });

        importJava.addActionListener(e -> {
            selectJava(lang.get("select_java"));
        });

        checkRes.addActionListener(e -> Events.switchCheckResource(true));
        noCheckRes.addActionListener(e -> Events.switchCheckResource(false));

        //        downloadAll.addActionListener(e -> {
        //            new Thread(() -> {
        //                ListModel<Object> listModel = verList_download.getModel();
        //                for(int i = 0;i < listModel.getSize();i++) {
        //                    int finalI = i;
        //                    new Thread(() -> {
        //                        new downloadMinecraft().startOneMinecraftDownload(listModel.getElementAt(finalI).toString(),listModel.getElementAt(finalI).toString(),false);
        //                    }).start();
        //
        //                    try {
        //                        Thread.sleep(10000);
        //                    } catch (InterruptedException ex) {
        //                        ex.printStackTrace();
        //                    }
        //                }
        //            }).start();
        //        });
    }

    public static void setLoadingStatus(boolean launch) {
        try {
            ListModel<?> listModel = verList.getModel();

            StringBuilder des;

            String source;
            source = minecraftVersions.get(listModel.getElementAt(verList.getSelectedIndex()).toString()).toString();

            if(launch) {
                for(Object o : downloadingMinecraft.keySet()) {
                    if(new JSONObject(downloadingMinecraft.get(o.toString()).toString()).get("status").toString().equals("checking"))
                        source = downloadingMinecraft.get(o.toString()).toString();
                }
            }

            JSONObject json = new JSONObject();
            try {
                json = new JSONObject(source);
            } catch (Exception v1) {

            }

            if(! launch) {
                Document doc = loadingStatus.getDocument();
                AttributeSet red = null;
                StyleContext sc = StyleContext.getDefaultStyleContext();

                if(Community.ColorID == 0) {
                    red = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 60, 80));
                } else if(Community.ColorID == 1) {
                    red = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 70, 49));
                }

                AttributeSet aset_normal = null;
                StyleContext sc_normal = StyleContext.getDefaultStyleContext();

                if(Community.ColorID == 0) {
                    aset_normal = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.gray);
                } else if(Community.ColorID == 1) {
                    aset_normal = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.gray);
                }

                doc.remove(0, doc.getLength());

                if(source.startsWith("error-game-not-found")) {
                    des = new StringBuilder(source.substring(20));

                    doc.insertString(doc.getLength(), des.toString(), aset_normal);
                } else {
                    for(Object o : json.keySet()) {
                        //                        des.append(lang.get(o.toString()) == null ? o : lang.get(o.toString())).append(": ").append(lang.get(json.get(o.toString()).toString()) == null ? json.get(o.toString()).toString() : lang.get(json.get(o.toString()).toString())).append("\n");

                        try {
                            doc.insertString(doc.getLength(), (lang.get(o.toString()) == null ? o.toString() : lang.get(o.toString())) + ":", red);
                            doc.insertString(doc.getLength(), lang.get(json.get(o.toString()).toString()) == null ? json.get(o.toString()).toString() : lang.get(json.get(o.toString()).toString()), aset_normal);
                            doc.insertString(doc.getLength(), "\n", aset_normal);
                        } catch (Exception ignored) {

                        }
                    }
                }
            }

            try {
                String pro = json.get("progress").toString();

                if(! pro.equals("1/1")) {
                    loadingProgress.setMaximum(Integer.parseInt(pro.substring(pro.indexOf("/") + 1)));
                    loadingProgress.setValue(Integer.parseInt(pro.substring(0, pro.indexOf("/"))));
                } else {
                    loadingProgress.setMaximum(0);
                }
            } catch (Exception e) {
                loadingProgress.setMaximum(0);
            }
        } catch (Exception e) {

        }
    }

    public static void setJavaStatus() {
        try {
            for(Object o : javaPaths.keySet()) {
                if(new File(o.toString()).isFile()) {
                    JavasList.add(o.toString());
                }
            }

            uploadConfig();

            try {
                Config.WriteIni();
            } catch (Exception e) {

            }

            ListModel<?> listModel = javaList.getModel();

            StringBuilder des;

            String source;
            source = javaPaths.get(listModel.getElementAt(javaList.getSelectedIndex()).toString()).toString();

            JSONObject json = new JSONObject();
            try {
                json = new JSONObject(source);
            } catch (Exception v1) {

            }

            Document doc = javaStatus.getDocument();
            AttributeSet red = null;
            StyleContext sc = StyleContext.getDefaultStyleContext();

            if(Community.ColorID == 0) {
                red = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 60, 80));
            } else if(Community.ColorID == 1) {
                red = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 70, 49));
            }

            AttributeSet aset_normal = null;
            StyleContext sc_normal = StyleContext.getDefaultStyleContext();

            if(Community.ColorID == 0) {
                aset_normal = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.gray);
            } else if(Community.ColorID == 1) {
                aset_normal = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.gray);
            }

            doc.remove(0, doc.getLength());

            if(source.startsWith("error-java-not-found")) {
                des = new StringBuilder(source.substring(20));

                doc.insertString(doc.getLength(), des.toString(), aset_normal);
            } else {
                for(Object o : json.keySet()) {
                    //                        des.append(lang.get(o.toString()) == null ? o : lang.get(o.toString())).append(": ").append(lang.get(json.get(o.toString()).toString()) == null ? json.get(o.toString()).toString() : lang.get(json.get(o.toString()).toString())).append("\n");

                    try {
                        doc.insertString(doc.getLength(), (lang.get(o.toString()) == null ? o.toString() : lang.get(o.toString())) + ":", red);
                        doc.insertString(doc.getLength(), lang.get(json.get(o.toString()).toString()) == null ? json.get(o.toString()).toString() : lang.get(json.get(o.toString()).toString()), aset_normal);
                        doc.insertString(doc.getLength(), "\n", aset_normal);
                    } catch (Exception ignored) {

                    }
                }
            }
        } catch (Exception e) {

        }
    }

    public static boolean exec(String command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static void visible(String panelName) {
        downloadPanel.setVisible(false);
        launchPanel.setVisible(false);
        settingsPanel.setVisible(false);
        javaPanel.setVisible(false);
        runningPanel.setVisible(false);
        minecraftAreaPanel.setVisible(false);
        switch(panelName) {
            case "download" -> downloadPanel.setVisible(true);
            case "launcher" -> launchPanel.setVisible(true);
            case "settings" -> settingsPanel.setVisible(true);
            case "java" -> javaPanel.setVisible(true);
            case "running" -> runningPanel.setVisible(true);
            case "areas" -> minecraftAreaPanel.setVisible(true);
        }
    }

    public static void launchMinecraft(String ver) {
        if(! launching) {
            launching = true;
            launch.setText(lang.get("waiting"));
            new Thread(() -> {
                try {
                    runMinecraft(new JSONObject(minecraftVersions.get(ver).toString()).get("path").toString(), ver, ! checkResource);
                } catch (Exception e) {
                    launching = false;
                }
            }).start();
        }
    }

    public static void manager() {
        //if (Community.os.equals("windows")) {
        //        Runtime r = Runtime.getRuntime();
        //        while(true) {
        //            try {
        //                try {
        //                    Process p = r.exec("cmd.exe /c tasklist /FI \"PID eq 7740\"");
        //                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        //
        //                    String s;
        //                    StringBuilder all = new StringBuilder();
        //
        //                    br.readLine();
        //                    br.readLine();
        //
        //                    while((s = br.readLine()) != null) {
        //                        s = s.toLowerCase();
        //
        //                        all.append(s).append("\n");
        //
        //                        StringBuilder displayString = new StringBuilder();
        //
        //                        String s1;
        //                        int c = 0;
        //
        //                        while(s.contains(" ")) {
        //                            c++;
        //
        //                            s1 = s.substring(0, s.indexOf(" "));
        //
        //                            s = s.substring(s.indexOf(" "));
        //                            try {
        //                                while(s.substring(0, 1).contains(" ")) {
        //                                    s = s.replaceFirst(" ", "");
        //                                }
        //                            } catch (Exception v1) {
        //
        //                            }
        //
        //                            switch(c) {
        //                                case 1 -> displayString.append("Package name: ").append(s1).append("\n");
        //                                case 2 -> displayString.append("PID: ").append(s1).append("\n");
        //                                case 5 -> displayString.append("Memory used: ").append(s1).append(" KB\n");
        //                            }
        //
        //                            if(c >= 5) {
        //                                break;
        //                            }
        //                        }
        //
        //                        Thread.sleep(50);
        //                    }
        //
        //                    if(Errors.CannotHandle) {
        //                        break;
        //                    }
        //                } catch (Exception e) {
        //
        //                }
        //            } catch (Exception e) {
        //                e.printStackTrace();
        //            }
        //        }
        //} else {
        //ExtraUI.McBeStatus.setText("Launcher cannot use");
        //    System.out.println("Launcher cannot use");
        //}
    }

    public static void unZipFiles(String zipPath, String descDir) throws IOException {
        unZipFiles(new File(zipPath), descDir);
    }

    public static void unZipFiles(File zipFile, String descDir) throws IOException {
        File pathFile = new File(descDir);
        if(! pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
        for(Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = entries.nextElement();
            String zipEntryName = entry.getName();
            BufferedInputStream in = new BufferedInputStream(zip.getInputStream(entry));
            String outPath = (descDir + zipEntryName).replace("\\", "/");
            File file = new File(outPath.substring(0, outPath.lastIndexOf("/")));
            if(! file.exists()) {
                file.mkdirs();
            }
            if(new File(outPath).isDirectory()) {
                continue;
            }
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outPath));
            byte[] buf1 = new byte[8192];
            int len;
            while((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
    }

    public static void configReads() {
        try {
            for(Object s : Community.launcherConf.keySet()) {
                configReads(s + "@" + Community.launcherConf.get(s.toString()));
            }
        } catch (Exception e) {
            configReads();
        }
    }

    public static void configReads(String s) {
        //            将大写字母全替换为小写
        s = s.toLowerCase();

        {
            int check = s.indexOf("checkresource@check");
            int ignored = s.indexOf("checkresource@ignored");

            if(! (check != - 1 & ignored != - 1)) {
                if(check != - 1) {
                    checkResource = true;
                    checkResource_config = "checkResource@check";
                    s = "";
                }

                if(ignored != - 1) {
                    checkResource = false;
                    checkResource_config = "checkResource@ignored";
                    s = "";
                }
            }
        }

        {
            boolean javas = s.startsWith("javapaths@");

            if(javas) {
                Community.launcherConf.put("javaPaths", s.substring(s.indexOf("@") + 1));
                s = "";
            }
        }

        {
            boolean javas = s.startsWith("java@");

            if(javas) {
                Community.launcherConf.put("java", s.substring(s.indexOf("@") + 1));
                s = "";
            }
        }

        try {
            if(Community.extraConf.get(s.substring(0, s.indexOf("@"))) != null)
                s = "";
        } catch (Exception e) {

        }

        if(! s.equals("")) {
            LoadAssembly.badLoadAssembly("[Main Thread/WARN] a config are did not supported in this MCH: " + s, "fail loading config");
            unSupportConfig += s + "\n";
        }
    }

    public static void main(String[] args) {
        try {
            File f = new File("C:\\Users\\lx\\MinecraftCommandHelper\\minecraft\\versions\\21w38a\\saves\\New World\\session.lock");
            //            File f = new File("C:\\Users\\lx\\MinecraftCommandHelper\\history.txt");
            //            RandomAccessFile raf = new RandomAccessFile(new File("C:\\Users\\lx\\MinecraftCommandHelper\\history.txt"), "r");
            FileOutputStream fis = new FileOutputStream(f);
            FileChannel fc = fis.getChannel();
            FileLock lock = fc.tryLock();
            if(lock == null) {
                System.out.println("已被打开");
            } else {
                lock.release();
            }
        } catch (Exception e) {

        }
    }

    public static class downloadMinecraft {
        public boolean downloadFileByMThread(String res, String toFile, boolean showProgress, long length, boolean launch, String download_Name) {
            if(downloadingMinecraft.get(download_Name) == null)
                return false;
            boolean r = customDownLoad(res, toFile, showProgress, length, launch);
            try {
                LinkedHashMap<Object, Integer> v = ((LinkedHashMap<Object, Integer>) downloads.get(download_Name));
                v.put("value", Integer.parseInt(String.valueOf(((LinkedHashMap) downloads.get(download_Name)).get("value"))) + 1);
                downloads.put(download_Name, v);
            } catch (Exception e) {

            }
            return r;
        }

        public void startOneMinecraftDownload() {
            startOneMinecraftDownload(downloadName.getText(), verList_download.getModel().getElementAt(verList_download.getSelectedIndex()).toString(), false);
        }

        public void startOneMinecraftDownload(String download_Name, String downloadTarget, boolean launchPro) {
            startOneMinecraftDownload(download_Name, downloadTarget, launchPro, false);
        }

        public void startOneMinecraftDownload(String download_Name, String downloadTarget, boolean launchPro, boolean downloadAndLaunch) {
            JSONObject status = new JSONObject();
            if(! launchPro) {
                status.put("status", "downloading");
                status.put("name", download_Name);
                status.put("target", downloadTarget);
            } else {
                status.put("status", "checking");
                status.put("name", download_Name);
                status.put("target", downloadTarget);
            }
            LinkedHashMap<Object, Integer> downloadingMap = new LinkedHashMap<>();
            downloadingMap.put("max", 0);
            downloadingMap.put("value", 0);
            downloads.put(download_Name, downloadingMap);

            String libsPath = Config.path + "minecraft/libs/";
            String indexesPath = Config.path + "minecraft/assets/indexes/";
            String objectsPath = Config.path + "minecraft/assets/objects/";

            downloadingMinecraft.put(download_Name, status);

            if(customDownLoad(new JSONObject(minecraftDownloadVersions.get(downloadTarget).toString()).get("url").toString(), versionPath + download_Name + "/" + download_Name + ".json", false, - 1)) {

                try {
                    FileOutputStream statusWriter = null;
                    if(! launchPro) {
                        statusWriter = new FileOutputStream(versionPath + download_Name + "/" + download_Name + "_status.json", false);

                        statusWriter.write(status.toString().getBytes());

                        statusWriter.close();
                    }

                    BufferedReader br = new BufferedReader(new FileReader(versionPath + download_Name + "/" + download_Name + ".json"));

                    StringBuilder json = new StringBuilder();
                    String s;
                    while((s = br.readLine()) != null)
                        json.append(s);

                    JSONObject source = new JSONObject(json.toString());
                    JSONArray lib = new JSONArray(source.get("libraries").toString());
                    JSONObject clientAndServer = new JSONObject(source.get("downloads").toString());

                    downloadingMap.put("max", lib.length());
                    downloads.put(download_Name, downloadingMap);

                    new Thread(() -> {
                        for(Object o : lib) {
                            if(! checkResource & launchPro)
                                break;

                            if(downloadingMinecraft.get(download_Name) == null)
                                break;

                            if(! launchPro)
                                downloadProgress.setValue(downloadProgress.getValue() + 1);
                            else
                                loadingProgress.setValue(loadingProgress.getValue() + 1);

                            JSONObject downloads = new JSONObject(new JSONObject(o.toString()).get("downloads").toString());

                            JSONObject artifact = new JSONObject();
                            try {
                                artifact = new JSONObject(downloads.get("artifact").toString());
                            } catch (Exception ex) {

                            }

                            try {
                                JSONObject nativeLib = new JSONObject(downloads.get("classifiers").toString());

                                for(Object nativeObj : nativeLib.keySet()) {
                                    JSONObject download = new JSONObject(nativeLib.get(nativeObj.toString()).toString());

                                    if(! (new File(libsPath + download.get("path")).length() == download.getLong("size"))) {
                                        new Thread(() -> {
                                            downloadFileByMThread(download.get("url").toString(), libsPath + download.get("path"), false, download.getLong("size"), launchPro, download_Name);
                                        }).start();
                                    } else {
                                        try {
                                            setTextByCustomDownload(new File(libsPath + artifact.get("path")), artifact.getLong("size"), launchPro, artifact.get("url").toString());
                                            LinkedHashMap<Object, Integer> v = ((LinkedHashMap<Object, Integer>) downloads.get(download_Name));
                                            v.put("value", Integer.parseInt(String.valueOf(((LinkedHashMap) downloads.get(download_Name)).get("value"))) + 1);
                                            downloads.put(download_Name, v);
                                        } catch (Exception e) {

                                        }
                                    }
                                }
                            } catch (Exception ex) {
                                if(! (new File(libsPath + artifact.get("path")).length() == artifact.getLong("size"))) {
                                    JSONObject finalArtifact = artifact;
                                    new Thread(() -> {
                                        downloadFileByMThread(finalArtifact.get("url").toString(), libsPath + finalArtifact.get("path"), false, finalArtifact.getLong("size"), launchPro, download_Name);
                                    }).start();
                                } else {
                                    try {
                                        setTextByCustomDownload(new File(libsPath + artifact.get("path")), artifact.getLong("size"), launchPro, artifact.get("url").toString());
                                        LinkedHashMap<Object, Integer> v = ((LinkedHashMap<Object, Integer>) downloads.get(download_Name));
                                        v.put("value", Integer.parseInt(String.valueOf(((LinkedHashMap) downloads.get(download_Name)).get("value"))) + 1);
                                        downloads.put(download_Name, v);
                                    } catch (Exception e) {

                                    }
                                }
                            }
                        }
                    }).start();

                    downloadingMap.put("max", downloadingMap.get("max") + clientAndServer.length());
                    downloads.put(download_Name, downloadingMap);

                    new Thread(() -> {
                        for(Object o : clientAndServer.keySet()) {
                            if(! checkResource & launchPro)
                                break;

                            if(downloadingMinecraft.get(download_Name) == null)
                                break;

                            JSONObject download = new JSONObject(clientAndServer.get(o.toString()).toString());

                            if(! (new File(versionPath + download_Name + "/" + o + ".jar").length() == download.getLong("size"))) {
                                new Thread(() -> {
                                    downloadFileByMThread(download.get("url").toString(), versionPath + download_Name + "/" + o + ".jar", false, download.getLong("size"), launchPro, download_Name);
                                }).start();
                            } else {
                                try {
                                    setTextByCustomDownload(new File(versionPath + download_Name + "/" + o + ".jar"), download.getLong("size"), launchPro, download.get("url").toString());
                                    LinkedHashMap<Object, Integer> v = ((LinkedHashMap<Object, Integer>) downloads.get(download_Name));
                                    v.put("value", Integer.parseInt(String.valueOf(((LinkedHashMap) downloads.get(download_Name)).get("value"))) + 1);
                                    downloads.put(download_Name, v);
                                } catch (Exception e) {

                                }
                            }
                        }
                    }).start();

                    JSONObject assetIndex = new JSONObject(source.get("assetIndex").toString());
                    customDownLoad(assetIndex.get("url").toString(), indexesPath + assetIndex.get("id") + ".json", false, assetIndex.getInt("size"));

                    json = new StringBuilder();
                    br = new BufferedReader(new FileReader(indexesPath + assetIndex.get("id") + ".json"));
                    while((s = br.readLine()) != null)
                        json.append(s);

                    JSONObject assets = new JSONObject(json.toString());
                    assets = new JSONObject(assets.get("objects").toString());

                    downloadingMap.put("max", downloadingMap.get("max") + assets.length());
                    downloads.put(download_Name, downloadingMap);

                    JSONObject finalAssets = assets;
                    new Thread(() -> {
                        for(Object o : finalAssets.keySet()) {
                            if(! checkResource & launchPro)
                                break;

                            if(downloadingMinecraft.get(download_Name) == null)
                                break;

                            JSONObject asset = new JSONObject(finalAssets.get(o.toString()).toString());

                            String hash = asset.get("hash").toString();
                            File file = new File(objectsPath + hash.substring(0, 2) + "/" + hash);
                            String res = "http://resources.download.minecraft.net/" + hash.substring(0, 2) + "/" + hash;
                            if(! (file.length() == asset.getLong("size"))) {
                                new Thread(() -> {
                                    //                                if(!
                                    downloadFileByMThread(res, objectsPath + hash.substring(0, 2) + "/" + hash, false, asset.getLong("size"), launchPro, download_Name);
                                    //                                ) {
                                    //                                    System.out.println("fail download: " + "http://resources.download.minecraft.net/" + hash.substring(0, 2) + "/" + hash);
                                    //                                }
                                }).start();

                                try {
                                    Thread.sleep(4);
                                } catch (InterruptedException e) {

                                }
                            } else {
                                try {
                                    setTextByCustomDownload(file, asset.getLong("size"), launchPro, res);
                                    LinkedHashMap<Object, Integer> v = ((LinkedHashMap<Object, Integer>) downloads.get(download_Name));
                                    v.put("value", Integer.parseInt(String.valueOf(((LinkedHashMap) downloads.get(download_Name)).get("value"))) + 1);
                                    downloads.put(download_Name, v);
                                } catch (Exception e) {

                                }
                            }
                        }
                    }).start();

                    Thread.sleep(200);

                    while(downloadingMinecraft.get(download_Name) != null) {
                        try {
                            LinkedHashMap<Object, Object> hashMap = ((LinkedHashMap<Object, Object>) downloads.get(download_Name));

                            status.put("progress", hashMap.get("value") + "/" + hashMap.get("max"));

                            downloadingMinecraft.put(download_Name, status);

                            statusWriter = new FileOutputStream(versionPath + download_Name + "/" + download_Name + "_status.json", false);

                            statusWriter.write(status.toString().getBytes());

                            statusWriter.close();
                        } catch (Exception ex) {

                        }

                        try {
                            String progress = new JSONObject(downloadingMinecraft.get(download_Name).toString()).get("progress").toString();
                            if(Integer.parseInt(progress.substring(progress.indexOf("/") + 1)) - Integer.parseInt(progress.substring(0, progress.indexOf("/"))) < 80)
                                break;
                        } catch (Exception e) {

                        }

                        try {
                            Thread.sleep(200);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if(! (downloadingMinecraft.get(download_Name) == null)) {
                        try {
                            LinkedHashMap<Object, Object> hashMap2 = ((LinkedHashMap<Object, Object>) downloads.get(download_Name));

                            status.put("progress", "1/1");
                            status.put("status", "ready");

                            downloadingMinecraft.put(download_Name, status);

                            statusWriter = new FileOutputStream(versionPath + download_Name + "/" + download_Name + "_status.json", false);

                            statusWriter.write(status.toString().getBytes());

                            statusWriter.flush();
                        } catch (Exception ex) {

                        }
                    }

                    statusWriter.close();

                    if(! launchPro)
                        downloadProgress.setMaximum(0);
                    else
                        loadingProgress.setMaximum(0);

                    downloads.remove(download_Name);

                    br.close();

                    downloadStatus.setText("Download Finished!");

                    status.put("status", "ready");

                    JSONObject mcVer = new JSONObject(minecraftVersions.get(download_Name).toString());
                    mcVer.put("progress", "1/1");
                    mcVer.put("status", "ready");

                    minecraftVersions.put(download_Name, mcVer);

                    if(! (downloadingMinecraft.get(download_Name) == null)) {
                        downloadingMinecraft.put(download_Name, status);
                    }

                    if(! launchPro) {
                        statusWriter = new FileOutputStream(versionPath + download_Name + "/" + download_Name + "_status.json", false);

                        statusWriter.write(status.toString().getBytes());
                    }

                    statusWriter.close();

                    if(downloadAndLaunch) {
                        launchPanel.setVisible(true);
                        downloadPanel.setVisible(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                downloadStatus.setText("may internet error");
            }
        }
    }

    public static class javaFileFilter extends FileFilter {
        String ext;

        public javaFileFilter(String ext) {
            this.ext = ext;
        }

        public boolean accept(File file) {
            if(file.isDirectory())
                return true;

            String fileName = file.getName();
            return fileName.equals(ext);
        }

        public String getDescription() {
            if(ext.equals("java.exe"))
                return "Java Runtime (Windows)";
            else if(ext.equals("java"))
                return "Java Runtime (Unix)";
            if(ext.equals("javaw.exe"))
                return "Javaw Runtime (Windows)";
            else if(ext.equals("javaw"))
                return "Javaw Runtime (Unix)";

            return ext + "(Other)";
        }
    }
}
