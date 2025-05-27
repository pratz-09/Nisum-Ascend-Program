package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class SetSessionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.setAttribute("username", "nisumUser");
        resp.getWriter().println("Session attribute 'username' set.");
    }
}