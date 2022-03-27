package com.example.shop_service_order.service.impl;

import com.example.shop_service_common.entity.Product;
import com.example.shop_service_order.service.OrderService;
import com.example.shop_service_order.service.ProductFeginClient;
import com.example.shop_service_product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductFeignClientImpl implements OrderService {
    @Autowired
    ProductFeginClient productFeginClient;


    @Override
    public void sayHello() {
        System.out.println("hello~");
    }
}
