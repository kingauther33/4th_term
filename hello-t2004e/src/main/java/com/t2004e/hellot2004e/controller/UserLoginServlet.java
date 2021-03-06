package com.t2004e.hellot2004e.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/client/login.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // check login information
        // login thành công => lưu vào session
        HttpSession session = req.getSession();
        session.setAttribute("currentLogin", username);
        if (username.equals("admin")) {
            session.setAttribute("isAdmin", true);
        }
        resp.getWriter().println("Login success!");
    }
}
