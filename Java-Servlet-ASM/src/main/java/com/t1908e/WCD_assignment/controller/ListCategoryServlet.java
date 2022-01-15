package com.t1908e.WCD_assignment.controller;

import com.t1908e.WCD_assignment.entity.Category;
import com.t1908e.WCD_assignment.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListCategory", urlPatterns = "/admin/category")
public class ListCategoryServlet extends HttpServlet {
    private CategoryService categoryService = new CategoryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> all = categoryService.getAll();
        req.setAttribute("list", all);

        req.getRequestDispatcher("/admin/list-category.jsp").forward(req, resp);
    }
}
