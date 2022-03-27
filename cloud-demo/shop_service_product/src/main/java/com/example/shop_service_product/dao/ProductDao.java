package com.example.shop_service_product.dao;

import com.example.shop_service_common.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductDao extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
}
