package com.example.hellosercurity.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/products")
public class ProductAPI {

    @RequestMapping(method = RequestMethod.GET)
    public String getProduct(){
        return "Hello product";
    }

}
