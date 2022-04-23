package com.sq.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.Set;

public class ChatClient {


    private Selector selector = null;

    private SocketChannel socketChannel = null;

    public ChatClient(String host, int port) {
        connectServer(host, port);
    }


    private void connectServer(String host, int port) {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress(host, port));//���ӿͻ���
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> handleSelectionKey(selectionKey));
                selectionKeys.clear();//����¼�
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ������ѯ�����¼�
     */
    private void handleSelectionKey(SelectionKey selectionKey) {
        if (selectionKey.isValid()) {
            if (selectionKey.isConnectable()) { //���Ӿ����¼�
                handleConnection(selectionKey);
            } else if (selectionKey.isReadable()) { //����Ϣ�ӷ���˷�����
                readMessage(selectionKey);
            }
        }
    }


    /**
     * ���Ӿ�����׼�������û�������
     */
    private void handleConnection(SelectionKey selectionKey) {
        try {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            if (channel.isConnectionPending()) {
                System.out.println("�����Ѿ�����....");
                channel.finishConnect();
                //�����̼߳����ͻ���������Ϣ
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Scanner scanner = new Scanner(System.in);
                        while (true) {
                            String message = scanner.nextLine();
                            ByteBuffer writeBuffer = ByteBuffer.allocate(message.getBytes().length);
                            writeBuffer.put(message.getBytes());
                            writeBuffer.flip();
                            try {
                                channel.write(writeBuffer);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

                channel.register(selector,SelectionKey.OP_READ);//ע�����Ϣ�¼�
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ȡ����˷��͵���Ϣ
     */
    public void readMessage(SelectionKey selectionKey) {
        try {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int bytes = socketChannel.read(readBuffer);
            if (bytes > 0) {
                readBuffer.flip();
                System.out.println(new String(readBuffer.array(), "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatClient("127.0.0.1", 8080);
    }
}


