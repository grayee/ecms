package com.qslion.framework.bean;


import java.lang.annotation.*;

/**
 * 显示列
 *
 * @author Gray.Z
 * @date 2018/9/22 22:05.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DisplayField {

    double order() default 0;

    String title() default "";

    /**
     * 显示列是否可见
     *
     * @return boolean
     */
    boolean visible() default true;

    /**
     * 是否隐藏显示列，会覆盖visible（列不隐藏）
     *
     * @return boolean
     */
    boolean hidden() default false;

    boolean sortable() default false;

    String align() default "center";

    String width() default "10";

}