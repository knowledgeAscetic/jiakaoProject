package com.wang.jk.filter;

import javax.servlet.*;
import java.io.IOException;

public class ErrorFilter implements Filter {

    public static final String ERROR_URL="/errorHandle";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            System.out.println(1);
        }catch (Exception e)
        {
            servletRequest.setAttribute("error",e);
            servletRequest.getRequestDispatcher(ErrorFilter.ERROR_URL).forward(servletRequest,servletResponse);
        }
    }
}
