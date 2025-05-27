package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ConfigParamServlet extends HttpServlet {
    private String paramValue;

    @Override
    public void init(ServletConfig config) {
        paramValue = config.getInitParameter("initParam");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<h3>Init Parameter Value: " + paramValue + "</h3>");
    }
}