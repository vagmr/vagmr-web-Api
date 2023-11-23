package spt.vagmr.webdev.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spt.vagmr.webdev.vo.User;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/26-22:29
 * springBootProject
 * @Description 响应json数据
 */
@RestController
public class JsonViewController {
    @GetMapping("/json")
    public void blog(HttpServletResponse response) throws IOException {
        String json = """
        {"msg":"hello world","date":"2023-10-26","code":200,"author":"vagmr"}
                """;
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.close();
    }


    //springMvc中支持通jackson将对象转成json
    /*@RestController相当于 @Controller  +  @ResponseBody
    *  @ResponseBody相当于上面的转化json对象的方法
    * */
    @GetMapping("/user")
    public User getUser(){
        User user = new User();
        user.setId(1L);
        user.setName("vagmr");
        user.setAge(18);
        user.setEmail("vagmr@qq.com");
        return user;
    }
}
