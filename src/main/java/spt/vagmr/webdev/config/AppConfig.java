package spt.vagmr.webdev.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/28-18:33
 * springBootProject
 * @Description 用来记录日志的过滤器，debug模式下生效，配置里logging.level.web=debug
 */
@Configuration
public class AppConfig {
    //使用框架内置的过滤器
    @Bean
    public FilterRegistrationBean<CommonsRequestLoggingFilter> addCommonFilter(){
     FilterRegistrationBean<CommonsRequestLoggingFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        /*记录请求的url地址*/
        filter.setIncludeQueryString(true);
        /*注册过滤器*/
        filterRegistrationBean.setFilter(filter);
        /*设置过滤的url地址*/
        filterRegistrationBean.addUrlPatterns("/*");
        return  filterRegistrationBean;
    }
}
