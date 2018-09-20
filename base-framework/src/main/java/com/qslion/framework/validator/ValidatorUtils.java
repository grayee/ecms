
package com.qslion.framework.validator;


import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.HandleException;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;


public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws HandleException  校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws HandleException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
        	String message = constraintViolations.parallelStream().map(ConstraintViolation::getMessage)
              .collect(Collectors.joining("，"));
            throw new HandleException(ResultCode.PARAMETER_ERROR,message);
        }
    }
}
