package com.sq.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AIOClient {

    final static String UTF_8 = "utf-8";
    final static int PORT = 30000;
    // 与服务器端通信的异步Channel
    AsynchronousSocketChannel clientChannel;

    /**
     1. 监听用户输入信息
     */
    public void listenUserInput() throws Exception {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            clientChannel.write(ByteBuffer.wrap(scan.nextLine().getBytes(UTF_8))).get(); // ①
        }
    }

    /**
     2. 连接服务器
     */
    public void connect() throws Exception {
        // 定义一个ByteBuffer准备读取数据
        final ByteBuffer buff = ByteBuffer.allocate(1024);
        // 创建一个线程池
        ExecutorService executor = Executors.newFixedThreadPool(80);
        // 以指定线程池来创建一个AsynchronousChannelGroup
        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(executor);
        // 以channelGroup作为组管理器来创建AsynchronousSocketChannel
        clientChannel = AsynchronousSocketChannel.open(channelGroup);
        // 让AsynchronousSocketChannel连接到指定IP、指定端口
        clientChannel.connect(new InetSocketAddress("127.0.0.1", PORT)).get();
        // jta.append("---与服务器连接成功---\n");
        System.out.println("---与服务器连接成功---");
        buff.clear();
        clientChannel.read(buff, null, new CompletionHandler<Integer, Object>() // ②
        {
            @Override
            public void completed(Integer result, Object attachment) {
                buff.flip();
                // 将buff中内容转换为字符串
                String content = StandardCharsets.UTF_8.decode(buff).toString();
                // 显示从服务器端读取的数据
                // jta.append("某人说：" + content + "\n");
                System.out.println("某人说：" + content);
                buff.clear();
                //读取下一次数据
                clientChannel.read(buff, null, this);
            }

            @Override
            public void failed(Throwable ex, Object attachment) {
                System.out.println("读取数据失败: " + ex);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        AIOClient client = new AIOClient();
        client.connect();
        client.listenUserInput();
    }
}

