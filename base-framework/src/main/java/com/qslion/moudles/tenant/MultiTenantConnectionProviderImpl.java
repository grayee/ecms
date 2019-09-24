package com.qslion.moudles.tenant;

import org.hibernate.cfg.Environment;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;

import javax.sql.DataSource;

/**
 * Hibernate框架拦截sql语句并在执行sql语句之前更换数据源提供的接口实现类
 * 负责提供租户标识对应的租户数据源信息
 *
 * @author Gray.Z
 * @date 2018/5/5 15:30.
 */
public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    // 在没有提供tenantId的情况下返回默认数据源
    @Override
    protected DataSource selectAnyDataSource() {
        return TenantDataSourceProvider.getTenantDataSource("default");
    }

    // 提供了tenantId的话就根据ID来返回数据源
    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        return TenantDataSourceProvider.getTenantDataSource(tenantIdentifier);
    }
}
