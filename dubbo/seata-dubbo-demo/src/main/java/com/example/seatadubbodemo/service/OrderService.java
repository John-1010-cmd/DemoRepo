package com.example.seatadubbodemo.service;

import com.example.seatadubbodemo.Order;

public interface OrderService {
    Order create(String userId, String commodityCode, int orderCount);
}
