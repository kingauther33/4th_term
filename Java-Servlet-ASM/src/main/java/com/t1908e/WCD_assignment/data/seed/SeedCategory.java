package com.t1908e.WCD_assignment.data.seed;

import com.t1908e.WCD_assignment.entity.Category;
import com.t1908e.WCD_assignment.entity.Food;
import com.t1908e.WCD_assignment.repository.GenericRepository;
import com.t1908e.WCD_assignment.repository.IGenericRepository;

import java.util.ArrayList;

public class SeedCategory implements ISeed{
    private IGenericRepository<Category> repository = new GenericRepository<Category>(Category.class);
    private ArrayList<Category> categories = new ArrayList<Category>();


    @Override
    public void seed() {
        //your seed data goes here
        categories.add(new Category("Món nướng"));
        categories.add(new Category("Món luộc"));
        categories.add(new Category("Món chay"));
        categories.add(new Category("Đồ uống"));
        for (Category category :
                categories) {
            repository.save(category);
        }
    }
}
