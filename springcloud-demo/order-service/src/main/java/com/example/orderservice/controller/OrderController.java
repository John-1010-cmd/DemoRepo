package com.example.orderservice.controller;

import com.example.orderservice.domain.Order;
import com.example.orderservice.service.Orderservice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private Orderservice orderservice;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("buy")
    public Object order() {
        //通过restTemplate调用商品微服务
//        开启负载均衡，按应用名调用
        String url="http://orderService/api/v1/order/find";
//        不开启负载均衡，可以按域名调用
//        String url="http://localhost:8771/api/v1/order/find";
        Map<String,Integer> params = new HashMap<String,Integer>();
        params.put("id",1);
        Order order = restTemplate.getForObject(url+"?id={id}", Order.class,params);
        return order;
    }

    @RequestMapping("list")
    public Object List(){
        System.out.println(" port : "+port);
        ArrayList<Order> orderList = new ArrayList();
        for(Order order : orderservice.listOrders()){
            Order returnOrder = new Order();
            BeanUtils.copyProperties(order, returnOrder);
            returnOrder.setOrdername(returnOrder.getOrdername() + " data from server port : " + port);
            orderList.add(returnOrder);
        }
        return orderList;
    }

    @RequestMapping("find")
    public Object getOrderById(@RequestParam(value = "id") int id){
        System.out.println(" port : "+port);
        return orderservice.findById(id);
    }
}
