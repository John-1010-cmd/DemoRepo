package com.sq.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port); //1.����socket����������˿�û�б�ռ�ã���������ɹ�
            System.out.println("ʱ��������Ѿ�������,�����˿�Ϊ" + port + "......");
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept(); //2. ѭ���ȴ��ͻ��˽������ӣ�û�пͻ������ӽ�����ʱ���ڴ�����
                System.out.println("�пͻ��˽��룬");
                new Thread(new TimeServerHandler(socket)).start(); //3.����һ���µ��̴߳������ӽ����Ŀͻ���
            }

        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}


