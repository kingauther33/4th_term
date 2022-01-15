package com.t1908e.WCD_assignment.service;

import com.t1908e.WCD_assignment.entity.Food;
import com.t1908e.WCD_assignment.repository.GenericRepository;
import com.t1908e.WCD_assignment.repository.IGenericRepository;
import com.t1908e.WCD_assignment.util.SQLConstant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FoodService {

    private IGenericRepository<Food> repository = new GenericRepository<Food>(Food.class);

    public boolean create(Food food) {
        food.setCreatedAt(new Date());
        food.setUpdatedAt(new Date());
        food.setStatus(1);
        return repository.save(food);
    }

    public List<Food> findAll() {
        return repository.where("status", SQLConstant.NOT_EQUAL_TO, -1);
    }

    public Food findById(int id) {
        return repository.find(id);
    }

    public boolean update(Food food) {
        if(food.getStatus() == -1 || food.getStatus() == 0) {
            return false;
        }
        food.setUpdatedAt(new Date());
        return repository.update(food);
    }

    public boolean toggleStatus(int id) {
        Food food = repository.find(id);
        if (food == null) {
            return false;
        }
        int status = food.getStatus();
        if (status == -1) {
            return false;
        }
        if (status == 1) {
            food.setStatus(0);
        }
        if (status == 0) {
            food.setStatus(1);
        }
        return repository.update(food);

    }

    public boolean softDelete(int id) {
        //change food status to -1
        Food food = repository.find(id);
        if (food == null) {
            return false;
        }
        int status = food.getStatus();
        if (status == -1) {
            return false;
        }
        food.setStatus(-1);
        return repository.update(food);
    }

    public boolean hardDelete(int id) {
        //delete specific food row in db
        return repository.delete(id);
    }

    public List<Food> paginate(int limit, int offset) {
        return repository.executeRawSqlQuery(String.format("SELECT * FROM foods WHERE status != -1 ORDER BY id LIMIT %d OFFSET %d", limit, offset));

    }

    public List<Food> executeRawSql(String q) {
        return repository.executeRawSqlQuery(q);
    }
}
