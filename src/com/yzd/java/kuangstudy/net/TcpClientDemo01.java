package com.yzd.java.kuangstudy.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by yzd on 2021/2/19
 */
public class TcpClientDemo01 {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port = 9999;

            socket = new Socket(address,port);
            outputStream = socket.getOutputStream();

            outputStream.write("你好".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
