package com.beasthr10.productserviceecomwebsite.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class BaseModel {
    private Date createdAt;
    private Date updatedAt;
    private boolean isDeleted;
}
