package com.qslion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextListener;

@EnableDiscoveryClient
@EnableJpaAuditing
@EnableTransactionManagement(order = 10)
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class AuthStartup {

    public static void main(String[] args) throws Exception {
        System.setProperty("nacos.logging.default.config.enabled","false");
        SpringApplication.run(AuthStartup.class, args);
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}