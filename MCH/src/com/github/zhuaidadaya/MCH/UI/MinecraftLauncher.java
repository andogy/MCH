package com.github.zhuaidadaya.MCH.UI;

import com.github.zhuaidadaya.MCH.Config.ConfigMain;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Events.Errors;
import com.github.zhuaidadaya.MCH.Events.Events;
import com.github.zhuaidadaya.MCH.Events.LoadAssembly;
import com.github.zhuaidadaya.MCH.Times.timeType;
import com.github.zhuaidadaya.MCH.Times.times;
import com.github.zhuaidadaya.MCH.UI.Color.displaySets;
import com.github.zhuaidadaya.MCH.UI.Lang.languageSet;
import com.github.zhuaidadaya.MCH.lib.Log;
import com.github.zhuaidadaya.MCH.lib.json.JSONArray;
import com.github.zhuaidadaya.MCH.lib.json.JSONObject;

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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.github.zhuaidadaya.MCH.lib.Resources.initLanguage.lang;

public class MinecraftLauncher {
    public static JFrame mainFrame = new JFrame();
    public static JButton launch = new JButton();
    public static JButton readyTag = new JButton();
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
    public static JLabel runMinecraftType = new JLabel();
    public static JButton runMinecraftClient = new JButton();
    public static JButton runMinecraftServer = new JButton();

    public static JPanel javaPanel = new JPanel();
    public static JList<Object> javaList = new JList<>();
    public static JLabel usingJava = new JLabel();
    public static JScrollPane javaScrollPane = new JScrollPane();
    public static JButton setUsingJava = new JButton();
    public static JButton importJava = new JButton();
    public static JButton removeJava = new JButton();
    public static JTextPane javaStatus = new JTextPane();

    public static JPanel runningPanel = new JPanel();
    public static JList<Object> runningList = new JList<>();
    public static JScrollPane runningScrollPane = new JScrollPane();
    public static JTextPane runningMinecraftStatus = new JTextPane();
    public static JTextPane runningMinecraftLogs = new JTextPane();
    public static JScrollPane runningMinecraftLogsScrollPanel = new JScrollPane();
    public static JPanel runningMinecraftLogsPanel = new JPanel();
    public static JButton forceStopMinecraft = new JButton();
    public static JButton removeInstance = new JButton();
    public static JButton logs = new JButton();

    public static JPanel minecraftAreaPanel = new JPanel();
    public static JList<Object> minecraftAreaList = new JList<>();
    public static JScrollPane minecraftAreaScrollPane = new JScrollPane();
    public static JTextPane minecraftAreaDescription = new JTextPane();
    public static JTextPane minecraftAreaStatus = new JTextPane();
    public static JButton selectThisArea = new JButton();
    public static JButton addMinecraftArea = new JButton();
    public static JButton renameMinecraftArea = new JButton();
    public static JButton removeMinecraftArea = new JButton();

    public static JPanel accountPanel = new JPanel();
    public static JList<Object> userList = new JList<>();
    public static JScrollPane userListScrollPane = new JScrollPane();
    public static JTextPane userStatus = new JTextPane();
    public static JButton selectAccount = new JButton();
    public static JButton addAccount = new JButton();
    public static JButton removeAccount = new JButton();
    public static JTextPane accountUsing = new JTextPane();

    public static JFrame addAccountFrame = new JFrame();
    public static JPanel addAccountPanel = new JPanel();
    public static JTextPane accountName = new JTextPane();
    public static JLabel accountNameTip = new JLabel();
    public static JTextPane accountPassword = new JTextPane();
    public static JLabel accountPasswordTip = new JLabel();
    public static JButton addThisAccount = new JButton();
    public static JButton addOfflineAccount = new JButton();
    public static JButton addMojangAccount = new JButton();

    public static JButton switchDownloadPanel = new JButton();
    public static JButton switchLauncherPanel = new JButton();
    public static JButton switchSettingsPanel = new JButton();
    public static JButton switchJavaPanel = new JButton();
    public static JButton switchRunningPanel = new JButton();
    public static JButton switchMinecraftAreaPanel = new JButton();
    public static JButton switchAccountPanel = new JButton();
    public static JButton nextPage = new JButton();
    public static JButton lastPage = new JButton();
    public static JPanel menuPanel = new JPanel();

    public static JFrame logsFrame = new JFrame();
    public static JPanel logsPanel = new JPanel();
    public static JLabel selectedMinecraft = new JLabel();
    public static JButton exportLogs = new JButton();
    public static JTextArea selectedMinecraftLogs = new JTextArea();
    public static JScrollPane selectedMinecraftLogsScrollPanel = new JScrollPane();

    //-1 = all, 0 = release, 1 = snapshot, 2 = old alpha/beta
    public static int downloadListDisplay = - 1;
    public static String DEFAULT_VERSIONS_PATH = ConfigMain.path + "minecraft/versions/";
    public static String DEFAULT_GAME_PATH = ConfigMain.path + "minecraft/";
    public static String versionsPath = "";
    public static String gamePath = "";

    public static LinkedHashMap<Object, Object> runningMinecraft = new LinkedHashMap<>();
    public static LinkedHashMap<Object, Object> runningMinecraft_Display = new LinkedHashMap<>();
    public static LinkedHashMap<Object, Object> runningLogs = new LinkedHashMap<>();
    public static LinkedHashMap<Object, Object> minecraftDownloadVersions;
    public static LinkedHashMap<Object, Object> downloadingMinecraft = new LinkedHashMap<>();
    public static LinkedHashMap<Object, Object> downloads = new LinkedHashMap<>();
    public static LinkedHashMap<Object, Object> javaPaths = new LinkedHashMap<>();
    public static LinkedHashMap<Object, Object> users = new LinkedHashMap<>();
    public static LinkedHashMap<Object, Object> minecraftAreas = new LinkedHashMap<>();
    public static LinkedHashSet<Object> minecraftVersionsList_fresh = new LinkedHashSet<>();
    public static LinkedHashSet<Object> minecraftVersionsList_use = new LinkedHashSet<>();
    public static LinkedHashSet<Object> deletingMinecraft = new LinkedHashSet<>();
    public static LinkedHashSet<Object> JavasList = new LinkedHashSet<>();

    public static boolean downloading = false;
    public static boolean launching = false;

    public static LinkedHashSet<Object> breakLaunch = new LinkedHashSet<>();
    public static LinkedHashSet<Object> breakPIDs = new LinkedHashSet<>();

    public static String selectedAreaName = lang.get("default_area");
    public static boolean fastLauncher = false;
    public static boolean exportingLogs = false;
    public static String addAccountType = "offline";
    public static boolean userSelected = false;
    public static boolean areaSelected = false;
    public static int selectionIndex = - 1;
    public static String unSupportConfig = "";
    public static String checkResource_config = "checkResource@check";
    public static boolean checkResource = true;
    public static JSONObject minecraftAreasConfig = new JSONObject();
    public static boolean invalidName = false;
    public static boolean invalidUUID = false;
    public static boolean addingAccount = false;
    public static int menuPage = 1;
    public static String javaVersion = "";
    public static String java = "java";
    public static String user = "";
    public static String uuid = "";
    public static String selectedLogDisplay = "";
    public static String launchType = "client";
    public static boolean useMThread = true;
    public static boolean compatibleMode = true;
    public static LinkedHashMap<Object, Object> minecraftVersions = getVersions(DEFAULT_VERSIONS_PATH);
    public static LinkedHashSet<Object> clientVmOption = new LinkedHashSet<>();
    public static LinkedHashSet<Object> serverVmOptions = new LinkedHashSet<>();

    public static LinkedHashMap<Integer, String> downloadingThreadsMap = new LinkedHashMap();

    public MinecraftLauncher() {

    }

    public static void ForceStop(long pid) {
        try {
            if(Community.os.contains("Linux"))
                Runtime.getRuntime().exec("kill -9 " + pid);
            else
                Runtime.getRuntime().exec("taskkill /F /PID " + pid);
        } catch (Exception e) {

        }
    }

