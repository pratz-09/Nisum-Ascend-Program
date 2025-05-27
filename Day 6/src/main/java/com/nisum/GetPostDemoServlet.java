package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class GetPostDemoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("This is a GET request.");
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("This is a POST request.");
    }
}