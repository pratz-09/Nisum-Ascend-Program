package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class FormPage1Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        HttpSession session = req.getSession();
        session.setAttribute("name", name);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<form action='form-page2' method='post'>");
        out.println("Age: <input type='text' name='age' /><br/>");
        out.println("<input type='submit' value='Next' />");
        out.println("</form>");
    }
}