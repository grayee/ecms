package com.qslion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextListener;

@EnableJpaAuditing
@EnableTransactionManagement(order = 10)
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class AuthStartup {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AuthStartup.class, args);
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}