package com.example.demo.service.impl;

import com.example.demo.bean.UserAddress;
import com.example.demo.service.UserService;

import java.util.Arrays;
import java.util.List;

public class UserServiceImpl2 implements UserService {

    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        System.out.println("User Implememt 02 is called.....");
        UserAddress userAddress1 = new UserAddress(1,"xx市xx区xx科技园综合楼3层","1","王老师","010-1532-4621","Y");
        UserAddress userAddress2 = new UserAddress(2,"xx市xx区xx大厦B座3层","1","李老师","010-1566-8465","Y");
        return Arrays.asList(userAddress1,userAddress2);
    }
}
