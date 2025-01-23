package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("DemoFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Demo 拦截到请求。。。放行前");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("Demo 拦截到请求。。。放行后");
    }

    @Override
    public void destroy() {
        System.out.println("DemoFilter destroy");
        Filter.super.destroy();
    }
}
