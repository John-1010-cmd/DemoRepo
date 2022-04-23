package com.sq.bio.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 作者：刘兴 时间:2019/5/8
 **/
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port); //1.创建socket监听，如果端口没有被占用，则会启动成功
            System.out.println("时间服务器已经启动了,监听端口为" + port + "......");
            TimeServerHandlerExecutor timeServerHandlerExecutor = new TimeServerHandlerExecutor(20, 1000);
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept(); //2. 循环等待客户端进行连接，没有客户端连接进来的时候在此阻塞
                System.out.println("有客户端接入，");
                timeServerHandlerExecutor.executeTask(new TimeServerHandler(socket)); //3.使用线程池开启一个新的线程处理连接进来的客户端
            }

        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
