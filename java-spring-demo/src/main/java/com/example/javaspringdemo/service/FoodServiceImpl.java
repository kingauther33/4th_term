package com.example.javaspringdemo.service;

import com.example.javaspringdemo.entity.Food;
import com.example.javaspringdemo.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Food save(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public Food update(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    @Override
    public Optional<Food> findById(int id) {
        return foodRepository.findById(id);
    }

    @Override
    public boolean delete(Food food) {
        foodRepository.delete(food);
        return true;
    }
}
