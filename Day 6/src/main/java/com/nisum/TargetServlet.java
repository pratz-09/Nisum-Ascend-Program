package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class TargetServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String message = (String) req.getAttribute("message");
        resp.getWriter().println("<h3>Message: " + message + "</h3>");
    }
}