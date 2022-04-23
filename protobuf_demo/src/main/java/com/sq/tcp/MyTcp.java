package com.sq.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import static java.lang.System.out;

public class MyTcp {

    private BufferedReader reader;
    private ServerSocket server;
    private Socket socket;

    void getServer(){
        try {
            server=new ServerSocket(8998);        //ʵ����Socket����
            out.println("�������׽����Ѵ����ɹ�");

            while(true) {
                out.println("�ȴ��ͻ���������");
                socket=server.accept();           //accept()�����᷵��һ���Ϳͻ���Socket����������Socket����
                reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                getClientMessage();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    //��ȡ�ͻ��˷��͹�������Ϣ
    private void getClientMessage() {
        try {
            while(true) {
                //��ÿͻ�����Ϣ
                out.println("�ͻ�����"+reader.readLine());
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        try {
            if(reader!=null) {
                reader.close();
            }
            if(socket!=null) {
                socket.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        MyTcp tcp=new  MyTcp();
        tcp.getServer();
    }

}
