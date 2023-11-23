package spt.vagmr.webdev.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spt.vagmr.webdev.Exception.NotFoundException;
import spt.vagmr.webdev.service.AboutService;
import spt.vagmr.webdev.vo.SkillVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/6-17:17
 * springBootProject
 * @Description 返回技能的控制器
 */
@RestController
public class SkillController {
    //声明常量表示响应状态
    private final int SUCCESS = 200;
    //注入服务对象
    private final AboutService aboutService;
    public SkillController(AboutService aboutService){
        this.aboutService = aboutService;
    }

    @GetMapping("/api/skill")
    public Map<String,Object> getAllSkills(){
        Map<String,Object> map = new HashMap<>();
        List<SkillVo> data = aboutService.getSkillList();
        if(data.isEmpty()){
            throw new NotFoundException(HttpStatus.NOT_FOUND,"没有找到数据");
        }
        map.put("code",SUCCESS);
        map.put("msg","获取成功");
        map.put("data",data);
        return map;
    }

    //添加数据
    @PostMapping("/api/skill")
    public Map<String,Object> addSkill(@Validated @RequestBody SkillVo skillVo ){
        Map<String,Object> map = new HashMap<>();
        aboutService.addSkillService
                (skillVo.getSkillName(),skillVo.getProficiency());
        map.put("code",SUCCESS);
        map.put("msg","添加成功");
        map.put("data","驱动程序: MySQL Connector/J (版本 mysql-connector-java-8.0.25");
        return map;
    }
    //TODO:完成增删改查
}
