package com.t1908e.WCD_assignment.controller;

import com.t1908e.WCD_assignment.entity.Category;
import com.t1908e.WCD_assignment.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "CreateCategory", urlPatterns = "/admin/category/create")
public class CreateCategoryServlet extends HttpServlet {
    private CategoryService categoryService = new CategoryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/create-category.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Category category= new Category(name);
        if(!category.isAValidCategory()) {
            req.setAttribute("status", "Create category failed");
            req.getRequestDispatcher("/admin/create-category.jsp").forward(req,resp);
            return;
        }
        boolean res = categoryService.create(category);
        if(!res) {
            req.setAttribute("status", "Create category failed");
            req.getRequestDispatcher("/admin/create-category.jsp").forward(req,resp);
            return;
        }
        resp.sendRedirect("/admin/category");
    }
}
