package com.example.springbootvalidation.repository;

import com.example.springbootvalidation.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest()
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSaveProductSuccess() {
        Product product = new Product();
        product.setId(10l);
        product.setPrice(new BigDecimal(100));
        product.setName("Product 1");
        product.setDescription("Product 1");

        Product savedProduct = productRepository.save(product);

        assertThat(savedProduct).isEqualTo(product);
    }

    @Test
    void testSaveProductFailExistingId() {
        Product product1 = new Product();
        product1.setId(10l);
        product1.setPrice(new BigDecimal(100));
        product1.setName("Product 1");
        product1.setDescription("Product 1");

        Product savedProductOne = productRepository.save(product1);

        Product product2 = new Product();
        product2.setId(10l);
        product2.setPrice(new BigDecimal(100));
        product2.setName("Product 2");
        product2.setDescription("Product 2");

        Product savedProductTwo = productRepository.save(product2);

        assertThat(savedProductOne).isEqualTo(savedProductTwo);
    }
}