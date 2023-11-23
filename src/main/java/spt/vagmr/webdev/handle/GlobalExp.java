package spt.vagmr.webdev.handle;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import spt.vagmr.webdev.Exception.BookException;
import spt.vagmr.webdev.vo.Result;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/29-23:29
 * springBootProject
 * @Description 全局异常处理的类，用来捕获并处理全局异常
 */
@ControllerAdvice
public class GlobalExp {
    //常量
    //客户端请求错误
    private static final int BAD_REQUEST_CODE = 400;
    //表示404状态
    private static final HttpStatusCode NOT_FOUND = HttpStatusCode.valueOf(404);
    private static final String BAD_REQUEST_MSG = "请求参数有误";

    @ResponseBody
    @ExceptionHandler({ArithmeticException.class})
    public Map<String,String> ArithmeticExceptionHandle(ArithmeticException e){
        Map<String,String> map = new HashMap<>();
        map.put("code","500");
        map.put("msg","出现算术异常");
        map.put("err",e.getMessage());
        map.put("advice","请检查请求参数是否正确");
        return map;
    }
    //处理验证出错的异常
    /*
    * 返回格式
    * {
    "msg": "出现了异常错误",
    "code": 400,
    "detail": {
        "error-0: count": "数量不能为空",
        "error-1: price": "价格不能为空"
        }
    }
    * */



    @ResponseBody
    public Map<String,Object> handleAuthException(BindException e){
        Map<String,Object> map = new HashMap<>();
        var res = e.getBindingResult();
        Map<String,Object> errorMap = new LinkedHashMap<>();
        if(res.hasErrors()){
            var errors = res.getFieldErrors();
            for(int i = 0,len =errors.size();i<len;i++){
                var field =  errors.get(i);
                errorMap.put("error-%d: %s".formatted(i,field.getField()),field.getDefaultMessage());
                map.put("code",400);
                map.put("msg","出现了异常错误");
                map.put("detail",errorMap);
            }
        }
        return map;
    }

    @ResponseBody
    @ExceptionHandler({BindException.class})
    public Map<String,Object> handleBindException(BindException e, HttpServletResponse response){
        response.setStatus(BAD_REQUEST_CODE);
        Map<String,Object> responseMap = new HashMap<>();
        BindingResult bindingResult = e.getBindingResult();
        Map<String,Object> errorDetailMap;
        if(bindingResult.hasErrors()){
            var fieldErrors = bindingResult.getFieldErrors();
            errorDetailMap = fieldErrors.stream()
                    .collect(Collectors.toMap(
                            err -> String.format("来源为%s,出错字段%s", err.getObjectName(), err.getField()),
                            err ->
                            {
                                if(err.getDefaultMessage() != null)
                                    return err.getDefaultMessage();
                                else return ""; }
                    ));
            responseMap.put("code", BAD_REQUEST_CODE);
            responseMap.put("msg", BAD_REQUEST_MSG);
            responseMap.put("detail", errorDetailMap);
        }
        return responseMap;
    }
    //更标准的异常返回值格式
    @ExceptionHandler({BookException.class})
    public ProblemDetail BookEcpHandler(BookException e){
        ProblemDetail errorInfo =  ProblemDetail.forStatusAndDetail(NOT_FOUND,e.getMessage());
        errorInfo.setTitle("试图获取不存在的信息");
        errorInfo.setType(URI.create("http://localhost:3004/errorDetail"));
        //添加自定义的属性
        errorInfo.setProperty("author","vagmr");
        return errorInfo;
    }


    /*校验失败时进行异常处理*/
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public Result handleViolationException(ConstraintViolationException e){
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败");
    }

}
