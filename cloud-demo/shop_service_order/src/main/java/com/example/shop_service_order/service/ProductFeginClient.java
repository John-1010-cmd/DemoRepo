package com.example.shop_service_order.service;

import com.example.shop_service_common.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="shop-service-product")
public interface ProductFeginClient {
    @RequestMapping(value = "/api/v1/order/find",method = RequestMethod.GET)
    public Product findById(@PathVariable("id") Long id);
}