    public static void selectJava(String title) {
        try {
            int result;
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

            result = fileChooser.showOpenDialog(mainFrame);
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

                for(Object o : javaPaths.keySet()) {
                    if(new File(o.toString()).isFile()) {
                        JavasList.add(o.toString());
                    }
                }

                uploadConfig();

                ConfigMain.WriteConfig();
            }
        } catch (Exception ignored) {

        }
    }

    public static void selectMinecraftArea(String title) {
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
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());

            result = fileChooser.showOpenDialog(mainFrame);
            if(JFileChooser.APPROVE_OPTION == result) {
                path = fileChooser.getSelectedFile().getPath();
                if(new File(path).isDirectory()) {
                    JSONObject info = new JSONObject();
                    info.put("name", path);
                    info.put("path", path);
                    minecraftAreas.put(path, info);
                }

                uploadConfig();

                ConfigMain.WriteConfig();
            }
        } catch (Exception ignored) {

        }
    }

    public static void freshDownloadList() {
        new Thread(() -> {
            downloading = true;

            verList_download.clearSelection();

            if(downloadListDisplay == - 1)
                minecraftDownloadVersions = getDownloadVersions("", true);
            else if(downloadListDisplay == 0)
                minecraftDownloadVersions = getDownloadVersions("release", true);
            else if(downloadListDisplay == 1)
                minecraftDownloadVersions = getDownloadVersions("snapshot", true);
            else if(downloadListDisplay == 2)
                minecraftDownloadVersions = getDownloadVersions("old", true);

            verList_download.setListData(minecraftDownloadVersions.keySet().toArray());

            downloading = false;
        }).start();
    }

    public static boolean customDownLoad(String res, String toFile, boolean showProgress, long length, String downlaod_Name) {
        return customDownLoad(res, toFile, showProgress, length, false, downlaod_Name);
    }

    public static boolean customDownLoad(String res, String toFile, boolean showProgress, long length, boolean launchPro, String download_Name) {
        return customDownLoad(res, toFile, showProgress, length, launchPro, 0, download_Name, false);
    }

    //    public static void setTextByCustomDownload(File file, long length, boolean launchPro, String res) {
    //        if(file.isFile() & file.length() == length) {
    //            if(! launchPro)
    //                downloadStatus.setText(lang.get("skip") + ": " + res + "\n" + downloadProgress.getValue() + " / " + downloadProgress.getMaximum() + "\n");
    //            else
    //                loadingStatus.setText(lang.get("skip") + ": " + res + "\n" + loadingProgress.getValue() + " / " + loadingProgress.getMaximum() + "\n");
    //        } else {
    //            if(! launchPro)
    //                downloadStatus.setText(lang.get("downloads") + ": " + res + "\n" + downloadProgress.getValue() + " / " + downloadProgress.getMaximum() + "\n");
    //            else
    //                loadingStatus.setText(lang.get("downloads") + ": " + res + "\n" + loadingProgress.getValue() + " / " + loadingProgress.getMaximum() + "\n");
    //        }
    //    }

    public static boolean customDownLoad(String res, String toFile, boolean showProgress, long length, boolean launchPro, int reTryCount, String download_Name, boolean noGui) {

        try {
            File file = new File(toFile);

            if(! (length == 0)) {
                if(file.isFile() & file.length() == length) {
                    return true;
                }
            }

            URL url = new URL(res);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setConnectTimeout(1000);
            //            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.connect();
            int fileLength = httpURLConnection.getContentLength();

            if(showProgress)
                downloadProgress.setMaximum(fileLength);

            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            if(! file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            int size;
            int len = 0;
            byte[] buf = new byte[1024 * 32];

            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file, false));

            //            while((size = bis.read(buf,0,fileLength / 8)) != - 1) {
            while((size = bis.read(buf)) != - 1) {
                if(! noGui) {
                    if(! (size == 0)) {
                        if(downloadingMinecraft == null || (! download_Name.equals("") & downloadingMinecraft.get(download_Name) == null)) {
                            file.delete();
                            bis.close();
                            out.close();
                            httpURLConnection.disconnect();
                            return false;
                        }
                    }
                }

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
            customDownLoad(res, toFile, showProgress, length, launchPro, reTryCount + 1, download_Name, noGui);
        }

        return false;
    }

    public static void show() {
        mainFrame.setVisible(true);
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

                        File statusFile = new File(path + "/" + version + "_status.json");

                        BufferedReader ver = new BufferedReader(new FileReader(path + "/" + version + ".json"));
                        String s;

                        BufferedReader status;

                        StringBuilder statusJson = new StringBuilder();

                        try {
                            status = new BufferedReader(new FileReader(statusFile));

                            while((s = status.readLine()) != null) {
                                statusJson.append(s);
                            }

                            status.close();
                        } catch (Exception e) {

                        }

                        StringBuilder verJson = new StringBuilder();
                        while((s = ver.readLine()) != null)
                            verJson.append(s);

                        ver.close();

                        try {
                            JSONObject source_status = new JSONObject(statusJson.toString());

                            downloadingMinecraft.put(version, source_status);

                            addTo.put("minecraft_type", source_status.get("minecraft_type").toString());
                            addTo.put("status", "#" + source_status.get("status").toString());
                            addTo.put("progress", source_status.get("progress").toString());
                        } catch (Exception e) {
                            addTo.put("status", "#Unknown");
                            addTo.put("progress", "NaN");
                        }
                        JSONObject source = new JSONObject(verJson.toString());

                        try {
                            addTo.put("path", f.getAbsolutePath());
                            addTo.put("game_type", source.get("type").toString());
                            addTo.put("id", source.get("id").toString());
                            addTo.put("releaseTime", source.get("releaseTime"));
                            addTo.put("time", source.get("time"));
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
                if(new File(o.toString()).isFile()) {
                    hashMap.put(o.toString(), "{\"path\":\"" + o + "\"}");
                }
            }
        } catch (Exception e) {

        }

        if(hashMap.size() == 0) {
            hashMap.put(lang.get("java_not_found"), lang.get("java_are_not_found_here"));
        }

        return hashMap;
    }

    public static LinkedHashMap<Object, Object> getMinecraftAreas() {
        configReads();

        LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();

        try {
            for(Object o : Community.launcherConf.get("minecraftAreas").toString().split(";")) {
                JSONObject info = new JSONObject();
                for(Object area : new JSONObject(o.toString()).keySet()) {
                    info.put(area.toString(), new JSONObject(o.toString()).get(area.toString()).toString());
                }
                hashMap.put(info.get("name"), info);
            }
        } catch (Exception e) {

        }

        JSONObject info = new JSONObject();
        info.put("path", DEFAULT_GAME_PATH);
        info.put("name", lang.get("default_area"));
        hashMap.put(lang.get("default_area"), info);

        return hashMap;
    }

    public static LinkedHashMap<Object, Object> getUsers() {
        configReads();

        LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();

        try {
            for(Object o : Community.launcherConf.get("users").toString().split(";")) {
                JSONObject info = new JSONObject();
                for(Object usr : new JSONObject(o.toString()).keySet()) {
                    info.put(usr.toString(), new JSONObject(o.toString()).get(usr.toString()).toString());
                }
                hashMap.put(info.get("name"), info);
            }
        } catch (Exception e) {

        }

        if(! (hashMap.size() > 0)) {
            hashMap.put(lang.get("no_user"), lang.get("create_a_user_to_show"));
        }

        return hashMap;
    }

    public static LinkedHashMap<Object, Object> getRunningMinecraftStatus() {
        configReads();

        LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();

        try {
            for(Object o : runningMinecraft.keySet()) {
                new JSONObject(runningMinecraft.get(o.toString()).toString()).get("status");
            }

            if(runningMinecraft.size() > 0)
                return runningMinecraft;
        } catch (Exception e) {

        }

        //        JSONObject info = new JSONObject();
        hashMap.put(lang.get("no_running"), lang.get("launch_to_manager"));

        return hashMap;
    }

    public static void runMinecraft(String gamePath_instance, String gameVersionName, boolean fastLaunch, String launchType, LinkedHashSet<Object> vmOptions, String minecraftType) {
        boolean javaCheck1 = false;
        String launchJar = launchType.equals("client") ? "client.jar" : "server.jar";
        Log.writeLog("[Launcher Thread/INFO] \"" + gameVersionName + "\" now launching in " + (fastLaunch ? "fast launch mode" : "safe launch mode"));

        Log.writeLog("[Launcher Thread/INFO] set launch type to \"" + launchType + "\"");

        Log.writeLog("[Launcher Thread/INFO] set parse type to \"" + minecraftType + "\"");

        Log.writeLog("[Launcher Thread/INFO] checking java...");

        stepNow.setText(lang.get("check_java"));

        try {
            Process p = Runtime.getRuntime().exec(java);

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream(), Charset.forName("GBk")));

            while(br.readLine() != null) {
                javaCheck1 = true;
            }

            ForceStop(p.pid());
        } catch (Exception e) {

        }

        boolean javaCheck = false;
        try {
            javaCheck = new File(java).isFile();
        } catch (Exception ignored) {

        }

        if(javaCheck | javaCheck1) {
            gamePath_instance = gamePath_instance.replace("\\", "/");

            BufferedReader versionReader;
            StringBuilder json = new StringBuilder();
            try {
                versionReader = new BufferedReader(new FileReader(gamePath_instance + "/" + gamePath_instance.substring(gamePath_instance.lastIndexOf("/") + 1) + ".json"));

                json = new StringBuilder();
                String readVer;
                while((readVer = versionReader.readLine()) != null)
                    json.append(readVer);

                versionReader.close();
            } catch (Exception e) {

            }

            JSONObject source = new JSONObject(json.toString());

            boolean minecraftCanUse = true;

            boolean accountCanUse = true;

            String account = "";
            String accountUUID = "";
            Log.writeLog("[Launcher Thread/INFO] checking minecraft...");

            if(launchType.equals("client")) {
                try {
                    Process p = Runtime.getRuntime().exec("\"" + java + "\" -cp \"" + gamePath_instance + "/" + launchJar + "\" " + source.get("mainClass").toString());

                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream(), Charset.forName("GBk")));
                    String info;

                    while((info = br.readLine()) != null) {
                        if(info.replace(" ", "").replace("\t", "").startsWith("java.lang.UnsupportedClassVersionError")) {
                            minecraftCanUse = false;
                        }
                    }

                    if(Community.os.contains("Linux"))
                        Runtime.getRuntime().exec("kill -9 " + p.pid());
                    else
                        Runtime.getRuntime().exec("taskkill /F /PID " + p.pid());
                } catch (Exception e) {

                }
            } else {
                try {
                    Process p = Runtime.getRuntime().exec("\"" + java + "\" -jar \"" + gamePath_instance + "/" + launchJar + "\"");

                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream(), Charset.forName("GBk")));
                    String info;

                    while((info = br.readLine()) != null) {
                        if(info.replace(" ", "").replace("\t", "").startsWith("java.lang.UnsupportedClassVersionError")) {
                            minecraftCanUse = false;
                        }
                    }

                    if(Community.os.contains("Linux"))
                        Runtime.getRuntime().exec("kill -9 " + p.pid());
                    else
                        Runtime.getRuntime().exec("taskkill /F /PID " + p.pid());
                } catch (Exception e) {

                }
            }

            if(launchType.equals("client")) {
                Log.writeLog("[Launcher Thread/INFO] checking account...");

                try {
                    accountCanUse = ! user.equals("");
                    Log.writeLog("[Launcher Thread/INFO] checking for UUID: " + UUID.fromString(uuid));
                    account = user;
                    accountUUID = uuid;
                } catch (Exception e) {
                    accountCanUse = false;
                }
            }

            if(minecraftCanUse & accountCanUse) {
                if(launchType.equals("client")) {
                    Log.writeLog("[Launcher Thread/INFO] launch account: " + account);
                    Log.writeLog("[Launcher Thread/INFO] launch account UUID: " + accountUUID);
                    Log.writeLog("[Launcher Thread/INFO] launch account Token: " + accountUUID);
                }

                String nativePath = gamePath_instance + "/natives";
                String nativeArg = "-Djava.library.path=\"" + nativePath + "\"";

                StringBuilder cpPath = new StringBuilder();

                try {
                    JSONArray libs = new JSONArray(source.getJSONArray("libraries").toString());
                    JSONObject assetIndex = new JSONObject(source.get("assetIndex").toString());

                    stepNow.setText(lang.get("check_res"));

                    Log.writeLog("[Launcher Thread/INFO] checking resources...");

                    if(! fastLaunch & launchType.equals("client"))
                        new downloadMinecraft().startOneMinecraftDownload(gamePath_instance.substring(gamePath_instance.lastIndexOf("/") + 1), source.get("id").toString(), true, minecraftType);

                    stepNow.setText(lang.get("format_files"));

                    try {
                        deleteFiles(nativePath + "/");
                    } catch (Exception e) {

                    }

                    if(launchType.equals("client")) {
                        for(Object o : libs) {
                            if(breakLaunch.contains(gameVersionName)) {
                                launching = false;
                                Log.writeLog("[Launcher Thread/INFO] launch break");
                                return;
                            }

                            loadingStatus.setText(o.toString());

                            JSONObject each = new JSONObject(o.toString());

                            JSONObject lib = new JSONObject(each.get("downloads").toString());

                            boolean breakOnce = false;
                            try {
                                for(Object o1 : each.getJSONArray("rules")) {
                                    boolean osxAllow = false;
                                    try {
                                        osxAllow = new JSONObject(new JSONObject(o1.toString()).get("os").toString()).get("name").toString().equals("osx") & new JSONObject(o1.toString()).get("action").toString().equals("allow");
                                    } catch (Exception e) {

                                    }

                                    boolean osxDisallow = false;
                                    try {
                                        osxDisallow = new JSONObject(new JSONObject(o1.toString()).get("os").toString()).get("name").toString().equals("osx") & new JSONObject(o1.toString()).get("action").toString().equals("disallow");
                                    } catch (Exception e) {

                                    }

                                    if(Community.os.contains("Windows")) {
                                        if(osxAllow)
                                            breakOnce = true;
                                    }
                                    if(Community.os.contains("Linux")) {
                                        if(osxDisallow)
                                            breakOnce = true;
                                    }
                                }
                            } catch (Exception e) {

                            }

                            if(! breakOnce) {
                                try {
                                    JSONObject natives = new JSONObject(lib.get("classifiers").toString());
                                    try {
                                        JSONObject nativeJson = new JSONObject();
                                        if(Community.os.contains("Windows"))
                                            nativeJson = new JSONObject(natives.get("natives-windows").toString());
                                        else if(Community.os.contains("Linux"))
                                            nativeJson = new JSONObject(natives.get("natives-linux").toString());
                                        else if(Community.os.contains("Macos"))
                                            nativeJson = new JSONObject(natives.get("natives-macos").toString());

                                        Log.writeLog("[Launcher Thread/INFO] replace native libs \"" + "minecraft/libraries/" + nativeJson.get("path").toString() + "\"");
                                        unZip(gamePath + "/libraries/" + nativeJson.get("path").toString(), nativePath + "/");
                                    } catch (Exception e) {

                                    }
                                } catch (Exception e) {
                                    try {
                                        JSONObject artifact = new JSONObject(lib.get("artifact").toString());

                                        Log.writeLog("[Launcher Thread/INFO] add classpath for \"" + "minecraft/libraries/" + artifact.get("path").toString() + "\"");
                                        cpPath.append(gamePath).append("/libraries/").append(artifact.get("path").toString()).append(Community.os.contains("Windows") ? ";" : ":");
                                    } catch (Exception ex) {

                                    }
                                }
                            } else {

                            }
                        }
                    }

                    stepNow.setText(lang.get("generate_starter"));

                    cpPath.append(gamePath_instance).append("/").append(launchJar);

                    StringBuilder vmOption = new StringBuilder(" ");
                    String mainClass = source.get("mainClass").toString();

                    for(Object o : vmOptions) {
                        vmOption.append(o.toString()).append(" ");
                        Log.writeLog("[Launcher Thread/INFO] add vm option: " + o);
                    }

                    if(launchType.equals("client"))
                        Log.writeLog("[Launcher Thread/INFO] set main class to: " + mainClass);

                    String javaUsed = java.equals("java") ? "java" : "\"" + java + "\"";

                    String CompleteLauncher;
                    if(launchType.equals("client")) {
                        CompleteLauncher = javaUsed + vmOption + nativeArg + " -cp \"" + cpPath + "\" " + mainClass + " --username " + account + " --version \"" + gameVersionName + "\" --gameDir \"" + gamePath_instance + "\" --assetsDir \"" + ConfigMain.path + "minecraft/assets\" --assetIndex " + assetIndex.get("id").toString() + " --uuid " + accountUUID + " --accessToken " + accountUUID + " --userProperties {} --userType Legacy --width 854 --height 480";
                    } else {
                        if(new File(gamePath_instance + "/eula.txt").createNewFile()) {
                            FileWriter fw = new FileWriter(gamePath_instance + "/eula.txt");
                            fw.write("""
                                    eula=true""");
                            fw.close();
                        }
                        CompleteLauncher = javaUsed + vmOption + " -Duser.dir=\"" + gamePath_instance + "\" \"" + gamePath_instance + "/server.jar\" nogui";
                    }

                    Log.writeLog("[Launcher Thread/INFO] launch parameter: " + CompleteLauncher);

                    //                    String starterType = "bat";
                    //                    if (Community.os.contains("Linux"))
                    //                        starterType = "sh";
                    //                    else if (Community.os.contains("Macos"))
                    //                        starterType = "";
                    //
                    //                    String starterBy = "";
                    //                    if (Community.os.contains("Linux"))
                    //                        starterBy = "sh";
                    //                    else if (Community.os.contains("Macos"))
                    //                        starterBy = "";

                    //                    String starter = versionPath + "start." + starterType;

                    //                    BufferedWriter bw = new BufferedWriter(new FileWriter(starter));

                    //                    bw.write(CompleteLauncher);

                    //                    bw.close();

                    loadingStatus.setText(CompleteLauncher);

                    Thread.sleep(500);

                    try {
                        if(breakLaunch.contains(gameVersionName)) {
                            launching = false;
                            Log.writeLog("[Launcher Thread/INFO] launch break");
                            return;
                        }

                        String instanceName;
                        String[] testLauncher = new String[3];
                        if(Community.os.contains("Linux")) {
                            testLauncher[0] = "/bin/bash";
                            testLauncher[1] = "-c";
                            testLauncher[2] = CompleteLauncher;
                            instanceName = createInstance("minecraft", testLauncher, gameVersionName, source, gamePath);
                        } else {
                            instanceName = createInstance("minecraft", CompleteLauncher, gameVersionName, source, gamePath);
                        }

                        //                        Process process = Runtime.getRuntime().exec(starterBy + " " + starter);


                        Thread.sleep(5000);
                        if(new JSONObject(runningMinecraft.get(instanceName).toString()).get("status").toString().equals("running")) {
                            Log.writeLog("[Launcher Thread/INFO] \"" + gameVersionName + "\" are launched");
                            stepNow.setText(lang.get("launched"));
                        } else {
                            Log.writeLog("[Launcher Thread/INFO] \"" + gameVersionName + "\" launch failed");
                            stepNow.setText(lang.get("launch_fail"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        stepNow.setText(lang.get("launch_fail"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    stepNow.setText(lang.get("unsupported_game_type"));
                }
            } else {
                if(! minecraftCanUse) {
                    Log.writeLog("[Launcher Thread/INFO] \"" + gameVersionName + "\" fail to launch: unsupported java version");
                    stepNow.setText(lang.get("java_cannot_use"));
                }

                if(! accountCanUse) {
                    Log.writeLog("[Launcher Thread/INFO] \"" + gameVersionName + "\" fail to launch: cannot initialization account");
                    stepNow.setText(lang.get("account_cannot_use"));
                }
            }
        } else {
            Log.writeLog("[Launcher Thread/INFO] \"" + gameVersionName + "\" fail to launch: java not found");
            stepNow.setText(lang.get("java_not_found"));
        }

        launching = false;
    }

    public static void customInstance(String command) {
        new Thread(() -> {
            createInstance("other", command, null, null, null, "Unknown", null);
        }).start();
    }

    public static String createInstance(String instanceType, String[] instanceCommand, String minecraftInstanceInfo_gameVersionName, JSONObject minecraftInstanceInfo_infoSource, String programPath) {
        return createInstance(instanceType, null, instanceCommand, minecraftInstanceInfo_gameVersionName, minecraftInstanceInfo_infoSource, programPath, minecraftInstanceInfo_gameVersionName);
    }

    public static String createInstance(String instanceType, String instanceCommand, String minecraftInstanceInfo_gameVersionName, JSONObject minecraftInstanceInfo_infoSource, String programPath) {
        return createInstance(instanceType, instanceCommand, null, minecraftInstanceInfo_gameVersionName, minecraftInstanceInfo_infoSource, programPath, minecraftInstanceInfo_gameVersionName);
    }

    public static String createInstance(String instanceType, String instanceCommand, String[] instanceCommandArray, String minecraftInstanceInfo_gameVersionName, JSONObject minecraftInstanceInfo_infoSource, String programPath, String instanceName) {
        try {
            Process process;
            if(instanceCommand == null)
                process = Runtime.getRuntime().exec(instanceCommandArray);
            else
                process = Runtime.getRuntime().exec(instanceCommand);

            JSONObject runningStatus = new JSONObject();
            runningStatus.put("status", "running");
            if(instanceType.equals("minecraft")) {
                runningStatus.put("name", minecraftInstanceInfo_gameVersionName);
                runningStatus.put("version", minecraftInstanceInfo_infoSource.get("id").toString());
                runningStatus.put("path", programPath);
                runningStatus.put("game_type", minecraftInstanceInfo_infoSource.get("type").toString());
                runningStatus.put("in_area", selectedAreaName);
                runningStatus.put("run_type", launchType);
            }
            runningStatus.put("pid", process.pid());

            instanceName = process.pid() + (instanceName != null ? " - " + instanceName : "");
            Log.writeLog("[Launcher Thread/INFO] created new Instance: " + instanceName);
            Log.writeLog("[Launcher Thread/INFO] Instance format: <" + instanceName + ">" + runningStatus);
            runningMinecraft.put(instanceName, runningStatus);

            StringBuilder err = new StringBuilder();

            String finalInstanceName = instanceName;
            new Thread(() -> {
                try {
                    //                                new Thread(() -> {
                    //                                    while (new JSONObject(runningMinecraft.get(process.pid()).toString()).get("status").toString().equals("running")) {
                    //                                        try {
                    //                                            Thread.sleep(100);
                    //                                        } catch (Exception e) {
                    //
                    //                                        }
                    //                                    }
                    //                                }).start();

                    if(instanceType.equals("minecraft")) {
                        downloadingMinecraft.remove(minecraftInstanceInfo_gameVersionName);
                        downloads.remove(minecraftInstanceInfo_gameVersionName);
                    }

                    new Thread(() -> {
                        try {
                            BufferedReader minecraftListener = new BufferedReader(new InputStreamReader(process.getInputStream()));

                            String readLog;
                            StringBuilder log = new StringBuilder();

                            String splitTime = times.getTime(timeType.LONG_LOG);
                            long split = 0;

                            new File(ConfigMain.path + (instanceType.equals("minecraft") ? "cache/minecraftLogs/" : "cache/customInstanceLogs/") + finalInstanceName + "/" + times.getTime(timeType.AS_DAY) + "/").mkdirs();
                            BufferedWriter cacheWriter = new BufferedWriter(new FileWriter(ConfigMain.path + (instanceType.equals("minecraft") ? "cache/minecraftLogs/" : "cache/customInstanceLogs/") + finalInstanceName + "/" + times.getTime(timeType.AS_DAY) + "/cache_" + finalInstanceName + ".cache"));
                            while((readLog = minecraftListener.readLine()) != null) {
                                log.insert(log.length(), readLog + "\n");
                                if(log.length() > 1024 * 200) {
                                    cacheWriter.write(splitTime + "[MCH exporter/INFO] " + log.length() + "/204800Byte(MCH STD) split cache - split: " + split++ + "\n");
                                    splitTime = times.getTime(timeType.LONG_LOG);
                                    cacheWriter.write(log.toString());
                                    cacheWriter.flush();
                                    log = new StringBuilder();
                                    Runtime.getRuntime().gc();
                                }
                                runningLogs.put(finalInstanceName, log);
                            }

                            cacheWriter.write(runningLogs.get(finalInstanceName).toString());

                            cacheWriter.close();
                        } catch (Exception e) {

                        }

                        Log.writeLog("[Launcher Thread/INFO] Instance stopped: " + finalInstanceName);

                        //                                    JSONObject nowSelectMinecraft = new JSONObject(minecraftVersions.get(verList.getSelectedValue()).toString());
                        //                                    if(nowSelectMinecraft.get("path").toString().replace("\\", "/").equals(finalGamePath)) {
                        runningStatus.put("status", "stopped");
                        Log.writeLog("[Launcher Thread/INFO] Instance format: <" + finalInstanceName + ">" + runningStatus);
                        runningMinecraft.put(finalInstanceName, runningStatus);
                        //                                    }
                    }).start();

                    new Thread(() -> {
                        try {
                            BufferedReader minecraftListener = new BufferedReader(new InputStreamReader(process.getErrorStream(), Charset.forName("GBK")));

                            String readLog;

                            while((readLog = minecraftListener.readLine()) != null) {
                                err.append(readLog).append("\n");
                            }

                        } catch (Exception e) {

                        }
                    }).start();
                } catch (Exception ignored) {

                }
            }).start();

            return instanceName;
        } catch (IOException e) {
            return null;
        }
    }

    public static void deleteFiles(String filePath) {
        for(File f : new File(filePath).listFiles()) {
            if(f.isFile())
                f.delete();
            else if(f.isDirectory())
                deleteFiles(f.getAbsolutePath());
        }

        new File(filePath).delete();
    }

    public static LinkedHashMap<Object, Object> getDownloadVersions(LinkedHashMap<Object, Object> hashMap, String type) {
        try {
            StringBuilder json = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(ConfigMain.path + "/minecraft/versions/ver.json"));

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
        } catch (Exception e) {

        }
        return hashMap;
    }

    public static void exportLog(String fileTo, String parentDir, File logFile) {
        try {
            new File(parentDir).mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileTo));
            BufferedReader br = new BufferedReader(new FileReader(logFile));
            String write;
            while((write = br.readLine()) != null) {
                bw.write(write + "\n");
            }

            //            BufferedWriter cacheWriter = new BufferedWriter(new FileWriter(logFile.getName()));
            //cacheWriter.write(selectedMinecraftLogs.getText());
            //            cacheWriter.close();

            if(new JSONObject(runningMinecraft.get(runningList.getSelectedValue().toString()).toString()).get("status").equals("running"))
                bw.write(selectedMinecraftLogs.getText());

            bw.close();
            br.close();

            Log.fileToZip(fileTo, parentDir + "/" + new File(fileTo).getParentFile().getName() + "_export.zip", logFile.getName() + ".log");

            new File(fileTo).delete();
        } catch (Exception e) {

        }
    }

    public static LinkedHashMap<Object, Object> getDownloadVersions(String type, boolean fresh) {
        LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();
        File f = new File(ConfigMain.path + "/minecraft/versions/ver.json");
        if(! f.isFile() | fresh) {
            if(! customDownLoad("https://launchermeta.mojang.com/mc/game/version_manifest.json", ConfigMain.path + "/minecraft/versions/ver.json", true, - 1, "")) {
                if(! f.isFile() | f.length() <= 0) {
                    hashMap.put("error", String.format(lang.get("get_ver_error"), """

                            =========
                            Error: Request not FeedBack
                            Error: URL are not existent
                            ========="""));
                } else {
                    getDownloadVersions(hashMap, type);
                    hashMap.put("error", String.format(lang.get("get_ver_error_internet"), """

                            =========
                            Error: Request not FeedBack
                            ========="""));
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

        StringBuilder areas = new StringBuilder();
        try {
            LinkedHashMap<Object, Object> areaMap = minecraftAreas;
            areaMap.remove(lang.get("default_area"));
            for(Object o : areaMap.keySet()) {
                if(new File(new JSONObject(minecraftAreas.get(o.toString()).toString()).get("path").toString()).isDirectory()) {
                    JSONObject area = new JSONObject(minecraftAreas.get(o.toString()).toString());
                    area.put("name", o.toString());
                    areas.append(area).append(";");
                }
            }
        } catch (Exception e) {

        }

        StringBuilder usersBuilder = new StringBuilder();
        try {
            LinkedHashMap<Object, Object> userMap = users;
            userMap.remove(lang.get("no_user"));
            for(Object o : userMap.keySet()) {
                JSONObject usr = new JSONObject(users.get(o.toString()).toString());
                usersBuilder.append(usr).append(";");
            }
        } catch (Exception e) {

        }

        areas.insert(0, "minecraftAreas@");
        javas.insert(0, "javaPaths@");
        usersBuilder.insert(0, "users@");
        String usingJava = "java@" + java;
        String usedUser = "user@" + user;
        String usedUUID = "userUUID@" + uuid;
        String usedArea = "minecraftArea@" + selectedAreaName;
        String runType = "runMinecraftType@" + launchType;

        for(String s : Arrays.asList(checkResource_config, javas.toString(), usingJava, areas.toString(), usedUser, usedUUID, usersBuilder.toString(), usedArea, runType)) {
            Community.launcherConf.put(s.substring(0, s.indexOf("@")), s.substring(s.indexOf("@") + 1));
        }
    }

    public static void UI() {
        clientVmOption.addAll(Arrays.asList("-XX:+UseG1GC", "-XX:-UseAdaptiveSizePolicy", "-XX:-OmitStackTraceInFastThrow", "-Dfml.ignoreInvalidMinecraftCertificates=True", "-Dfml.ignorePatchDiscrepancies=True", "-XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump", "-Dminecraft.launcher.brand=MCH", "-Dminecraft.launcher.version=10"));
        serverVmOptions.addAll(Arrays.asList("-XX:+UseG1GC", "-jar", "-server"));

        visibleMenuButton(menuPage);

        configReads();

        java = Community.launcherConf.get("java") == null ? "java" : Community.launcherConf.get("java").toString();
        javaPaths = getJavaVersions();

        for(Object o : javaPaths.keySet()) {
            if(new File(o.toString()).isFile()) {
                JavasList.add(o.toString());
            }
        }

        users = getUsers();

        minecraftAreas = getMinecraftAreas();

        uploadConfig();

        try {
            ConfigMain.WriteConfig();
        } catch (Exception e) {

        }

        downloadStatus.setEditable(false);
        loadingStatus.setEditable(false);
        javaStatus.setEditable(false);
        runningMinecraftStatus.setEditable(false);

        deleteWarning.setEditable(false);

        minecraftAreaDescription.setEditable(false);

        runningMinecraftLogs.setEditable(false);
        selectedMinecraftLogs.setEditable(false);

        mainFrame.setResizable(false);

        addAccountFrame.setResizable(false);

        selectedMinecraftLogs.setLineWrap(true);

        new Thread(() -> {
            minecraftDownloadVersions = getDownloadVersions("", true);
            verList_download.setListData(minecraftDownloadVersions.keySet().toArray());
        }).start();
        new Thread(() -> {
            for(Object o : downloadingMinecraft.keySet()) {
                JSONObject status = new JSONObject(downloadingMinecraft.get(o.toString()).toString());
                String downloadStatus = status.get("status").toString();
                String download_Name = status.get("name").toString();
                String downloadTarget = status.get("target").toString();

                if(downloadStatus.equals("downloading")) {
                    new Thread(() -> {
                        new downloadMinecraft().startOneMinecraftDownload(download_Name, downloadTarget, false, status.get("minecraft_type").toString());
                    }).start();
                }

                if(downloadStatus.equals("checking")) {
                    new Thread(() -> {
                        try {
                            status.put("status", "ready");
                            status.put("progress", "1/1");

                            downloadingMinecraft.put(download_Name, status);

                            FileOutputStream statusWriter = new FileOutputStream(DEFAULT_VERSIONS_PATH + download_Name + "/" + download_Name + "_status.json", false);

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
        new Thread(() -> {
            Document uuidDoc = accountPassword.getDocument();
            Document nameDoc = accountName.getDocument();
            AttributeSet red = null;
            AttributeSet yellow = null;
            AttributeSet fore = null;
            AttributeSet gray = null;
            StyleContext sc = StyleContext.getDefaultStyleContext();

            if(Community.ColorID == 0) {
                red = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 60, 80));
            } else if(Community.ColorID == 1) {
                red = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 70, 49));
            }

            if(Community.ColorID == 0) {
                yellow = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 160, 49));
            } else if(Community.ColorID == 1) {
                yellow = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 165, 80));
            }

            if(Community.ColorID == 0) {
                fore = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);
            } else if(Community.ColorID == 1) {
                fore = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.white);
            }

            StyleContext sc_normal = StyleContext.getDefaultStyleContext();

            if(Community.ColorID == 0) {
                gray = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.gray);
            } else if(Community.ColorID == 1) {
                gray = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.gray);
            }

            while(! Errors.CannotHandle) {
                try {
                    if(addAccountFrame.isVisible() & ! addingAccount) {
                        int passwordCaretPosition = accountPassword.getCaretPosition();
                        int nameCaretPosition = accountName.getCaretPosition();
                        String name = accountName.getText();

                        if(name.contains("\n") | name.contains("\r")) {
                            accountName.setText(name.replace("\n", "").replace("\r", ""));
                            accountName.setCaretPosition(nameCaretPosition - 1);
                            createAccount();

                            name = "";
                        }
                        if(name.length() > 20) {
                            int caret_20 = Math.max(accountName.getCaretPosition(), 20);
                            accountName.setText(name.substring(0, 20));
                            accountName.setCaretPosition(caret_20);
                        }

                        String uuid = accountPassword.getText();
                        if(uuid.contains("\n") | uuid.contains("\r")) {
                            accountPassword.setText(uuid.replace("\n", "").replace("\r", ""));
                            accountPassword.setCaretPosition(passwordCaretPosition - 1);
                            createAccount();

                            name = "";
                        }

                        if(! invalidName) {
                            nameDoc.remove(0, nameDoc.getLength());
                            nameDoc.insertString(0, name, fore);
                            accountName.setCaretPosition(nameCaretPosition);
                        }
                    }
                } catch (Exception e) {

                }

                try {
                    Thread.sleep(Community.isDaemons ? 500 : 25);
                } catch (InterruptedException e) {

                }
            }
        }).start();
        new Thread(() -> {
            while(! Errors.CannotHandle) {
                try {
                    if(downloadPanel.isVisible()) {
                        if(downloadName.getText().contains("\n") || downloadName.getText().contains("\r")) {
                            try {
                                new JSONObject(minecraftDownloadVersions.get(verList_download.getSelectedValue().toString()));
                                downloadName.setText(downloadName.getText().replace("\n", "").replace("\r", ""));
                                new Thread(() -> {
                                    new downloadMinecraft().startOneMinecraftDownload("Vanilla");
                                }).start();
                            } catch (Exception e) {

                            }
                        }

                        if(! downloadName.getText().matches("^\\w+$") & ! downloadName.getText().equals("")) {
                            try {
                                new JSONObject(minecraftDownloadVersions.get(verList_download.getSelectedValue().toString()));
                                downloadName.setText(verList_download.getSelectedValue().toString());
                            } catch (Exception e) {

                            }
                        }
                    }
                } catch (Exception e) {

                }

                try {
                    if(launchPanel.isVisible()) {
                        try {
                            minecraftAreas = getMinecraftAreas();
                            if(minecraftAreas.get(selectedAreaName) == null)
                                Community.launcherConf.put("minecraftArea", lang.get("default_area"));
                            selectedAreaName = Community.launcherConf.get("minecraftArea").toString();
                            JSONObject json = new JSONObject(minecraftAreas.get(selectedAreaName).toString());
                            versionsPath = json.get("path").toString().replace("\\", "/") + "/versions/";
                            gamePath = json.get("path").toString().replace("\\", "/") + "/";
                            minecraftVersions = getVersions(versionsPath);
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

                        Thread.sleep(30);

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

                        Thread.sleep(30);

                        String status = "";
                        try {
                            status = new JSONObject(minecraftVersions.get(verList.getSelectedValue().toString()).toString()).get("status").toString();
                        } catch (Exception e) {

                        }

                        boolean hasSelected = minecraftVersionsList_use.size() > 0 & verList.getSelectedIndex() != - 1;
                        launch.setVisible(hasSelected & (status.equals("#ready") | status.equals("#checking")));
                        readyTag.setVisible(hasSelected & status.equals("#Unknown"));
                        stepNow.setVisible(launch.isVisible());
                        if(selectionIndex != verList.getSelectedIndex()) {
                            stepNow.setText("");
                            selectionIndex = verList.getSelectedIndex();
                            if(verList.getModel().getSize() < selectionIndex)
                                verList.setSelectedIndex(selectionIndex);
                        }

                        setLoadingStatus(launching);
                    }
                } catch (Exception e) {

                }

                try {
                    if(javaPanel.isVisible()) {
                        try {
                            javaPaths = getJavaVersions();
                            int selected = Math.max(javaList.getSelectedIndex(), 0);
                            removeJava.setVisible(JavasList.size() > 0);
                            setUsingJava.setVisible(JavasList.size() > 0);
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

                        setJavaStatus();
                    }
                } catch (Exception e) {

                }

                try {
                    if(minecraftAreaPanel.isVisible()) {
                        try {
                            if(minecraftAreas.get(selectedAreaName) == null)
                                Community.launcherConf.put("minecraftArea", lang.get("default_area"));
                            minecraftAreas = getMinecraftAreas();
                            int selected = Math.max(minecraftAreaList.getSelectedIndex(), 0);
                            minecraftAreaList.setListData(minecraftAreas.keySet().toArray());
                            minecraftAreaList.setSelectedIndex(selected);
                        } catch (Exception e) {

                        }

                        areaSelected = minecraftAreaList.getSelectedValue().toString().equals(selectedAreaName);

                        removeMinecraftArea.setVisible(! minecraftAreaList.getSelectedValue().toString().equals(lang.get("default_area")));
                        renameMinecraftArea.setVisible(! minecraftAreaList.getSelectedValue().toString().equals(lang.get("default_area")));

                        setAreaStatus();
                    }
                } catch (Exception e) {

                }

                try {
                    if(accountPanel.isVisible()) {
                        try {
                            users = getUsers();
                            int selected = Math.max(userList.getSelectedIndex(), 0);
                            userList.setListData(users.keySet().toArray());
                            userList.setSelectedIndex(selected);
                        } catch (Exception e) {

                        }

                        userSelected = userList.getSelectedValue().toString().equals(user);

                        boolean showOperators = ! userList.getSelectedValue().toString().equals(lang.get("no_user"));
                        selectAccount.setVisible(showOperators);
                        removeAccount.setVisible(showOperators);

                        if(! users.containsKey(user)) {
                            user = "";
                            uuid = "";
                        }

                        setUserStatus();
                    }
                } catch (Exception e) {

                }

                try {
                    if(runningPanel.isVisible() | logsFrame.isVisible()) {
                        try {
                            runningMinecraft_Display = getRunningMinecraftStatus();
                            int selected = Math.max(runningList.getSelectedIndex(), 0);
                            runningList.setListData(runningMinecraft_Display.keySet().toArray());
                            runningList.setSelectedIndex(selected);
                        } catch (Exception e) {

                        }

                        boolean showForceStop;

                        try {
                            showForceStop = new JSONObject(runningMinecraft.get(runningList.getSelectedValue().toString()).toString()).get("status").toString().equals("running");
                        } catch (Exception ex) {
                            showForceStop = false;
                        }

                        forceStopMinecraft.setVisible(showForceStop);

                        boolean showRemove;

                        try {
                            new JSONObject(runningMinecraft.get(runningList.getSelectedValue().toString()).toString()).get("pid");
                            showRemove = true;
                        } catch (Exception ex) {
                            showRemove = false;
                        }

                        removeInstance.setVisible(showRemove);

                        boolean showLogs;

                        try {
                            showLogs = runningLogs.containsKey(runningList.getSelectedValue().toString());
                        } catch (Exception ex) {
                            showLogs = false;
                        }

                        logs.setVisible(showLogs);
                        exportLogs.setVisible(showLogs);

                        setRunningStatus();
                    }
                } catch (Exception e) {

                }

                try {
                    Thread.sleep(Community.isDaemons ? 500 : 150);
                } catch (InterruptedException e) {

                }

                Runtime.getRuntime().gc();
            }
        }).start();

        runningMinecraftLogs.setFont(new Font("", Font.PLAIN, 10));

        mainFrame.setSize(811, 488);
        logsFrame.setSize(811, 533);
        addAccountFrame.setSize(399, 500);

        mainFrame.getContentPane().setBackground(Color.white);

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

        selectedMinecraftLogsScrollPanel.getVerticalScrollBar().setValue(0);
        selectedMinecraftLogsScrollPanel.getHorizontalScrollBar().setValue(0);
        selectedMinecraftLogsScrollPanel.setViewportView(selectedMinecraftLogs);

        runningMinecraftLogsScrollPanel.getVerticalScrollBar().setValue(0);
        runningMinecraftLogsScrollPanel.getHorizontalScrollBar().setValue(0);
        runningMinecraftLogsScrollPanel.setViewportView(runningMinecraftLogs);

        userListScrollPane.getVerticalScrollBar().setValue(0);
        userListScrollPane.getHorizontalScrollBar().setValue(0);
        userListScrollPane.setViewportView(userList);

        //        窗口初始化设置
        //获得屏幕大小
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        mainFrame.setLocation(width / 2 - mainFrame.getWidth() / 2, height / 2 - mainFrame.getHeight() / 2);
        logsFrame.setLocation(width / 2 - logsFrame.getWidth() / 2, height / 2 - logsFrame.getHeight() / 2);
        addAccountFrame.setLocation(width / 2 - logsFrame.getWidth() / 2, height / 2 - logsFrame.getHeight() / 2);

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
                accountNameTip.setBounds(25, 100, 70, 22);
                accountName.setBounds(95, 100, 250, 22);
                accountPasswordTip.setBounds(25, 135, 70, 22);
                accountPassword.setBounds(95, 135, 250, 22);
                addOfflineAccount.setBounds(0, addAccountPanel.getHeight() - 80, 132, 40);
                addMojangAccount.setBounds(132, addAccountPanel.getHeight() - 80, 132, 40);
                addThisAccount.setBounds(125, 175, 122, 40);

                runningMinecraftLogsScrollPanel.setBounds(0, 0, runningMinecraftLogsPanel.getWidth() + 30, runningMinecraftLogsPanel.getHeight() + 18);
                selectedMinecraft.setBounds(0, 0, logsFrame.getWidth() / 2, 40);
                exportLogs.setBounds(logsFrame.getWidth() - 130, 0, 122, 40);
                selectedMinecraftLogsScrollPanel.setBounds(0, 40, logsFrame.getWidth() + 30, logsFrame.getHeight() - 40);

                verScrollPane.setBounds(mainFrame.getWidth() - mainFrame.getWidth() / 3 + 10, 0, mainFrame.getWidth(), launchPanel.getHeight());
                loadingProgress.setBounds(150, 0, mainFrame.getWidth() / 3 + 120, 20);
                loadingStatus.setBounds(150, 20, mainFrame.getWidth() / 3 + 120, mainFrame.getHeight());

                runningScrollPane.setBounds(mainFrame.getWidth() - mainFrame.getWidth() / 3 + 10, 0, mainFrame.getWidth() / 3, runningPanel.getHeight());
                forceStopMinecraft.setBounds(5, 100, 122, 40);
                removeInstance.setBounds(5, 150, 122, 40);
                logs.setBounds(5, 200, 122, 40);
                runningMinecraftStatus.setBounds(150, 0, runningPanel.getWidth() / 3 + 120, runningPanel.getHeight() / 2);
                runningMinecraftLogsPanel.setBounds(150, runningPanel.getHeight() / 2, runningPanel.getWidth() / 3 + 120, runningPanel.getHeight() / 2);

                javaScrollPane.setBounds(0, - 2, mainFrame.getWidth() + 10, javaPanel.getHeight() / 2);
                removeJava.setBounds(5, javaPanel.getHeight() / 2 + 60, 122, 30);
                javaStatus.setBounds(mainFrame.getWidth() / 2, javaPanel.getHeight() / 2, mainFrame.getWidth() / 2, javaPanel.getHeight() / 2);
                usingJava.setBounds(0, javaPanel.getHeight() / 2, mainFrame.getWidth() / 2, 30);
                setUsingJava.setBounds(5, javaPanel.getHeight() / 2 + 100, 122, 30);
                importJava.setBounds(5, javaPanel.getHeight() / 2 + 140, 122, 30);

                minecraftAreaList.setBounds(mainFrame.getWidth() - mainFrame.getWidth() / 3 + 10, 0, mainFrame.getWidth(), minecraftAreaPanel.getHeight());
                minecraftAreaDescription.setBounds(10, 300, 200, 150);
                minecraftAreaStatus.setBounds(200, 0, mainFrame.getWidth() / 3 + 70, mainFrame.getHeight());
                addMinecraftArea.setBounds(10, 100, 122, 40);
                selectThisArea.setBounds(10, 150, 122, 40);
                renameMinecraftArea.setBounds(10, 200, 122, 40);
                removeMinecraftArea.setBounds(10, 250, 122, 40);

                userListScrollPane.setBounds((accountPanel.getWidth() / 3) * 2, 0, accountPanel.getWidth() + 30, accountPanel.getHeight());
                selectAccount.setBounds(5, 150, 122, 40);
                removeAccount.setBounds(5, 200, 122, 40);
                addAccount.setBounds(5, 250, 122, 40);
                userStatus.setBounds(150, 0, accountPanel.getWidth() / 3 + 100, accountPanel.getHeight() - 50);
                accountUsing.setBounds(150, accountPanel.getHeight() - 50, accountPanel.getWidth() / 3 + 100, 50);

                checkResOption.setBounds(5, 5, 100, 30);
                checkRes.setBounds(110, 5, 100, 30);
                noCheckRes.setBounds(220, 5, 100, 30);
                runMinecraftType.setBounds(5, 45, 100, 30);
                runMinecraftClient.setBounds(110, 45, 100, 30);
                runMinecraftServer.setBounds(220, 45, 100, 30);

                stepNow.setBounds(10, 70, 140, 30);
                launch.setBounds(10, 100, 122, 40);
                readyTag.setBounds(10, 100, 122, 40);

                deleteVersion.setBounds(10, 250, 122, 40);
                deleteWarning.setBounds(10, 300, 150, 150);

                downloadName_label.setBounds(2, 40, (mainFrame.getWidth() / 3) / 2 + 60, 20);
                downloadName.setBounds(2, 60, (mainFrame.getWidth() / 3) / 2 + 60, 20);

                startDownload.setBounds(10, 100, 170, 40);
                downloadAndLaunch.setBounds(10, 150, 170, 40);
                downloadAndFastLaunch.setBounds(10, 200, 170, 40);
                //                downloadAll.setBounds(10, 350, 170, 40);
                downloadProgress.setBounds(mainFrame.getWidth() - (mainFrame.getWidth() / 3) * 2 - 60, 0, mainFrame.getWidth() / 3 + 70, 20);

                downloadStatus.setBounds(mainFrame.getWidth() - (mainFrame.getWidth() / 3) * 2 - 60, 20, mainFrame.getWidth() / 3 + 70, mainFrame.getHeight());

                downloadScrollPane.setBounds(mainFrame.getWidth() - mainFrame.getWidth() / 3 + 10, 0, mainFrame.getWidth() / 3 + 100, mainFrame.getHeight() - 150);
                showSelect.setBounds(mainFrame.getWidth() - mainFrame.getWidth() / 3 + 10, mainFrame.getHeight() - 155, (mainFrame.getWidth() / 3), 30);
                showRelease.setBounds(mainFrame.getWidth() - mainFrame.getWidth() / 3 + 10, mainFrame.getHeight() - 150 + 20, (mainFrame.getWidth() / 3) / 3 - 10, 30);
                showSnapshot.setBounds(mainFrame.getWidth() - mainFrame.getWidth() / 3 + (mainFrame.getWidth() / 3) / 3, mainFrame.getHeight() - 150 + 20, (mainFrame.getWidth() / 3) / 3, 30);
                showAlpha.setBounds(mainFrame.getWidth() - mainFrame.getWidth() / 3 + ((mainFrame.getWidth() / 3) / 3) * 2, mainFrame.getHeight() - 150 + 20, (mainFrame.getWidth() / 3) / 3, 30);
                showAll.setBounds(mainFrame.getWidth() - mainFrame.getWidth() / 3 + 10, mainFrame.getHeight() - 120 + 20, (mainFrame.getWidth() / 3) / 2 - 10, 30);
                freshList.setBounds(mainFrame.getWidth() - mainFrame.getWidth() / 3 + (mainFrame.getWidth() / 3) / 2, mainFrame.getHeight() - 120 + 20, (mainFrame.getWidth() / 3) / 2, 30);

                switchLauncherPanel.setBounds(0, 1, 100, 31);
                switchDownloadPanel.setBounds(100, 1, 100, 31);
                switchSettingsPanel.setBounds(200, 1, 100, 31);
                switchJavaPanel.setBounds(300, 1, 100, 31);
                switchRunningPanel.setBounds(400, 1, 100, 31);
                switchMinecraftAreaPanel.setBounds(500, 1, 100, 31);
                switchAccountPanel.setBounds(600, 1, 100, 31);
                nextPage.setBounds(700, 1, 100, 31);
                lastPage.setBounds(0, 1, 100, 31);

                launchPanel.setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight() - 70);
                downloadPanel.setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight() - 70);
                settingsPanel.setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight() - 70);
                javaPanel.setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight() - 70);
                runningPanel.setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight() - 70);
                minecraftAreaPanel.setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight() - 70);
                accountPanel.setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight() - 70);
                addAccountPanel.setBounds(0, 0, addAccountFrame.getWidth(), addAccountFrame.getHeight());

                menuPanel.setBounds(0, mainFrame.getHeight() - 70, mainFrame.getWidth(), 70);
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
        launchPanel.add(readyTag);
        launchPanel.add(deleteVersion);
        launchPanel.add(deleteWarning);
        launchPanel.add(shortInfo);
        launchPanel.add(loadingProgress);
        launchPanel.add(loadingStatus);
        launchPanel.add(stepNow);
        mainFrame.add(launchPanel);

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
        mainFrame.add(downloadPanel);

        menuPanel.add(switchLauncherPanel);
        menuPanel.add(switchDownloadPanel);
        menuPanel.add(switchSettingsPanel);
        menuPanel.add(switchJavaPanel);
        menuPanel.add(switchRunningPanel);
        menuPanel.add(switchMinecraftAreaPanel);
        menuPanel.add(switchAccountPanel);
        menuPanel.add(nextPage);
        menuPanel.add(lastPage);
        mainFrame.add(menuPanel);

        settingsPanel.add(checkResOption);
        settingsPanel.add(checkRes);
        settingsPanel.add(noCheckRes);
        settingsPanel.add(runMinecraftType);
        settingsPanel.add(runMinecraftClient);
        settingsPanel.add(runMinecraftServer);
        mainFrame.add(settingsPanel);

        javaPanel.add(javaScrollPane);
        javaPanel.add(usingJava);
        javaPanel.add(javaStatus);
        javaPanel.add(setUsingJava);
        javaPanel.add(importJava);
        javaPanel.add(removeJava);
        mainFrame.add(javaPanel);

        runningPanel.add(runningScrollPane);
        runningPanel.add(runningMinecraftStatus);
        runningPanel.add(forceStopMinecraft);
        runningPanel.add(removeInstance);
        runningPanel.add(logs);
        runningPanel.add(runningMinecraftLogsPanel);
        runningMinecraftLogsPanel.add(runningMinecraftLogsScrollPanel);
        mainFrame.add(runningPanel);

        minecraftAreaPanel.add(minecraftAreaList);
        minecraftAreaPanel.add(minecraftAreaScrollPane);
        minecraftAreaPanel.add(minecraftAreaDescription);
        minecraftAreaPanel.add(minecraftAreaStatus);
        minecraftAreaPanel.add(addMinecraftArea);
        minecraftAreaPanel.add(selectThisArea);
        minecraftAreaPanel.add(renameMinecraftArea);
        minecraftAreaPanel.add(removeMinecraftArea);
        mainFrame.add(minecraftAreaPanel);

        accountPanel.add(userListScrollPane);
        accountPanel.add(userStatus);
        accountPanel.add(accountUsing);
        accountPanel.add(selectAccount);
        accountPanel.add(addAccount);
        accountPanel.add(removeAccount);
        mainFrame.add(accountPanel);

        launchPanel.setVisible(true);
        downloadPanel.setVisible(false);
        settingsPanel.setVisible(false);
        javaPanel.setVisible(false);
        runningPanel.setVisible(false);
        minecraftAreaPanel.setVisible(false);
        launch.setVisible(false);
        logsFrame.setVisible(false);
        accountPanel.setVisible(false);

        logsPanel.add(selectedMinecraftLogsScrollPanel);
        logsPanel.add(selectedMinecraft);
        logsPanel.add(exportLogs);
        logsFrame.add(logsPanel);

        addAccountPanel.add(accountName);
        addAccountPanel.add(addThisAccount);
        addAccountPanel.add(addMojangAccount);
        addAccountPanel.add(addOfflineAccount);
        addAccountPanel.add(accountPassword);
        addAccountPanel.add(accountNameTip);
        addAccountPanel.add(accountPasswordTip);
        addAccountFrame.add(addAccountPanel);

        logsPanel.setLayout(layoutManager);
        menuPanel.setLayout(layoutManager);
        downloadPanel.setLayout(layoutManager);
        launchPanel.setLayout(layoutManager);
        runningPanel.setLayout(layoutManager);
        mainFrame.setLayout(layoutManager);
        settingsPanel.setLayout(layoutManager);
        javaPanel.setLayout(layoutManager);
        minecraftAreaPanel.setLayout(layoutManager);
        runningMinecraftLogsPanel.setLayout(layoutManager);
        accountPanel.setLayout(layoutManager);
        addAccountPanel.setLayout(layoutManager);
        addAccountFrame.setLayout(layoutManager);

        readyTag.addActionListener(e -> {
            try {
                JSONObject source = new JSONObject(minecraftVersions.get(verList.getSelectedValue().toString()).toString());
                String path = source.get("path").toString().replace("\\", "/");

                BufferedReader br = new BufferedReader(new FileReader(path + "/" + path.substring(path.lastIndexOf("/") + 1) + ".json"));

                StringBuilder gameSourceBuilder = new StringBuilder();
                String s;
                while((s = br.readLine()) != null)
                    gameSourceBuilder.append(s);

                br.close();

                JSONObject gameSource = new JSONObject(gameSourceBuilder.toString());

                String minecraftType;
                switch(gameSource.get("mainClass").toString()) {
                    case "net.fabricmc.loader.impl.launch.knot.KnotClient" -> minecraftType = "Fabric-Impl-Knot";
                    case "net.fabricmc.loader.launch.knot.KnotClient" -> minecraftType = "Fabric-Knot";
                    case "net.minecraft.launchwrapper.Launch" -> minecraftType = "Forge";
                    case "net.minecraft.client.main.Main" -> minecraftType = "Vanilla";
                    default -> minecraftType = "Unknown";
                }

                source.put("minecraft_type", minecraftType);
                source.put("progress", "1/1");
                source.put("status", "ready");

                BufferedWriter bw = new BufferedWriter(new FileWriter(path + "/" + path.substring(path.lastIndexOf("/") + 1) + "_status.json"));
                bw.write(source.toString());
                bw.close();
            } catch (Exception v1) {
                v1.printStackTrace();
            }
        });

        nextPage.addActionListener(e -> {
            menuPage++;
            visibleMenuButton(menuPage);
        });

        lastPage.addActionListener(e -> {
            menuPage--;
            visibleMenuButton(menuPage);
        });

        runMinecraftClient.addActionListener(e -> {
            launchType = "client";

            uploadConfig();

            try {
                ConfigMain.WriteConfig();
            } catch (Exception v1) {

            }
        });

        runMinecraftServer.addActionListener(e -> {
            launchType = "server";

            uploadConfig();

            try {
                ConfigMain.WriteConfig();
            } catch (Exception v1) {

            }
        });

        addMinecraftArea.addActionListener(e -> {
            selectMinecraftArea("");
        });

        selectThisArea.addActionListener(e -> {
            selectedAreaName = minecraftAreaList.getSelectedValue().toString();
            uploadConfig();

            try {
                ConfigMain.WriteConfig();
            } catch (Exception v1) {

            }
        });

        renameMinecraftArea.addActionListener(e -> {
            //            String renameTarget = minecraftAreaList.getSelectedValue().toString();
            //            if(! renameTarget.equals(lang.get("default_area"))) {
            //                JSONObject rename = new JSONObject(minecraftAreas.get(renameTarget).toString());
            //                rename.put("name", "r");
            //                minecraftAreas.remove(renameTarget);
            //                minecraftAreas.put("r", rename);
            //
            //                uploadConfig();
            //
            //                try {
            //                    Config.WriteConfig();
            //                } catch (Exception v1) {
            //
            //                }
            //
            //                minecraftAreaList.setSelectedIndex(0);
            //            }
        });

        removeMinecraftArea.addActionListener(e -> {
            String removeTarget = minecraftAreaList.getSelectedValue().toString();
            if(! removeTarget.equals(lang.get("default_area"))) {
                minecraftAreas.remove(removeTarget);

                uploadConfig();

                try {
                    ConfigMain.WriteConfig();
                } catch (Exception v1) {

                }

                minecraftAreaList.setSelectedIndex(0);
            }
        });

        addThisAccount.addActionListener(e -> {
            createAccount();
        });

        removeAccount.addActionListener(e -> {
            users.remove(userList.getSelectedValue());

            uploadConfig();

            try {
                ConfigMain.WriteConfig();
            } catch (Exception v1) {

            }
        });

        selectAccount.addActionListener(e -> {
            try {
                JSONObject info = new JSONObject(users.get(userList.getSelectedValue().toString()).toString());

                user = info.get("name").toString();
                uuid = info.get("uuid").toString();

                uploadConfig();

                ConfigMain.WriteConfig();
            } catch (Exception v1) {

            }
        });

        exportLogs.addActionListener(e -> {
            if(! exportingLogs) {
                new Thread(() -> {
                    exportingLogs = true;

                    languageSet.Language();

                    try {

                        String savePath = ConfigMain.path + "export/" + selectedMinecraft.getText() + "/" + times.getTime(timeType.AS_SECOND) + "/";
                        String fileName = savePath + "latest.log";

                        exportLog(fileName, savePath, new File(ConfigMain.path + "cache/minecraftLogs/" + selectedMinecraft.getText() + "/" + times.getTime(timeType.AS_DAY) + "/cache_" + selectedMinecraft.getText() + ".cache"));

                        if(Community.os.equals("Linux")) {
                            try {
                                Runtime.getRuntime().exec("nautilus \"" + savePath + "\"");
                            } catch (Exception exception) {

                            }
                        } else {
                            try {
                                Runtime.getRuntime().exec("explorer.exe \"" + savePath.replace("/", "\\") + "\"");
                            } catch (Exception exception) {

                            }
                        }
                    } catch (Exception v1) {

                    }

                    exportingLogs = false;
                }).start();
            }
        });

        addAccount.addActionListener(e -> {
            addAccountFrame.setVisible(true);
        });

        addMojangAccount.addActionListener(e -> {
            addAccountType = "mojang";
            languageSet.Language();
        });

        addOfflineAccount.addActionListener(e -> {
            addAccountType = "offline";
            languageSet.Language();
        });

        logs.addActionListener(e -> {
            try {
                if(! logsFrame.isVisible()) {
                    selectedLogDisplay = runningList.getSelectedValue().toString();
                    selectedMinecraftLogs.setCaretPosition(selectedMinecraftLogs.getText().length());
                }
                logsFrame.setVisible(true);
            } catch (Exception v1) {
            }
        });

        forceStopMinecraft.addActionListener(e -> {
            try {
                ForceStop(new JSONObject(runningMinecraft.get(runningList.getSelectedValue().toString()).toString()).getInt("pid"));
            } catch (Exception ignored) {

            }
        });

        setUsingJava.addActionListener(e -> {
            try {
                if(javaList.getSelectedIndex() != - 1)
                    if(new File(javaList.getSelectedValue().toString()).isFile())
                        java = javaList.getSelectedValue().toString();
            } catch (Exception ignored) {

            }
        });

        switchAccountPanel.addActionListener(e -> {
            visiblePanel("account");
        });

        switchDownloadPanel.addActionListener(e -> {
            visiblePanel("download");
        });

        switchLauncherPanel.addActionListener(e -> {
            visiblePanel("launcher");
        });

        removeJava.addActionListener(e -> {
            String remove = javaList.getSelectedValue().toString();
            javaPaths.remove(remove);
            JavasList.remove(remove);

            if(remove.equals(java))
                java = "java";

            uploadConfig();

            try {
                ConfigMain.WriteConfig();
            } catch (Exception ex) {

            }
        });

        switchSettingsPanel.addActionListener(e -> {
            visiblePanel("settings");
        });

        switchJavaPanel.addActionListener(e -> {
            visiblePanel("java");
            if(JavasList.size() == 0) {
                selectJava(lang.get("select_java"));
            }
        });

        switchRunningPanel.addActionListener(e -> {
            visiblePanel("running");
        });

        switchMinecraftAreaPanel.addActionListener(e -> {
            visiblePanel("areas");
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

        removeInstance.addActionListener(e -> {
            removeMinecraftInstance();
        });

        startDownload.addActionListener(e -> {
            new Thread(() -> {
                if(! downloadName.getText().equals("")) {
                    try {
                        new JSONObject(minecraftDownloadVersions.get(verList_download.getSelectedValue().toString()));
                        new downloadMinecraft().startOneMinecraftDownload("Vanilla");
                    } catch (Exception v1) {

                    }
                }
            }).start();
        });

        downloadAndLaunch.addActionListener(e -> {
            new Thread(() -> {
                if(! downloadName.getText().equals("")) {
                    try {
                        new JSONObject(minecraftDownloadVersions.get(verList_download.getSelectedValue().toString()));
                        new downloadMinecraft().startOneMinecraftDownload("Vanilla");

                        String gameVersion = verList_download.getModel().getElementAt(verList_download.getSelectedIndex()).toString();
                        String gameName = downloadName.getText();

                        JSONObject info = new JSONObject(minecraftVersions.get(gameName).toString());

                        new downloadMinecraft().startOneMinecraftDownload(gameName, gameVersion, false, true, "Vanilla");
                        if(! (downloadingMinecraft.get(gameName) == null)) {
                            visiblePanel("launcher");
                            runMinecraft(info.get("path").toString(), gameVersion, false, launchType, launchType.equals("client") ? clientVmOption : serverVmOptions, "Vanilla");
                            Runtime.getRuntime().gc();
                        }
                    } catch (Exception v1) {

                    }
                }
            }).start();
        });

        downloadAndFastLaunch.addActionListener(e -> {
            new Thread(() -> {
                if(! downloadName.getText().equals("")) {
                    try {
                        new JSONObject(minecraftDownloadVersions.get(verList_download.getSelectedValue().toString()));
                        new downloadMinecraft().startOneMinecraftDownload("Vanilla");

                        String gameVersion = verList_download.getModel().getElementAt(verList_download.getSelectedIndex()).toString();

                        JSONObject info = new JSONObject(minecraftVersions.get(gameVersion).toString());

                        String gameName = downloadName.getText();

                        new downloadMinecraft().startOneMinecraftDownload(gameName, gameVersion, false, true, "Vanilla");
                        if(! (downloadingMinecraft.get(gameName) == null)) {
                            visiblePanel("launcher");
                            runMinecraft(info.get("path").toString(), gameVersion, true, launchType, launchType.equals("client") ? clientVmOption : serverVmOptions, "Vanilla");
                            Runtime.getRuntime().gc();
                        }
                    } catch (Exception v1) {

                    }
                }
            }).start();
        });

        deleteVersion.addActionListener(e -> {
            new Thread(() -> {
                try {
                    String deleteVersion = verList.getSelectedValue().toString();
                    String deletePath = new JSONObject(minecraftVersions.get(deleteVersion).toString()).get("path").toString();

                    try {
                        new File(new JSONObject(minecraftVersions.get(deleteVersion).toString()).get("path").toString() + "/DELETING").createNewFile();
                    } catch (Exception v2) {

                    }

                    try {
                        for(Object o : runningMinecraft.keySet()) {
                            JSONObject stat = new JSONObject(runningMinecraft.get(o.toString()).toString());
                            if(stat.get("path").equals(deletePath)) {

                            }
                        }

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
                            deleteFiles(deletePath);
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
                } catch (Exception v0) {

                }
            }).start();
        });

        importJava.addActionListener(e -> {
            selectJava(lang.get("select_java"));
        });

        checkRes.addActionListener(e -> Events.switchCheckResource(true));
        noCheckRes.addActionListener(e -> Events.switchCheckResource(false));

        displaySets.Color();

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

    public static void removeMinecraftInstance() {
        new Thread(() -> {
            String select = runningList.getSelectedValue().toString();
            try {
                ForceStop(new JSONObject(runningMinecraft.get(select).toString()).getInt("pid"));
            } catch (Exception ignored) {

            }

            try {
                if(new JSONObject(runningMinecraft.get(select).toString()).get("status").toString().equals("running"))
                    Thread.sleep(1000);
            } catch (Exception ignored) {

            }

            try {
                while(true) {
                    deleteFiles(ConfigMain.path + "/cache/minecraftLogs/" + select);
                }
            } catch (Exception ignored) {

            }

            runningMinecraft.remove(select);
            runningLogs.remove(select);
        }).start();
    }

    public static void createAccount() {
        invalidName = false;
        invalidUUID = false;

        String name = accountName.getText();

        Document uuidDoc = accountPassword.getDocument();
        Document nameDoc = accountName.getDocument();
        AttributeSet red = null;
        AttributeSet yellow = null;
        AttributeSet fore = null;
        AttributeSet gray = null;
        StyleContext sc = StyleContext.getDefaultStyleContext();

        if(Community.ColorID == 0) {
            red = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 60, 80));
        } else if(Community.ColorID == 1) {
            red = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 70, 49));
        }

        if(Community.ColorID == 0) {
            yellow = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 160, 49));
        } else if(Community.ColorID == 1) {
            yellow = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, new Color(255, 165, 80));
        }

        if(Community.ColorID == 0) {
            fore = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);
        } else if(Community.ColorID == 1) {
            fore = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.white);
        }

        StyleContext sc_normal = StyleContext.getDefaultStyleContext();

        if(Community.ColorID == 0) {
            gray = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.gray);
        } else if(Community.ColorID == 1) {
            gray = sc_normal.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.gray);
        }

        if(addAccountType.equals("offline")) {
            String uuid = accountPassword.getText();
            if(name.equals("") | name.contains(" ") | users.containsKey(name))
                invalidName = true;
            else
                invalidName = ! name.matches("^\\w+$");

            try {
                if(! uuid.equals("")) {
                    UUID.fromString(uuid);
                }
            } catch (Exception v1) {
                invalidUUID = true;
            }

            if(! invalidUUID & ! invalidName) {
                JSONObject info = new JSONObject();
                info.put("uuid", uuid.equals("") ? new UUID(new Random().nextLong(), new Random().nextLong()).toString() : uuid);
                info.put("name", name);
                users.put(name, info);

                uploadConfig();

                try {
                    ConfigMain.WriteConfig();
                } catch (Exception v1) {

                }

                try {
                    uuidDoc.remove(0, uuidDoc.getLength());
                    nameDoc.remove(0, nameDoc.getLength());

                    uuidDoc.insertString(0, info.get("uuid").toString(), gray);
                    nameDoc.insertString(0, name, gray);

                    addingAccount = true;

                    accountName.setEditable(false);
                    accountPassword.setEditable(false);

                    Thread.sleep(100);

                    uuidDoc.remove(0, uuidDoc.getLength());
                    nameDoc.remove(0, nameDoc.getLength());

                    accountName.setEditable(true);
                    accountPassword.setEditable(true);

                    addingAccount = false;
                } catch (Exception e) {

                }
            } else {
                if(invalidUUID) {
                    try {
                        uuidDoc.remove(0, uuidDoc.getLength());

                        String[] uuids = uuid.split("-");
                        int[] uuidSize = {8, 4, 4, 4, 12};
                        if(uuids.length < 5) {
                            uuidDoc.insertString(0, new UUID(new Random().nextLong(), new Random().nextLong()).toString(), gray);
                            invalidUUID = false;
                        } else {
                            for(int i = 0; i < 5; i++) {
                                if(uuids[i].length() != uuidSize[i]) {
                                    uuidDoc.insertString(uuidDoc.getLength(), uuids[i], red);
                                } else {
                                    for(char o : uuids[i].toCharArray()) {
                                        String s = String.valueOf(o);
                                        System.out.println(s);
                                        if(s.matches("[a-fA-F0-9]"))
                                            uuidDoc.insertString(uuidDoc.getLength(), s, gray);
                                        else
                                            uuidDoc.insertString(uuidDoc.getLength(), s, red);
                                    }
                                }

                                if(i < 4)
                                    uuidDoc.insertString(uuidDoc.getLength(), "-", gray);
                            }
                        }
                    } catch (Exception e) {

                    }
                }

                if(invalidName) {
                    try {
                        nameDoc.remove(0, nameDoc.getLength());

                        if(! users.containsKey(name)) {
                            for(char c : name.toCharArray()) {
                                String s = String.valueOf(c);
                                nameDoc.insertString(nameDoc.getLength(), s.replace(" ", "_"), users.containsKey(name) | ! s.matches("^\\w+$") ? red : yellow);
                            }
                        } else {
                            nameDoc.insertString(nameDoc.getLength(), name.replace(" ", "_"), users.containsKey(name) ? red : yellow);
                        }

                        invalidName = ! (! users.containsKey(name) & ! name.matches("^\\w+$"));
                    } catch (Exception e) {

                    }
                }
            }
        }
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
                ConfigMain.WriteConfig();
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

    public static void setAreaStatus() {
        try {
            ListModel<?> listModel = minecraftAreaList.getModel();

            String source;

            source = minecraftAreas.get(minecraftAreaList.getSelectedValue().toString()).toString();

            JSONObject json = new JSONObject();
            try {
                json = new JSONObject(source);
            } catch (Exception v1) {

            }

            Document doc = minecraftAreaStatus.getDocument();
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

            for(Object o : json.keySet()) {
                //                        des.append(lang.get(o.toString()) == null ? o : lang.get(o.toString())).append(": ").append(lang.get(json.get(o.toString()).toString()) == null ? json.get(o.toString()).toString() : lang.get(json.get(o.toString()).toString())).append("\n");

                try {
                    doc.insertString(doc.getLength(), (lang.get(o.toString()) == null ? o.toString() : lang.get(o.toString())) + ":", red);
                    doc.insertString(doc.getLength(), lang.get(json.get(o.toString()).toString()) == null ? json.get(o.toString()).toString() : lang.get(json.get(o.toString()).toString()), aset_normal);
                    doc.insertString(doc.getLength(), "\n", aset_normal);
                } catch (Exception ignored) {

                }
            }
        } catch (Exception e) {

        }
    }

    public static void setRunningStatus() {
        try {
            String source;

            source = runningMinecraft_Display.get(runningList.getSelectedValue().toString()).toString();

            JSONObject json = new JSONObject();
            try {
                json = new JSONObject(source);
            } catch (Exception v1) {

            }

            if(runningList.getSelectedValue().toString().equals(lang.get("no_running"))) {
                selectedMinecraft.setText(lang.get("no_selected_instance"));
            } else {
                selectedMinecraft.setText(runningList.getSelectedValue().toString());
            }

            try {
                try {
                    String logs = "";
                    try {
                        logs = runningLogs.get(selectedLogDisplay).toString();
                    } catch (Exception e) {

                    }
                    if(! (logs + "\n\n").equals(selectedMinecraftLogs.getText())) {
                        selectedMinecraftLogs.setText(logs + "\n\n");
                        selectedMinecraftLogs.setCaretPosition(selectedMinecraftLogs.getText().length());
                    }

                    if(! runningList.getSelectedValue().toString().equals(selectedLogDisplay)) {
                        String selectedInListLog = runningLogs.get(runningList.getSelectedValue().toString()).toString();
                        runningMinecraftLogs.setText(selectedInListLog + "\n");
                    } else {
                        runningMinecraftLogs.setText(logs + "\n");
                    }
                    runningMinecraftLogs.setCaretPosition(runningMinecraftLogs.getText().length());


                } catch (Exception e) {
                    runningMinecraftLogs.setText("");
                    selectedMinecraftLogs.setText("");
                }
            } catch (OutOfMemoryError oom) {
                Runtime.getRuntime().gc();
                Errors.errors(oom, null, true, "Minecraft Manager", String.format(lang.get("log_too_large"), Runtime.getRuntime().freeMemory(), (runningMinecraftLogs.getText().length() + selectedMinecraftLogs.getText().length()) + "(STD: " + (1024 * 200) + ")"), 800, 600, true, true);
            }
            Document doc = runningMinecraftStatus.getDocument();
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

            if(source.startsWith("error-instance-not-found")) {
                doc.insertString(doc.getLength(), source.substring(24), aset_normal);
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

    public static void setUserStatus() {
        try {
            String source;

            source = users.get(userList.getSelectedValue().toString()).toString();

            JSONObject json = new JSONObject();
            try {
                json = new JSONObject(source);
            } catch (Exception v1) {

            }

            Document statusDoc = userStatus.getDocument();
            Document usingAccountDoc = accountUsing.getDocument();
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

            usingAccountDoc.remove(0, usingAccountDoc.getLength());
            if(user.equals("")) {
                usingAccountDoc.insertString(0, lang.get("no_account_selected"), red);
            } else {
                usingAccountDoc.insertString(usingAccountDoc.getLength(), lang.get("using_account") + ": ", red);
                usingAccountDoc.insertString(usingAccountDoc.getLength(), user + "\n", aset_normal);
                usingAccountDoc.insertString(usingAccountDoc.getLength(), "uuid: ", red);
                usingAccountDoc.insertString(usingAccountDoc.getLength(), uuid, aset_normal);
            }

            statusDoc.remove(0, statusDoc.getLength());

            if(source.startsWith("error-no-account-found")) {
                statusDoc.insertString(statusDoc.getLength(), source.substring(22), aset_normal);
            } else {
                for(Object o : json.keySet()) {
                    //                        des.append(lang.get(o.toString()) == null ? o : lang.get(o.toString())).append(": ").append(lang.get(json.get(o.toString()).toString()) == null ? json.get(o.toString()).toString() : lang.get(json.get(o.toString()).toString())).append("\n");

                    try {
                        statusDoc.insertString(statusDoc.getLength(), (lang.get(o.toString()) == null ? o.toString() : lang.get(o.toString())) + ":", red);
                        statusDoc.insertString(statusDoc.getLength(), lang.get(json.get(o.toString()).toString()) == null ? json.get(o.toString()).toString() : lang.get(json.get(o.toString()).toString()), aset_normal);
                        statusDoc.insertString(statusDoc.getLength(), "\n", aset_normal);
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

    public static void visiblePanel(String panelName) {
        downloadPanel.setVisible(false);
        launchPanel.setVisible(false);
        settingsPanel.setVisible(false);
        javaPanel.setVisible(false);
        runningPanel.setVisible(false);
        minecraftAreaPanel.setVisible(false);
        accountPanel.setVisible(false);
        switch(panelName) {
            case "download" -> downloadPanel.setVisible(true);
            case "launcher" -> launchPanel.setVisible(true);
            case "settings" -> settingsPanel.setVisible(true);
            case "java" -> javaPanel.setVisible(true);
            case "running" -> runningPanel.setVisible(true);
            case "areas" -> minecraftAreaPanel.setVisible(true);
            case "account" -> accountPanel.setVisible(true);
        }
        displaySets.Color();
    }

    public static void visibleMenuButton(int page) {
        switchLauncherPanel.setVisible(false);
        switchDownloadPanel.setVisible(false);
        switchSettingsPanel.setVisible(false);
        switchJavaPanel.setVisible(false);
        switchRunningPanel.setVisible(false);
        switchAccountPanel.setVisible(false);
        switchMinecraftAreaPanel.setVisible(false);
        nextPage.setVisible(false);
        lastPage.setVisible(false);
        switch(page) {
            case 1 -> {
                switchLauncherPanel.setVisible(true);
                switchDownloadPanel.setVisible(true);
                switchSettingsPanel.setVisible(true);
                switchJavaPanel.setVisible(true);
                switchRunningPanel.setVisible(true);
                switchAccountPanel.setVisible(true);
                switchMinecraftAreaPanel.setVisible(true);
                nextPage.setVisible(true);
            }
            case 2 -> {
                lastPage.setVisible(true);
            }
        }
    }

    public static void launchMinecraft(String ver) {
        if(! launching) {
            launching = true;
            launch.setText(lang.get("waiting"));
            new Thread(() -> {
                try {
                    JSONObject info = new JSONObject(minecraftVersions.get(ver).toString());
                    runMinecraft(info.get("path").toString(), ver, ! checkResource, launchType, launchType.equals("client") ? clientVmOption : serverVmOptions, info.get("minecraft_type").toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    launching = false;
                }
                Runtime.getRuntime().gc();
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

    public static void unZip(String zipPath, String descDir) throws IOException {
        unZip(new File(zipPath), descDir);
    }

    public static void unZip(File zipFile, String descDir) throws IOException {
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

        {
            int check = s.indexOf("checkResource@check");
            int ignored = s.indexOf("checkResource@ignored");

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
            boolean javas = s.startsWith("javaPaths@");

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

        {
            boolean areas = s.startsWith("minecraftAreas@");

            if(areas) {
                Community.launcherConf.put("minecraftAreas", s.substring(s.indexOf("@") + 1));
                s = "";
            }
        }

        {
            boolean area = s.startsWith("minecraftArea@");

            if(area) {
                Community.launcherConf.put("minecraftArea", s.substring(s.indexOf("@") + 1));
                selectedAreaName = Community.launcherConf.get("minecraftArea").toString();
                s = "";
            }
        }

        {
            boolean userUsed = s.startsWith("user@");

            if(userUsed) {
                Community.launcherConf.put("user", s.substring(s.indexOf("@") + 1));
                user = Community.launcherConf.get("user").toString();
                s = "";
            }
        }

        {
            boolean userUuid = s.startsWith("userUUID@");

            if(userUuid) {
                Community.launcherConf.put("userUUID", s.substring(s.indexOf("@") + 1));
                uuid = Community.launcherConf.get("userUUID").toString();
                s = "";
            }
        }

        {
            boolean usersList = s.startsWith("users@");

            if(usersList) {
                Community.launcherConf.put("users", s.substring(s.indexOf("@") + 1));
                s = "";
            }
        }

        {
            boolean runType = s.startsWith("runMinecraftType@");

            if(runType) {
                Community.launcherConf.put("runMinecraftType", s.substring(s.indexOf("@") + 1));
                launchType = Community.launcherConf.get("runMinecraftType").toString();
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
                return true;
            downloadingThreadsMap.put(toFile.hashCode(), toFile);
            boolean r = customDownLoad(res, toFile, showProgress, length, launch, download_Name);
            try {
                LinkedHashMap<Object, Integer> v = ((LinkedHashMap<Object, Integer>) downloads.get(download_Name));
                v.put("value", Integer.parseInt(String.valueOf(((LinkedHashMap) downloads.get(download_Name)).get("value"))) + 1);
                downloads.put(download_Name, v);
            } catch (Exception e) {

            }
            downloadingThreadsMap.remove(toFile.hashCode());
            return r;
        }

        public void startOneMinecraftDownload(String type) {
            startOneMinecraftDownload(downloadName.getText(), verList_download.getModel().getElementAt(verList_download.getSelectedIndex()).toString(), false, type);
        }

        public void startOneMinecraftDownload(String download_Name, String downloadTarget, boolean launchPro, String type) {
            startOneMinecraftDownload(download_Name, downloadTarget, launchPro, false, type);
        }

        public void startOneMinecraftDownload(String download_Name, String downloadTarget, boolean launchPro, boolean downloadAndLaunch, String type) {
            long startDownloadTime = System.currentTimeMillis();

            LinkedList<Object> downloadList = new LinkedList<>();
            LinkedList<Object> downloadedList = new LinkedList<>();

            AtomicInteger downloadFilesSize = new AtomicInteger();

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
            status.put("minecraft_type", "vanilla");

            LinkedHashMap<Object, Integer> downloadingMap = new LinkedHashMap<>();
            downloadingMap.put("max", 0);
            downloadingMap.put("value", 0);
            downloads.put(download_Name, downloadingMap);

            String libsPath = gamePath + "libraries/";
            String indexesPath = gamePath + "assets/indexes/";
            String objectsPath = gamePath + "assets/objects/";

            downloadingMinecraft.put(download_Name, status);

            minecraftDownloadVersions = getDownloadVersions("", true);

            if(type.equals("Vanilla")) {
                if(customDownLoad(new JSONObject(minecraftDownloadVersions.get(downloadTarget).toString()).get("url").toString(), versionsPath + download_Name + "/" + download_Name + ".json", false, - 1, "")) {

                    try {
                        FileOutputStream statusWriter = null;
                        if(! launchPro) {
                            statusWriter = new FileOutputStream(versionsPath + download_Name + "/" + download_Name + "_status.json", false);

                            statusWriter.write(status.toString().getBytes());

                            statusWriter.close();
                        }

                        BufferedReader br = new BufferedReader(new FileReader(versionsPath + download_Name + "/" + download_Name + ".json"));

                        StringBuilder json = new StringBuilder();
                        String s;
                        while((s = br.readLine()) != null)
                            json.append(s);

                        JSONObject source = new JSONObject(json.toString());
                        JSONArray lib = new JSONArray(source.get("libraries").toString());
                        JSONObject clientAndServer = new JSONObject(source.get("downloads").toString());

                        if(useMThread) {
                            new Thread(() -> {
                                for(Object o : clientAndServer.keySet())
                                    downloadFilesSize.getAndIncrement();

                                downloadingMap.put("max", downloadFilesSize.get());
                                downloads.put(download_Name, downloadingMap);

                                for(Object o : clientAndServer.keySet()) {
                                    if(! checkResource & launchPro)
                                        break;

                                    if(downloadingMinecraft.get(download_Name) == null)
                                        break;

                                    JSONObject download = new JSONObject(clientAndServer.get(o.toString()).toString());

                                    String res = download.get("url").toString();
                                    downloadList.add(res);
                                    if(new File(versionsPath + download_Name + "/" + o + ".jar").length() != download.getLong("size")) {
                                        new Thread(() -> {
                                            try {
                                                while(true) {
                                                    boolean downloaded = downloadFileByMThread(res, versionsPath + download_Name + "/" + o + ".jar", false, download.getLong("size"), launchPro, download_Name);
                                                    if(downloaded) {
                                                        downloadedList.add(res);
                                                        break;
                                                    }
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }).start();
                                    } else {
                                        try {
                                            downloadedList.add(res);
                                            LinkedHashMap<Object, Integer> v = ((LinkedHashMap<Object, Integer>) downloads.get(download_Name));
                                            v.put("value", Integer.parseInt(String.valueOf(((LinkedHashMap) downloads.get(download_Name)).get("value"))) + 1);
                                            downloads.put(download_Name, v);
                                        } catch (Exception e) {

                                        }
                                    }
                                }
                            }).start();
                        } else {
                            downloadFilesSize.addAndGet(clientAndServer.keySet().size());

                            downloadingMap.put("max", downloadFilesSize.get());
                            downloads.put(download_Name, downloadingMap);

                            for(Object o : clientAndServer.keySet()) {
                                if(! checkResource & launchPro)
                                    break;

                                if(downloadingMinecraft.get(download_Name) == null)
                                    break;

                                JSONObject download = new JSONObject(clientAndServer.get(o.toString()).toString());

                                String res = download.get("url").toString();
                                downloadList.add(res);
                                if(new File(versionsPath + download_Name + "/" + res.substring(res.lastIndexOf("/") + 1 )).length() != download.getLong("size")) {
                                    try {
                                        while(true) {
                                            boolean downloaded = downloadFileByMThread(res, versionsPath + download_Name + "/" + o + ".jar", false, download.getLong("size"), launchPro, download_Name);
                                            if(downloaded) {
                                                downloadedList.add(res);
                                                break;
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    try {
                                        downloadedList.add(res);
                                        LinkedHashMap<Object, Integer> v = ((LinkedHashMap<Object, Integer>) downloads.get(download_Name));
                                        v.put("value", Integer.parseInt(String.valueOf(((LinkedHashMap) downloads.get(download_Name)).get("value"))) + 1);
                                        downloads.put(download_Name, v);
                                    } catch (Exception e) {

                                    }
                                }
                            }
                        }

                        if(useMThread) {
                            new Thread(() -> {
                                for(Object o : lib) {
                                    if(! checkResource & launchPro)
                                        break;

                                    if(downloadingMinecraft.get(download_Name) == null)
                                        break;

                                    JSONObject downloads = new JSONObject(new JSONObject(o.toString()).get("downloads").toString());

                                    JSONObject artifact = new JSONObject();
                                    try {
                                        artifact = new JSONObject(downloads.get("artifact").toString());
                                    } catch (Exception ex) {

                                    }

                                    try {
                                        JSONObject nativeLib = new JSONObject(downloads.get("classifiers").toString());

                                        for(Object ob : nativeLib.keySet())
                                            downloadFilesSize.getAndIncrement();

                                        downloadingMap.put("max", downloadFilesSize.get());
                                        downloads.put(download_Name, downloadingMap);

                                        for(Object nativeObj : nativeLib.keySet()) {

                                            JSONObject download = new JSONObject(nativeLib.get(nativeObj.toString()).toString());

                                            String res = download.get("url").toString();
                                            downloadList.add(res);
                                            if(new File(libsPath + download.get("path")).length() != download.getLong("size")) {
                                                new Thread(() -> {
                                                    try {
                                                        while(true) {
                                                            boolean downloaded = downloadFileByMThread(res, libsPath + download.get("path"), false, download.getLong("size"), launchPro, download_Name);
                                                            if(downloaded) {
                                                                downloadedList.add(res);
                                                                break;
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                }).start();
                                            } else {
                                                try {
                                                    downloadedList.add(res);
                                                    LinkedHashMap<Object, Integer> v = ((LinkedHashMap<Object, Integer>) downloads.get(download_Name));
                                                    v.put("value", Integer.parseInt(String.valueOf(((LinkedHashMap) downloads.get(download_Name)).get("value"))) + 1);
                                                    downloads.put(download_Name, v);
                                                } catch (Exception e) {

                                                }
                                            }
                                        }
                                    } catch (Exception ex) {
                                        downloadFilesSize.getAndIncrement();

                                        String res = artifact.get("url").toString();
                                        downloadList.add(res);
                                        if(! (new File(libsPath + artifact.get("path")).length() == artifact.getLong("size"))) {
                                            JSONObject finalArtifact = artifact;

                                            new Thread(() -> {
                                                try {
                                                    while(true) {
                                                        boolean downloaded = downloadFileByMThread(res, libsPath + finalArtifact.get("path"), false, finalArtifact.getLong("size"), launchPro, download_Name);
                                                        if(downloaded) {
                                                            downloadedList.add(res);
                                                            break;
                                                        }
                                                    }
                                                } catch (Exception e) {

                                                }
                                            }).start();
                                        } else {
                                            try {
                                                downloadedList.add(artifact.get("url").toString());
                                                LinkedHashMap<Object, Integer> v = ((LinkedHashMap<Object, Integer>) downloads.get(download_Name));
                                                v.put("value", Integer.parseInt(String.valueOf(((LinkedHashMap) downloads.get(download_Name)).get("value"))) + 1);
                                                downloads.put(download_Name, v);
                                            } catch (Exception e) {

                                            }
                                        }
                                    }
                                }
                            }).start();
                        } else {
                            for(Object o : lib) {
                                if(! checkResource & launchPro)
                                    break;

                                if(downloadingMinecraft.get(download_Name) == null)
                                    break;

                                JSONObject downloads = new JSONObject(new JSONObject(o.toString()).get("downloads").toString());

                                JSONObject artifact = new JSONObject();
                                try {
                                    artifact = new JSONObject(downloads.get("artifact").toString());
                                } catch (Exception ex) {

                                }

                                try {
                                    JSONObject nativeLib = new JSONObject(downloads.get("classifiers").toString());

                                    for(Object ob : nativeLib.keySet())
                                        downloadFilesSize.getAndIncrement();

                                    downloadingMap.put("max", downloadFilesSize.get());
                                    downloads.put(download_Name, downloadingMap);

                                    for(Object nativeObj : nativeLib.keySet()) {

                                        JSONObject download = new JSONObject(nativeLib.get(nativeObj.toString()).toString());

                                        String res = download.get("url").toString();
                                        downloadList.add(res);
                                        if(new File(libsPath + download.get("path")).length() != download.getLong("size")) {
                                            try {
                                                while(true) {
                                                    boolean downloaded = downloadFileByMThread(res, libsPath + download.get("path"), false, download.getLong("size"), launchPro, download_Name);
                                                    if(downloaded) {
                                                        downloadedList.add(res);
                                                        break;
                                                    }
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            try {
                                                downloadedList.add(res);
                                                LinkedHashMap<Object, Integer> v = ((LinkedHashMap<Object, Integer>) downloads.get(download_Name));
                                                v.put("value", Integer.parseInt(String.valueOf(((LinkedHashMap) downloads.get(download_Name)).get("value"))) + 1);
                                                downloads.put(download_Name, v);
                                            } catch (Exception e) {

                                            }
                                        }
                                    }
                                } catch (Exception ex) {
                                    downloadFilesSize.getAndIncrement();

                                    String res = artifact.get("url").toString();
                                    downloadList.add(res);
                                    if(! (new File(libsPath + artifact.get("path")).length() == artifact.getLong("size"))) {
                                        try {
                                            while(true) {
                                                boolean downloaded = downloadFileByMThread(res, libsPath + artifact.get("path"), false, artifact.getLong("size"), launchPro, download_Name);
                                                if(downloaded) {
                                                    downloadedList.add(res);
                                                    break;
                                                }
                                            }
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        try {
                                            downloadedList.add(artifact.get("url").toString());
                                            LinkedHashMap<Object, Integer> v = ((LinkedHashMap<Object, Integer>) downloads.get(download_Name));
                                            v.put("value", Integer.parseInt(String.valueOf(((LinkedHashMap) downloads.get(download_Name)).get("value"))) + 1);
                                            downloads.put(download_Name, v);
                                        } catch (Exception e) {

                                        }
                                    }
                                }
                            }
                        }

                        JSONObject assetIndex = new JSONObject(source.get("assetIndex").toString());
                        downloadList.add(assetIndex.get("url").toString());
                        downloadFilesSize.incrementAndGet();
                        while(true) {
                            try {
                                if(customDownLoad(assetIndex.get("url").toString(), indexesPath + assetIndex.get("id") + ".json", false, assetIndex.getInt("size"), download_Name)) {
                                    downloadedList.add(assetIndex.get("url").toString());
                                    break;
                                }
                            } catch (Exception e) {
                                downloadedList.add(assetIndex.get("url").toString());
                            }
                        }

                        json = new StringBuilder();
                        br = new BufferedReader(new FileReader(indexesPath + assetIndex.get("id") + ".json"));
                        while((s = br.readLine()) != null)
                            json.append(s);

                        JSONObject assets = new JSONObject(json.toString());
                        assets = new JSONObject(assets.get("objects").toString());

                        if(useMThread) {
                            JSONObject finalAssets = assets;
                            new Thread(() -> {
                                for(Object o : finalAssets.keySet())
                                    downloadFilesSize.getAndIncrement();

                                downloadingMap.put("max", downloadFilesSize.get());
                                downloads.put(download_Name, downloadingMap);

                                for(Object o : finalAssets.keySet()) {
                                    if(! checkResource & launchPro)
                                        break;

                                    if(downloadingMinecraft.get(download_Name) == null)
                                        break;

                                    JSONObject asset = new JSONObject(finalAssets.get(o.toString()).toString());

                                    String hash = asset.get("hash").toString();
                                    File file = new File(objectsPath + hash.substring(0, 2) + "/" + hash);

                                    String res = "http://resources.download.minecraft.net/" + hash.substring(0, 2) + "/" + hash;
                                    downloadList.add(res);
                                    if(file.length() != asset.getLong("size")) {
                                        new Thread(() -> {
                                            try {
                                                while(true) {
                                                    boolean downloaded = downloadFileByMThread(res, objectsPath + hash.substring(0, 2) + "/" + hash, false, asset.getLong("size"), launchPro, download_Name);
                                                    if(downloaded) {
                                                        downloadedList.add(res);
                                                        break;
                                                    }
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }).start();

                                        try {
                                            Thread.sleep(3);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        try {
                                            downloadedList.add(res);
                                            LinkedHashMap<Object, Integer> v = ((LinkedHashMap<Object, Integer>) downloads.get(download_Name));
                                            v.put("value", Integer.parseInt(String.valueOf(((LinkedHashMap) downloads.get(download_Name)).get("value"))) + 1);
                                            downloads.put(download_Name, v);
                                        } catch (Exception e) {

                                        }
                                    }
                                }

                            }).start();
                        } else {
                            downloadFilesSize.addAndGet(assets.length());

                            downloadingMap.put("max", downloadFilesSize.get());
                            downloads.put(download_Name, downloadingMap);

                            for(Object o : assets.keySet()) {
                                if(! checkResource & launchPro)
                                    break;

                                if(downloadingMinecraft.get(download_Name) == null)
                                    break;

                                JSONObject asset = new JSONObject(assets.get(o.toString()).toString());

                                String hash = asset.get("hash").toString();
                                File file = new File(objectsPath + hash.substring(0, 2) + "/" + hash);

                                String res = "http://resources.download.minecraft.net/" + hash.substring(0, 2) + "/" + hash;
                                downloadList.add(res);
                                if(file.length() != asset.getLong("size")) {
                                    try {
                                        while(true) {
                                            if(downloadingThreadsMap.size() < 200) {
                                                boolean downloaded = downloadFileByMThread(res, objectsPath + hash.substring(0, 2) + "/" + hash, false, asset.getLong("size"), launchPro, download_Name);
                                                if(downloaded) {
                                                    downloadedList.add(res);
                                                    break;
                                                }
                                            }

                                            Thread.sleep(10);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    try {
                                        downloadedList.add(res);
                                        LinkedHashMap<Object, Integer> v = ((LinkedHashMap<Object, Integer>) downloads.get(download_Name));
                                        v.put("value", Integer.parseInt(String.valueOf(((LinkedHashMap) downloads.get(download_Name)).get("value"))) + 1);
                                        downloads.put(download_Name, v);
                                    } catch (Exception e) {

                                    }
                                }
                            }
                        }

                        if(useMThread) {
                            while(downloadList.size() != downloadedList.size()) {
                                if(downloadingMinecraft.get(download_Name) == null)
                                    break;

                                try {
                                    //                            status.put("progress", downloadedList.size() + "/" + downloadList.size());
                                    status.put("progress", downloadedList.size() + " / " + downloadFilesSize.get());

                                    if(launchPro) {
                                        loadingProgress.setMaximum(downloadFilesSize.get());
                                        loadingProgress.setValue(downloadedList.size());
                                    }

                                    downloadingMinecraft.put(download_Name, status);

                                    statusWriter = new FileOutputStream(versionsPath + download_Name + "/" + download_Name + "_status.json", false);

                                    statusWriter.write(status.toString().getBytes());

                                    statusWriter.close();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                                //                        try {
                                //                            String progress = new JSONObject(downloadingMinecraft.get(download_Name).toString()).get("progress").toString();
                                //                            if(Integer.parseInt(progress.substring(progress.indexOf("/") + 1)) - Integer.parseInt(progress.substring(0, progress.indexOf("/"))) < 80)
                                //                                break;
                                //                        } catch (Exception e) {
                                //
                                //                        }

                                Runtime.getRuntime().gc();

                                try {
                                    Thread.sleep(500);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        if(! (downloadingMinecraft.get(download_Name) == null)) {
                            try {
                                status.put("progress", "1/1");
                                status.put("status", "ready");

                                downloadingMinecraft.put(download_Name, status);

                                statusWriter = new FileOutputStream(versionsPath + download_Name + "/" + download_Name + "_status.json", false);

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

                        status.put("status", "ready");

                        JSONObject mcVer = new JSONObject(minecraftVersions.get(download_Name).toString());
                        mcVer.put("progress", "1/1");
                        mcVer.put("status", "ready");

                        minecraftVersions.put(download_Name, mcVer);

                        if(! (downloadingMinecraft.get(download_Name) == null)) {
                            downloadingMinecraft.put(download_Name, status);
                        }

                        if(! launchPro) {
                            statusWriter = new FileOutputStream(versionsPath + download_Name + "/" + download_Name + "_status.json", false);

                            statusWriter.write(status.toString().getBytes());
                        }

                        statusWriter.close();

                        if(downloadAndLaunch) {
                            launchPanel.setVisible(true);
                            downloadPanel.setVisible(false);
                        }
                    } catch (Exception e) {

                    }
                } else {
                    downloadStatus.setText("may internet error");
                }
            }

            Runtime.getRuntime().gc();
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

    public static class forceFound {
        public static String javas = "";

        public static String getJavas() {
            return javas;
        }

        public static void foundJava(File f, int foundPage) {
            try {
                if(foundPage != 0) {
                    for(File file : Objects.requireNonNull(f.listFiles())) {
                        if(file.isFile()) {
                            if(file.getName().equals("java.exe") || file.getName().equals("javaw.exe"))
                                javas += file.getAbsolutePath() + "\n";
                        } else if(file.isDirectory()) {
                            foundJava(file, foundPage - 1);
                        }
                    }
                }
            } catch (Exception e) {

            }
        }
    }
}
