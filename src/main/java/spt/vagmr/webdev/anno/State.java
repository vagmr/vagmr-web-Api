package spt.vagmr.webdev.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import spt.vagmr.webdev.validation.StateValidation;

import java.lang.annotation.*;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/20-17:43
 * springBootProject
 * @Description
 */
@Documented
@Target({ElementType.FIELD})
//target注解用于指定应用的范围
@Retention(RetentionPolicy.RUNTIME) //用于指定在哪个阶段保留
@Constraint(
        validatedBy = {StateValidation.class}
)
/*@Repeatable(List.class)*/
public @interface State {
    String message() default "state的参数只能是只发布或者草稿";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
