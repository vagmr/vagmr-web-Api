package spt.vagmr.webdev.service.bigEvent.impl;

import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import spt.vagmr.webdev.Exception.NotFoundException;
import spt.vagmr.webdev.mapper.main.bigEvent.CategoryMapper;
import spt.vagmr.webdev.service.bigEvent.CategoryService;
import spt.vagmr.webdev.util.ThreadLocalUtil;
import spt.vagmr.webdev.vo.bigEvent.Category;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/20-12:33
 * springBootProject
 * @Description
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public void add(Category category) {
        //还得手动添加创建时间和更新时间，以及创建人的id
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String,Object> info = ThreadLocalUtil.get();
        Integer id = (Integer) info.get("id");
        category.setCreateUser(id);
        categoryMapper.add(category);
    }

    @Override
    public ArrayList<Category> list() {
        Map<String,Object> info = ThreadLocalUtil.get();
        Integer userId = (Integer) info.get("id");
       return   categoryMapper.findAll(userId);
    }

    @Override
    public Category get(Integer id) {
        if(id == null){
            throw new NotFoundException(HttpStatus.NOT_FOUND,"分类id不能为空");
        }
        return categoryMapper.findById(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        int res = categoryMapper.update(category);
        if(res < 1){
            throw new NotFoundException(HttpStatus.NOT_FOUND,"请检查id是否存在");
        }
    }

    @Override
    public void delete(Integer id) {
       int res =  categoryMapper.delete(id);
       if(res < 1){
           throw new NotFoundException(HttpStatus.NOT_FOUND,"请检查id是否存在");
       }
    }
}
