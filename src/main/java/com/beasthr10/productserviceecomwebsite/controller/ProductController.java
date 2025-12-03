package com.beasthr10.productserviceecomwebsite.controller;

import com.beasthr10.productserviceecomwebsite.Builder.ProductMapper;
import com.beasthr10.productserviceecomwebsite.Service.ProductService;
import com.beasthr10.productserviceecomwebsite.dto.CreateProductRequestDTO;
import com.beasthr10.productserviceecomwebsite.dto.ProductResponseDTO;
import com.beasthr10.productserviceecomwebsite.exception.InvalidProductcall;
import com.beasthr10.productserviceecomwebsite.model.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper mapper;

    public ProductController(ProductService productService, ProductMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    // create product
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

        // convert this to dto and  return
        ProductResponseDTO productResponseDTO =  mapper.convertToProductResponseDTO(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDTO);
    }

    //Get product by id
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") Long id){
        Product product = productService.getProductById(id);

        ProductResponseDTO dto = mapper.convertToProductResponseDTO(product);

        return ResponseEntity.ok(dto);
    }

    //Update product
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody CreateProductRequestDTO dto){

        // normalizing input data
        dto.setTitle(dto.getTitle().trim());
        dto.setDescription(dto.getDescription().trim());
        dto.setCategory(dto.getCategory().trim());

        Product product =productService.updateProduct(id,
                dto.getTitle(),
                dto.getDescription(),
                dto.getCategory(),
                dto.getPrice(),
                dto.getImage());

        // convert this to dto and return
        ProductResponseDTO productResponseDTO =  mapper.convertToProductResponseDTO(product);
        return ResponseEntity.ok(productResponseDTO);
    }

    //Delete product
    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }

    // Products by Category

    @GetMapping("/product/cat/{category}")
    public List<ProductResponseDTO> getProductByCategory(@PathVariable("category") String category){
        category.trim();

        List<Product> listproduct = productService.getProductByCategory(category);

        List<ProductResponseDTO> listofprod = new ArrayList<>();

        for(Product product : listproduct){
            ProductResponseDTO dto = mapper.convertToProductResponseDTO(product);
            listofprod.add(dto);
        }
        return listofprod;
    }




}
