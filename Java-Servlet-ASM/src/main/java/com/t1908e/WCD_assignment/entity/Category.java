package com.t1908e.WCD_assignment.entity;

import com.t1908e.WCD_assignment.modelAnnotation.Column;
import com.t1908e.WCD_assignment.modelAnnotation.Entity;
import com.t1908e.WCD_assignment.modelAnnotation.Id;
import com.t1908e.WCD_assignment.repository.GenericRepository;
import com.t1908e.WCD_assignment.repository.IGenericRepository;
import com.t1908e.WCD_assignment.util.SQLConstant;
import com.t1908e.WCD_assignment.util.SQLDataTypes;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "categories")
public class Category {
    @Id(AutoIncrement = true)
    @Column(columnType = SQLDataTypes.INTEGER, columnName = "id")
    private int id;
    @Column(columnName = "name", columnType = SQLDataTypes.VARCHAR255)
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category() {
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Read Only method.<br>
     * Get the list of food belongs to this category
     * Created by LuuHuy
     *
     * @return list of Food instances
     */
    public List<Food> getFoods() {
        IGenericRepository<Food> repository = new GenericRepository<Food>(Food.class);
        List<Food> foods = repository.where("category_id", SQLConstant.EQUAL, this.id);
        if(foods == null) {
            foods = new ArrayList<Food>();
        }
        return foods;

    }

    //validate methods

    private boolean validatorFlag = true;
    private void checkName() {
        if(this.name == null || this.name.length() == 0) {
            validatorFlag = false;
        }
    }

    public boolean isAValidCategory() {
        checkName();
        return validatorFlag;
    }
}
