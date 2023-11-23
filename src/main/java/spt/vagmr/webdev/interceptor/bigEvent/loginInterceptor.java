package spt.vagmr.webdev.interceptor.bigEvent;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import spt.vagmr.webdev.Exception.TokenVerifyException;
import spt.vagmr.webdev.util.JwtUtil;
import spt.vagmr.webdev.util.ThreadLocalUtil;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/13-17:38
 * springBootProject
 * @Description 登录拦截器
 */
@Component
public class loginInterceptor implements HandlerInterceptor {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("vagmr_key");
        System.out.println("=========登录拦截器=========");
        try {
            //将token与redis中的做对比
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(token);
            if(redisToken == null){
                throw new TokenVerifyException(HttpStatus.UNAUTHORIZED,"token可能失效了");
            }
            var res = JwtUtil.parseToken(token);
            //存储到ThreadLocal中
            ThreadLocalUtil.set(res);
            return true;
        }
        catch (Exception e) {
            response.setStatus(401);
            //抛出自定义异常
            throw new TokenVerifyException(HttpStatus.UNAUTHORIZED,"未登录");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /*在请求完成后，清空ThreadLocal中的数据*/
        System.out.println("=========清除ThreadLocal中的数据=========");
        ThreadLocalUtil.remove();
    }
}
