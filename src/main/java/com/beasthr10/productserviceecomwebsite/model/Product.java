package com.beasthr10.productserviceecomwebsite.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
public class Product extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    @ManyToOne(cascade = {CascadeType.PERSIST}) // cascade type is used to do action in category if it is delete
    private Category category;
}
