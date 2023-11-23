package spt.vagmr.webdev.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import spt.vagmr.webdev.interceptor.BgAuth;
import spt.vagmr.webdev.interceptor.LoginAuth;
import spt.vagmr.webdev.interceptor.bigEvent.loginInterceptor;

/**
 * @author vagmr
 * @version 0.0.1
 *          2023/10/28-23:14
 *          springBootProject
 * @Description
 */
@Configuration
public class MvcSetting implements WebMvcConfigurer {
    @Resource
    private loginInterceptor loginInterceptor;
    /* 页面跳转控制器 */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/errorDetail").setViewName("errorDoc");
    }

    /* 注册请求拦截器 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //权限拦截器
        BgAuth bgAuth = new BgAuth();
        registry.addInterceptor(bgAuth)
                .order(2)     //拦截器执行顺序,值越小越先执行
                .addPathPatterns("/blogauth/*"); // 拦截的路径

        //登录拦截器
        LoginAuth lgAuth = new LoginAuth();
        registry.addInterceptor(lgAuth)
                .order(1)
                .addPathPatterns("/login");
        registry.addInterceptor(loginInterceptor)
                .order(0)
                .addPathPatterns("/bigEvent/**")
                .excludePathPatterns("/bigEvent/user/login","/bigEvent/user/register");
    }
}
