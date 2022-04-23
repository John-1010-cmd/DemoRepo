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
            out.println("�뷵��ϵͳ��ǰʱ��...."); //1.������˷�����Ϣ
            String serverMsg = in.readLine(); //2.���ܷ���˷��ص���Ϣ
            System.out.println("����˷��ص���ϢΪ: " + serverMsg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("��������������ͷ������˵�����....");
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


