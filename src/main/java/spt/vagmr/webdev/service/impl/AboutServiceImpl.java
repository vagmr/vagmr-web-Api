package spt.vagmr.webdev.service.impl;

import org.springframework.stereotype.Service;
import spt.vagmr.webdev.mapper.main.SkillMapper;
import spt.vagmr.webdev.service.AboutService;
import spt.vagmr.webdev.vo.SkillVo;

import java.util.List;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/6-17:10
 * springBootProject
 * @Description
 */
@Service
public class AboutServiceImpl implements AboutService {
    //声明mapper对象
    private final SkillMapper skillMapper;
    //构造注入
    public AboutServiceImpl(SkillMapper skillMapper){
        this.skillMapper = skillMapper;
    }

    @Override
    public List<SkillVo> getSkillList() {
       return skillMapper.getAll();
    }
    @Override
    public void addSkillService(String name, Double proficiency) {
        skillMapper.addSkill(name, proficiency);
    }
}
