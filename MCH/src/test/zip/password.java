package test.zip;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipException;

public class password extends Thread{
    public static boolean stopThreads = false;

    static BigDecimal number = BigDecimal.ZERO;

    public static void main(String[] args) {
        new password().start();
    }

    @Override
    public void run() {
        stopThreads = false;
        try {
            BigDecimal pass = Unzip4j1("G:\\Code-Java\\MCH\\test.zip",null, null, "1");
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
    // 解压方法2
    public static BigDecimal Unzip4j1 (String zipFile, BigDecimal passwordStart, BigDecimal passwordStop,String thread) throws ZipException {
        BigDecimal i = BigDecimal.ZERO;
        long startTime = System.currentTimeMillis();
        List<FileHeader> fileHeaderList;
        ZipFile zipFile2;
        zipFile2 = new ZipFile(zipFile);
        //设置编码格式
        zipFile2.setCharset(StandardCharsets.UTF_8);
        if (!zipFile2.isValidZipFile()) {
            throw new ZipException("文件不合法或不存在" + zipFile);
        }
        //检查是否需要密码
        //        checkEncrypted(zipFile2);
        fileHeaderList = null;
        while (true) {
            System.out.println("in Thread" + thread + " try:" + passwordStart);

            try {
                fileHeaderList = zipFile2.getFileHeaders();
            } catch (net.lingala.zip4j.exception.ZipException e) {
                e.printStackTrace();
            }
            assert fileHeaderList != null;

            for (FileHeader fileHeader : fileHeaderList) {
                try {
                    if (!fileHeader.isDirectory()) {
                        unzip(fileHeaderList, zipFile, startTime,passwordStart.toString());
                    }
                    zipFile2.extractFile(fileHeader, "G:\\zip\\test");
                } catch (Exception e) {
                    passwordStart = passwordStart.add(BigDecimal.ONE);
                    checkEncrypted(zipFile2, passwordStart.toString());
                    break;
                }
            }

            i = i.add(BigDecimal.ONE);
            if (i.equals(passwordStop)) {
                break;
            }

            if (stopThreads) {
                break;
            }
        }

        return passwordStart;
    }

    public static void unzip (List<FileHeader> fileHeaderList, String zipFile, long startTime,String password) {
        stopThreads = true;

        ZipFile zipFile2 = new ZipFile(zipFile);
        zipFile2.setPassword(password.toCharArray());

        try {
            fileHeaderList = zipFile2.getFileHeaders();
        } catch (net.lingala.zip4j.exception.ZipException e) {
            e.printStackTrace();
        }

        for (FileHeader fileHeader : fileHeaderList) {
            try {
                zipFile2.extractFile(fileHeader, "G:\\zip\\test");
                System.out.println(fileHeader);
            } catch (net.lingala.zip4j.exception.ZipException e) {
                e.printStackTrace();
            }
        }
        System.out.println("解压成功！");
        long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime) / 1000 + "s");
        System.out.println("密码: " + password);
        System.exit(0);
    }

    //检测密码
    private static void checkEncrypted (ZipFile zip, String pass) throws ZipException {
        try {
            if (zip.isEncrypted()) {
                char[] password = pass.toCharArray();
                zip.setPassword(password);
            }
        } catch (net.lingala.zip4j.exception.ZipException e) {
            e.printStackTrace();
        }
    }
}
