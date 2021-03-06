

package com.qslion.authority.configuration;


import com.qslion.authority.security.AuAccessDecisionManager;
import com.qslion.authority.security.AuAccessDeniedHandler;
import com.qslion.authority.security.AuSecurityMetadataSource;
import com.qslion.authority.security.filter.AuAuthenticationEntryPoint;
import com.qslion.authority.security.filter.AuFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * OAuth2 资源服务器配置,用于保护oauth要开放的资源，同时主要作用于client端以及token的认证(Bearer auth)
 *
 * @author Gray.Z
 * @date 2018/5/1 19:45.
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String[] PERMIT_ALL_URLS = new String[]{"/", "/login/*", "/logout/*", "/login*", "/logout**", "error**", "/swagger**", "/swagger**/*",
            "/swagger**/*/*",
            "/webjars/**", "/csrf", "/index", "/v2/*", "/hello*"};

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuAccessDecisionManager auAccessDecisionManager;

    @Autowired
    private AuSecurityMetadataSource auSecurityMetadataSource;


    /**
     * ResourceServerSecurityConfigurer 创建了OAuth2核心过滤器OAuth2AuthenticationProcessingFilter,
     * 并为其提供固定的AuthenticationManager即OAuth2AuthenticationManager,它并没有将OAuth2AuthenticationManager
     * 添加到spring的容器中，不然可能会影响spring security的普通认证流程（非oauth2请求），只有被 OAuth2AuthenticationProcessingFilter拦截到的oauth2相关请求才被特殊的身份认证器处理,设置了TokenExtractor默认的
     * 实现—BearerTokenExtractor,以及相关的异常处理器
     *
     * @param resources 资源安全配置
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(new AuAuthenticationEntryPoint())
                .accessDeniedHandler(new AuAccessDeniedHandler())
                .stateless(true);
    }

    /**
     * 配置需要token验证的资源
     *
     * @param http http安全配置 HttpSecurity
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //所有符合/api**的请求都要进行认证
        http.antMatcher("/**").csrf().disable()
                .authorizeRequests()// 授权通过以后
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(PERMIT_ALL_URLS).permitAll()
                .anyRequest()
                .authenticated();//允许认证过的用户访问
        http.addFilterAfter(createApiAuthenticationFilter(), FilterSecurityInterceptor.class);
    }

    /**
     * API权限控制
     * 过滤器优先度在 FilterSecurityInterceptor 之后
     * spring-security 的默认过滤器列表见 https://docs.spring.io/spring-security/site/docs/5.0.0.M1/reference/htmlsingle/#ns-custom-filters
     *
     * @return
     */
    private AuFilterSecurityInterceptor createApiAuthenticationFilter() {
        AuFilterSecurityInterceptor interceptor = new AuFilterSecurityInterceptor();
        interceptor.setAuthenticationManager(authenticationManager);
        interceptor.setAccessDecisionManager(auAccessDecisionManager);
        interceptor.setSecurityMetadataSource(auSecurityMetadataSource);
        return interceptor;
    }

}
