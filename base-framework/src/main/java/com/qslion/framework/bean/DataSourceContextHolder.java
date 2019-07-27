package com.qslion.framework.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 数据源上下文-切换读/写模式
 *
 * @author Gray.Z
 * @date 2018/9/18.
 */
public class DataSourceContextHolder {
    protected static final Logger logger = LogManager.getLogger(DataSourceContextHolder.class);

    public enum DataSourceType {
        MASTER,
        SLAVE
    }

    /**
     * 保存当前线程是否处于读模式（通过开始READ_ONLY注解在开始操作前设置模式为读模式
     */
    private static ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

    public static void setDataSouceType(DataSourceType dataSourceType) {
        if (dataSourceType == null) {
            throw new NullPointerException();
        }
        logger.info("设置dataSourceType为：{}", dataSourceType);
        contextHolder.set(dataSourceType);
    }

    public static DataSourceType getDataSourceType() {
        return contextHolder.get() == null ? DataSourceType.MASTER : contextHolder.get();
    }

    /**
     * 操作结束后清除该数据，避免内存泄漏，同时也为了后续在该线程进行写操作时任然为读模式
     */
    public static void clearDbType() {
        contextHolder.remove();
    }
}
