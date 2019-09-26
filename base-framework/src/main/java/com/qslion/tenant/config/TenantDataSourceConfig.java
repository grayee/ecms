package com.qslion.tenant.config;

import com.qslion.tenant.CurrentTenantIdentifierResolverImpl;
import com.qslion.tenant.MultiTenantConnectionProviderImpl;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 多租户配置
 *
 * @author Gray.Z
 * @date 2018/5/5 15:30.
 */
//@Configuration
//@EnableTransactionManagement(order = 0)
public class TenantDataSourceConfig {

    @Bean
    @ConditionalOnBean(name = "entityManagerFactory")
    public MultiTenantConnectionProvider multiTenantConnectionProvider() {
        return new MultiTenantConnectionProviderImpl();
    }

    @Bean
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
        return new CurrentTenantIdentifierResolverImpl();
    }

    @Bean
    @ConditionalOnBean(name = "multiTenantConnectionProvider")
    public LocalContainerEntityManagerFactoryBean tenantEntityManagerFactory(MultiTenantConnectionProvider multiTenantConnectionProvider,
                                                                       CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {
        // No dataSource is set to resulting entityManagerFactoryBean
        LocalContainerEntityManagerFactoryBean lcemf = new LocalContainerEntityManagerFactoryBean();
        lcemf.setPackagesToScan("com.qslion.core.entity");
        lcemf.setPersistenceUnitName("tenant-database-persistence-unit");
        lcemf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Map<String, Object> hibernateProps = new LinkedHashMap<>();

        hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
        hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
        hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
        hibernateProps.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        hibernateProps.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
        hibernateProps.put(Environment.PHYSICAL_NAMING_STRATEGY, "com.qslion.framework.component.TableNameStrategy");
        hibernateProps.put(Environment.SHOW_SQL, true);
        hibernateProps.put(Environment.FORMAT_SQL, true);
        hibernateProps.put(Environment.HBM2DDL_AUTO, "update");
        lcemf.setJpaPropertyMap(hibernateProps);
        return lcemf;
    }

    @Bean(name = "tenantTransactionManager")
    public JpaTransactionManager transactionManager(@Qualifier("tenantEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
