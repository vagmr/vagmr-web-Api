package spt.vagmr.webdev.Exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

/**
 * @author vagmr
 * @version 0.0.1
 *          2023/11/13-16:46
 *          springBootProject
 * @Description 自定义异常类型用于处理token验证相关异常
 */
public class TokenVerifyException extends ErrorResponseException {
    public TokenVerifyException(HttpStatusCode status, String detail) {
        super(status, createProblemDetail(status, detail), null);
    }

    /**
     * Creates a ProblemDetail object with a specific HTTP status and detail,
     * setting properties for severity and a tip in Chinese characters.
     *
     * @param status the HTTP status code for the problem detail
     * @param detail the detailed message of the problem
     * @return a ProblemDetail instance with set properties
     */
    public static ProblemDetail createProblemDetail(HttpStatusCode status, String detail) {
        ProblemDetail errorInfo = ProblemDetail.forStatusAndDetail(status, detail);
        errorInfo.setProperty("严重程度", "中");
        errorInfo.setProperty("提示", "检查登录信息");
        return errorInfo;
    }
}
