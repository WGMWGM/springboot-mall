package com.alextsai.springbootmall.service;

import com.alextsai.springbootmall.dto.ProductQueryParams;
import com.alextsai.springbootmall.dto.ProductRequest;
import com.alextsai.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    int updateProduct(Integer productId, ProductRequest productRequest);

    int deleteProductById(Integer productId);

    List<Product> getProducts(ProductQueryParams productQueryParams);
}
