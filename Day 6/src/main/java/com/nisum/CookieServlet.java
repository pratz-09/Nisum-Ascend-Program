package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class CookieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie cookie = new Cookie("user", "nisum");
        resp.addCookie(cookie);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h3>Cookie set. Reading cookies:</h3>");
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                out.println("<p>" + c.getName() + ": " + c.getValue() + "</p>");
            }
        }
    }
}