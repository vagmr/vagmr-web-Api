package spt.vagmr.webdev.vo;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/27-22:14
 * springBootProject
 * @Description
 */
@Data
public class Acc {
    //增加验证组
    public interface EditGroup{}
    @NotNull(message = "编辑时id不能为空",groups = EditGroup.class)
    @Min(value = 1,message = "id必须大于0",groups = EditGroup.class)
    private Long id;
    @NotBlank(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空",groups = EditGroup.class)
    @Size(min=4,max = 8,message = "用户名长度必须在4-8之间")
    @Size(min=4,max = 8,message = "用户名长度必须在4-8之间",groups = EditGroup.class)
    private  String username;
    @NotBlank(message = "密码不能为空")
    @Size(min=8,max=14,message = "密码长度必须在8-14之间")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-zA-Z]).{8,14}",message = "密码必须包含数字和字母")
    @NotBlank(message = "密码不能为空",groups = EditGroup.class)
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-zA-Z]).{8,14}",message = "密码必须包含数字和字母",groups = EditGroup.class)
    private  String password;

    public Acc(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Acc{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
