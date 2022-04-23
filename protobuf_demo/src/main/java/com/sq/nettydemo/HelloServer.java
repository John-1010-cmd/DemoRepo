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
//        //1.创建 ServerSocket 对象并且绑定一个端口
//        try (ServerSocket server = new ServerSocket(port);) {
//            Socket socket;
//            //2.通过 accept()方法监听客户端请求， 这个方法会一直阻塞到有一个连接建立
//            while ((socket = server.accept()) != null) {
//                System.out.println("client connected");
//                logger.info("client connected");
//                try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//                     ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
//                    //3.通过输入流读取客户端发送的请求信息
//                    Message message = (Message) objectInputStream.readObject();
//                    logger.info("server receive message:" + message.getContent());
//                    message.setContent("new content");
//                    //4.通过输出流向客户端发送响应信息
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
        // 1.bossGroup 用于接收连接，workerGroup 用于具体的处理
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //2.创建服务端启动引导/辅助类：ServerBootstrap
            ServerBootstrap b = new ServerBootstrap();
            //3.给引导类配置两大线程组,确定了线程模型
            b.group(bossGroup, workerGroup)
                    // (非必备)打印日志
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 4.指定 IO 模型
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            //5.可以自定义客户端消息的业务处理逻辑
                            p.addLast(new HelloServerHandler());
                        }
                    });
            // 6.绑定端口,调用 sync 方法阻塞知道绑定完成
            ChannelFuture f = b.bind(port).sync();
            // 7.阻塞等待直到服务器Channel关闭(closeFuture()方法获取Channel 的CloseFuture对象,然后调用sync()方法)
            f.channel().closeFuture().sync();
        } finally {
            //8.优雅关闭相关线程组资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        new HelloServer(8080).start();
    }

}
