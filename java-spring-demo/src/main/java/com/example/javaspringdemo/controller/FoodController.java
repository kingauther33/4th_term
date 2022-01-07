package com.example.javaspringdemo.controller;

import com.example.javaspringdemo.entity.Food;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Lưu ý khi đặt link cũng như method của 1 api
 * /api/version/resources (số nhiều)
 * - /api/v1/products
 * - /api/v1/foods
 * - /api/v1/orders
 * <p>
 * - GET http://localhost:8080/api/v1/products - trả về danh sách sản phẩm.
 * - POST http://localhost:8080/api/v1/products
 */

@RestController
@RequestMapping(path = "api/v1/foods")
public class FoodController {

    private static ArrayList<Food> listFood;

    static {
        listFood = new ArrayList<>();
        listFood.add(new Food(1, "Food 01", "Food 01", 1000, 1));
        listFood.add(new Food(2, "Food 02", "Food 02", 1000, 1));
        listFood.add(new Food(3, "Food 03", "Food 03", 1000, 1));
        listFood.add(new Food(4, "Food 04", "Food 04", 1000, 1));
        listFood.add(new Food(5, "Food 05", "Food 05", 1000, 1));
        listFood.add(new Food(6, "Food 06", "Food 06", 1000, 1));
    }

    // create
    @RequestMapping(method = RequestMethod.POST)
    public Food create(@RequestBody Food food) {
        listFood.add(food);

        return food;
    }

    // get List
    @RequestMapping(method = RequestMethod.GET)
    public HashMap<String, Object> getList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("page", page);
        response.put("limit", limit);
        response.put("data", listFood);

        return response;
    }

    // get details
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public Food getDetail(@PathVariable int id) {
        for (Food food :
                listFood) {
            if (food.getId() == id) {
                return food;
            }
        }

        return new Food();
    }

    // update
    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public Food update(@PathVariable int id, @RequestBody Food updatedFood) {
        Food foodExists = null;
        for (Food food:
             listFood) {
            if (food.getId() == id) {
                foodExists = food;
            }
        }
        if (foodExists != null) {
            foodExists.setName(updatedFood.getName());
            foodExists.setDescription(updatedFood.getDescription());
            foodExists.setPrice(updatedFood.getPrice());
            foodExists.setStatus(updatedFood.getStatus());
        }
        return foodExists;
    }

    // delete
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public boolean delete(@PathVariable int id) {
        int indexItem = -1;
        for (int i = 0; i < listFood.size(); i++) {
            if(listFood.get(i).getId() == id) {
                indexItem = i;
            }
        }
        if (indexItem == -1) return false;

        listFood.remove(indexItem);

        return true;
    }
}
