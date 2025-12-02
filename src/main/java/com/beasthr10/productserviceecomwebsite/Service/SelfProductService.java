package com.beasthr10.productserviceecomwebsite.Service;

import com.beasthr10.productserviceecomwebsite.Repository.CategoryRepo;
import com.beasthr10.productserviceecomwebsite.Repository.ProductRepo;
import com.beasthr10.productserviceecomwebsite.exception.InvalidProductCreationException;
import com.beasthr10.productserviceecomwebsite.exception.InvalidProductcall;
import com.beasthr10.productserviceecomwebsite.model.Category;
import com.beasthr10.productserviceecomwebsite.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    // Get Product by id
    @Override
    public Product getProductById(Long id){
        return productRepo.findById(id)
                .orElseThrow(() -> new InvalidProductcall(
                        "Product with id " + id + " does not exist"));
    }

    // Update Product
    @Override
    public Product updateProduct(Long id,String title, String description, String titlecategory, BigDecimal price, String image){

        Product product =productRepo.findById(id)
                .orElseThrow(() -> new InvalidProductcall(
                        "Product with id " + id + " does not exist"));

        Category exestingcat = categoryRepo.findByCategory(titlecategory);

        if(exestingcat == null){
            exestingcat = new Category();
            exestingcat.setCategory(titlecategory);
            exestingcat.setCreatedAt(new Date());
            exestingcat.setUpdatedAt(new Date());
            exestingcat.setDeleted(false);
            exestingcat =   categoryRepo.save(exestingcat);
        }
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);
        product.setCategory(exestingcat);
        product.setUpdatedAt(new Date());
        product.setDeleted(false);

        return productRepo.save(product);

    }
    // Delete Product
    @Override
    public String deleteProduct(Long id){
        Optional<Product> productopt = productRepo.findById(id);

        if(!productopt.isPresent()){
            return "Product with id " + id + " does not exist";
        }

        Product product = productopt.get();
        product.setDeleted(true);
        productRepo.save(product);
        return "Product with id " + id + " deleted successfully";

    }

    // List of Product By Categeory
    @Override
    public List<Product> getProductByCategory(String category_name){

        Category exestingcat = categoryRepo.findByCategory(category_name);

        if(exestingcat == null){
            throw new InvalidProductcall("Category with title " + category_name + " does not exist");
        }

        return productRepo.findAllByCategory_Category(category_name);
    }




}
