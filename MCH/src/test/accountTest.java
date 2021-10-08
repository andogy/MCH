package test;

import com.github.zhuaidadaya.MCH.lib.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Map;

public class accountTest {
    public static boolean mojangAccount(String res, byte[] bytes) {
        try {
            URL url = new URL(res);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();

            System.out.println(httpURLConnection.getResponseMessage());
            System.out.println(httpURLConnection.getResponseCode());
            System.out.println(httpURLConnection.getRequestMethod());

            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String s;
            while((s = br.readLine()) != null) {
                System.out.println(s);
            }

            httpURLConnection.disconnect();


            return true;
        } catch (Exception e) {

        }

        return false;
    }

    public static void microsoftAccount(String res, byte[] bytes) {
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

            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String s;
            StringBuilder all = new StringBuilder();
            while((s = br.readLine()) != null) {
                System.out.println(s);
                all.append(s);
            }

            JSONObject info = new JSONObject(all.toString());

            StringBuilder decode = new StringBuilder();
            String decodeCache;
            for(Object o : Base64.getDecoder().decode(info.get("access_token").toString().getBytes())) {
                decodeCache = String.valueOf(Integer.parseInt(o.toString()));
                decode.append(decodeCache);
            }

            System.out.println(decode.toString());

            httpURLConnection.disconnect();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //        mojangAccount("https://authserver.mojang.com/authenticate",
        //                """
        //                {
        //                "agent": {
        //                        "name": "Minecraft",
        //                        "version": 1
        //                    },
        //                "username": "lingxian0874@163.com",
        //                "password": "123456789"
        //                }
        //                """.getBytes()
        //        );

        Map<Object, Object> data = Map.of("client_id", "00000000402b5328", // 还是Minecraft客户端id
                "code", "82945523-78b8-de2d-d4aa-76f531417eef&lc=2052", // 第一步中获取的代码
                "grant_type", "authorization_code", "redirect_uri", "https://login.live.com/oauth20_desktop.srf", "scope", "service::user.auth.xboxlive.com::MBI_SSL");

        microsoftAccount("https://login.live.com/oauth20_token.srf?redirect_uri=" + data.get("redirect_uri") + "&scope=" + data.get("scope") + "&grant_type=" + data.get("grant_type") + "&client_id=" + data.get("client_id") + "&code=" + data.get("code"), "".getBytes());
    }
}
