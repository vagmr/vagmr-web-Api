package spt.vagmr.webdev.vo.bigEvent;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class User {
    @NotNull(message = "id不能为空值") //id不能为空值
    @Min(value = 1,message = "id最小值必须大于等于1")
    private Integer id;//主键ID

    private String username;//用户名


    @JsonIgnore //转化为json时忽略密码
    private String password;//密码

    @NotEmpty(message = "昵称必须有值")
    @Pattern(regexp = "^\\S{1,10}$",message = "1-10位非空字符")
    private String nickname;//昵称

    @NotEmpty(message = "邮箱必须有值")
    @Email(message = "邮箱格式不正确")
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
