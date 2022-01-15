package com.t1908e.WCD_assignment.controller;

import com.t1908e.WCD_assignment.entity.Category;
import com.t1908e.WCD_assignment.entity.Food;
import com.t1908e.WCD_assignment.service.CategoryService;
import com.t1908e.WCD_assignment.service.FoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "dashboard", urlPatterns = "/admin")
public class DashboardServlet extends HttpServlet {
    private CategoryService categoryService = new CategoryService();
    private FoodService foodService = new FoodService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Food> foods = foodService.executeRawSql("SELECT * FROM foods WHERE status != -1 ORDER BY created_at LIMIT 5 OFFSET 0");
        List<Category> categories = categoryService.getAll();
        req.setAttribute("list", foods);
        //get food size
        int foodSize = foodService.findAll().size();
        int categorySize = categories.size();
        req.setAttribute("foodSize", foodSize);
        req.setAttribute("categorySize", categorySize);
        req.setAttribute("listCate", categories);
        req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
    }
}
