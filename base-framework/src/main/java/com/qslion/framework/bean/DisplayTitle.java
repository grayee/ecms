package com.qslion.framework.bean;


import java.lang.annotation.*;

/**
 * 显示列
 *
 * @author Gray.Z
 * @date 2018/9/22 22:05.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DisplayTitle {
    String name() default "";
}