package spt.vagmr.webdev.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/29-17:00
 * springBootProject
 * @Description 登录拦截器
 */
public class LoginAuth implements HandlerInterceptor {
    private final List<String> premUser = List.of("vagmr", "admin");
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("=========登录拦截器=========");
        String user = request.getParameter("userName");
        return StringUtils.hasText(user) && premUser.contains(user);
    }
}
