package com.alextsai.springbootmall.dao;

import com.alextsai.springbootmall.dto.ProductRequest;
import com.alextsai.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
