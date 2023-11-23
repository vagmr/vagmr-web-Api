package spt.vagmr.webdev.service;

import spt.vagmr.webdev.vo.SkillVo;

import java.util.List;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/6-17:07
 * springBootProject
 * @Description 所有的方法定义放在接口中
 */

public interface AboutService {
    List<SkillVo> getSkillList();
    //添加数据到skill表
    void addSkillService(String name, Double proficiency);
}
