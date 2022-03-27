//package com.example.shop_service_order.controller;
//
//import com.example.shop_service_common.entity.Product;
////import com.example.shop_service_order.service.ProductFeginClient;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@RequestMapping("product")
//public class RestTemplateController {
////    @Autowired
////    DiscoveryClient discoveryClient;
////    @Autowired
////    RestTemplate restTemplate;
////    @Autowired
////    private ProductFeginClient productFeginClient;
//
////    @PostMapping("/{id}")
////    @GetMapping("/{id}")
////    public String order(Integer num) {
////        //通过restTemplate调用商品微服务
////        Product object =restTemplate.getForObject("http://localhost:9002/product/"+num, Product.class);
////        System.out.println(object);
////        return "操作成功";
////    }
//
////    @GetMapping("/buy/{id}")
////    public Product order(@PathVariable Long id) {
////        System.out.println("order buy id ="+id);
////        return productFeginClient.findById(id);
////    }
//
////    @Test
////    public void test() {
////        //根据微服务名称从注册中心获取相关的元数据信息
////        List<ServiceInstance> instances = discoveryClient.getInstances("shop-service-product");
////        System.out.println("discoveryClient: "+discoveryClient);
////        System.out.println("instances:"+instances);
////        for (ServiceInstance instance : instances) {
////            System.out.println(instance);
////        }
////        if(instances == null){
////            System.out.println("instances == null");
////        }else {
////            System.out.println("instantces is not null");
////        }
////        this.order(1L);
////    }
//}
