package com.alextsai.springbootmall.dto;

import com.alextsai.springbootmall.constant.ProductCategory;
import jakarta.validation.constraints.NotNull;

public class ProductQueryParams {
    private String keyword;
    private ProductCategory category;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
