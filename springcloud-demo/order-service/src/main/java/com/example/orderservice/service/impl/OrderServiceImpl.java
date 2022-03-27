package com.example.orderservice.service.impl;

import com.example.orderservice.domain.Order;
import com.example.orderservice.service.Orderservice;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements Orderservice {

    private static final Map<Integer, Order> orderMap = new HashMap<>();
    static {
        orderMap.put(1,new Order(1, "pizza"));
        orderMap.put(2,new Order(2, "fried-chicken"));
        orderMap.put(3,new Order(3, "hamburge"));
        orderMap.put(4,new Order(4, "sandwich"));
        orderMap.put(5,new Order(5, "noodle"));
    }

    @Override
    public List<Order> listOrders() {
        Collection<Order> collection = orderMap.values();
        List<Order> list = new ArrayList<>(collection);
        return list;
    }

    @Override
    public Order findById(int id) {
        return orderMap.get(id);
    }
}
