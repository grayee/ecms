package com.qslion.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * OAuth2 授权服务器配置
 *
 * @author Gray.Z
 * @date 2018/10/20 13:41.
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    private TokenStore jwtTokenStore;



  /*  @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
*/

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 配置客户端认证详情服务（ClientDetailsService）,定义客户端细节服务中的内存或 JDBC 实现 用户端为          :  （UserDetailsService）
     *
     * @param clients 客户端认证配置
     * @throws Exception 异常
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
    }

    /**
     * 配置认证（authorization）以及令牌（token）的访问端点和令牌服务(token services) 默认情况下，除了密码之外，所有的授权类型都是受支持
     *
     * @param endpoints 端点配置 TokenStore，TokenGranter，OAuth2RequestFactory
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        //通过注入 AuthenticationManager 来开启密码授权
        endpoints.authenticationManager(authenticationManager)
            .tokenStore(jwtTokenStore)
            .userDetailsService(userDetailService);
        //endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))  --redis 存储TOKEN
        endpoints.tokenServices(defaultTokenServices());

        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancerList = new ArrayList<>();
        enhancerList.add(jwtTokenEnhancer);
        enhancerList.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancerList);

        endpoints.tokenEnhancer(enhancerChain)
            .accessTokenConverter(jwtAccessTokenConverter);
    }

    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(jwtTokenStore);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetails());
        // token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
        //默认30天，这里修改
        tokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30));
        return tokenServices;
    }


    /**
     * 配置令牌端点(Token Endpoint)的安全约束 配置AuthorizationServer安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter核心过滤器,
     * 经过ClientCredentialsTokenEndpointFilter之后，身份信息已经得到了AuthenticationManager的验证。 接着便到达TokenEndpoint
     *
     * @param oauthServer 授权服务配置
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        super.configure(oauthServer);
        //允许表单认证
        oauthServer.allowFormAuthenticationForClients();
        //获取token策略
        oauthServer.tokenKeyAccess("permitAll()");
        //验证token策略
        oauthServer.tokenKeyAccess("isAuthenticated()");
    }
}
