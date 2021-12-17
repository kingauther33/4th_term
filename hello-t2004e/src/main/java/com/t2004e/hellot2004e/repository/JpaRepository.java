package com.t2004e.hellot2004e.repository;

import com.t2004e.hellot2004e.annotation.Entity;
import com.t2004e.hellot2004e.exception.EntityException;

import java.sql.Connection;
import java.util.List;

public class JpaRepository<T> {

    // Thông tin getClass của repository.
    private Class<T> clazz;

    // Constructor nhận tham số đầu vào là 1 kiểu Class, thông tin này đc
    // lưu lại để dùng cho cac function bên dưới
    public JpaRepository(Class<T> clazz) {
        this.clazz = clazz;
    }

    private  boolean isEntity() {
        return clazz.isAnnotationPresent(Entity.class);
    }

    public List<T> toList() {
        return null;
    }

    public boolean save(T obj) {
        try {
            if (!isEntity()) {
                // chủ động quăng lỗi
                throw new EntityException("Not an entity model, check your annotation")
            }
            Connection connection
        } catch (EntityException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }
}
