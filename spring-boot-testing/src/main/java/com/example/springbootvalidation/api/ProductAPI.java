package com.example.springbootvalidation.api;

import com.example.springbootvalidation.dto.ResponseDTO;
import com.example.springbootvalidation.entity.Product;
import com.example.springbootvalidation.repository.ProductRepository;
import com.example.springbootvalidation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductAPI {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

//    public ResponseEntity<Object> create(@Valid @RequestBody Product product) {
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        ResponseDTO<Product> responseDTO = ResponseDTO.<Product>builder()
                .message("Success")
                .status(HttpStatus.CREATED.value())
                .body(savedProduct)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
