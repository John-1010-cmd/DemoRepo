package com.example.shop_service_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages = "com.example.shop_service_order")
//@EntityScan("com.example.shop_service_common.entity")
//@EntityScan("com.example.shop_service_order.service")
@EnableFeignClients
public class ShopServiceOrderApplication {

    //基于Ribbon的服务调用与负载均衡
//    @LoadBalanced
//    //配置RestTemplate交给spring管理
//    @Bean
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }

    public static void main(String[] args) {
        SpringApplication.run(ShopServiceOrderApplication.class, args);
    }

}
