package spt.vagmr.webdev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import spt.vagmr.webdev.service.PostService;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/30-22:37
 * springBootProject
 * @Description 创建远程服务的代理对象
 */
@Configuration(proxyBeanMethods = false)//不代理此类
public class HttpConfiguration {
    private static final  String BASE_URL = "https://jsonplaceholder.typicode.com";
    //创建webclient对象，基于访问基地址
    @Bean
    public PostService getPhoto(){
        WebClient webClient = WebClient.create(BASE_URL);
        //代理工厂
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient)).build();
        return  proxyFactory.createClient(PostService.class);
    }


}
