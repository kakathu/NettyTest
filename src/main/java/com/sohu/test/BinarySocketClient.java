package com.sohu.test;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * User: wujian (jianwu203076@sohu-inc.com)
 * Date: 05/14/2015 16:05
 */
public class BinarySocketClient {
    public static void main(String[] args) throws Exception{
        String ip = "127.0.0.1";
        int port = 8000;
        Socket socket = new Socket(ip, port);
        OutputStream output = socket.getOutputStream();
        DataOutputStream out = new DataOutputStream(output);
        String email = "wujiankaka";
        String password = "123456";
        String so_sig = "asdf";
//        int length = 1 + 1 + 1 + email.length() + 1 + password.length() + so_sig.length();
        int length = 1 + 1 + 4 + 1 + email.length() + 1 + password.length() + so_sig.length();
        //out.writeInt(length);
        out.writeByte(1);//版本号
        out.writeByte(1);//请求操作类型
        out.writeInt(length);
        out.writeByte(email.length());
        out.writeBytes(email);
        out.writeByte(password.length());
        out.writeBytes(password);
        out.writeBytes(so_sig);
        out.flush();
        Thread.sleep(2000);
        out.close();
        output.close();
        socket.close();
    }
}
