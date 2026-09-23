package com.alextsai.springbootmall.service;

import com.alextsai.springbootmall.dto.ProductRequest;
import com.alextsai.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    int updateProduct(Integer productId, ProductRequest productRequest);

    int deleteProductById(Integer productId);
}
