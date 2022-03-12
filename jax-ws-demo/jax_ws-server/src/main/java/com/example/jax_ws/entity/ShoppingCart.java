package com.example.jax_ws.entity;

import java.util.List;

public class ShoppingCart {
    private int id; // khóa chính
    private int userId; // của ai, để default hard code = 1.
    private String shipName;
    private String shipAddress;
    private String shipPhone;
    private double totalPrice;
    private List<CartItem> cartItems;

    public ShoppingCart() {
    }

    public ShoppingCart(int id, int userId, String shipName, String shipAddress, String shipPhone, double totalPrice, List<CartItem> cartItems) {
        this.id = id;
        this.userId = userId;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipPhone = shipPhone;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipPhone() {
        return shipPhone;
    }

    public void setShipPhone(String shipPhone) {
        this.shipPhone = shipPhone;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void calculateTotalPrice() {
        double totalPrice = 0;
        for (CartItem item:
             this.getCartItems()) {
            totalPrice += item.getUnitPrice() * item.getQuantity();
        }

        this.setTotalPrice(totalPrice);
    }
}
