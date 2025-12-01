package com.beasthr10.productserviceecomwebsite.Repository;

import com.beasthr10.productserviceecomwebsite.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    Category findByCategory(String title);
}
