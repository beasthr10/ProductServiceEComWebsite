package com.beasthr10.productserviceecomwebsite.controller;

import com.beasthr10.productserviceecomwebsite.Builder.ProductMapper;
import com.beasthr10.productserviceecomwebsite.Service.ProductService;
import com.beasthr10.productserviceecomwebsite.dto.CreateProductRequestDTO;
import com.beasthr10.productserviceecomwebsite.dto.ProductResponseDTO;
import com.beasthr10.productserviceecomwebsite.model.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper mapper;

    public ProductController(ProductService productService, ProductMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody CreateProductRequestDTO dto){

        // normalizing input data
        dto.setTitle(dto.getTitle().trim());
        dto.setDescription(dto.getDescription().trim());
        dto.setCategory(dto.getCategory().trim());


        Product product =productService.createProduct(dto.getTitle(),
                dto.getDescription(),
                dto.getCategory(),
                dto.getPrice(),
                dto.getImage());

        // convert this to dto and return
        ProductResponseDTO productResponseDTO =  mapper.convertToProductResponseDTO(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDTO);
    }

}
