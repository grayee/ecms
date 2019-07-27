package com.qslion.framework.enums;

import com.qslion.framework.bean.DataSourceContextHolder.DataSourceType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2019/7/27 22:06.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChooseDataSource {

    DataSourceType dataSource() default DataSourceType.MASTER;
}