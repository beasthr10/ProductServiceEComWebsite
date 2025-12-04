package com.beasthr10.productserviceecomwebsite.Service;

import com.beasthr10.productserviceecomwebsite.Exception.InvalidProductCreationException;
import com.beasthr10.productserviceecomwebsite.Exception.InvalidProductcall;
import com.beasthr10.productserviceecomwebsite.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    public Product createProduct(String title,
                                 String description,
                                 String category,
                                 BigDecimal price,
                                 String image) throws InvalidProductCreationException;

    public Product getProductById(Long id) throws InvalidProductcall;

    public Product updateProduct(Long id,
                                 String title,
                                 String description,
                                 String category,
                                 BigDecimal price,
                                 String image) throws InvalidProductcall;
    public String deleteProduct(Long id);

    public List<Product> getProductByCategory(String category_name) throws InvalidProductcall;

}
