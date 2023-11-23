package spt.vagmr.webdev.controller.bigEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spt.vagmr.webdev.service.bigEvent.CategoryService;
import spt.vagmr.webdev.vo.Result;
import spt.vagmr.webdev.vo.bigEvent.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/20-12:32
 * springBootProject
 * @Description
 */
@RestController
@RequestMapping("/bigEvent/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /*实现添加分类的功能*/
    @PostMapping
    public Result<String> addCategory
    (@Validated(Category.add.class) @RequestBody Category category){
        categoryService.add(category);
        return Result.success("添加分类成功");
    }
    /*获取文章分类列表*/
    @GetMapping
    public  Result<List<Category>> getCategoryList(){
        ArrayList<Category> cl = categoryService.list();
    return Result.success(cl);
    }
    /*获取文章分类详情*/
    @GetMapping("/detail")
    public Result<Category> getCategoryDetail(Integer id){
        Category detail = categoryService.get(id);
        return  Result.success(detail);
    }
    /*更新文章分类*/
    @PutMapping
    public Result<String> updateCategory
    (@RequestBody @Validated(Category.update.class) Category category)
    {
        categoryService.update(category);
        return  Result.success("更新分类成功");
    }
    /*删除文章功能*/
    @DeleteMapping
    public Result<String> deleteCategory(Integer id){
        categoryService.delete(id);
        return  Result.success("删除文章成功");
    }
}
