package com.qslion.framework.interceptor;

import com.qslion.framework.bean.DataSourceContextHolder;
import com.qslion.framework.bean.DataSourceContextHolder.DataSourceType;
import com.qslion.framework.enums.ChooseDataSource;
import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 数据源切换切面
 *
 * @author Gray.Z
 * @date 2018/9/18.
 */
//@Aspect
@Order(1)
@Component
public class DataSourceAopAspect {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private String masterPackage;
    private String secondPackage;


    @Pointcut("execution(* com.qslion..*.*(..))  ||@annotation(com.qslion.framework.enums.ChooseDataSource)")
    public void switchDataSource() {
    }

    @Before("switchDataSource()")
    public void doBefore(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //获取方法上的注解
        ChooseDataSource chooseDataSource = method.getAnnotation(ChooseDataSource.class);
        if (chooseDataSource == null) {
            //获取类上面的注解
            chooseDataSource = joinPoint.getTarget().getClass().getAnnotation(ChooseDataSource.class);
            if (chooseDataSource == null) {
                String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
                if (declaringTypeName.startsWith(masterPackage)) {
                    DataSourceContextHolder.setDataSourceType(DataSourceType.MASTER);
                    logger.info("使用包默认数据源，包={}，数据源={}", masterPackage, DataSourceType.MASTER.name());
                } else if (declaringTypeName.startsWith(secondPackage)) {
                    DataSourceContextHolder.setDataSourceType(DataSourceType.SLAVE);
                    logger.info("使用包默认数据源，包={}，数据源={}", secondPackage, DataSourceType.SLAVE.name());
                }
                return;
            } else {
                logger.info("类注解生效，切换数据源={}", chooseDataSource.dataSource().name());
            }
        } else {
            logger.info("方法注解生效，切换数据源={}", chooseDataSource.dataSource().name());
        }
        //获取注解上的数据源的值的信息
        DataSourceType dataSourceType = chooseDataSource.dataSource();
        if (dataSourceType != null) {
            //给当前的执行SQL的操作设置特殊的数据源的信息
            DataSourceContextHolder.setDataSourceType(dataSourceType);
        }
        String nowDatasource = "".equals(dataSourceType.name()) ? "默认数据源master" : dataSourceType.name();
        logger.info("AOP注解切换数据源，className" + joinPoint.getTarget().getClass().getName() + "methodName" + method.getName() + ";dataSourceName:" + nowDatasource);
    }

    @After("switchDataSource()")
    public void after(JoinPoint point) {
        //清理掉当前设置的数据源，让默认的数据源不受影响
       DataSourceContextHolder.clearDateSoureType();
    }



}
