package spt.vagmr.webdev.service.bigEvent.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import spt.vagmr.webdev.Exception.NotFoundException;
import spt.vagmr.webdev.mapper.main.bigEvent.ArticleMapper;
import spt.vagmr.webdev.service.bigEvent.ArticleService;
import spt.vagmr.webdev.util.ThreadLocalUtil;
import spt.vagmr.webdev.vo.bigEvent.Article;
import spt.vagmr.webdev.vo.bigEvent.PageBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/20-12:27
 * springBootProject
 * @Description
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> info = ThreadLocalUtil.get();
        Integer id = (Integer) info.get("id");
        article.setCreateUser(id);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> pb = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> list = articleMapper.list(categoryId,state,userId);
        Page<Article> p = (Page<Article>) list;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return  pb;
    }

    @Override
    public Article getDetail(Integer id) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return articleMapper.detail(id,userId);
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }

    @Override
    public void delete(Integer id) {
       int row =  articleMapper.delete(id);
       if (row < 1){
           throw new NotFoundException(HttpStatus.NOT_FOUND,"请检查id是否存在");
       }
    }
}
