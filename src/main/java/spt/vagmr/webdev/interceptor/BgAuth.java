package spt.vagmr.webdev.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/29-16:22
 * springBootProject
 * @Description
 */
/*自定义拦截器*/
public class BgAuth implements HandlerInterceptor {
    //放行的用户
    private static final String Admin = "vagmr";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("=========访问权限拦截器=========");
        String loginUser = request.getParameter("loginUser");
        String uri = request.getRequestURI();
        String method = request.getMethod();
        if (method.equals("GET")) {
            return true;
        }
        return loginUser.equals(Admin) && (
                uri.startsWith("/blogauth") &&
                        (method.equals("PUT") || method.equals("POST")
                        ));
    }
}
