package com.qslion.moudles.tenant;

import com.qslion.framework.util.SpringUtil;
import com.qslion.moudles.tenant.dao.TenantRepository;
import com.qslion.moudles.tenant.entity.TenantInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gray.Z
 * @date 2018/5/5 15:30.
 */
@Component
public class TenantDataSourceProvider {

    // 使用一个map来存储我们租户和对应的数据源，租户和数据源的信息就是从我们的tenant_info表中读出来
    private static Map<String, DataSource> dataSourceMap = new HashMap<>();

    // 根据传进来的tenantId决定返回的数据源
    static DataSource getTenantDataSource(String tenantId) {
        if (dataSourceMap.containsKey(tenantId)) {
            System.out.println("GetDataSource:" + tenantId);
            return dataSourceMap.get(tenantId);
        } else {
            System.out.println("GetDataSource:" + "Default");
            TenantRepository repository = (TenantRepository) SpringUtil.getBean("");
            List<TenantInfo> tenants = repository.findAll();
            tenants.forEach(tenant -> dataSourceMap.put(tenant.getTenant(), getDataSource(tenant)));
            return dataSourceMap.get("default");
        }
    }

    // 初始化的时候用于添加数据源的方法
    public static void addDataSource(TenantInfo tenantInfo) {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(tenantInfo.getUrl());
        dataSourceBuilder.username(tenantInfo.getUsername());
        dataSourceBuilder.password(tenantInfo.getPassword());
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceMap.put(tenantInfo.getTenant(), dataSourceBuilder.build());
    }

    private static DataSource getDataSource(TenantInfo tenantInfo) {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(tenantInfo.getUrl());
        dataSourceBuilder.username(tenantInfo.getUsername());
        dataSourceBuilder.password(tenantInfo.getPassword());
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        return dataSourceBuilder.build();
    }
}
