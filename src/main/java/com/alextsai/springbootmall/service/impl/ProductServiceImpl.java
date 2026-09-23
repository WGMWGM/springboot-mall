package com.alextsai.springbootmall.service.impl;

import com.alextsai.springbootmall.dao.ProductDao;
import com.alextsai.springbootmall.dto.ProductRequest;
import com.alextsai.springbootmall.model.Product;
import com.alextsai.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public int updateProduct(Integer productId, ProductRequest productRequest) {
        return productDao.updateProduct(productId,productRequest);
    }

    @Override
    public int deleteProductById(Integer productId) {
        return productDao.deleteProductById(productId);
    }
}
