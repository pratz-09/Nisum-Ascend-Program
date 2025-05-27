package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class FormPage2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String age = req.getParameter("age");
        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("name");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h3>Name: " + name + "</h3>");
        out.println("<h3>Age: " + age + "</h3>");
    }
}