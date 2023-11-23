package spt.vagmr.webdev.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/28-18:17
 * springBootProject
 * @Description
 */
@WebFilter(urlPatterns = "/filter/*")
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest)request).getRequestURI();
        System.out.println("经过过滤器LogFilter,请求路径为:" + uri);
        filterChain.doFilter(request, response);
    }
}
