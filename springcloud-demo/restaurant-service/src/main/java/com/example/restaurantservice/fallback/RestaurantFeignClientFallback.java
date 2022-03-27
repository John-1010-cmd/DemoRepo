package com.example.restaurantservice.fallback;

import com.example.restaurantservice.service.RestaurantFeignClient;
import org.springframework.stereotype.Component;

@Component
public class RestaurantFeignClientFallback implements RestaurantFeignClient {

    @Override
    public String findAllOrders(int id) {
        System.out.println("降级处理！");
        return "{\"id\":\"0\",\"ordername\":\"default\"}";
    }
}
