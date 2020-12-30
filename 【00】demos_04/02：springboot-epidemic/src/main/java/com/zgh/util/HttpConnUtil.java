package com.zgh.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Java 实现 http 请求
 */
public class HttpConnUtil {

    public static String doGet(String urlStr) {

        StringBuilder result = new StringBuilder();

        HttpURLConnection conn = null;
        InputStream inputStream = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            // 1.连接时间
            //   含义: 发送请求的主机 开始连接到 url 所对应的远程主机的时间
            //   影响因素: 距离 宽带
            // 2.读取时间
            //   含义: 建立连接后 获取数据所需要的时间
            //   影响因素: 数据大小
            conn.setConnectTimeout(15000); // 15秒
            conn.setReadTimeout(60000); // 60秒

            conn.setRequestProperty("Accept", "application/json");

            // 发送请求
            conn.connect();

            if (conn.getResponseCode() == 200) {
                inputStream = conn.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return result.toString();
    }


}
