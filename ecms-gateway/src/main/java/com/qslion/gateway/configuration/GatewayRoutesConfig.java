package com.qslion.gateway.configuration;

import com.qslion.gateway.filter.RequestTimeFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Gateway 配置类
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
@Configuration
public class GatewayRoutesConfig {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/book")
                        .filters(f -> f.filter(new RequestTimeFilter()).addRequestHeader("Hello", "World"))
                        .uri("http://localhost:8080/ecms/book"))
                .build();
    }
}
