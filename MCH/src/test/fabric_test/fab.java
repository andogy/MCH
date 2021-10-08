package test.fabric_test;

import com.github.zhuaidadaya.MCH.UI.MinecraftLauncher;
import com.github.zhuaidadaya.MCH.lib.json.JSONArray;
import com.github.zhuaidadaya.MCH.lib.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class fab {
    public static void main(String[] args) {
        //        MinecraftLauncher.customDownLoad("https://maven.fabricmc.net/net/fabricmc/sponge-mixin/0.10.2+mixin.0.8.4/sponge-mixin-0.10.2+mixin.0.8.4.jar", "C:\\Users\\lx\\AppData\\Roaming\\MinecraftCommandHelper\\minecraft\\libraries\\cao.jar",false,0,false,0,"name");
        test();
    }

    public static void test() {
        try {
            //            https://meta.fabricmc.net/v2/versions/loader/1.17.1/0.12.0/profile/json
            JSONObject json = new JSONObject(getRes(String.format("https://meta.fabricmc.net/v2/versions/loader/%s/%s/profile/json", "1.17.1", "0.12.0"), "", false, false));
            getRes(String.format("https://meta.fabricmc.net/v2/versions/loader/%s/%s/profile/json", "1.17.1", "0.12.0"), "C:\\Users\\lx\\AppData\\Roaming\\MinecraftCommandHelper\\minecraft\\versions\\21w40a\\fabric_index.json", false, true);
            //            JSONObject json = new JSONObject(getRes(String.format("https://meta.fabricmc.net/v2/versions/loader/%s/%s/profile/json", "1.17.1", "0.1.0.48")));
            System.out.println(json);

            JSONArray libs = new JSONArray(json.get("libraries").toString());
            System.out.println(libs);

            for(Object o : libs) {
                JSONObject lib = new JSONObject(o.toString());
                String[] data = lib.get("name").toString().split(":");
                //                System.out.println(o);
                //                System.out.println(Arrays.toString(data));
                //                System.out.println("url: " + lib.get("url") + data[0].replace(".", "/") + "/" + data[1] + "/" + data[2] + "/" + data[1] + "-" + data[2] + ".jar");

                //                https://maven.fabricmc.net/net/fabricmc/fabric-loader/0.12.0/fabric-loader-0.12.0
                MinecraftLauncher.customDownLoad(lib.get("url") + data[0].replace(".", "/") + "/" + data[1] + "/" + data[2] + "/" + data[1] + "-" + data[2] + ".jar", "C:\\Users\\lx\\AppData\\Roaming\\MinecraftCommandHelper\\minecraft\\libraries\\".replace("\\", "/") + data[0].replace(".", "/") + "/" + data[1] + "/" + data[2] + "/" + data[1] + "-" + data[2] + ".jar", false, 0, false, 0, "name", true);
                //                getRes("url: " + lib.get("url") + data[0].replace(".", "/") + "/" + data[1] + "/" + data[2] + "/" + data[1] + "-" + data[2] + ".jar", "C:\\Users\\lx\\AppData\\Roaming\\MinecraftCommandHelper\\minecraft\\libraries\\".replace("\\", "/") + data[0].replace(".", "/") + "/" + data[1] + "/" + data[2] + "/" + data[1] + "-" + data[2] + ".jar", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getRes(String res, String downloadTo, boolean useStream, boolean download) {
        try {
            System.out.println(res);

            URL url = new URL(res);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setConnectTimeout(10000);
            //            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();

            //            OutputStream outputStream = httpURLConnection.getOutputStream();
            //            outputStream.write(bytes);
            //            outputStream.flush();

            System.out.println(httpURLConnection.getContentType());

            System.out.println(httpURLConnection.getResponseMessage());
            System.out.println(httpURLConnection.getResponseCode());
            System.out.println(httpURLConnection.getRequestMethod());

            StringBuilder all = new StringBuilder();

            File file = new File(downloadTo);
            if(useStream) {

                BufferedInputStream bi = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(file, false));

                byte[] b = new byte[2048];
                int length;
                while((length = bi.read()) != - 1) {
                    if(download)
                        bo.write(b, 0, length);
                    else
                        all.append(b.toString());
                }

                bi.close();
                bo.close();
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                BufferedWriter bw = new BufferedWriter(FileWriter.nullWriter());
                if(download)
                    bw = new BufferedWriter(new FileWriter(file));
                String s;
                while((s = br.readLine()) != null) {
                    if(download)
                        bw.write(s);
                    else
                        all.append(s);
                }

                br.close();
                bw.close();
            }

            httpURLConnection.disconnect();

            return all.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
