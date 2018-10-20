

package com.qslion.configuration;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * OAuth2 资源服务器配置
 *
 * @author Gray.Z
 * @date 2018/5/1 19:45.
 */
//@Configuration
//@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String API_RESOURCE_ID = "api";

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
     * @param http http安全配置 HttpSecurity
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
