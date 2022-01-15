package com.t1908e.WCD_assignment.entity;

import com.t1908e.WCD_assignment.modelAnnotation.Column;
import com.t1908e.WCD_assignment.modelAnnotation.Entity;
import com.t1908e.WCD_assignment.modelAnnotation.ForeignKey;
import com.t1908e.WCD_assignment.modelAnnotation.Id;
import com.t1908e.WCD_assignment.repository.GenericRepository;
import com.t1908e.WCD_assignment.repository.IGenericRepository;
import com.t1908e.WCD_assignment.util.SQLConstant;
import com.t1908e.WCD_assignment.util.SQLDataTypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(tableName = "foods")
public class Food {
    @Column(columnName = "id", columnType = SQLDataTypes.INTEGER)
    @Id(AutoIncrement = true)
    private int id;

    @Column(columnName = "name", columnType = SQLDataTypes.VARCHAR255)
    private String name;

    @Column(columnName = "description", columnType = SQLDataTypes.TEXT)
    private String description;

    @Column(columnName = "covers", columnType = SQLDataTypes.TEXT)
    private String covers;

    @Column(columnName = "price", columnType = SQLDataTypes.DOUBLE)
    private double price;

    @Column(columnName = "created_at", columnType = SQLDataTypes.DATETIME)
    private Date createdAt;

    @Column(columnName = "updated_at", columnType = SQLDataTypes.DATETIME)
    private Date updatedAt;

    @Column(columnName = "status", columnType = SQLDataTypes.INTEGER)
    private int status; // 1: active 0: deActive -1: deleted

    @Column(columnName = "category_id", columnType = SQLDataTypes.INTEGER)
    @ForeignKey(referenceTable = "categories", referenceColumn = "id")
    private int categoryId;

    public Food() {
    }

    public Food(String name, String description, String covers, double price, Date createdAt, Date updatedAt, int status, int categoryId) {
        this.name = name;
        this.description = description;
        this.covers = covers;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCovers() {
        return covers;
    }

    public void setCovers(String covers) {
        this.covers = covers;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", covers='" + covers + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", status=" + status +
                ", categoryId=" + categoryId +
                '}';
    }

    /**
     * Read Only method.<br>
     * Get the correspond Category of this Food
     * Created by LuuHuy
     *
     * @return an instance of Category class which correspond to this food in database
     */
    public Category getCategory() {
        IGenericRepository<Category> repository = new GenericRepository<Category>(Category.class);
        return repository.find(this.categoryId);
    }
    //validate methods

    private boolean validatorFlag = true;
    private void checkName() {
        if(this.name == null || this.name.length() < 7) {
            validatorFlag = false;
        }
    }
    private void checkPrice() {
        if(this.price <= 0) {
            validatorFlag = false;
        }
    }

    private void checkDescription() {
        if(this.description == null || this.description.length() == 0) {
            validatorFlag = false;
        }
    }
    public boolean isAValidFood() {
        checkDescription();
        checkPrice();
        checkName();
        return validatorFlag;
    }
    //get cloudianary img
    //"https://res.cloudinary.com/dnby4zyda/image/upload/v1616932607/lvnuiiti3efqjb3avjwo.png";
    private static String cloudinaryDomain = "https://res.cloudinary.com/";
    private static String cloudinaryProjectId = "dnby4zyda";
    public ArrayList<String> getListImages() {
        if (this.covers == null || this.covers.length() == 0)
        {
            this.covers = "u5ffd9gmurnsjtqibwdw";
        }
        String[] listCover = this.covers.split(",");
        ArrayList<String> listImagesUrl = new ArrayList<String>();
        for (String item : listCover)
        {
            String url = cloudinaryDomain + cloudinaryProjectId + "/image/upload/v1621514718/" + item + ".jpg";
            listImagesUrl.add(url);
        }
        return listImagesUrl;
    }
    public String getFirstImage() {
        return getListImages().get(0);
    }
    public String getFirstSmallImage() {
        if(this.covers == null || this.covers.length() == 0)
        {
            this.covers = "u5ffd9gmurnsjtqibwdw";
        }
        //get first cover
        String[] listCover = this.covers.split(",");
        String firstCover = listCover[0];
        return cloudinaryDomain + cloudinaryProjectId + "/image/upload/c_scale,w_100/v1616932607/" + firstCover + ".jpg";
    }
}
