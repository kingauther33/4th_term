package com.t1908e.WCD_assignment.controller;

import com.t1908e.WCD_assignment.entity.Food;
import com.t1908e.WCD_assignment.service.FoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListFood", urlPatterns = {"/admin/food"})
public class ListFoodServlet extends HttpServlet {
    private FoodService service = new FoodService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Food> all = service.findAll();
        //pagination handlers
        String pageString = req.getParameter("page");
        String limitString = req.getParameter("limit");
        int page;
        int limit;
        try {
            page = Integer.parseInt(pageString);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            page = 1;
        }


        try {
            limit = Integer.parseInt(limitString);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            limit = 5;
        }
        int startIndex = (page - 1) * limit;
        int endIndex = page * limit;
        if (startIndex < 0) {
            req.getRequestDispatcher("/admin/list-food.jsp").forward(req, resp);
            return;
        }
        if (endIndex > all.size()) {
            req.getRequestDispatcher("/admin/list-food.jsp").forward(req, resp);
            return;
        }
        int totalPage = all.size() / limit;
        if(page < totalPage) {
            req.setAttribute("nextPage", page + 1);
        }
        if(page > 1) {
            req.setAttribute("previousPage", page - 1);
        }

        req.setAttribute("currentLimit", limit);
        req.setAttribute("totalPage", all.size() / limit);
        req.setAttribute("currentPage", page);
        //slide array
        List<Food> sliced = all.subList(startIndex, endIndex);
        req.setAttribute("list", sliced);
        List<Food> paginated = service.paginate(limit, startIndex);
        req.setAttribute("list", paginated);
        req.getRequestDispatcher("/admin/list-food.jsp").forward(req, resp);
    }
}
