package com.sq.tcp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class MyClient extends JFrame{

    private PrintWriter writer;
    Socket socket;
    private JTextArea ta=new JTextArea();
    private JTextField tf=new JTextField();
    Container cc;

    public MyClient(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cc=this.getContentPane();

        final JScrollPane scrollPane=new JScrollPane();
        scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED));
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        scrollPane.setViewportView(ta);
        cc.add(tf,"South");

        tf.addFocusListener(new JTextFieldListener(tf,"���ڴ���������"));
        tf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(tf.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(MyClient.this, "���������ݣ�");
                }else {
                    writer.println(tf.getText());

                    ta.append(tf.getText()+"\n");
                    ta.setSelectionEnd(ta.getText().length());
                    tf.setText("");
                }
            }
        });
    }

    //���������
    class JTextFieldListener implements FocusListener{

        private String hintText;          //��ʾ����
        private JTextField textField;

        public JTextFieldListener(JTextField textField,String hintText) {
            this.textField=textField;
            this.hintText=hintText;
            textField.setText(hintText);   //Ĭ��ֱ����ʾ
            textField.setForeground(Color.GRAY);
        }

        @Override
        public void focusGained(FocusEvent e) {

            //��ȡ����ʱ�������ʾ����
            String temp=textField.getText();
            if(temp.equals(hintText)){
                textField.setText("");
                textField.setForeground(Color.BLACK);
            }

        }

        @Override
        public void focusLost(FocusEvent e) {

            //ʧȥ����ʱ��û���������ݣ���ʾ��ʾ����
            String temp=textField.getText();
            if(temp.equals("")) {
                textField.setForeground(Color.GRAY);
                textField.setText(hintText);
            }

        }

    }

    private void connect() {
        ta.append("��������\n");
        try {
            socket=new Socket("127.0.0.1",8998);
            writer=new PrintWriter(socket.getOutputStream(),true);

            ta.append("�������\n");

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        //EventQueue�¼����У���װ���첽�¼�ָ�ɻ���
        EventQueue.invokeLater(new Runnable(){
            public void run() {
                MyClient client=new MyClient("���������������");
                client.setSize(400,400);
                client.setVisible(true);
                client.connect();


            }
        });

    }

}