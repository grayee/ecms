package com.qslion.framework.bean;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口返回结果增强  会通过拦截器拦截后放入标记，在ResponseResultHandler 进行结果处理
 *
 * @author Gray.Z
 * @date 2018/9/22 22:05.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {

    Class<RestResult> value() default RestResult.class;

}