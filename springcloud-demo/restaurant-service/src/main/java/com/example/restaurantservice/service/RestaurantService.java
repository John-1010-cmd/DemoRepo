package com.example.restaurantservice.service;

import com.example.restaurantservice.domain.Order;
import com.example.restaurantservice.domain.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> listRestaurants();

    Order findOrderById(int id);
}
