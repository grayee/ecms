package com.qslion.web.sob;

import com.qslion.web.sob.dao.AccountSetRepository;
import com.qslion.web.sob.entity.AccountSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class MultiSOBConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    protected final Logger logger = LogManager.getLogger(this.getClass());
    @Value("${spring.datasource.master.username}")
    private String username;
    @Value("${spring.datasource.master.password}")
    private String password;
    @Value("${spring.datasource.master.driver-class-name}")
    private String driver;
    @Autowired
    private AccountSetRepository accountSetRepository;
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
        logger.info("sob GetDataSource:{}", tenantId);
        if (dataSourceMap.containsKey(tenantId)) {
            return dataSourceMap.get(tenantId);
        } else {
            //load from master datasource
            List<AccountSet> tenants = accountSetRepository.findAll();
            tenants.forEach(sob -> dataSourceMap.put(sob.getTenant(), getDataSource(sob)));
            return dataSourceMap.get("default");
        }
    }

    private DataSource getDataSource(AccountSet accountSet) {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(accountSet.getDbUrl());
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        dataSourceBuilder.driverClassName(driver);
        return dataSourceBuilder.build();
    }
}
