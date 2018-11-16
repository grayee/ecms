package com.qslion.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.filter.CompositeFilter;

/**
 * OAuth2 客户端配置
 *
 * @author Gray.Z
 * @date 2018/11/16.
 */
@Configuration
@EnableOAuth2Client
public class OAuth2ClientConfig {

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Bean
    public OAuth2RestTemplate oauth2RestTemplate() {
        OAuth2RestTemplate template = new OAuth2RestTemplate(oAuth2ProtectedResourceDetails(), oauth2ClientContext);
        AuthorizationCodeAccessTokenProvider authCodeProvider = new AuthorizationCodeAccessTokenProvider();
        authCodeProvider.setStateMandatory(false);
        AccessTokenProviderChain provider = new AccessTokenProviderChain(
            Collections.singletonList(authCodeProvider));
        template.setAccessTokenProvider(provider);
        return template;
    }

    @Bean
    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {
        OAuth2ProtectedResourceDetails resourceDetails = new AuthorizationCodeResourceDetails();
        //resourceDetails = new ResourceOwnerPasswordResourceDetails();
        return resourceDetails;
    }

    /**
     * 注册check token服务
     */
    @Bean
    public RemoteTokenServices tokenService() {
        OAuth2ProtectedResourceDetails details = oAuth2ProtectedResourceDetails();
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl("checkTokenUrl");
        tokenService.setClientId(details.getClientId());
        tokenService.setClientSecret(details.getClientSecret());
        return tokenService;
    }

    /**
     * OAuth2ClientAuthenticationProcessingFilter
     */
    @Bean
    public Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        filters.add(ssoFilter(facebook(), "/login/facebook"));
        filters.add(ssoFilter(github(), "/login/github"));
        filter.setFilters(filters);
        return filter;
    }

    /**
     * 注册处理redirect uri的filter
     *
     * 对rest template的封装，为获取token等提供便捷方法 DefaultUserInfoRestTemplateFactory实例了OAuth2RestTemplate
     *
     * 而DefaultUserInfoRestTemplateFactory主要是在ResourceServerTokenServicesConfiguration配置中创建的 这个是给resource
     * server用的，因而client要使用的话，需要自己创建
     */
    private OAuth2ClientAuthenticationProcessingFilter ssoFilter(ClientResources client, String path) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
        //封装获取token方法
        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
        filter.setRestTemplate(template);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(
            client.getResource().getUserInfoUri(), client.getClient().getClientId());
        tokenServices.setRestTemplate(template);
        filter.setTokenServices(tokenServices);
        return filter;
    }

    /**
     * 注册一个额外的Filter：OAuth2ClientContextFilter，主要作用是重定向，当遇到需要权限的页面或URL，代码抛出异常， 这时这个Filter将重定向到OAuth鉴权的地址，即/login/github
     */
    @Bean
    public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(
        OAuth2ClientContextFilter filter) {
        FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    @Bean
    @ConfigurationProperties("github")
    public ClientResources github() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("facebook")
    public ClientResources facebook() {
        return new ClientResources();
    }

    private class ClientResources {

        @NestedConfigurationProperty
        private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

        @NestedConfigurationProperty
        private ResourceServerProperties resource = new ResourceServerProperties();

        public AuthorizationCodeResourceDetails getClient() {
            return client;
        }

        public ResourceServerProperties getResource() {
            return resource;
        }
    }
}
