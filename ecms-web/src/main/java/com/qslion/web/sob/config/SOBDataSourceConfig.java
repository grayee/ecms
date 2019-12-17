package com.qslion.web.sob.config;

import com.qslion.framework.component.TableNameStrategy;
import com.qslion.web.sob.CurrentSOBIdentifierResolverImpl;
import com.qslion.web.sob.MultiSOBConnectionProviderImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
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
 * 多账套配置-账套数据源配置
 *
 * @author Gray.Z
 * @date 2018/5/5 15:30.
 */
//@Configuration
//@EnableTransactionManagement(order = 2)
//@EnableJpaRepositories(basePackages = {
//        "com.qslion.web.accounting.dao"
//}, entityManagerFactoryRef = "sobEntityManagerFactory",
//        transactionManagerRef = "sobTransactionManager")
public class SOBDataSourceConfig {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    @Bean(name = "multiSobConnectionProvider")
    @ConditionalOnBean(name = "entityManagerFactory")
    public MultiTenantConnectionProvider multiSobConnectionProvider() {
        return new MultiSOBConnectionProviderImpl();
    }

    @Bean(name = "currentSobIdentifierResolver")
    public CurrentTenantIdentifierResolver currentSobIdentifierResolver() {
        return new CurrentSOBIdentifierResolverImpl();
    }

    /**
     * Primary ann is must.or it will be occur no transaction in progress exception...
     *
     * @param multiSobConnectionProvider   tenant connection provider
     * @param currentSobIdentifierResolver tenant resolver
     * @return emf
     */
    @Bean(name = "sobEntityManagerFactory")
    @ConditionalOnBean(name = "multiSobConnectionProvider")
    public LocalContainerEntityManagerFactoryBean sobEntityManagerFactory(@Qualifier("multiSobConnectionProvider") MultiTenantConnectionProvider multiSobConnectionProvider,
                                                                       @Qualifier("currentSobIdentifierResolver") CurrentTenantIdentifierResolver currentSobIdentifierResolver) {
        // No dataSource is set to resulting entityManagerFactoryBean
        LocalContainerEntityManagerFactoryBean lcemf = new LocalContainerEntityManagerFactoryBean();
        lcemf.setPackagesToScan("com.qslion.web.accounting.entity");
        lcemf.setPersistenceUnitName("SOB-database-persistence-unit");
        lcemf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Map<String, Object> hibernateProps = new LinkedHashMap<>();
        hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
        hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiSobConnectionProvider);
        hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentSobIdentifierResolver);
        hibernateProps.put(Environment.STORAGE_ENGINE, "innodb");
        hibernateProps.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
        hibernateProps.put(Environment.PHYSICAL_NAMING_STRATEGY, TableNameStrategy.class.getName());
        hibernateProps.put(Environment.SHOW_SQL, true);
        hibernateProps.put(Environment.FORMAT_SQL, true);
        hibernateProps.put(Environment.HBM2DDL_AUTO, "update");
        lcemf.setJpaPropertyMap(hibernateProps);
        logger.info("Setup of SOB entityManagerFactory successfully...");
        return lcemf;
    }

    /**
     * Bean name is must transactionManager,because it is jpaRepository implementation SimpleJpaRepository default name
     *
     * @param sobEntityManagerFactory emf
     * @return JpaTransactionManager
     */
    @Bean(name = "sobTransactionManager")
    public PlatformTransactionManager sobTransactionManager(@Qualifier("sobEntityManagerFactory") EntityManagerFactory sobEntityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(sobEntityManagerFactory);
        logger.info("Setup of SOB transactionManager successfully...");
        return transactionManager;
    }
}
