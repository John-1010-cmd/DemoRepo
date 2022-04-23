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
 * ���ߣ����� ʱ��:2019/5/13
 **/
public class ChatServer {

    private int port; //��������˿ں�

    private ServerSocketChannel serverSocketChannel = null; //�����Socket


    private Selector selector = null; //��·������

    //�ͻ�������Channel�б�
    Map<String, SocketChannel> clientChannelMap = new HashMap<>();

    public ChatServer(int port) {
        this.port = port;
        initServer();
    }

    /**
     * ��ʼ�����񣬴򿪼����˿�
     */
    private void initServer() {
        try {
            serverSocketChannel = ServerSocketChannel.open();//��Socket
            selector = Selector.open(); //������·����
            serverSocketChannel.bind(new InetSocketAddress(port));//�󶨶˿�
            serverSocketChannel.configureBlocking(false);//���÷�����
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//׼�������ͻ��˽���
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * �����ͻ�������
     */
    public void listen() {
        try {
            System.out.println("���ڽ��м����������˿�Ϊ��" + this.port);
            while (true) {
                selector.select(); //��ѯֱ�����¼�����
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> handleSelectKey(selectionKey)); //����ѯ�����¼����д���
                selectionKeys.clear();//�����ѯ���¼�
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * ������ѯ�����¼�
     */
    private void handleSelectKey(SelectionKey selectionKey) {
        try {
            if (selectionKey.isValid()) {
                if (selectionKey.isAcceptable()) { //���¿ͻ��˽���
                    handleNewClientConnect(selectionKey);
                } else if (selectionKey.isReadable()) { //�пͻ���д������
                    handleClientMessage(selectionKey);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * �����¿ͻ��˽���
     */
    private void handleNewClientConnect(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel ss = (ServerSocketChannel) selectionKey.channel();
        SocketChannel client = ss.accept();
        System.out.println("���µĿͻ��˽���.....");
        String address = client.getRemoteAddress().toString(); //�ͻ��˵ĵ�ַ
        clientChannelMap.put(address, client); //����ͻ��˵�����
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);//׼����ȡ����
    }


    /**
     * ��ȡ�ͻ��˷��͵���Ϣ��Ȼ�����ת��
     */
    private void handleClientMessage(SelectionKey selectionKey) {
        try {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int bytes = channel.read(readBuffer);
            if (bytes > 0) {
                readBuffer.flip();
                String message = new String(readBuffer.array(), "UTF-8");
                System.out.println("�пͻ��˷�����ϢΪ��" + message);
                //ת����Ϣ�������������ᷢ�͸���Ӧ���û��������Ϣ��Я����message����ģ�������򵥵�Ⱥ����������
                dispathMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ת����Ϣ
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


