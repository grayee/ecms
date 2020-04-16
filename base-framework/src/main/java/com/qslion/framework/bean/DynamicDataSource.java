package com.qslion.framework.bean;

import java.util.Map;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * DynamicDataSource 动态数据源
 *
 * @author Gray.Z
 * @date 2018/9/18.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getDataSourceType().name();
        logger.debug("当前数据源类型为：{}", typeKey);
        return typeKey;
    }

}
