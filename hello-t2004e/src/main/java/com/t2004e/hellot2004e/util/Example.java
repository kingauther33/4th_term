package com.t2004e.hellot2004e.util;

import com.t2004e.hellot2004e.entity.Product;
import com.t2004e.hellot2004e.repository.JpaRepository;

import java.util.List;

public class Example {
    public static void main(String[] args) {
        JpaRepository<Product> jpaRepository = new JpaRepository<>(Product.class);
        List<Product> list = jpaRepository.where("price", ">=", "10000");
        for (Product p :
                list) {
            System.out.println(p.toString());
        }
    }

    // cảnh báo cho thằng gọi hàm này có thể có lỗi
    static void proactiveHandleError(String name) throws Exception {
        if (name.equals("Hung")) {
            // chủ động quăng lỗi
            throw new Exception("Ko choi voi Hung");
        }
        System.out.println(name);
    }
}
