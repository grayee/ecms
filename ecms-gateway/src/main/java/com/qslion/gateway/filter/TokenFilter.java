package com.qslion.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;


/**
 * TokenFilter
 *
 * @author Gray.Z
 * @date 2018/4/21 13:43.
 */
public class TokenFilter implements GlobalFilter, Ordered {

    private Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (token ==null||token.isEmpty()) {
            logger.info("query param token is empty,start to check header...");
            List<String> headerToken = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
            if (CollectionUtils.isEmpty(headerToken)) {
                logger.info("header token is empty...");
            } else {
                return chain.filter(exchange);
            }
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
