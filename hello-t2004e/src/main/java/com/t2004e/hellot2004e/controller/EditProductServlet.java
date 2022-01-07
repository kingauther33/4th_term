package com.t2004e.hellot2004e.controller;

import com.t2004e.hellot2004e.entity.Product;
import com.t2004e.hellot2004e.repository.JpaRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProductServlet extends HttpServlet {

    private JpaRepository<Product> productJpaRepository = new JpaRepository<>(Product.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Product product = productJpaRepository.findById(id);
            if (product == null) {
                resp.getWriter().println("Product is not found");
            } else {
                req.setAttribute("product", product);
                req.getRequestDispatcher("/admin/product/edit.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.getWriter().println("Bad request");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            int id = Integer.parseInt(req.getParameter("id"));
            Product product = productJpaRepository.findById(id);
            if (product == null) {
                resp.getWriter().println("Product is not found or has been deleted");
            }
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            String description = req.getParameter("description");
            String thumbnail = req.getParameter("thumbnail");
            int status = Integer.parseInt(req.getParameter("status"));

            // update giá trị mới.
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setThumbnail(thumbnail);
            product.setStatus(status);

            // update trong db.
            productJpaRepository.update(product);
            resp.sendRedirect("/admin/product/list");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
