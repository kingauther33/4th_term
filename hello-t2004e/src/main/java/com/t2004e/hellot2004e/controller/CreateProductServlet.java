package com.t2004e.hellot2004e.controller;

import com.t2004e.hellot2004e.entity.Product;
import com.t2004e.hellot2004e.repository.JpaRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class CreateProductServlet extends HttpServlet {
    private JpaRepository<Product> productJpaRepository = new JpaRepository<>(Product.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            String description = req.getParameter("description");
            String thumbnail = req.getParameter("thumbnail");
            int status = Integer.parseInt(req.getParameter("status"));
            // validate data.

            // tạo ra đối tượng của lớp product.
            Product product = new Product(name, description, thumbnail, price, status);
            // kiểm tra tính hợp lệ bằng hàm isValidate
            if (product.isValid()) {
                productJpaRepository.save(product);
                resp.sendRedirect("/admin/product/list");
            } else {
                // trong trường hợp lỗi thì,
                // trả về lại chính form đó,
                // kèm theo thông tin lỗi
                HashMap<String, String> errors = product.getErrors();

                req.setAttribute("errors", errors);
                // trả về thông tin product đang bị lỗi để show lại cho người dùng.
                req.setAttribute("product", product);
                req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            resp.getWriter().println("Bad Request");
        }
    }
}
