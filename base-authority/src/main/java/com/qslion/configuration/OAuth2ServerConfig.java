

package com.qslion.configuration;

import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * server
 *
 * @author Gray.Z
 * @date 2018/5/1 19:45.
 */
@Configuration
public class OAuth2ServerConfig {

    private static final String API_RESOURCE_ID = "api";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        /**
         * ResourceServerSecurityConfigurer 创建了OAuth2核心过滤器OAuth2AuthenticationProcessingFilter,
         * 并为其提供固定的AuthenticationManager即OAuth2AuthenticationManager,它并没有将OAuth2AuthenticationManager
         * 添加到spring的容器中，不然可能会影响spring security的普通认证流程（非oauth2请求），只有被
         * OAuth2AuthenticationProcessingFilter拦截到的oauth2相关请求才被特殊的身份认证器处理,设置了TokenExtractor默认的
         * 实现—BearerTokenExtractor,以及相关的异常处理器
         *
         * @param resources 资源安全配置
         */
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(API_RESOURCE_ID).stateless(true);
        }

        /**
         *
         * @param http http安全配置 HttpSecurity
         */
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().anyRequest().and()
                .authorizeRequests()
                .antMatchers("/*").authenticated();
        }
    }


    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        RedisConnectionFactory redisConnectionFactory;

        @Autowired
        private UserDetailsService userDetailService;

        @Autowired
        private DataSource dataSource;

        @Bean
        public TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }

        @Bean
        public ClientDetailsService clientDetails() {
            return new JdbcClientDetailsService(dataSource);
        }

        /**
         * 配置客户端认证详情服务（ClientDetailsService）,定义客户端细节服务中的内存或 JDBC 实现
         *
         * @param clients 客户端认证配置
         * @throws Exception 异常
         */
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(clientDetails());
        }

        /**
         * 配置认证（authorization）以及令牌（token）的访问端点和令牌服务(token services)
         *
         * @param endpoints 端点配置 TokenStore，TokenGranter，OAuth2RequestFactory
         */
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
            endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
            //配置默认认证器
            endpoints.authenticationManager(authenticationManager);
            endpoints.tokenStore(tokenStore());
            endpoints.userDetailsService(userDetailService);
            //endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))  --redis 存储TOKEN
            endpoints.tokenServices(defaultTokenServices());
        }

        /**
         * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
         */
        @Primary
        @Bean
        public DefaultTokenServices defaultTokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setTokenStore(tokenStore());
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setClientDetailsService(clientDetails());
            // token有效期自定义设置，默认12小时
            tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
            //默认30天，这里修改
            tokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30));
            return tokenServices;
        }


        /**
         * 配置令牌端点(Token Endpoint)的安全约束
         * 配置AuthorizationServer安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter核心过滤器,
         * 经过ClientCredentialsTokenEndpointFilter之后，身份信息已经得到了AuthenticationManager的验证。
         * 接着便到达TokenEndpoint
         *
         * @param oauthServer 授权服务配置
         */
        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
            //允许表单认证
            oauthServer.allowFormAuthenticationForClients();
            oauthServer.tokenKeyAccess("permitAll()");
            oauthServer.tokenKeyAccess("isAuthenticated()");
        }

        @Bean
        PasswordEncoder passwordEncoder() {
            return new PasswordEncoder() {
                @Override
                public String encode(CharSequence charSequence) {
                    return charSequence.toString();
                }

                @Override
                public boolean matches(CharSequence charSequence, String s) {
                    return s.equals(charSequence.toString());
                }
            };
        }
    }
}
