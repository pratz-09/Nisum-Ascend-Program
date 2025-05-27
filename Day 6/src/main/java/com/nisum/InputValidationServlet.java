package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ValidationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        if (username == null || username.isEmpty() || email == null || email.isEmpty()) {
            out.println("<h3>Error: All fields are required!</h3>");
        } else {
            out.println("<h3>Validation Successful!</h3>");
        }
    }
}