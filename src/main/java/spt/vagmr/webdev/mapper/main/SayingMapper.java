package spt.vagmr.webdev.mapper.main;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import spt.vagmr.webdev.vo.EnglishData;

import java.util.List;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/31-14:03
 * springBootProject
 * @Description mybatis的sql语句存放处
 */
@Mapper
public interface SayingMapper {
    @Select("""
            select id,content,note,author
            from englishDaily where id >= 100
            order by id
            limit #{limit}
            """)
    List<EnglishData> selectAll(int limit);
}
