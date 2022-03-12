package com.example.jax_ws.model;

import com.example.jax_ws.entity.CartItem;
import com.example.jax_ws.entity.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartModelTest {

    private ShoppingCartModel model;
    @BeforeEach
    void setUp() {
        model = new ShoppingCartModel();
    }

    @Test
    void save() {
        ShoppingCart shoppingCart = new ShoppingCart();
        List<CartItem> items = new ArrayList<>();
        items.add(new CartItem(0, 1, "Product 01", 10000, 1));
        items.add(new CartItem(0, 2, "Product 01", 10000, 2));
        shoppingCart.setCartItems(items);
        shoppingCart.setShipPhone("012345678");
        shoppingCart.setShipAddress("Hanoi");
        shoppingCart.setShipName("Hung");
        shoppingCart.setUserId(1);
        shoppingCart.calculateTotalPrice();
    }
}