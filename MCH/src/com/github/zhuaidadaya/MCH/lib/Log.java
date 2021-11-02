package com.github.zhuaidadaya.MCH.lib;

import com.github.zhuaidadaya.MCH.Config.ConfigMain;
import com.github.zhuaidadaya.MCH.Community;
import com.github.zhuaidadaya.MCH.Times.timeType;
import com.github.zhuaidadaya.MCH.Times.times;
import com.github.zhuaidadaya.MCH.UI.loadingWindow;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Log {
    public static boolean defAppend = true;
    public static Charset defCharset = StandardCharsets.UTF_8;
    public static File defRunPath = new File(ConfigMain.runLogsPath);
    public static File defErrPath = new File(ConfigMain.errLogsPath);

    public static void outLog(Object log, boolean WARN) {
        writeLog(null, log, WARN, null);
    }

    public static void writeLog(File logFile, boolean append, Charset charset, Object log, boolean WARN) {
        writeLog(logFile == null ? null : logFile.getAbsolutePath(), append, charset, log, WARN);
    }

    public static void writeLog(String logFile, boolean append, Charset charset, Object log, boolean WARN) {
        try {
            if(logFile != null) {
                logFile = logFile.replace("\\", "/");
                File logF = new File(logFile);
                try {
                    if(! logF.exists()) {
                        new File(logFile.substring(0, logFile.lastIndexOf("/"))).mkdirs();
                        logF.createNewFile();
                    }
                } catch (Exception e) {

                }
            }
            writeLog(logFile == null ? null : new BufferedWriter(new FileWriter(logFile, charset, append)), log, WARN, null);
        } catch (Exception e) {

        }
    }

    public static void writeLog(BufferedWriter logger, Object log, boolean WARN, Object exID) {
        try {
            log = exID == null ? times.getTime(timeType.LONG_LOG) + log : times.getTime(timeType.LONG_LOG) + "[" + exID + "] " + log;

            if (WARN)
                System.err.println(log);
            else
                System.out.println(log);

            if(logger != null) {
                logger.write(log + "\n");
                logger.close();
            }
        } catch (Exception e) {

        }
    }

    public static void writeLog(Object log, String type) {
        loadingWindow.loading.setText(log.toString() + "\n" + loadingWindow.loading.getText());

        if (type.equals("error"))
            writeLog(Community.saveErrorLog ? defErrPath : null, defAppend, defCharset, log, true);
        else if (type.equals("log"))
            writeLog(Community.saveRunLog ? defRunPath : null, defAppend, defCharset, log, false);
    }

    public static void writeLog(Object log) {
        writeLog(log, "log");
    }

    public static void writeErr(Object log) {
        writeLog(log, "error");
    }

    public static void compress(String srcPath, String dstPath) throws IOException {
        File srcFile = new File(srcPath);
        File dstFile = new File(dstPath);

        BufferedOutputStream out = null;
        ZipOutputStream zipOut = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(dstFile));
            CheckedOutputStream cos = new CheckedOutputStream(out, new CRC32());
            zipOut = new ZipOutputStream(cos);
            if (srcFile.isDirectory())
                for (File f : Objects.requireNonNull(srcFile.listFiles())) {
                    compress(f, zipOut, "");
                }
            else
                compress(srcFile, zipOut, "");
        } finally {
            if (null != zipOut) {
                zipOut.close();
                out = null;
            }

            if (null != out) {
                out.close();
            }
        }
    }

    private static void compress(File file, ZipOutputStream zipOut, String baseDir) throws IOException {
        if (file.isDirectory()) {
            compressDirectory(file, zipOut, baseDir);
        } else {
            compressFile(file, zipOut, baseDir);
        }
    }

    private static void compressDirectory(File dir, ZipOutputStream zipOut, String baseDir) throws IOException {
        File[] files = dir.listFiles();
        for (File file : files) {
            compress(file, zipOut, baseDir + dir.getName() + "/");
        }
    }

    private static void compressFile(File file, ZipOutputStream zipOut, String baseDir) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            ZipEntry entry = new ZipEntry(baseDir + file.getName());
            zipOut.putNextEntry(entry);
            BufferedOutputStream out = new BufferedOutputStream(zipOut);
            byte[] b = new byte[1024 * 1024 * 12];
            int count;
            while ((count = bis.read(b, 0, b.length)) != -1) {
                out.write(b, 0, count);
            }
        }
    }

    public static void packetLog(File path, String log) {
        try {
            for (File f : Objects.requireNonNull(path.listFiles())) {
                if (f.isFile()) {
                    if (f.getName().equals("latest.log")) {

                        String fp = f.getPath().replace("\\", "/");

                        if (f.length() > 100) {

                            String pack_for = (fp.substring(fp.substring(0, fp.indexOf("/") + 1).length()));

                            if (log != null)
                                Log.writeLog("[Log Packer/INFO] Pack latest.log for <" + pack_for.substring(0, pack_for.lastIndexOf("/")) + ">");

                            try {
                                if (!new File(fp.substring(0, fp.lastIndexOf("/")) + "/" + times.getTime(timeType.AS_SECOND) + ".log.zip").isFile()) {
                                    String name = fp.substring(0, fp.lastIndexOf("/")) + "/" + times.getTime(timeType.AS_SECOND) + ".log";
                                    fileToZip(f, name + ".zip", new File(name).getName());

                                    f.delete();
                                    f.createNewFile();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    packetLog(f, log);
                }
            }
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        packet(new File("C:\\Users\\server\\MinecraftCommandHelper\\history.txt"),true);
    }

    public static void packet(File f,boolean log) {
        try {
            String fp = f.getPath().replace("\\", "/");

            if (f.length() > 100) {

                String pack_for = (fp.substring(fp.substring(0, fp.indexOf("/") + 1).length()));

                if (log)
                    Log.writeLog("[Log Packer/INFO] Pack latest.log for <" + pack_for.substring(0, pack_for.lastIndexOf("/")) + ">");

                try {
                    if (!new File(fp.substring(0, fp.lastIndexOf("/")) + "/" + times.getTime(timeType.AS_SECOND) + ".log.zip").isFile()) {
                        String name = fp.substring(0, fp.lastIndexOf("/")) + "/" + times.getTime(timeType.AS_SECOND) + ".log";
                        fileToZip(f, name + ".zip", new File(name).getName());

                        f.delete();
                        f.createNewFile();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
        }

    }

    public static void packetLog(File path, String zipName, String log) {
        for (File f : Objects.requireNonNull(path.listFiles())) {
            if (f.isFile()) {
                if (f.getName().equals("latest.log")) {

                    String fp = f.getPath().replace("\\", "/");

                    if (f.length() > 100) {

                        String pack_for = (fp.substring(fp.substring(0, fp.indexOf("/") + 1).length()));

                        if (log != null)
                            Log.writeLog("[Log Packer/INFO] Pack latest.log for <" + pack_for.substring(0, pack_for.lastIndexOf("/")) + ">");

                        try {
                            if (!new File(fp.substring(0, fp.lastIndexOf("/")) + "/" + zipName + ".log.zip").isFile()) {
                                String name = fp.substring(0, fp.lastIndexOf("/")) + "/" + zipName + ".log";
                                fileToZip(f, name + ".zip", new File(name).getName());

                                f.delete();
                                f.createNewFile();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                packetLog(f, log);
            }
        }
    }

    public static void fileToZip(File sourceFilePath, String fileOutPath, String inZipFileName) {
        fileToZip(sourceFilePath.getPath(), fileOutPath, inZipFileName);
    }

    public static void fileToZip(String sourceFilePath, String fileOutPath, String inZipFileName) {
        File sourceFile = new File(sourceFilePath);
        byte[] b = new byte[1024 * 16];

        if (!sourceFile.isDirectory()) {
            BufferedInputStream sourceReader;
            BufferedInputStream bis = null;
            ZipOutputStream zos = null;

            if (sourceFile.exists()) {
                try {
                    zos = new ZipOutputStream(new BufferedOutputStream(new BufferedOutputStream(new FileOutputStream(fileOutPath))));
                    zos.putNextEntry(new ZipEntry(inZipFileName));
                    sourceReader = new BufferedInputStream(new FileInputStream(sourceFile));
                    bis = new BufferedInputStream(sourceReader, b.length);
                    int read;
                    while ((read = bis.read(b)) != -1) {
                        zos.write(b, 0, read);
                    }
                } catch (Exception ignored) {
                } finally {
                    try {
                        if (null != bis) {
                            bis.close();
                        }
                        if (null != zos) {
                            zos.close();
                        }
                    } catch (IOException ignored) {

                    }
                }
            }
        }
    }
}
