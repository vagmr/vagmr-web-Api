package spt.vagmr.webdev.mapper.main;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import spt.vagmr.webdev.vo.SkillVo;

import java.util.List;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/6-17:01
 * springBootProject
 * @Description
 */
@Mapper
public interface SkillMapper {
    @Select("""
    select skillName,proficiency,id
    from skill
            """)
    List<SkillVo> getAll();
    //添加数据
    @Insert("""
    insert into skill(skillName,proficiency) value(#{skillName},#{proficiency})
            """)
    int addSkill(String skillName,Double proficiency);
 }