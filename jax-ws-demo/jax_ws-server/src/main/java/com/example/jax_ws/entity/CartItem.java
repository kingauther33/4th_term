package com.example.jax_ws.entity;

// Các túi đồ ở trong giỏ hàng theo nguyên tắc chỉ có một loại sản phẩm
// và các túi không có sản phẩm trùng nhau.
public class CartItem {
    // khóa chính đc tạo nên từ 2 trường là shoppingCartId productId
    private int shoppingCartId; // thuộc xe hàng nào, khóa ngoại từ bảng shopping_carts
    private int productId; // sản phẩm nào trong xe hàng, khóa ngoại từ bảng products
    private String productName;
    private double unitPrice; // giá tại thời điểm bán, không phải giá
    private int quantity;

    public CartItem() {
    }

    public CartItem(int shoppingCartId, int productId, String productName, double unitPrice, int quantity) {
        this.shoppingCartId = shoppingCartId;
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public int getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


