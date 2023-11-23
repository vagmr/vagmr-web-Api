package spt.vagmr.webdev.vo.bigEvent;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import spt.vagmr.webdev.anno.State;

import java.time.LocalDateTime;
@Data
public class Article {
    //添加修改的分组
    public interface put extends Default {}
    @NotNull(message = "id不能为空",groups = {put.class})
    private Integer id;//主键ID

    @NotEmpty(message = "标题不能为空")
    private String title;//文章标题
    @NotEmpty(message = "内容不能为空")
    private String content;//文章内容
    @NotEmpty(message = "封面图像不能为空")
    @URL(message = "必须为链接格式")
    private String coverImg;//封面图像
    @State
    private String state;//发布状态 已发布|草稿
    @NotNull(message = "分类id不能为空")
    private Integer categoryId;//文章分类id
    private Integer createUser;//创建人ID
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
