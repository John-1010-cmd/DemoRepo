package com.example.shop_service_product.controller;

import com.example.shop_service_common.entity.Product;
import com.example.shop_service_product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Value("${server.port}")
    private String port;
    @Value("${spring.cloud.client.ip-address}")
    private String ip;

    @GetMapping
    public List findAll() {
        return productService.findAll();
    }

//    @GetMapping("/{id}")
//    public Product findById(@PathVariable Long id) {
//        return productService.findById(id);
//    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
//        Product product = productService.findById(id);
//        //设置端口
//        product.setProductDesc("调用shop-service-product服务,ip:"+ip+",服务提供者端:"+port);
//        return product;
        System.out.println("id = "+id);
        System.out.println("test success....");
        return null;
}

    @PostMapping
    public String save(@RequestBody Product product) {
        productService.save(product);
        return "保存成功";
    }
    @PutMapping("/{id}")
    public String update(@RequestBody Product product) {
        productService.update(product);
        return "修改成功";
    }

    @DeleteMapping("/{id}")
    public String delete(Long id) {
        productService.delete(id);
        return "删除成功";
    }
}
