package spt.vagmr.webdev.service.bigEvent;

import spt.vagmr.webdev.vo.bigEvent.Article;
import spt.vagmr.webdev.vo.bigEvent.PageBean;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/20-12:27
 * springBootProject
 * @Description
 */
public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    Article getDetail(Integer id);

    void update(Article article);

    void delete(Integer id);
}
