package spt.vagmr.webdev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spt.vagmr.webdev.vo.Acc;
import spt.vagmr.webdev.vo.LoginStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/26-22:05
 * springBootProject
 * @Description 响应html页面
 */
@Controller
public class WebController {
    @GetMapping("/")
    public String start(Model model) {
        model.addAttribute("message", "Hello world");
        model.addAttribute("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return "index";
    }
    @GetMapping("/login")
    //自定义响应状态码
    public ResponseEntity<Map<String,Object>> getLoginStatus(){
        Map<String,Object> map = new HashMap<>();
        LoginStatus st = new LoginStatus();
        st.setMessage("登录成功");
        Acc acc = new Acc("vagmr","123456");
        acc.setId(1L);
        st.setAcc(acc);
        st.setLogin(true);
        map.put("msg","响应成功");
        map.put("data",st);
        map.put("server","springboot");
        return ResponseEntity.ok(map);
    }
}
