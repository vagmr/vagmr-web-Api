package spt.vagmr.webdev.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/6-17:01
 * springBootProject
 * @Description
 */
@Data
public class SkillVo {
    @NotBlank(message = "技能名称不能为空")
    private String skillName;
    private Integer id;
    @NotNull(message = "熟练度不能为空")
    @Min(value = 0)
    private Double proficiency;
}
