package com.example.jax_ws.model;

import com.example.jax_ws.entity.CartItem;
import com.example.jax_ws.entity.Product;
import com.example.jax_ws.entity.ShoppingCart;
import com.example.jax_ws.utils.ConnectionHelper;

import java.sql.*;

public class ShoppingCartModel {

    private Connection conn;

    public ShoppingCartModel() {
        conn = ConnectionHelper.getConnection();
    }

    public ShoppingCart save(ShoppingCart shoppingCart) throws SQLException {
        boolean isSuccess = true;
        conn.setAutoCommit(false);
        try {
            // trường hợp shoppingCart null hoặc không có sản phẩm
            if (shoppingCart == null || shoppingCart.getCartItems().size() == 0) {
                throw new Error("Shopping's null or empty.");
            }
            PreparedStatement stmtShoppingCart = conn.prepareStatement("insert into shopping_carts (userId, shipName, shipAddress, shipPhone, totalPrice) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmtShoppingCart.setInt(1, shoppingCart.getUserId());
            stmtShoppingCart.setString(2, shoppingCart.getShipName());
            stmtShoppingCart.setString(3, shoppingCart.getShipAddress());
            stmtShoppingCart.setString(4, shoppingCart.getShipPhone());
            stmtShoppingCart.setDouble(5, shoppingCart.getTotalPrice());
            int affectedRows = stmtShoppingCart.executeUpdate();
            if (affectedRows > 0) {
                ResultSet resultSetGeneratedKeys = stmtShoppingCart.getGeneratedKeys();
                if (resultSetGeneratedKeys.next()) {
                    int id = resultSetGeneratedKeys.getInt(1);
                    shoppingCart.setId(id);
                }
            }
            if (shoppingCart.getId() == 0) {
                throw new Error("Can't insert to shoping cart.");
            }

            // insert vào cart_items
            for (CartItem item : shoppingCart.getCartItems()) {
                PreparedStatement stmtCartItem = conn.prepareStatement("insert into cart_items (shoppingCartId , productId, productName, unitPrice, quantity) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                stmtCartItem.setInt(1, shoppingCart.getId());
                stmtCartItem.setInt(2, item.getProductId());
                stmtCartItem.setString(3, item.getProductName());
                stmtCartItem.setDouble(4, item.getUnitPrice());
                stmtCartItem.setInt(5, item.getQuantity());
                int affectedCartItemRows = stmtShoppingCart.executeUpdate();
                if (affectedCartItemRows == 0) { // lỗi xảy ra
                    throw new Error("Insert cart item fails.");
                }
            }

            conn.commit(); // lưu tất cả vào db
        } catch (Exception ex) {
            shoppingCart = null;
            conn.rollback();
        } finally {
            conn.setAutoCommit(true); // trả trạng thái auto commit default.
        }

        return shoppingCart;
    }

    public Product update(int id, Product updateProduct) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("update products set name = ?, price = ?, status = ? where id = ?");
        stmt.setString(1, updateProduct.getName());
        stmt.setDouble(2, updateProduct.getPrice());
        stmt.setInt(3, updateProduct.getStatus());
        stmt.setInt(4, id);
        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
            updateProduct.setId(id);
            return updateProduct;
        }
        return null;
    }

    public boolean delete(int id) throws SQLException {
        PreparedStatement stmtDelete = conn.prepareStatement("delete from products where id = ?");
        stmtDelete.setInt(1, id);
        int affectedRows = stmtDelete.executeUpdate();
        if (affectedRows > 0) {
            return true;
        }
        return false;
    }


}
