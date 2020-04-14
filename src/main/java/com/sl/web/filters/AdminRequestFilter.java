package com.sl.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminRequestFilter", urlPatterns = "/admin/*")
public class AdminRequestFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            chain.doFilter(req, resp);
        } else if(((HttpServletRequest) req).getRequestURI().contains("login.jsp")){
            chain.doFilter(req,resp);
        }else{
            response.sendRedirect("login.jsp");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}