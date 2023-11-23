package spt.vagmr.webdev.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import spt.vagmr.webdev.vo.PostsData;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/30-22:30
 * springBootProject
 * @Description 远程访问对象
 */
public interface PostService {
    @GetExchange("/posts/{id}")
    PostsData getPhoto(@PathVariable("id") Integer id);
}
