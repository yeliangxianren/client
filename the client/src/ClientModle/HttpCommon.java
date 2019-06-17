package ClientModle;

import com.google.gson.Gson;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpCommon {
//    public static String url = "http://localhost:8010";
    public static String url = "http://139.155.104.140:8010";
//    public static String password = "bgroup";
    private static String cookie = null;

    public HttpCommon() {

    }

    public static void setCookie(String cookie) {
        HttpCommon.cookie = cookie;
    }

    public CustomResp doHttp(String path) {
        return doHttp(path, "GET", null);
    }

    public CustomResp doHttp(String path, Object object) {
        return doHttp(path, "POST", object);
    }

    public CustomResp doHttp(String path, String method, Object object) {
        String data = new Gson().toJson(object);
        path = url + path;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setRequestMethod(method);
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);

             conn.setRequestProperty("Connection", "Keep-Alive");
             conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
             conn.setRequestProperty("accept","application/json");
             
            if (cookie != null) {
                conn.setRequestProperty("Cookie", cookie);
            }
            try {
                if (method.equals("POST") && data != null) {
                    conn.setRequestProperty("Content-Length", String.valueOf(data.length()));
                    OutputStream os = conn.getOutputStream();
                    os.write(data.getBytes());
                    os.flush();
                    os.close();
                }
                conn.connect();
            } catch (ConnectException e) {
                return new CustomResp(new Result("杩炴帴鏈嶅姟鍣ㄥけ璐�"), null);
            }

            if (cookie == null) {
                // 閼惧嘲褰嘋ookie
                cookie = conn.getHeaderField("Set-Cookie");
                if (cookie != null)
                    cookie = cookie.split(";\40")[0];
                // System.out.println(cookie);
            }
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                CustomResp cr = new CustomResp(br.readLine(), br.readLine());
                br.close();
                return cr;
            } else {
                System.out.println(path);
                System.out.println(data);
                return new CustomResp(new Result("鏈嶅姟鍣ㄥ搷搴斿紓甯�(HTTP鍝嶅簲鐮�:" + code + ")"));
            }
        } catch (MalformedURLException e) {
            // TODO 閼奉亜濮╅悽鐔稿灇閻拷 catch 閸э拷
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 閼奉亜濮╅悽鐔稿灇閻拷 catch 閸э拷
            e.printStackTrace();
        }
        return null;
    }
}