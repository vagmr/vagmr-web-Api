package spt.vagmr.webdev.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

/**
 * @author vagmr
 * @version 0.0.1
 *          2023/10/30-21:33
 *          springBootProject
 * @Description 自定义异常处理类，让springMvc来处理,需要传入状态码和错误信息
 */

public class NotFoundException extends ErrorResponseException {
    public NotFoundException(HttpStatus status, String detail) {
        super(status, createProblemDetail(status, detail), null);
    }

    /**
     * Creates a ProblemDetail object with the given status code and detail.
     *
     * @param status the status code of the ProblemDetail object
     * @param detail the detail message of the ProblemDetail object
     * @return the created ProblemDetail object
     */
    public static ProblemDetail createProblemDetail(HttpStatusCode status, String detail) {
        ProblemDetail errorInfo = ProblemDetail.forStatusAndDetail(status, detail);
        errorInfo.setProperty("严重程度", "低");
        return errorInfo;
    }

}
