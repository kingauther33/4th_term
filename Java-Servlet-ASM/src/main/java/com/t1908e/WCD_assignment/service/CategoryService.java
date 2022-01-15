package com.t1908e.WCD_assignment.service;

import com.t1908e.WCD_assignment.entity.Category;
import com.t1908e.WCD_assignment.repository.GenericRepository;
import com.t1908e.WCD_assignment.repository.IGenericRepository;

import java.util.List;

public class CategoryService {
    private IGenericRepository<Category> repository = new GenericRepository<Category>(Category.class);
    public List<Category> getAll() {
        return repository.toList();
    }

    public boolean create(Category category) {
        return repository.save(category);
    }
}
