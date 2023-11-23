package spt.vagmr.webdev.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spt.vagmr.webdev.Exception.NotFoundException;
import spt.vagmr.webdev.service.SayingService;
import spt.vagmr.webdev.vo.EnglishData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/31-14:14
 * springBootProject
 * @Description
 */
@RestController
public class SayingController {
    private final SayingService sayingService;

    public SayingController(SayingService sayingService) {
        this.sayingService = sayingService;
    }

    @GetMapping("/sayings")
    public Map<String,Object> getSayings(){
        Map<String,Object> res = new HashMap<>();
        List<EnglishData> sayings = sayingService.querySaying();
        if(sayings.isEmpty()){
            throw  new NotFoundException(HttpStatus.NOT_FOUND,"数据不存在");
        }
        res.put("code",200);
        res.put("msg","响应成功");
        res.put("result",sayings);
        return  res;
    }
}
