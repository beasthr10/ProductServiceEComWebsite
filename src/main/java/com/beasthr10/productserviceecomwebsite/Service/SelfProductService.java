package com.beasthr10.productserviceecomwebsite.Service;

import com.beasthr10.productserviceecomwebsite.Repository.CategoryRepo;
import com.beasthr10.productserviceecomwebsite.Repository.ProductRepo;
import com.beasthr10.productserviceecomwebsite.exception.InvalidProductCreationException;
import com.beasthr10.productserviceecomwebsite.model.Category;
import com.beasthr10.productserviceecomwebsite.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class SelfProductService implements ProductService{

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;


    public SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product createProduct(String title, String description, String titlecategory, BigDecimal price, String image) {

        //NORMALIZE INPUT DATA
        titlecategory = titlecategory.toLowerCase();
        title = title.toLowerCase();

        //CHECK IF PRODUCT ALREADY EXISTS
        if (productRepo.findByTitle(title) != null){
            throw new InvalidProductCreationException("Product with title " + title + " already exists");
        }

        //CATEGORY EXIST CHECK
        Category exestingcat = categoryRepo.findByCategory(titlecategory);

        if(exestingcat == null){
            exestingcat = new Category();
            exestingcat.setCategory(titlecategory);
            exestingcat.setCreatedAt(new Date());
            exestingcat.setUpdatedAt(new Date());
            exestingcat.setDeleted(false);
            exestingcat =   categoryRepo.save(exestingcat);
        }

        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);
        product.setCategory(exestingcat);
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        product.setDeleted(false);
        return productRepo.save(product);
    }
}
