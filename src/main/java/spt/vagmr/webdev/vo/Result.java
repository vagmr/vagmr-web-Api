package spt.vagmr.webdev.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/12-11:10
 * springBootProject
 * @Description 统一格式的返回实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;//响应状态码
    private String msg;//响应信息
    private T data;//响应数据

    //带有响应数据的返回响应
    public static <E> Result<E> success(E data){
        return new Result<>(0,"success",data);
    }
    //没有响应数据的返回响应
    public  static  Result  success(){
        return new Result(0,"success",null);
    }
    //错误响应
    public static Result error(String msg){
        return new Result(1,msg,null);
    }
}
