package com.example.restaurantservice.controller;



import com.example.restaurantservice.domain.Restaurant;
import com.example.restaurantservice.service.RestaurantService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/restaurant")
public class RestaurantController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping("list")
    public Object List(){
        System.out.println(" port : "+port);
        ArrayList<Restaurant> restaurantList = new ArrayList();
        for(Restaurant restaurant : restaurantService.listRestaurants()){
            Restaurant returnRestaurant = new Restaurant();
            BeanUtils.copyProperties(restaurant, returnRestaurant);
            returnRestaurant.setRestaurantName(returnRestaurant.getRestaurantName() + " data from server port : " + port);
            restaurantList.add(returnRestaurant);
        }
        return restaurantList;
    }

    @RequestMapping("findOrder")
//    @HystrixCommand(fallbackMethod = "defaultFindOrderById")
    @HystrixCommand(fallbackMethod = "defaultFindOrderById",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public Object findOrderById(@RequestParam("order_id")int orderId){
        System.out.println(" port : "+port);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("code",0);
        result.put("data",restaurantService.findOrderById(orderId));
        return result;
    }

    //方法签名和API方法一致
    public Object defaultFindOrderById(int orderId){
        Map<String, Object> result = new HashMap<>();
        result.put("code",-1);
        result.put("msg","暂时不提供服务，稍后重试！");
        return result;
    }

}
