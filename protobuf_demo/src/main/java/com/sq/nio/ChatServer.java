package com.sq.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 作者：刘兴 时间:2019/5/13
 **/
public class ChatServer {

    private int port; //服务监听端口号

    private ServerSocketChannel serverSocketChannel = null; //服务端Socket


    private Selector selector = null; //多路复用器

    //客户端连接Channel列表
    Map<String, SocketChannel> clientChannelMap = new HashMap<>();

    public ChatServer(int port) {
        this.port = port;
        initServer();
    }

    /**
     * 初始化服务，打开监听端口
     */
    private void initServer() {
        try {
            serverSocketChannel = ServerSocketChannel.open();//打开Socket
            selector = Selector.open(); //创建多路复用
            serverSocketChannel.bind(new InetSocketAddress(port));//绑定端口
            serverSocketChannel.configureBlocking(false);//设置非阻塞
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//准备监听客户端接入
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 监听客户端请求
     */
    public void listen() {
        try {
            System.out.println("正在进行监听，监听端口为：" + this.port);
            while (true) {
                selector.select(); //轮询直到有事件进入
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> handleSelectKey(selectionKey)); //对轮询到的事件进行处理
                selectionKeys.clear();//清空轮询了事件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 处理轮询到的事件
     */
    private void handleSelectKey(SelectionKey selectionKey) {
        try {
            if (selectionKey.isValid()) {
                if (selectionKey.isAcceptable()) { //有新客户端接入
                    handleNewClientConnect(selectionKey);
                } else if (selectionKey.isReadable()) { //有客户端写入数据
                    handleClientMessage(selectionKey);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理新客户端接入
     */
    private void handleNewClientConnect(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel ss = (ServerSocketChannel) selectionKey.channel();
        SocketChannel client = ss.accept();
        System.out.println("有新的客户端接入.....");
        String address = client.getRemoteAddress().toString(); //客户端的地址
        clientChannelMap.put(address, client); //保存客户端的请求
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);//准备读取数据
    }


    /**
     * 读取客户端发送的信息，然后进行转发
     */
    private void handleClientMessage(SelectionKey selectionKey) {
        try {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int bytes = channel.read(readBuffer);
            if (bytes > 0) {
                readBuffer.flip();
                String message = new String(readBuffer.array(), "UTF-8");
                System.out.println("有客户端发送消息为：" + message);
                //转发消息，正常聊天程序会发送给对应的用户（这个信息是携带在message里面的），这里简单的群发给所有人
                dispathMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转发信息
     */
    public void dispathMessage(String message) {
        clientChannelMap.values().forEach(client -> {
            ByteBuffer writeBuffer = ByteBuffer.allocate(message.getBytes().length);
            writeBuffer.put(message.getBytes());
            writeBuffer.flip();
            try {
                client.write(writeBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer(8080);
        server.listen();
    }
}


