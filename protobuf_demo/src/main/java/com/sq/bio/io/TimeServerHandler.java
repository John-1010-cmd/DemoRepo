package com.sq.bio.io;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            String clientMsg = null;
            while (true) { //1.循环接受客户端的消息
                clientMsg = in.readLine();
                if (clientMsg == null) {
                    break;
                }
                System.out.println("收到客户端的消息为: " + clientMsg);
                out.println("当前时间为：" + new Date().toString()); //2.返回信息
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("客户端下线，释放socket....");
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


