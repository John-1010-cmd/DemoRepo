package com.sq.protobuf;

import com.google.protobuf.TextFormat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Test {
    public static void main(String[] args) throws IOException {
        //模拟将对象转成byte[]，方便传输
        UserProtobuf.UserBuf.Builder builder = UserProtobuf.UserBuf.newBuilder();
        builder.setId(1);
        builder.setUsername("username");
        builder.setPassword("password");
        builder.setAddress(UserProtobuf.UserBuf.Address.newBuilder()
                .setReceiver("王老师好").setPhoneNum("134-1111-2341").build());
        UserProtobuf.UserBuf userBuf = builder.build();
        System.out.println("before :\n"+ TextFormat.printToUnicodeString(userBuf));

        System.out.println("===========testBuf Byte==========");
        int i = 0;
        for(byte b : userBuf.toByteArray()){
            System.out.print(b);
            i++;
        }
        System.out.println("\ni = "+i);
        System.out.println("\n"+userBuf.toByteString());
        System.out.println("=================================");

        //模拟接收Byte[]，反序列化成Person类
        byte[] byteArray =userBuf.toByteArray();
        UserProtobuf.UserBuf p2 = UserProtobuf.UserBuf.parseFrom(byteArray);
//        System.out.println(byteArray);
//        System.out.println(userBuf.toByteArray());
//        System.out.println(String.valueOf(userBuf.toByteArray()));
        System.out.println("after :\n" + TextFormat.printToUnicodeString(p2));

//        MsgProtobuf.MsgBuf.Builder builder2 = MsgProtobuf.MsgBuf.newBuilder();
//        builder2.setSender("张三");
//        builder2.setReceiver("李四");
//        builder2.setMessage("8118811711510111411097109101268112971151151191111141003426109-25-114-117-24-128-127-27-72-120181349515245494949494550515249");
//        MsgProtobuf.MsgBuf msgBuf = builder2.build();
//        System.out.println("before :\n"+ TextFormat.printToUnicodeString(msgBuf));
//
//        System.out.println("===========testBuf Byte==========");
//        for(byte b : msgBuf.toByteArray()){
//            System.out.print(b);
//        }
//        System.out.println("\n"+msgBuf.toByteString());
//        System.out.println("=================================");
//
//        //模拟接收Byte[]，反序列化成Person类
//        byte[] byteArray2 =msgBuf.toByteArray();
////        UserProtobuf.UserBuf p2 = UserProtobuf.UserBuf.parseFrom(byteArray);
//        MsgProtobuf.MsgBuf p22 = MsgProtobuf.MsgBuf.parseFrom(byteArray2);
//        System.out.println(p22.getMessage());
//        System.out.println("....");
//        System.out.println(byteArray);
//        System.out.println(p22.getMessage().getBytes());
////        UserProtobuf.UserBuf p3 = UserProtobuf.UserBuf.parseFrom(p2.getMessage().getBytes());
//        System.out.println("after :\n" + TextFormat.printToUnicodeString(p22));
    }
}