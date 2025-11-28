package com.beasthr10.productserviceecomwebsite.Service;

import com.beasthr10.productserviceecomwebsite.model.Product;

import java.math.BigDecimal;

public interface ProductService {
    public Product createProduct(String title, String description, String category, BigDecimal price, String image);
}
