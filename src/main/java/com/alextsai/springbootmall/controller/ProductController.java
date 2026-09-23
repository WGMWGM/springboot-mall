package com.alextsai.springbootmall.controller;

import com.alextsai.springbootmall.dto.ProductRequest;
import com.alextsai.springbootmall.model.Product;
import com.alextsai.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.ok().body(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody @Valid ProductRequest productRequest) {
        if (productService.updateProduct(productId, productRequest) == 0) {
            return ResponseEntity.notFound().build();
        }
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
