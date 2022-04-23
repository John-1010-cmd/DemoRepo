package com.example.demo.service.impl;

<<<<<<< HEAD
import java.util.Arrays;
import java.util.List;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

=======
//import com.alibaba.dubbo.config.annotation.Service;
>>>>>>> 662dec2eea7a8b29b2dff80460fd81cac9fcdc77
import com.alibaba.dubbo.config.annotation.Reference;
import com.example.demo.bean.UserAddress;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
<<<<<<< HEAD
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 1、将服务提供者注册到注册中心（暴露服务）
 * 		1）、导入dubbo依赖（2.6.2）\操作zookeeper的客户端(curator)
 * 		2）、配置服务提供者
 * 
 * 2、让服务消费者去注册中心订阅服务提供者的服务地址
 * @author lfy
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	//@Autowired
	@Reference(loadbalance = "roundrobin")//dubbo直连
	UserService userService;
	
	@HystrixCommand(fallbackMethod="hello")
	@Override
	public List<UserAddress> initOrder(String userId) {
		// TODO Auto-generated method stub
		System.out.println("用户id："+userId);
		//1、查询用户的收货地址
		List<UserAddress> addressList = userService.getUserAddressList(userId);
		return addressList;
	}
	
	
	public List<UserAddress> hello(String userId) {
		// TODO Auto-generated method stub
	
		return Arrays.asList(new UserAddress(10, "测试地址", "1", "测试", "测试", "Y"));
	}
	

=======
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
>>>>>>> 662dec2eea7a8b29b2dff80460fd81cac9fcdc77
}
