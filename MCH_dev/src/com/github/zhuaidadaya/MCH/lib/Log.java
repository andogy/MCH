package com.github.zhuaidadaya.MCH.lib;

import com.github.zhuaidadaya.MCH.Command.Config;
import com.github.zhuaidadaya.MCH.Times.timeType;
import com.github.zhuaidadaya.MCH.Times.times;

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
    public static File defRunPath = new File(Config.runLogsPath);
    public static File defErrPath = new File(Config.errLogsPath);

    public static void writeLog(File logFile, boolean append, Charset charset, Object log, boolean WARN) {
        writeLog(logFile.getAbsolutePath(), append, charset, log, WARN);
    }

    public static void writeLog(String logFile, boolean append, Charset charset, Object log, boolean WARN) {
        try {
            logFile = logFile.replace("\\", "/");
            File logF = new File(logFile);
            if(! logF.exists()) {
                new File(logFile.substring(0, logFile.lastIndexOf("/"))).mkdirs();
                logF.createNewFile();
            }
            writeLog(new BufferedWriter(new FileWriter(logFile, charset, append)), log, WARN, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLog(BufferedWriter logger, Object log, boolean WARN, Object exID) {
        try {
            if(exID == null)
                log = times.getTime(timeType.LONG_LOG) + log;
            else
                log = times.getTime(timeType.LONG_LOG) + "[" + exID + "] " + log;

            if(WARN)
                System.err.println(log);
            else
                System.out.println(log);

            logger.write(log + "\n");
            logger.close();
        } catch (Exception e) {

        }
    }

    public static void writeLog(Object log) {
        writeLog(defRunPath,defAppend,defCharset,log,false);
    }

    public static void writeErr(Object log) {
        writeLog(defErrPath,defAppend,defCharset,log,true);
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
            if(srcFile.isDirectory())
                for(File f : Objects.requireNonNull(srcFile.listFiles())) {
                    compress(f, zipOut, "");
                }
            else
                compress(srcFile, zipOut, "");
        } finally {
            if(null != zipOut) {
                zipOut.close();
                out = null;
            }

            if(null != out) {
                out.close();
            }
        }
    }

    private static void compress(File file, ZipOutputStream zipOut, String baseDir) throws IOException {
        if(file.isDirectory()) {
            compressDirectory(file, zipOut, baseDir);
        } else {
            compressFile(file, zipOut, baseDir);
        }
    }

    private static void compressDirectory(File dir, ZipOutputStream zipOut, String baseDir) throws IOException {
        File[] files = dir.listFiles();
        for(File file : files) {
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
            while((count = bis.read(b, 0, b.length)) != - 1) {
                out.write(b, 0, count);
            }
        }
    }

    public static void packet(File path, String oldDay, String log) {
        for(File f : Objects.requireNonNull(path.listFiles())) {
            if(f.isFile()) {
                if(f.getName().equals("latest.log")) {

                    if(! oldDay.equals(times.getTime(timeType.AS_DAY))) {
                        String fp = f.getPath().replace("\\", "/");
                        File pack;
                        String zipF = fp.substring(0, fp.lastIndexOf("/")) + "/" + oldDay + ".log.zip";
                        pack = new File(zipF);

                        if(f.length() > 100) {

                            String pack_for = (fp.substring(fp.substring(0, fp.indexOf("/") + 1).length()));

                            if(log != null)
                                Log.writeLog(log, true, StandardCharsets.UTF_8, "[Log Packer/INFO] Pack latest.log for " + pack_for.substring(0, pack_for.lastIndexOf("/")), false);
                            if(pack.isFile()) {
                                try {
                                    if(! new File(fp.substring(0, fp.lastIndexOf("/")) + "/" + times.getTime(timeType.AS_MINUTE) + ".log.zip").isFile()) {
                                        String name = fp.substring(0, fp.lastIndexOf("/")) + "/" + times.getTime(timeType.AS_MINUTE) + ".log";
                                        fileToZip(f, name + ".zip", new File(name).getName());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    if(! new File(zipF).isFile()) {
                                        String name = fp.substring(0, fp.lastIndexOf("/")) + "//" + oldDay + ".log";
                                        fileToZip(f, name + ".zip", new File(name).getName());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                new File((fp.substring(0, f.getPath().lastIndexOf("/"))) + "/latest/latest.log").delete();
                                new File((fp.substring(0, f.getPath().lastIndexOf("/"))) + "/latest/latest.log").createNewFile();
                            } catch (Exception e) {

                            }
                        }
                    }

                    f.delete();
                    try {
                        f.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                packet(f, oldDay, log);
            }
        }
    }

    public static void fileToZip(File sourceFilePath, String fileOutPath, String inZipFileName) {
        fileToZip(sourceFilePath.getPath(), fileOutPath, inZipFileName);
    }

    public static void fileToZip(String sourceFilePath, String fileOutPath, String inZipFileName) {
        File sourceFile = new File(sourceFilePath);
        byte[] b = new byte[1024 * 1024 * 12];

        if(! sourceFile.isDirectory()) {
            BufferedInputStream sourceReader;
            BufferedInputStream bis = null;
            ZipOutputStream zos = null;

            if(sourceFile.exists()) {
                try {
                    zos = new ZipOutputStream(new BufferedOutputStream(new BufferedOutputStream(new FileOutputStream(fileOutPath))));
                    zos.putNextEntry(new ZipEntry(inZipFileName));
                    sourceReader = new BufferedInputStream(new FileInputStream(sourceFile));
                    bis = new BufferedInputStream(sourceReader, b.length);
                    int read;
                    while((read = bis.read(b)) != - 1) {
                        zos.write(b, 0, read);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } finally {
                    try {
                        if(null != bis) {
                            bis.close();
                        }
                        if(null != zos) {
                            zos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
