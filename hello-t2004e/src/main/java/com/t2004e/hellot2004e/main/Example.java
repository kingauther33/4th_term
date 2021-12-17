package com.t2004e.hellot2004e.main;

import com.t2004e.hellot2004e.entity.Product;
import com.t2004e.hellot2004e.repository.JpaRepository;

public class Example {
    public static void main(String[] args) {
        JpaRepository<Product> productJpaRepository = new JpaRepository<>(Product.class);
        productJpaRepository.toList();
        // handle EXCEPTION
        try {
            proactiveHandleError("Hung");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Xin lỗi quý khách...");
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
