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

@WebServlet(name = "EditFood", urlPatterns = {"/admin/food/edit"})
public class EditFoodServlet extends HttpServlet {
    FoodService service = new FoodService();
    CategoryService categoryService = new CategoryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if(idStr == null || idStr.length() == 0) {
            req.getRequestDispatcher("/admin/error-404.jsp").forward(req, resp);
            return;
        }
        int id = 0;
        try {
           id = Integer.parseInt(idStr);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            req.getRequestDispatcher("/admin/error-404.jsp").forward(req, resp);
            return;
        }
        Food current = service.findById(id);
        if(current == null) {
            req.getRequestDispatcher("/admin/error-404.jsp").forward(req, resp);
            return;
        }
        //can not edit a deleted food and disable food
        if(current.getStatus() == -1 || current.getStatus() == 0) {
            req.getRequestDispatcher("/admin/error-404.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("food", current);
        List<Category> cates = categoryService.getAll();
        req.setAttribute("listCat", cates);
        req.getRequestDispatcher("/admin/edit-food.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = 0.00;
        try {
            price = Double.parseDouble(req.getParameter("price"));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            price = 0.00;
        }
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        Food food = service.findById(Integer.parseInt(req.getParameter("id")));
        food.setName(name);
        food.setDescription(description);
        food.setCategoryId(categoryId);
        food.setDescription(description);
        food.setPrice(price);
        String[] thumbnails = req.getParameterValues("thumbnails");
        if(thumbnails != null && thumbnails.length > 0) {
            StringBuilder thumbnailsCommaSeparated = new StringBuilder();
            for (String thumb :
                    thumbnails) {
                thumbnailsCommaSeparated.append(thumb);
                thumbnailsCommaSeparated.append(",");
            }
            //delete last comma
            thumbnailsCommaSeparated.setLength(thumbnailsCommaSeparated.length() - 1);
            food.setCovers(thumbnailsCommaSeparated.toString());
        }


        if(!food.isAValidFood()) {
            req.setAttribute("status", "Invalid data");
            List<Category> cates = categoryService.getAll();
            req.setAttribute("listCat", cates);
            req.getRequestDispatcher("/admin/create-food.jsp").forward(req, resp);
            return;
        }
        boolean res = service.update(food);
        if(!res) {
            req.setAttribute("status", "Update food failed");
            List<Category> cates = categoryService.getAll();
            req.setAttribute("listCat", cates);
            req.getRequestDispatcher("/admin/create-food.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/admin/food");
    }
}
