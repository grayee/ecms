package com.qslion.framework.configuration;

import com.google.common.collect.Maps;
import com.qslion.framework.bean.DataSourceContextHolder;
import com.qslion.framework.bean.DynamicDataSource;

import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.qslion.framework.component.TableNameStrategy;
import com.qslion.framework.dao.impl.GenericRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * DataSource配置类
 *
 * @author Gray.Z
 * @date 2018/9/18.
 */
@Configuration
@EnableTransactionManagement(order = 0)
@EnableJpaRepositories(basePackages = {"com.qslion.tenant.dao"},
        repositoryBaseClass = GenericRepositoryImpl.class,
        entityManagerFactoryRef = "masterEntityManagerFactory",
        transactionManagerRef = "masterTransactionManager")
public class DataSourceConfig {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    private final static String MASTER_DATASOURCE_KEY = "masterDataSource";
    private final static String SLAVE_DATASOURCE_KEY = "slaveDataSource";

    @Bean(name = MASTER_DATASOURCE_KEY)
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        logger.info("init master datasource...");
        //多数据源需要将配置文件中的url 换成了 jdbc-url，否则报错！！！
        return DataSourceBuilder.create().build();
    }

    @Bean(name = SLAVE_DATASOURCE_KEY)
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        logger.info("init slave datasource...");
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource routingDataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = Maps.newHashMap();
        targetDataSources.put(DataSourceContextHolder.DataSourceType.MASTER.name(), masterDataSource);
        targetDataSources.put(DataSourceContextHolder.DataSourceType.SLAVE.name(), slaveDataSource);
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }

    @Bean(name = "masterEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory(DataSource masterDataSource, DataSource slaveDataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(routingDataSource(masterDataSource, slaveDataSource));
        //这里一定要是App实体类的位置
        emf.setPackagesToScan("com.qslion.tenant.entity");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setPersistenceUnitName("master-database-persistence-unit");
        //Setting the hibernate properties
        emf.setJpaProperties(hibernateProperties());
        //emf.setJpaPropertyMap(jpaProperties.getProperties());
        logger.info("Setup of master entityManagerFactory successfully...");
        return emf;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(Environment.STORAGE_ENGINE, "innodb");
        properties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
        properties.put(Environment.PHYSICAL_NAMING_STRATEGY, TableNameStrategy.class.getName());
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.FORMAT_SQL, true);
        properties.put(Environment.HBM2DDL_AUTO, "update");
        return properties;
    }

    @Bean(name = "masterTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("masterEntityManagerFactory") EntityManagerFactory emf) {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(emf);
        logger.info("Setup of master transactionManager successfully...");
        return tm;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
