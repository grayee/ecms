package com.qslion.tenant;

import com.qslion.tenant.entity.TenantInfo;
import com.qslion.tenant.service.TenantService;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hibernate框架拦截sql语句并在执行sql语句之前更换数据源提供的接口实现类
 * 负责提供租户标识对应的租户数据源信息
 *
 * @author Gray.Z
 * @date 2018/5/5 15:30.
 */
@Component
public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    @Autowired
    private TenantService tenantService;
    // 使用一个map来存储我们租户和对应的数据源，租户和数据源的信息就是从我们的tenant_info表中读出来
    private static Map<String, DataSource> dataSourceMap = new HashMap<>();

    // 在没有提供tenantId的情况下返回默认数据源
    @Override
    protected DataSource selectAnyDataSource() {
        return getTenantDataSource("default");
    }

    // 提供了tenantId的话就根据ID来返回数据源
    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        return getTenantDataSource(tenantIdentifier);
    }

    // 根据传进来的tenantId决定返回的数据源
    private DataSource getTenantDataSource(String tenantId) {
        System.out.println("GetDataSource:" + tenantId);
        if (dataSourceMap.containsKey(tenantId)) {
            return dataSourceMap.get(tenantId);
        } else {
            //load from master datasource
            List<TenantInfo> tenants = tenantService.findAll();
            tenants.forEach(tenant -> dataSourceMap.put(tenant.getTenant(), getDataSource(tenant)));
            return dataSourceMap.get("default");
        }
    }

    private DataSource getDataSource(TenantInfo tenantInfo) {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(tenantInfo.getUrl());
        dataSourceBuilder.username(tenantInfo.getUsername());
        dataSourceBuilder.password(tenantInfo.getPassword());
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        return dataSourceBuilder.build();
    }
}
