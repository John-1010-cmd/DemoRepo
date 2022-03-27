package com.example.restaurantservice.domain;

import java.io.Serializable;

public class Restaurant implements Serializable {

    public Restaurant() {
    }

    public Restaurant(int id, String restaurantName) {
        this.id = id;
        this.restaurantName = restaurantName;
    }

    private int id;
    private String restaurantName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
