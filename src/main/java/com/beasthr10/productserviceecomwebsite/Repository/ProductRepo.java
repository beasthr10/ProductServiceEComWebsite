package com.beasthr10.productserviceecomwebsite.Repository;

import com.beasthr10.productserviceecomwebsite.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {

    Product findByTitle(String title);

    Product findProductById(Long id);

    List<Product> findAllByCategory_Category(String categoryName);


}
