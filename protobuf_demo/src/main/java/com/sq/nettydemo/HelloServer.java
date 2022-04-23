package com.sq.nettydemo;



import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloServer {
//    private static final Log logger = LogFactory.getLog(HelloServer.class);
//
//    public void start(int port) {
//        //1.���� ServerSocket �����Ұ�һ���˿�
//        try (ServerSocket server = new ServerSocket(port);) {
//            Socket socket;
//            //2.ͨ�� accept()���������ͻ������� ���������һֱ��������һ�����ӽ���
//            while ((socket = server.accept()) != null) {
//                System.out.println("client connected");
//                logger.info("client connected");
//                try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//                     ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
//                    //3.ͨ����������ȡ�ͻ��˷��͵�������Ϣ
//                    Message message = (Message) objectInputStream.readObject();
//                    logger.info("server receive message:" + message.getContent());
//                    message.setContent("new content");
//                    //4.ͨ���������ͻ��˷�����Ӧ��Ϣ
//                    objectOutputStream.writeObject(message);
//                    objectOutputStream.flush();
//                } catch (IOException | ClassNotFoundException e) {
//                    logger.error("occur exception:", e);
//                }
//            }
//        } catch (IOException e) {
//            logger.error("occur IOException:", e);
//        }
//    }
//
//    public static void main(String[] args) {
//        HelloServer helloServer = new HelloServer();
//        helloServer.start(6666);
//    }

    private  final int port;

    public HelloServer(int port) {
        this.port = port;
    }

    private  void start() throws InterruptedException {
        // 1.bossGroup ���ڽ������ӣ�workerGroup ���ھ���Ĵ���
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //2.�����������������/�����ࣺServerBootstrap
            ServerBootstrap b = new ServerBootstrap();
            //3.�����������������߳���,ȷ�����߳�ģ��
            b.group(bossGroup, workerGroup)
                    // (�Ǳر�)��ӡ��־
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 4.ָ�� IO ģ��
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            //5.�����Զ���ͻ�����Ϣ��ҵ�����߼�
                            p.addLast(new HelloServerHandler());
                        }
                    });
            // 6.�󶨶˿�,���� sync ��������֪�������
            ChannelFuture f = b.bind(port).sync();
            // 7.�����ȴ�ֱ��������Channel�ر�(closeFuture()������ȡChannel ��CloseFuture����,Ȼ�����sync()����)
            f.channel().closeFuture().sync();
        } finally {
            //8.���Źر�����߳�����Դ
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        new HelloServer(8080).start();
    }

}
