package com.alextsai.springbootmall.service;

import com.alextsai.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);
}
