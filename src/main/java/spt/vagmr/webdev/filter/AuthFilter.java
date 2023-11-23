package spt.vagmr.webdev.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/28-18:10
 * springBootProject
 * @Description
 */
@WebFilter(urlPatterns = "/filter/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        System.out.println("经过过滤器AuthFilter，请求路径为:" + uri);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
