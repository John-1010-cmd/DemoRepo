package com.example.demo.service.impl;

<<<<<<< HEAD
import java.util.Arrays;
import java.util.List;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.example.demo.bean.UserAddress;
import com.example.demo.service.UserService;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service//暴露服务 
@Component
public class UserServiceImpl implements UserService {

	@HystrixCommand
	@Override
	public List<UserAddress> getUserAddressList(String userId) {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl..3.....");
		UserAddress address1 = new UserAddress(1, "北京市昌平区宏福科技园综合楼3层", "1", "李老师", "010-56253825", "Y");
		UserAddress address2 = new UserAddress(2, "深圳市宝安区西部硅谷大厦B座3层（深圳分校）", "1", "王老师", "010-56253825", "N");
		try {
//			Thread.sleep(2000);
			Thread.sleep(990);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(Math.random()>0.5) {
			throw new RuntimeException();
		}
		return Arrays.asList(address1,address2);
	}

=======
import com.alibaba.dubbo.config.annotation.Service;
import com.example.demo.bean.UserAddress;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Service
@Component
public class UserServiceImpl implements UserService {

    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        System.out.println("UserServiceImpl...3....");
        UserAddress userAddress1 = new UserAddress(1,"xx市xx区xx科技园综合楼3层","1","王老师","010-1532-4621","Y");
        UserAddress userAddress2 = new UserAddress(2,"xx市xx区xx大厦B座3层","1","李老师","010-1566-8465","Y");
        return Arrays.asList(userAddress1,userAddress2);
    }
>>>>>>> 662dec2eea7a8b29b2dff80460fd81cac9fcdc77
}
