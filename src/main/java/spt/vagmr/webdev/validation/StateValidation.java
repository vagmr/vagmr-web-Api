package spt.vagmr.webdev.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import spt.vagmr.webdev.anno.State;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/20-17:58
 * springBootProject
 * @Description
 */



public class StateValidation implements ConstraintValidator<State,String> {
    /**
     * @param value 需要验证的值
     * @return 如果为false检验不通过，如果为true则检验通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) return false;
        if(value.equals("已发布") || value.equals("草稿"))
            return true;
        return false;
    }
}
