package spt.vagmr.webdev.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/29-23:23
 * springBootProject
 * @Description 用于测试异常处理
 */
@RestController
public class NumberCtl {
    @PostMapping("/number")
    public String getNumber(int n1,int n2){
        int res = n1 / n2;
        return  "结果为:" + res;
    }
}
