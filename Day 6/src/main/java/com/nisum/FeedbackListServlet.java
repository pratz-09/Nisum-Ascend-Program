package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackListServlet extends HttpServlet {
    private static final List<String> feedbackList = new ArrayList<>();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String feedback = req.getParameter("feedback");
        feedbackList.add(feedback);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h3>Feedback submitted successfully!</h3>");
        out.println("<h3>All Feedbacks:</h3>");
        for (String fb : feedbackList) {
            out.println("<p>" + fb + "</p>");
        }
    }
}