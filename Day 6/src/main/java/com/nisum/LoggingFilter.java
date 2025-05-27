package com.nisum;

import javax.servlet.*;
import java.io.IOException;

public class LoggingFilter implements Filter {
    public void init(FilterConfig filterConfig) {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("Request URL: " + request.getRemoteAddr());
        chain.doFilter(request, response);
    }

    public void destroy() {}
}