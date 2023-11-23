package spt.vagmr.webdev.vo.bigEvent;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {
    //定义检验的分组
    public interface add{}
    public interface update{}

    @NotNull(groups = {update.class}, message = "id不能为空")
    @Min(value = 1, message = "id不能小于1")
    private Integer id;//主键ID
    @NotEmpty(groups = {add.class,update.class},message = "文章分类名字不能为空")
    private String categoryName;//分类名称
    @NotEmpty(groups = {add.class,update.class},message = "文章分类别名不能为空")
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间
}
