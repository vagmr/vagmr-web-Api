package spt.vagmr.webdev.service.bigEvent;

import spt.vagmr.webdev.vo.bigEvent.Category;

import java.util.ArrayList;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/20-12:33
 * springBootProject
 * @Description
 */
public interface CategoryService {
    void add(Category category);

    ArrayList<Category> list();

    Category get(Integer id);

    void update(Category category);

    void delete(Integer id);
}
