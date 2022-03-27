package com.example.orderservice.service;

import com.example.orderservice.domain.Order;

import java.util.List;

public interface Orderservice {

    List<Order> listOrders();
    Order findById(int id);
}
