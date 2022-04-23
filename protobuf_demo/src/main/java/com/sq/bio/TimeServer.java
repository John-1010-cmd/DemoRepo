package com.sq.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port); //1.创建socket监听，如果端口没有被占用，则会启动成功
            System.out.println("时间服务器已经启动了,监听端口为" + port + "......");
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept(); //2. 循环等待客户端进行连接，没有客户端连接进来的时候在此阻塞
                System.out.println("有客户端接入，");
                new Thread(new TimeServerHandler(socket)).start(); //3.开启一个新的线程处理连接进来的客户端
            }

        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}


