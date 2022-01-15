package com.t1908e.WCD_assignment.data.seed;

import com.t1908e.WCD_assignment.entity.Food;
import com.t1908e.WCD_assignment.repository.GenericRepository;
import com.t1908e.WCD_assignment.repository.IGenericRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SeedFood implements ISeed {

    private IGenericRepository<Food> repository = new GenericRepository<Food>(Food.class);
    private ArrayList<Food> foods = new ArrayList<Food>();

    @Override
    public void seed() {
        //your seed data goes here
//
        foods.add(new Food("Thịt gà nướng", "Thịt gà nướng giòn ngon", "krws38md2akqpyr6j7wn", 120000, new Date(), new Date(), 1, 1));
        foods.add(new Food("Thịt bò nướng", "thịt bò tươi nướng", "dk7ce84n27s1rewo76oi", 200000, new Date(), new Date(), 1, 1));
        foods.add(new Food("Thịt gà luộc", "Thịt gà luộc", "zcnjk2kh80ivxq5dfc7h", 100000, new Date(), new Date(), 1, 2));
        foods.add(new Food("Thịt ba chỉ luộc", "Thịt ngon", "hpkoksqaj8m40krrmbts", 30000, new Date(), new Date(), 1, 2));
        foods.add(new Food("Bánh bao chay", "bánh bao cho người cho ăn chay", "wefb4yds2i31jqauv9dt", 10000, new Date(), new Date(), 1, 3));
        foods.add(new Food("Cơm chay", "cơm chay", "zwyaqmhufi1khwfgmeh7", 20000, new Date(), new Date(), 1, 3));
        foods.add(new Food("Coca cola", "nước giải khát coca", "ifdceoyda2mtrvfubpdi", 12000, new Date(), new Date(), 1, 4));
        foods.add(new Food("Pepsi", "nước giải khát pepsi", "sjgyovbjo4ruh2vue6i9", 12000, new Date(), new Date(), 1, 4));
        foods.add(new Food("Thịt chim nướng", "Chim bồ câu nướng", "xwlyxmk6vlbodvqr3fny", 110000, new Date(), new Date(), 1, 1));
        foods.add(new Food("Rau muống luộc", "rau muống luộc", "spkysqs5hb4vnvr4lhzn", 4000, new Date(), new Date(), 1, 2));
        for (Food food :
                foods) {
            repository.save(food);
        }
    }


}
