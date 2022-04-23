package com.sq.bio.io;

import java.io.*;
import java.net.Socket;

public class TimeClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8080);
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            out.println("请返回系统当前时间...."); //1.往服务端发送消息
            String serverMsg = in.readLine(); //2.接受服务端返回的消息
            System.out.println("服务端返回的消息为: " + serverMsg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("请求结束，主动释放与服务端的连接....");
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}


