package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ContextParamServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext context = getServletContext();
        String globalParam = context.getInitParameter("globalParam");

        resp.setContentType("text/html");
        resp.getWriter().println("<h3>Context Parameter Value: " + globalParam + "</h3>");
    }
}