package com.example.demo.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.example.demo.bean.UserAddress;
import com.example.demo.service.UserService;

import java.util.List;

public class UserServiceStub implements UserService {

    private final UserService userService;

    public UserServiceStub(UserService userService) {
        super();
        this.userService = userService;
    }

    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        System.out.println("Method Stub is called....");
        if(!StringUtils.isEmpty(userId)){
            return userService.getUserAddressList(userId);
        }
        return null;
    }
}
