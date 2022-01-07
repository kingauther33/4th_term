package com.t2004e.hellot2004e.entity;

import com.t2004e.hellot2004e.annotation.Column;
import com.t2004e.hellot2004e.annotation.Entity;
import com.t2004e.hellot2004e.annotation.Id;
import com.t2004e.hellot2004e.util.SQLDataTypes;
import com.t2004e.hellot2004e.util.ValidationUtil;

import java.util.HashMap;

@Entity(tableName = "products")
public class Product {

    @Id(autoIncrement = true)
    @Column(columnName = "id", columnType = SQLDataTypes.INTEGER)
    private int id;
    @Column(columnName = "name", columnType = SQLDataTypes.VARCHAR50)
    private String name;
    @Column(columnName = "description", columnType = SQLDataTypes.VARCHAR255)
    private String description;
    @Column(columnName = "thumbnail", columnType = SQLDataTypes.TEXT)
    private String thumbnail;
    @Column(columnName = "price", columnType = SQLDataTypes.DOUBLE)
    private double price;
    @Column(columnName = "status", columnType = SQLDataTypes.INTEGER)
    private int status;

    public Product() {
        this.name = "";
        this.description = "";
        this.thumbnail = "";
        this.price = 0;
        this.status = 1;
    }

    public Product(String name, String description, String thumbnail, double price, int status) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.status = status;
    }

    public Product(int id, String name, String description, String thumbnail, double price, int status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // kiểm tra đối tượng có hợp lệ hay không.
    public boolean isValid() {
        return getErrors().size() == 0;
    }

    // trả về danh sách lỗi
    public HashMap<String, String> getErrors() {
        HashMap<String, String> errors = new HashMap<>();
        if (name == null || name.length() == 0) {
            errors.put("name", "Vui lòng nhập tên sản phẩm");
        }

        if (description == null || description.length() == 0) {
            errors.put("description", "Vui lòng nhập mô tả sản phẩm");
        }

        if (thumbnail == null || thumbnail.length() == 0) {
            errors.put("thumbnail", "Vui lòng lựa chọn ảnh cho sản phẩm.");
        } else if (!ValidationUtil.checkUrl(thumbnail)) {
            errors.put("thumbnail", "Ảnh sai định dạng, vui lòng nhập vào một link.");
        }

        if (price ==  0) {
            errors.put("price", "Vui lòng nhập giá cho sản phẩm.");
        }
        return errors;
    }
}
