package com.beasthr10.productserviceecomwebsite.Builder;

import com.beasthr10.productserviceecomwebsite.dto.ProductResponseDTO;
import com.beasthr10.productserviceecomwebsite.model.Category;
import com.beasthr10.productserviceecomwebsite.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public static ProductResponseDTO convertToProductResponseDTO(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId(product.getId());
        productResponseDTO.setTitle(product.getTitle());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setImageUrl(product.getImageUrl());
        productResponseDTO.setCategory(product.getCategory());

        return productResponseDTO;
    }
}