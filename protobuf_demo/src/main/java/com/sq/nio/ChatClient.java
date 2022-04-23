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
            socketChannel.connect(new InetSocketAddress(host, port));//连接客户端
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> handleSelectionKey(selectionKey));
                selectionKeys.clear();//清空事件
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理被轮询到的事件
     */
    private void handleSelectionKey(SelectionKey selectionKey) {
        if (selectionKey.isValid()) {
            if (selectionKey.isConnectable()) { //连接就绪事件
                handleConnection(selectionKey);
            } else if (selectionKey.isReadable()) { //有信息从服务端发过来
                readMessage(selectionKey);
            }
        }
    }


    /**
     * 连接就绪，准备接受用户的输入
     */
    private void handleConnection(SelectionKey selectionKey) {
        try {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            if (channel.isConnectionPending()) {
                System.out.println("连接已经就绪....");
                channel.finishConnect();
                //启动线程监听客户端输入信息
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

                channel.register(selector,SelectionKey.OP_READ);//注册读消息事件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取服务端发送的消息
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


