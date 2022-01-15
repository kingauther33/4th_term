package com.t1908e.WCD_assignment.controller;

import com.t1908e.WCD_assignment.service.FoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "DeleteFood", urlPatterns = "/admin/food/delete")
public class DeleteFoodServlet extends HttpServlet {
    private FoodService foodService = new FoodService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        boolean res = foodService.softDelete(id);
        if(!res) {
            req.getRequestDispatcher("/admin/error-404.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/admin/food");
    }
}
