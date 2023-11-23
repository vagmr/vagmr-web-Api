package spt.vagmr.webdev.controller;

import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spt.vagmr.webdev.Exception.NotFoundException;
import spt.vagmr.webdev.config.BookContainer;
import spt.vagmr.webdev.vo.BookRecord;

import java.util.Optional;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/30-17:10
 * springBootProject
 * @Description book相关控制器，用来抛出异常
 */
@RestController
public class BookController {
    @Resource
    private BookContainer bookContainer;

    @GetMapping("/books/{id}")
    public BookRecord getBook(@PathVariable Integer id){
        Optional<BookRecord> book = bookContainer
                .getBooks().stream().filter(el -> el.isbn().equals(id))
                .findFirst();
        if(book.isEmpty()){
//            throw new BookException("isbn为" + id +"书籍不存在");
            throw new NotFoundException(HttpStatus.NOT_FOUND,"isbn为" + id +"书籍不存在");
        }
        return book.get();
    }
}
