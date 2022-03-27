package com.example.shop_service_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages="com.example.shop_service_product")
@EntityScan("com.example.shop_service_common.entity")
@EnableEurekaClient
public class ShopServiceProductApplication {

//    @PostMapping("/{id}")
//    public String order(Integer num) {
//      通过restTemplate调用商品微服务
//      Product object =restTemplate.getForObject("http://127.0.0.1:9002/product/1", Product.class);
//      System.out.println(object);
//      return "操作成功";
//      }

    public static void main(String[] args) {
        SpringApplication.run(ShopServiceProductApplication.class, args);
    }

}
