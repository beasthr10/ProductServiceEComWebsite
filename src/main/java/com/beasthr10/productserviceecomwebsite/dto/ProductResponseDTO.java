package com.beasthr10.productserviceecomwebsite.dto;

import com.beasthr10.productserviceecomwebsite.model.Category;

import java.math.BigDecimal;

@lombok.Data
public class ProductResponseDTO {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private Category category;
}
