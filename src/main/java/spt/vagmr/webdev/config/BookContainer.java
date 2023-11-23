package spt.vagmr.webdev.config;

import lombok.Getter;
import lombok.Setter;
import spt.vagmr.webdev.vo.BookRecord;

import java.util.List;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/30-17:00
 * springBootProject
 * @Description
 */
@Getter
@Setter
//通过getter和setter方法给books赋值和取值
//@ConfigurationProperties(prefix = "mock")
public class BookContainer {
    private List<BookRecord> books;
}
