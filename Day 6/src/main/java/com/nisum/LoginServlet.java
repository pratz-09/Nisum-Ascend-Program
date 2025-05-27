package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        if ("admin".equals(username) && "password".equals(password)) {
            out.println("<h3>Login Successful</h3>");
        } else {
            out.println("<h3>Invalid Credentials</h3>");
        }
    }
}