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
//        //1. 创建Socket对象并且指定服务器的地址和端口号
//        try (Socket socket = new Socket(host, port)) {
//            logger.info("test.....");
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//            //2.通过输出流向服务器端发送请求信息
//            objectOutputStream.writeObject(message);
//            //3.通过输入流获取服务器响应的信息
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
        //1.创建一个 NioEventLoopGroup 对象实例
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //2.创建客户端启动引导/辅助类：Bootstrap
            Bootstrap b = new Bootstrap();
            //3.指定线程组
            b.group(group)
                    //4.指定 IO 模型
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            // 5.这里可以自定义消息的业务处理逻辑
                            p.addLast(new HelloClientHandler(message));
                        }
                    });
            // 6.尝试建立连接
            ChannelFuture f = b.connect(host, port).addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("连接 "+host+":"+port+" 成功!");
                } else {
                    System.err.println("连接失败!");
                }
            }).sync();
            // 7.等待连接关闭（阻塞，直到Channel关闭）
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception {
        new HelloClient("127.0.0.1",8080, "你好,你真帅啊！哥哥！").start();
    }
}
