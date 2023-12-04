package spt.vagmr.webdev;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "spt.vagmr.webdev.filter")
@MapperScan(basePackages = "spt.vagmr.webdev.mapper")
//开启自动属性导入
//@EnableConfigurationProperties({BookContainer.class})
public class WebDevApplication {
    /**
     * Runs the main method of the Java application.
     *
     * @param args the command line arguments
     *
     */
    public static void main(String[] args) {
        SpringApplication.run(WebDevApplication.class, args);
    }

}
