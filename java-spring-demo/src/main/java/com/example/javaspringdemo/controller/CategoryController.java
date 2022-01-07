package com.example.javaspringdemo.controller;

import com.example.javaspringdemo.entity.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController // @Controller
@RequestMapping(path = "api/v1/categories")
public class CategoryController {

    // Request Mapping
    @RequestMapping(method = RequestMethod.POST)
    public Category create() {
        return new Category(1, "Category 01");
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> getList() {
        ArrayList<Category> listCategories = new ArrayList<>();
        listCategories.add(new Category(1, "Category 1"));
        listCategories.add(new Category(2, "Category 2"));
        listCategories.add(new Category(3, "Category 3"));
        listCategories.add(new Category(4, "Category 4"));

        return listCategories;
    }

    @RequestMapping(method = RequestMethod.GET, path = "1")
    public Category getDetail() {
        return new Category(10, "Category 1");
    }

    @RequestMapping(method = RequestMethod.PUT, path = "1")
    public Category update() {
        return new Category(11, "Category 1");
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "1")
    public boolean delete() {
        return false;
    }
}
