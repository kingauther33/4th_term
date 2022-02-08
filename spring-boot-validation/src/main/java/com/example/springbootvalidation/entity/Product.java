package com.example.springbootvalidation.entity;

import com.example.springbootvalidation.validation.ProductIdExisting;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @ProductIdExisting
    private Long id;

    @NotNull
    private String name;

    @Size(max = 100)
    private String description;

    @Min(1)
    private BigDecimal price;
}
