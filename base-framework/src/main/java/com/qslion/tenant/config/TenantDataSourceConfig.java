package com.qslion.tenant.config;

import com.qslion.framework.component.TableNameStrategy;
import com.qslion.framework.dao.impl.GenericRepositoryImpl;
import com.qslion.tenant.CurrentTenantIdentifierResolverImpl;
import com.qslion.tenant.MultiTenantConnectionProviderImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 多租户配置-租户业务模块数据源配置
 * https://segmentfault.com/a/1190000019300288?utm_source=tag-newest
 *
 * @author Gray.Z
 * @date 2018/5/5 15:30.
 */
@Configuration
@EnableTransactionManagement(order = 1)
@EnableJpaRepositories(basePackages = {
        "com.qslion.web.*.dao",
        "com.qslion.authority.*.dao",
        "com.qslion.moudles.*.dao",
}, considerNestedRepositories = true,repositoryBaseClass = GenericRepositoryImpl.class)
public class TenantDataSourceConfig {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    @Bean
    @ConditionalOnBean(name = "masterEntityManagerFactory")
    public MultiTenantConnectionProvider multiTenantConnectionProvider() {
        return new MultiTenantConnectionProviderImpl();
    }

    @Bean
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
        return new CurrentTenantIdentifierResolverImpl();
    }

    /**
     * Primary ann is must.or it will be occur no transaction in progress exception...
     *
     * @param multiTenantConnectionProvider   tenant connection provider
     * @param currentTenantIdentifierResolver tenant resolver
     * @return emf
     */
    @Primary
    @Bean(name = "entityManagerFactory")
    @ConditionalOnBean(name = "multiTenantConnectionProvider")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(MultiTenantConnectionProvider multiTenantConnectionProvider,
                                                                       CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {
        // No dataSource is set to resulting entityManagerFactoryBean
        LocalContainerEntityManagerFactoryBean lcemf = new LocalContainerEntityManagerFactoryBean();
        lcemf.setPackagesToScan("com.qslion.web.*.entity", "com.qslion.authority.*.entity", "com.qslion.moudles.*.entity");
        lcemf.setPersistenceUnitName("tenant-database-persistence-unit");
        lcemf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Map<String, Object> hibernateProps = new LinkedHashMap<>();
        hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
        hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
        hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
        hibernateProps.put(Environment.STORAGE_ENGINE, "innodb");
        hibernateProps.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
        hibernateProps.put(Environment.PHYSICAL_NAMING_STRATEGY, TableNameStrategy.class.getName());
        hibernateProps.put(Environment.SHOW_SQL, true);
        hibernateProps.put(Environment.FORMAT_SQL, true);
        hibernateProps.put(Environment.HBM2DDL_AUTO, "update");
        lcemf.setJpaPropertyMap(hibernateProps);
        logger.info("Setup of tenant entityManagerFactory successfully...");
        return lcemf;
    }

    /**
     * Bean name is must transactionManager,because it is jpaRepository implementation SimpleJpaRepository default name
     *
     * @param entityManagerFactory emf
     * @return JpaTransactionManager
     */
    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        logger.info("Setup of tenant transactionManager successfully...");
        return transactionManager;
    }
}
