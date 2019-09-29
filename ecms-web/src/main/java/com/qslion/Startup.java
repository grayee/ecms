package com.qslion;


import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.google.common.base.Splitter;
import com.qslion.moudles.district.dao.DistrictRepository;
import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextListener;
//@EnableDiscoveryClient
@EnableJpaAuditing
@EnableTransactionManagement(order = 10)
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class Startup {

    public static void main(String[] args) throws Exception {
        System.setProperty("nacos.logging.default.config.enabled","false");
        SpringApplication.run(Startup.class, args);
    }

    @Bean
    //@LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }


    @Component
    public class Runner implements CommandLineRunner {

        protected final Logger logger = LogManager.getLogger(this.getClass());
        @Autowired
        private DataSource dataSource;
        @Autowired
        private DistrictRepository districtRepository;

        @Override
        public void run(String... args) throws Exception {
            logger.info("The Runner start to initialize ...");
            Statement st = dataSource.getConnection().createStatement();
            long count = districtRepository.count();
            if (count < 0) {
                //初始化区域数据
                for (int i = 0; i < 3; i++) {
                    File file = ResourceUtils.getFile(String.format("classpath:install/data/common_district_%s.sql", i + 1));
                    String sqlStr = FileUtils.readFileToString(file);
                    Splitter.on(";").split(sqlStr).forEach(sql -> {
                        try {
                            if (StringUtils.isNotEmpty(sql)) {
                                logger.info(sql);
                                st.addBatch(sql);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
            st.executeBatch();
            st.clearBatch();
            st.close();
        }
    }

}