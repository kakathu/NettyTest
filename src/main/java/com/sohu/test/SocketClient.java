package com.sohu.test;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * User: wujian (jianwu203076@sohu-inc.com)
 * Date: 05/13/2015 16:38
 */
public class SocketClient {
    public static void main(String[] args) {
        Socket s = null;
        try {
            s = new Socket("127.0.0.1", 8000);
            PrintWriter writer = new PrintWriter(s.getOutputStream());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));

            // String message =
            // "auth|6|1000|bagtest_1@focus.cn|12345678|1245673bd9cf1e6946882b33a2f5a532|10.1.80.71|1378268103450";

            String userid = "bagtest@chinaren.com";
            String pwd = "12345687";
            String appid = "1000";
            String key = "1000123456";
            String ct = String.valueOf(System.currentTimeMillis());
            String cip = "10.1.80.71";

            String sign = DigestUtils.md5Hex(appid + userid + pwd + cip
                    + ct + key);

            String message = "auth|6|" + appid + "|" + userid + "|"
                    + pwd + "|" + sign + "|" + cip + "|" + ct;

            long a1 = System.currentTimeMillis();
            writer.println(message);
            writer.flush();
            System.out.println("client finish sending...");

            Thread.sleep(2000);

            String line = reader.readLine();

            long a2 = System.currentTimeMillis();
            System.out.println("client receive: " + line);
            System.out.println(userid + "+" + (a2 - a1) + "ms");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
