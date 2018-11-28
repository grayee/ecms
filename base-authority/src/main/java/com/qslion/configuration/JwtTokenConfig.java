package com.qslion.configuration;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * jwt 配置
 *
 * @author Gray.Z
 * @date 2018/11/28.
 */
@Configuration
public class JwtTokenConfig {

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * token生成处理：指定签名
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("secret");
        return accessTokenConverter;
    }

    @Bean
    public TokenEnhancer jwtTokenEnhancer(){
        return (oAuth2AccessToken, oAuth2Authentication) -> {
            Map<String,Object> info = new HashMap<>();
            info.put("provider","Gray.Z");
            //设置附加信息
            ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(info);
            return oAuth2AccessToken;
        };
    }
}
