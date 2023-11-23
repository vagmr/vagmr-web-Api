package spt.vagmr.webdev.Exception;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/30-17:08
 * springBootProject
 * @Description 自定义book异常类
 */
public class BookException extends RuntimeException{
    public BookException() {
        super();
    }

    public BookException(String message) {
        super(message);
    }

}
