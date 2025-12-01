package com.beasthr10.productserviceecomwebsite.Repository;

import com.beasthr10.productserviceecomwebsite.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {

    Product findByTitle(String title);

}
