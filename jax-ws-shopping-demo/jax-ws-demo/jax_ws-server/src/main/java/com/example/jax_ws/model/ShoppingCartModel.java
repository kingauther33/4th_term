package com.example.jax_ws.model;

import com.example.jax_ws.entity.ShoppingCart;

import java.sql.SQLException;

public interface ShoppingCartModel {
    ShoppingCart get(int userId) throws SQLException; // 1 người dùng 1 shopping cart
    ShoppingCart save(ShoppingCart shoppingCart) throws SQLException;
    ShoppingCart update(int id, ShoppingCart updateObject) throws SQLException;
    boolean delete(int id) throws SQLException;
}
