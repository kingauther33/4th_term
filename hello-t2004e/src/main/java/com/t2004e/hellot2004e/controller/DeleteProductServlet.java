package com.t2004e.hellot2004e.controller;

import com.t2004e.hellot2004e.entity.Product;
import com.t2004e.hellot2004e.repository.JpaRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductServlet extends HttpServlet {

    private JpaRepository<Product> productJpaRepository = new JpaRepository<>(Product.class);


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Product product = productJpaRepository.findById(id);
            if (product == null) {
                resp.getWriter().println("Product is not found");
            } else {
                productJpaRepository.delete(id);
                resp.getWriter().println("Oke");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.getWriter().println("Bad request");
        }
    }
}
