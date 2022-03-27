package com.example.shop_service_order.controller;

import com.example.shop_service_common.entity.Product;
import com.example.shop_service_order.service.ProductFeginClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
@RestController
@RequestMapping("api/v1/restaurant")
public class OrderController {
    @Autowired
    private ProductFeginClient productFeginClient;

    @GetMapping("/buy/{id}")
    public Product order(@PathVariable Long id) {
        System.out.println("order buy id ="+id);
        return productFeginClient.findById(id);
    }

//    @Test
//    public void test(){
//        this.order(1L);
//    }
}
