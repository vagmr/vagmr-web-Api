package spt.vagmr.webdev.controller.bigEvent;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spt.vagmr.webdev.service.bigEvent.ArticleService;
import spt.vagmr.webdev.vo.Result;
import spt.vagmr.webdev.vo.bigEvent.Article;
import spt.vagmr.webdev.vo.bigEvent.PageBean;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/13-16:33
 * springBootProject
 * @Description
 */
@RestController
@RequestMapping("/bigEvent/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping
    public Result<String> addArticle(@Validated @RequestBody Article article){
        articleService.add(article);
        return Result.success("添加文章成功");
    }
    /*实现分页查询功能*/
    @GetMapping
    public Result<PageBean<Article>> getArticleList
    (Integer pageNum,Integer pageSize,
     @RequestParam(required = false) Integer categoryId,
     @RequestParam(required = false) String  state
    )
    {
     PageBean<Article> pa = articleService.list(pageNum,pageSize,categoryId,state);
     return Result.success(pa);
    }
    /*获取文章详情*/
    @GetMapping("/detail")
    public Result<Article> getArticleDetail(@RequestParam Integer id){
        Article article = articleService.getDetail(id);
        return  Result.success(article);
    }
    /*更新文章*/
    @PutMapping
    public Result<String> updateArticle
    (@Validated(Article.put.class) @RequestBody Article article){
     articleService.update(article);
        return Result.success("更新文章成功");
    }
    /*删除文章*/
    @DeleteMapping
    public Result<String> deleteArticle(@RequestParam Integer id){
        articleService.delete(id);
        return Result.success("删除文章成功");
    }
}
