package com.example.orderservice.domain;

import java.io.Serializable;

public class Order implements Serializable {

    public Order() {
    }

    public Order(int id, String ordername) {
        this.id = id;
        this.ordername = ordername;
    }

    private int id;
    private String ordername;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }
}
