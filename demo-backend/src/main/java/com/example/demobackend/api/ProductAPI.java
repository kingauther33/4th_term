package com.example.demobackend.api;

import com.example.demobackend.entity.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/products")
public class ProductAPI {

    private static ArrayList<Product> list = new ArrayList<>();

    static
    {
        list.add(new Product(1, "Product 1", 10000, "anh1.png", 1));
        list.add(new Product(2, "Product 2", 10000, "anh1.png", 1));
        list.add(new Product(3, "Product 3", 10000, "anh1.png", 1));
        list.add(new Product(4, "Product 4", 10000, "anh1.png", 1));
        list.add(new Product(5, "Product 5", 10000, "anh1.png", 1));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Product>> findAll() {
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Product>> create(@RequestBody Product product) {
        list.add(product);
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }
}
