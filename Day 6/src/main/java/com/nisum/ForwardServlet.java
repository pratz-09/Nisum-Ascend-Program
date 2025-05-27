package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ForwardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", "Hello from ForwardServlet!");
        RequestDispatcher dispatcher = req.getRequestDispatcher("target");
        dispatcher.forward(req, resp);
    }
}