package spt.vagmr.webdev.mapper.main;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import spt.vagmr.webdev.vo.Acc;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/27-23:58
 * springBootProject
 * @Description
 */
@Mapper
public interface AccMapper {
    //新增数据
    @Insert("""
            insert into userInfo(username,password) values(#{username},#{password})
            """)
    int insert(Acc acc);
    //查询全部数据
    @Select("""
            select * from userInfo""")
    Acc[] selectAll();
    //修改数据
    @Update("""
            update userInfo set username=#{username},password=#{password} where id=#{id}
            """)

    int updateInfo(String username,String password,Long id);
}
