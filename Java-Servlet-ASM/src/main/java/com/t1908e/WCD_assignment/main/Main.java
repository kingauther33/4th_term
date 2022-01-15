package com.t1908e.WCD_assignment.main;

import com.t1908e.WCD_assignment.data.migration.InitDatabase;
import com.t1908e.WCD_assignment.data.seed.SeedCategory;
import com.t1908e.WCD_assignment.entity.Category;
import com.t1908e.WCD_assignment.entity.Food;
import com.t1908e.WCD_assignment.repository.GenericRepository;
import com.t1908e.WCD_assignment.repository.IGenericRepository;
import com.t1908e.WCD_assignment.util.SQLConstant;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InitDatabase.init();

    }
}
