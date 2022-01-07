package com.t2004e.hellot2004e;

import com.t2004e.hellot2004e.entity.Product;

import java.util.HashMap;
import java.util.Map;

public class TestValidation {
    public static void main(String[] args) {
        Product product = new Product("Sản phẩm 1", "Sản phẩm 1", "https://anh1.png", 100, 1);
        if (product.isValid()) {
            System.out.println("Product hợp lệ");
            System.out.println(product.toString());
        } else {
            HashMap<String, String> errors = product.getErrors();
            for (Map.Entry<String, String> entry :
                    errors.entrySet()) {
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
            }
        }

    }
}
