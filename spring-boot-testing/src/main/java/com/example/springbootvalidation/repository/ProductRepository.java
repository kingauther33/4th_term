package com.example.springbootvalidation.repository;

import com.example.springbootvalidation.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
