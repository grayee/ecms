package com.qslion.framework.interceptor;

import com.qslion.framework.bean.DataSourceContextHolder;
import com.qslion.framework.bean.DataSourceContextHolder.DataSourceType;
import com.qslion.framework.enums.ReadOnlyDataSource;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 数据源切换切面
 *
 * @author Gray.Z
 * @date 2018/9/18.
 */
@Aspect
@Order(1)
@Component
public class DataSourceAopAspect {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Around("execution(* com.qslion..service.find*(..))||execution(* com.qslion..service.get*(..))" +
            "||execution(* com.qslion..service.query*(..))||@annotation(com.qslion.framework.enums.ReadOnlyDataSource)")
    public Object doBefore(ProceedingJoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        try {
            logger.info("AOP注解切换只读数据源，className" + joinPoint.getTarget().getClass().getName() + "methodName" + method.getName());
            DataSourceContextHolder.setDataSourceType(DataSourceType.SLAVE);
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage());
        } finally {
            //清理掉当前设置的数据源，让默认的数据源不受影响
            DataSourceContextHolder.clearDateSourceType();
        }
        return null;
    }

}
