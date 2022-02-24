package com.example.springbootvalidation.service;

import com.example.springbootvalidation.entity.Product;
import com.example.springbootvalidation.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }
}
