package com.example.seatadubbodemo.service.impl;

import com.example.seatadubbodemo.service.BusinessService;
import com.example.seatadubbodemo.service.OrderService;
import com.example.seatadubbodemo.service.StockService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class BusinessServiceImpl implements BusinessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessService.class);

    private StockService stockService;
    private OrderService orderService;
    private Random random = new Random();

    @Override
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-demo-tx")
    public void purchase(String userId, String commodityCode, int orderCount) {
        LOGGER.info("purchase begin ... xid: " + RootContext.getXID());
        stockService.deduct(commodityCode, orderCount);
        // just test batch update
        //stockService.batchDeduct(commodityCode, orderCount);
        orderService.create(userId, commodityCode, orderCount);
        if (random.nextBoolean()) {
            throw new RuntimeException("random exception mock!");
        }

    }

    /**
     * Sets stock service.
     *
     * @param stockService the stock service
     */
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Sets order service.
     *
     * @param orderService the order service
     */
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

}
