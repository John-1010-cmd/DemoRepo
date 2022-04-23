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
    // ���������ͨ�ŵ��첽Channel
    AsynchronousSocketChannel clientChannel;

    /**
     1. �����û�������Ϣ
     */
    public void listenUserInput() throws Exception {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            clientChannel.write(ByteBuffer.wrap(scan.nextLine().getBytes(UTF_8))).get(); // ��
        }
    }

    /**
     2. ���ӷ�����
     */
    public void connect() throws Exception {
        // ����һ��ByteBuffer׼����ȡ����
        final ByteBuffer buff = ByteBuffer.allocate(1024);
        // ����һ���̳߳�
        ExecutorService executor = Executors.newFixedThreadPool(80);
        // ��ָ���̳߳�������һ��AsynchronousChannelGroup
        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(executor);
        // ��channelGroup��Ϊ�������������AsynchronousSocketChannel
        clientChannel = AsynchronousSocketChannel.open(channelGroup);
        // ��AsynchronousSocketChannel���ӵ�ָ��IP��ָ���˿�
        clientChannel.connect(new InetSocketAddress("127.0.0.1", PORT)).get();
        // jta.append("---����������ӳɹ�---\n");
        System.out.println("---����������ӳɹ�---");
        buff.clear();
        clientChannel.read(buff, null, new CompletionHandler<Integer, Object>() // ��
        {
            @Override
            public void completed(Integer result, Object attachment) {
                buff.flip();
                // ��buff������ת��Ϊ�ַ���
                String content = StandardCharsets.UTF_8.decode(buff).toString();
                // ��ʾ�ӷ������˶�ȡ������
                // jta.append("ĳ��˵��" + content + "\n");
                System.out.println("ĳ��˵��" + content);
                buff.clear();
                //��ȡ��һ������
                clientChannel.read(buff, null, this);
            }

            @Override
            public void failed(Throwable ex, Object attachment) {
                System.out.println("��ȡ����ʧ��: " + ex);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        AIOClient client = new AIOClient();
        client.connect();
        client.listenUserInput();
    }
}

