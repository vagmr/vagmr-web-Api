package spt.vagmr.webdev.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/26-23:33
 * springBootProject
 * @Description
 */
@RestController
public class PathController {
    @GetMapping("/test/?")
    public String test(HttpServletRequest request){
        return "测试方法1：path路径为%s".formatted(request.getRequestURI());
    }
    //*通配
    @GetMapping("/test/*")
    public String test2(HttpServletRequest request){
        return "测试方法2：path路径为%s".formatted(request.getRequestURI());
    }
    //匹配test开头的任意路径
    @GetMapping("/test/**")
    public String test3(HttpServletRequest request){
        return "测试方法3:path路径为%s".formatted(request.getRequestURI());
    }
    //restful风格的路径变量
    @GetMapping("/test/{id}")
    public String test4(@PathVariable("id") String pathId, HttpServletRequest request){
        return  "测试方法4:path路径为%s,参数id为%s".formatted(request.getRequestURI(),pathId);
    }
    /*Spring MVC 在进行路径匹配时会按照先精确匹配再模糊匹配的顺序进行*/
    //匹配多级路径,精确匹配,后面不能再有参数或路径
    /*@GetMapping("/test/{*id}")
    public String test5(@PathVariable("id") String pathId, HttpServletRequest request){
        return  "测试方法5:path路径为%s,参数id为%s".formatted(request.getRequestURI(),pathId);
    }*/
    //正则表达式路径匹配
    @GetMapping("/test/sb/{pathname:\\w+.log}")
    public String test6(@PathVariable("pathname") String pathId, HttpServletRequest request){
        return  "测试方法6:path路径为%s,参数id为%s".formatted(request.getRequestURI(),pathId);
    }

}
