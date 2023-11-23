package spt.vagmr.webdev.mapper.bigEvent;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import spt.vagmr.webdev.vo.bigEvent.User;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/12-11:27
 * springBootProject
 * @Description
 */
@Mapper
public interface bigEventMapper {
    @Select("""
            select * from user
            where username = #{username}
            """)
    User getByUsername(String username);
    @Insert("""
            insert into user(username,password,create_time,update_time)
            values(#{username},#{password},now(),now())
            """)
    void addUser(String username,String password);

    @Update("""
    update user set nickname = #{nickname}, email = #{email}
            ,update_time = now()
            where id = #{id}
            """)
    void putUser(User user);

    @Update("""
    update user set user_pic = #{avatarUrl},update_time = now()
    where id = #{id}
    """)
    void updateAvatar(String avatarUrl,Integer id);

    @Update("""
     update user set password = #{new_pwd},update_time = now()
            where id = #{id}
            """)
    void updatePwd(String new_pwd, Integer id);

}
