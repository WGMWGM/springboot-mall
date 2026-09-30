package com.alextsai.springbootmall.dao;

import com.alextsai.springbootmall.dto.ProductRequest;
import com.alextsai.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    int updateProduct(Integer productId, ProductRequest productRequest);

    int deleteProductById(Integer productId);

    List<Product> getProducts();
}
