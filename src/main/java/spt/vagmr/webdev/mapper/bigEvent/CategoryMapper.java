package spt.vagmr.webdev.mapper.bigEvent;

import org.apache.ibatis.annotations.*;
import spt.vagmr.webdev.vo.bigEvent.Category;

import java.util.ArrayList;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/20-12:33
 * springBootProject
 * @Description
 */
@Mapper
public interface CategoryMapper {
    @Insert("""
    insert into category(category_name,category_alias,create_time,update_time,create_user)
    values(#{categoryName},#{categoryAlias},#{createTime},#{updateTime},#{createUser})
            """)
    void add(Category category);
    @Select("""
    select * from category where create_user = #{userId}
            """)
    ArrayList<Category> findAll(Integer userId);
    @Select("""
    select * from category 
    where id = #{id}
    """
    )
    Category findById(Integer id);

    @Update("""
    update category set
    category_name = #{categoryName},category_alias = #{categoryAlias},
    update_time = #{updateTime}
    where id = #{id}
           """)
    int update(Category category);
    @Delete("""
    delete from category
    where id = #{id}
           """)
    int delete(Integer id);
}
