package com.qslion.framework.configuration;

import com.google.common.collect.Maps;
import com.qslion.framework.bean.DataSourceContextHolder;
import com.qslion.framework.bean.DynamicDataSource;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * DataSource配置类
 *
 * @author Gray.Z
 * @date 2018/9/18.
 */
@Configuration
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

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean emf(DataSource masterDataSource, DataSource slaveDataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(routingDataSource(masterDataSource, slaveDataSource));
        //这里一定要是你自己App实体类的位置
        emf.setPackagesToScan(new String[]{"com.qslion.**.entity"});
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return emf;
    }


    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(DataSource masterDataSource, DataSource slaveDataSource) {
        JpaTransactionManager tm = new JpaTransactionManager();
        EntityManagerFactory e = emf(masterDataSource, slaveDataSource).getNativeEntityManagerFactory();
        tm.setEntityManagerFactory(e);
        return tm;
    }

}
