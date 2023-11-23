package spt.vagmr.webdev.mapper.bigEvent;

import org.apache.ibatis.annotations.*;
import spt.vagmr.webdev.vo.bigEvent.Article;

import java.util.List;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/20-12:28
 * springBootProject
 * @Description
 */
@Mapper
public interface ArticleMapper {

    @Insert("""
    insert into article(title,content,cover_img,state,category_id
    ,create_user,create_time,update_time)
    values(#{title},#{content},#{coverImg},#{state},#{categoryId}
    ,#{createUser},#{createTime},#{updateTime})
            """)
    void add(Article article);

    List<Article> list(Integer categoryId, String state, Integer userId);
    @Select("""
    select * from article
    where id = #{id}
    and create_user = #{userId}
            """)
    Article detail(Integer id, Integer userId);

    @Update("""
    update article set
    title = #{title},content = #{content},
    cover_img = #{coverImg},state = #{state},
    category_id = #{categoryId},update_time = #{updateTime}
    where id = #{id}
            """)
    void update(Article article);

    @Delete("""
    delete from article
    where id = #{id}
            """)
    int delete(Integer id);
}
