package com.beasthr10.productserviceecomwebsite.Service;

import com.beasthr10.productserviceecomwebsite.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    public Product createProduct(String title,
                                 String description,
                                 String category,
                                 BigDecimal price,
                                 String image);

    public Product getProductById(Long id);

    public Product updateProduct(Long id,
                                 String title,
                                 String description,
                                 String category,
                                 BigDecimal price,
                                 String image);
    public String deleteProduct(Long id);

    public List<Product> getProductByCategory(String category_name);

}
