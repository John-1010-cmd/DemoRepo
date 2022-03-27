package com.example.shop_eureka_server;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RestTemplateTest {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Test
    public void test() {
        //根据微服务名称从注册中心获取相关的元数据信息
        System.out.println("test....");
        List<ServiceInstance> instances = discoveryClient.getInstances("shop-service-product");
        for (ServiceInstance instance : instances) {
            System.out.println(instance);
        }
        if(instances == null)
            System.out.println("instances == null");
        else{
            System.out.println("instances is not null");
        }
    }
}
