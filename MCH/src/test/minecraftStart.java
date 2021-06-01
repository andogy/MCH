package test;

import project.Community.UI.ExtraUI;
import project.Community.lib.filesOperator;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class minecraftStart extends Thread {

    public static void startMc(String LIB_PATH) {

        filesOperator.ReadFiles(new File(LIB_PATH).listFiles(), "", true);

        {
            String java = System.getProperty("java.home");
            String jvmArgs = "-XX:+UseG1GC -XX:-UseAdaptiveSizePolicy -XX:-OmitStackTraceInFastThrow -Dfml.ignoreInvalidMinecraftCertificates=True -Dfml.ignorePatchDiscrepancies=True -XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump -Dos.name=\"Windows 10\" -Dos.version=10.0";
            String jLib = " -Djava.library.path=\"C:\\normal\\Minecraft\\.minecraft\\versions\\1.16.4\\1.16.4-natives\"";
            String jName = " -Dminecraft.launcher.brand=";
            String jVer = "-Dminecraft.launcher.version=";
            String jCP = " -cp";
            String jMemoryMax = " -Xmx";

            String mUserName = " --username ";
            String mVer = " --version ";
            String mGameDir = " --gameDir ";
            String mAssetsDir = " --assetsDir ";
            String mAssets = " --assetIndex ";
            String mUUID = " --uuid ";
            String mAccessToken = " --accessToken ";
            String mUserProperties = " --userProperties {} ";
            String mUsertype = " --userType ";
            String mWidth = " --width ";
            String mHeight = " --height ";

            String path = """
                     "$LIB_PATH$com\\mojang\\patchy\\1.1\\patchy-1.1.jar
                    $LIB_PATH$oshi-project\\oshi-core\\1.1\\oshi-core-1.1.jar
                    $LIB_PATH$net\\java\\dev\\jna\\jna\\4.4.0\\jna-4.4.0.jar
                    $LIB_PATH$net\\java\\dev\\jna\\platform\\3.4.0\\platform-3.4.0.jar
                    $LIB_PATH$com\\ibm\\icu\\icu4j\\66.1\\icu4j-66.1.jar
                    $LIB_PATH$com\\mojang\\javabridge\\1.0.22\\javabridge-1.0.22.jar
                    $LIB_PATH$net\\sf\\jopt-simple\\jopt-simple\\5.0.3\\jopt-simple-5.0.3.jar
                    $LIB_PATH$io\\netty\\netty-all\\4.1.25.Final\\netty-all-4.1.25.Final.jar
                    $LIB_PATH$com\\google\\guava\\guava\\21.0\\guava-21.0.jar
                    $LIB_PATH$org\\apache\\commons\\commons-lang3\\3.5\\commons-lang3-3.5.jar
                    $LIB_PATH$commons-io\\commons-io\\2.5\\commons-io-2.5.jar
                    $LIB_PATH$commons-codec\\commons-codec\\1.10\\commons-codec-1.10.jar
                    $LIB_PATH$net\\java\\jinput\\jinput\\2.0.5\\jinput-2.0.5.jar
                    $LIB_PATH$net\\java\\jutils\\jutils\\1.0.0\\jutils-1.0.0.jar
                    $LIB_PATH$com\\mojang\\brigadier\\1.0.17\\brigadier-1.0.17.jar
                    $LIB_PATH$com\\mojang\\datafixerupper\\4.0.26\\datafixerupper-4.0.26.jar
                    $LIB_PATH$com\\google\\code\\gson\\gson\\2.8.0\\gson-2.8.0.jar
                    $LIB_PATH$com\\mojang\\authlib\\2.0.27\\authlib-2.0.27.jar
                    $LIB_PATH$org\\apache\\commons\\commons-compress\\1.8.1\\commons-compress-1.8.1.jar
                    $LIB_PATH$org\\apache\\httpcomponents\\httpclient\\4.3.3\\httpclient-4.3.3.jar
                    $LIB_PATH$commons-logging\\commons-logging\\1.1.3\\commons-logging-1.1.3.jar
                    $LIB_PATH$org\\apache\\httpcomponents\\httpcore\\4.3.2\\httpcore-4.3.2.jar
                    $LIB_PATH$it\\unimi\\dsi\\fastutil\\8.2.1\\fastutil-8.2.1.jar
                    $LIB_PATH$org\\apache\\logging\\log4j\\log4j-api\\2.8.1\\log4j-api-2.8.1.jar
                    $LIB_PATH$org\\apache\\logging\\log4j\\log4j-core\\2.8.1\\log4j-core-2.8.1.jar
                    $LIB_PATH$org\\lwjgl\\lwjgl\\3.2.2\\lwjgl-3.2.2.jar
                    $LIB_PATH$org\\lwjgl\\lwjgl-jemalloc\\3.2.2\\lwjgl-jemalloc-3.2.2.jar
                    $LIB_PATH$org\\lwjgl\\lwjgl-openal\\3.2.2\\lwjgl-openal-3.2.2.jar
                    $LIB_PATH$org\\lwjgl\\lwjgl-opengl\\3.2.2\\lwjgl-opengl-3.2.2.jar
                    $LIB_PATH$org\\lwjgl\\lwjgl-glfw\\3.2.2\\lwjgl-glfw-3.2.2.jar
                    $LIB_PATH$org\\lwjgl\\lwjgl-stb\\3.2.2\\lwjgl-stb-3.2.2.jar
                    $LIB_PATH$org\\lwjgl\\lwjgl-tinyfd\\3.2.2\\lwjgl-tinyfd-3.2.2.jar
                    $LIB_PATH$com\\mojang\\text2speech\\1.11.3\\text2speech-1.11.3.jar
                    $GAME_PATH$1.16.5-Fabric 0.11.3-OptiFine_G8_pre12.jar"
                    """;

            String classPaths = path.replace("$LIB_PATH$", LIB_PATH).replace("\n", ";").replace("$GAME_PATH$", "C:\\normal\\Minecraft\\.minecraft\\versions\\1.16.5-Fabric 0.11.3-OptiFine_G8_pre12\\");
            String outClassPath = classPaths;
            System.out.println(outClassPath);
            while (true) {
                try {
                    System.out.println(outClassPath.substring(0, outClassPath.indexOf(";")));
                    outClassPath = outClassPath.substring(outClassPath.indexOf(";") + 1);
                } catch (Exception e) {
                    break;
                }
            }
            String mainClass = " net.minecraft.client.main.Main";
            classPaths = classPaths.replace("\n", "");
            String name = "MCH";
            String launcherVer = "0.0.1";
            String username = "Zhuaidadaya";
            String displayVer = "\"TestLauncher 0.0.1\"";
            String gameDir = "G:\\Code-Java\\MCH\\MCH\\src\\project\\resources\\mc_testDir";
            String assetsDir = "C:\\normal\\Minecraft\\.minecraft\\assets";
            String asset = "1.16";
            String uuid = "00000000000000B7CCBB111C683F0DD";
            String accessToken = "000000000000000B7CCBB111C683F0DD";
            String userType = "Legacy";
            int width = 500;
            int height = 300;
            String memory = "8G";

            String start = "\"" + java + "\\bin\\javaw.exe\" " + jvmArgs + jLib + jName + name + jVer + displayVer + jCP + classPaths + " -Xmn256M" + jMemoryMax + memory + mainClass + mUserName + username + mVer + launcherVer + mGameDir + gameDir + mAssetsDir + assetsDir + mAssets + asset + mUUID + uuid + mAccessToken + accessToken + mUsertype + userType + mWidth + width + mHeight + height;

            System.out.println("运行配置:" + start);


            try {
                Runtime r = Runtime.getRuntime();
                InputStream inputStream = r.exec(start).getInputStream();
                String s;
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                while ((s = br.readLine()) != null) {
                    System.out.println(s);
                    ExtraUI.McJeStatus.setText(s + "\n" + ExtraUI.McJeStatus.getText());
                }

                System.out.println("game is quit");
            } catch (Exception ignored) {

            }
        }
    }

    public static void main(String[] args) {
        new minecraftStart().start();
    }

    @Override
    public void run() {
        startMc("C:\\normal\\Minecraft\\.minecraft\\libraries\\");
    }
}
