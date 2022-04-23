package com.example.seatadubbodemo.service;

public interface StockService {
    /**
     * 扣减库存
     *
     * @param commodityCode 商品编号
     * @param count         扣减数量
     */
    void deduct(String commodityCode, int count);

    /**
     * 批量扣减
     *
     * @param commodityCode
     * @param count
     */
    void batchDeduct(String commodityCode, int count);
}
