
package com.qslion.framework.util;


import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.HandleException;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.GroupSequence;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * 手工校验工具类
 *
 * @author Gray.Z
 * @date 2018/9/18.
 */
public class ValidatorUtils {

    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws HandleException 校验不通过，则报HandleException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
        throws HandleException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            String message = constraintViolations.parallelStream().map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("，"));
            throw new HandleException(ResultCode.PARAMETER_ERROR, message);
        }
    }

    /**
     * 新增数据 Group
     */
    public interface AddGroup {
    }


    /**
     * 新增数据 Group
     */
    public interface UpdateGroup {

    }


    /**
     * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
     */
    @GroupSequence({AddGroup.class, UpdateGroup.class})
    public interface Group {

    }
}
