package com.sq.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
            while (true) { //1.ѭ�����ܿͻ��˵���Ϣ
                clientMsg = in.readLine();
                if (clientMsg == null) {
                    break;
                }
                System.out.println("�յ��ͻ��˵���ϢΪ: " + clientMsg);
                out.println("��ǰʱ��Ϊ��" + new Date().toString()); //2.������Ϣ
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("�ͻ������ߣ��ͷ�socket....");
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


