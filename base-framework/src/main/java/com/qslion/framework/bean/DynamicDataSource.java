package com.qslion.framework.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

/**
 * DynamicDataSource 动态数据源
 *
 * @author Gray.Z
 * @date 2018/9/18.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getDataSourceType().name();
        logger.info("当前数据源类型为：{}", typeKey);
        return typeKey;
    }

}
