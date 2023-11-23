package spt.vagmr.webdev.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/30-12:57
 * springBootProject
 * @Description
 */
@Data
public class Order {
    @NotBlank(message = "订单不能为空")
    private String name;
    @NotNull(message = "数量不能为空")
    @Range(min = 1,max = 99,message = "单个订单的数量要在{min} - {max}之间")
    private Integer count;
    //似乎@NotNull验证只对包装类生效
    @NotNull(message = "订单id不能为空")
    private Integer orderId;
    @NotNull(message = "价格不能为空")
    private Double price;
}
