package spt.vagmr.webdev.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vagmr
 * @version 0.0.1
 *          2023/10/29-16:06
 *          springBootProject
 * @Description
 */
@RestController
public class BlogAuth {
    @GetMapping("/blogauth/getAll")
    public Map<String, Object> getAll() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "响应成功");
        map.put("code", 200);
        map.put("data", "全部文章");
        return map;
    }

    @GetMapping("/blogauth/{id}")
    public Map<String, Object> getBlog(@PathVariable("id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "响应成功");
        map.put("data", "文章id为" + id);
        return map;
    }

    @PutMapping("/blogauth/{id}")
    public Map<String, Object> updateBlog(@PathVariable("id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "响应成功");
        map.put("data", "文章id为" + id);
        return map;
    }

    @PostMapping("/blogauth")
    public Map<String, Object> addBlog() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "响应成功");
        map.put("data", "新增文章");
        return map;
    }
}
