package com.example.javaspringdemo.service;

import com.example.javaspringdemo.entity.Food;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FoodService {

    Food save(Food food);

    Food update(Food food);

    List<Food> findAll();

    Optional<Food> findById(int id);

    boolean delete(Food food);

}
