package com.example.restaurantservice.service.impl;

import com.example.restaurantservice.domain.Order;
import com.example.restaurantservice.domain.Restaurant;
import com.example.restaurantservice.service.RestaurantFeignClient;
import com.example.restaurantservice.service.RestaurantService;
import com.example.restaurantservice.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantFeignClient restaurantFeignClient;

    private static final Map<Integer, Restaurant> restaurantMap = new HashMap<>();
    static {
        restaurantMap.put(1,new Restaurant(1, "pizza shop"));
        restaurantMap.put(2,new Restaurant(2, "fried-chicken shop"));
        restaurantMap.put(3,new Restaurant(3, "hamburge shop"));
        restaurantMap.put(4,new Restaurant(4, "sandwich shop"));
        restaurantMap.put(5,new Restaurant(5, "noodle shop"));
    }


    @Override
    public List<Restaurant> listRestaurants() {
        Collection<Restaurant> collection = restaurantMap.values();
        List<Restaurant> list = new ArrayList<>(collection);
        return list;
    }

    @Override
    public Order findOrderById(int id) {
        String response = restaurantFeignClient.findAllOrders(id);
        System.out.println("response = " + response);
        JsonNode jsonNode = JsonUtils.str2JsonNode(response);

        Order order = new Order();
        order.setId(jsonNode.get("id").intValue());
        order.setOrdername(jsonNode.get("ordername").toString());
        return order;
    }


}
