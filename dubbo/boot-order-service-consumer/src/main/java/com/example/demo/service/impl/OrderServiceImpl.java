package com.example.demo.service.impl;

//import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.example.demo.bean.UserAddress;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 1、将服务提供者注册到注册中心
 *      1) 导入dubbo依赖 操作zookeeper的客户端
 *      2） 配置服务提供者
 * 2、让消费者区注册中心订阅服务提供者的服务地址
 */
@Service
public class OrderServiceImpl implements OrderService {
//    @Autowired
//    @Reference(url="127.0.0.1:20882")
//    @Reference(loadbalance="roundrobin")
    @Reference(loadbalance="random")
    UserService userService;
    @Override
    public List<UserAddress> initOrder(String userId) {
        System.out.println("userId = "+userId);
        List<UserAddress> addresses = userService.getUserAddressList(userId);
        System.out.println(addresses);
        for(UserAddress userAddress:addresses) {
            System.out.println(userAddress.getUserAddress());
        }
        return addresses;
    }
}
