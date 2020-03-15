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

    double id() default 0;

    String title() default "";

    boolean show() default true;

    boolean sortable() default false;

    String align() default "center";

    String width() default "10";

}