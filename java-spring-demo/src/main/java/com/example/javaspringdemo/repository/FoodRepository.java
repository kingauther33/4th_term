package com.example.javaspringdemo.repository;

import com.example.javaspringdemo.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {
}
