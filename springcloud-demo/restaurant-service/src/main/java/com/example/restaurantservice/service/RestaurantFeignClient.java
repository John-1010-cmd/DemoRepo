package com.example.restaurantservice.service;


import com.example.restaurantservice.fallback.RestaurantFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "orderService", fallback = RestaurantFeignClientFallback.class)
//@FeignClient(name = "orderService")
public interface RestaurantFeignClient {
    @GetMapping("/api/v1/order/find")
    String findAllOrders(@RequestParam(value = "id") int id);
}
