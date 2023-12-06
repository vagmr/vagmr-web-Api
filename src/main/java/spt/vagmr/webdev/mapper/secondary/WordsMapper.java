package spt.vagmr.webdev.mapper.secondary;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import spt.vagmr.webdev.vo.words.WordsItem;

import java.util.List;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/12/6-0:04
 * springBootProject
 * @Description
 */
@Mapper
public interface WordsMapper {
    @Select("""
    SELECT * from wine_cet4_word limit 10
            """)
    List<WordsItem> queryAll();
}
