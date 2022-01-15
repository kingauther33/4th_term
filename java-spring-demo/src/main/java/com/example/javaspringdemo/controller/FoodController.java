package com.example.javaspringdemo.controller;

import com.example.javaspringdemo.entity.Food;
import com.example.javaspringdemo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

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

    @Autowired
    private FoodService foodService;

    // create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Food> create(@RequestBody Food food) {
//      listFood.add(food);
        foodService.save(food);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    // get List
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("page", page);
        response.put("limit", limit);
        response.put("data", foodService.findAll());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // get details
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<Object> getDetail(@PathVariable int id) {
        Optional<Food> optionalFood = foodService.findById(id);
        if (optionalFood.isPresent()) {
            return new ResponseEntity<>(optionalFood.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // update
    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Food updatedFood) {
        Optional<Food> optionalFood = foodService.findById(id);

        // trường hợp = null trả 404.
        if (optionalFood.isPresent()) {
            Food food = optionalFood.get();
            food.setName(updatedFood.getName());
            food.setDescription(updatedFood.getDescription());
            food.setPrice(updatedFood.getPrice());
            food.setStatus(updatedFood.getStatus());
            foodService.save(food);

            return new ResponseEntity<>(food, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // delete
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Optional<Food> optionalFood = foodService.findById(id);

        // trường hợp = null trả 404.
        if (optionalFood.isPresent()) {
            foodService.delete(optionalFood.get());
            return new ResponseEntity<>(true, HttpStatus.OK);
        }

        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
