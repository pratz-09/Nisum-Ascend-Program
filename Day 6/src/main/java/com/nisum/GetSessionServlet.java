package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class GetSessionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        if (session != null) {
            String username = (String) session.getAttribute("username");
            out.println("<h3>Username from session: " + username + "</h3>");
        } else {
            out.println("<h3>No active session found.</h3>");
        }
    }
}