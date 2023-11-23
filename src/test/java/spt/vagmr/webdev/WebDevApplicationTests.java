package spt.vagmr.webdev;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import spt.vagmr.webdev.service.PostService;
import spt.vagmr.webdev.vo.PostsData;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class WebDevApplicationTests {

    //注入服务对象
    @Resource
    private PostService postService;

   @Test
    void test01(){
      PostsData p1 = postService.getPhoto(1);
       System.out.println(p1);
   }
   @Test
    void test02(){
       List<String> s1 = new ArrayList<>();
       s1.add("1");
       s1.add("2");
       s1.add("3");
       s1.forEach(System.out::println);
    }
}
