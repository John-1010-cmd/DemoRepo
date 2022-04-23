package com.sq.nettydemo;





import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HelloClient {

//    private static final Logger logger = LoggerFactory.getLogger(HelloClient.class);
//
//    public Object send(Message message, String host, int port) {
//        //1. ����Socket������ָ���������ĵ�ַ�Ͷ˿ں�
//        try (Socket socket = new Socket(host, port)) {
//            logger.info("test.....");
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//            //2.ͨ���������������˷���������Ϣ
//            objectOutputStream.writeObject(message);
//            //3.ͨ����������ȡ��������Ӧ����Ϣ
//            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//            return objectInputStream.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            logger.error("occur exception:", e);
//        }
//        return null;
//    }
//
//    public static void main(String[] args) {
//        Message message=new Message("new context");
//        HelloClient helloClient = new HelloClient();
//        helloClient.send(new Message("content from client"), "127.0.0.1", 6666);
//        System.out.println("client receive message:" + message.getContent());
//    }

    private final String host;
    private final int port;
    private final String message;

    public HelloClient(String host, int port, String message) {
        this.host = host;
        this.port = port;
        this.message = message;
    }

    private void start() throws InterruptedException {
        //1.����һ�� NioEventLoopGroup ����ʵ��
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //2.�����ͻ�����������/�����ࣺBootstrap
            Bootstrap b = new Bootstrap();
            //3.ָ���߳���
            b.group(group)
                    //4.ָ�� IO ģ��
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            // 5.��������Զ�����Ϣ��ҵ�����߼�
                            p.addLast(new HelloClientHandler(message));
                        }
                    });
            // 6.���Խ�������
            ChannelFuture f = b.connect(host, port).addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("���� "+host+":"+port+" �ɹ�!");
                } else {
                    System.err.println("����ʧ��!");
                }
            }).sync();
            // 7.�ȴ����ӹرգ�������ֱ��Channel�رգ�
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception {
        new HelloClient("127.0.0.1",8080, "���,����˧������磡").start();
    }
}
