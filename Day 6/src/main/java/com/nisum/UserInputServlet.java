package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class UserInputServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h2>Name: " + name + "</h2>");
        out.println("<h2>Email: " + email + "</h2>");
    }
}
