package com.qslion.configuration;

import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

import com.qslion.security.filter.AuFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * security config
 *
 * @author Gray.Z
 * @date 2018/4/30 19:15.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuFilterSecurityInterceptor auFilterSecurityInterceptor;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login").failureUrl("/loginFailure").defaultSuccessUrl("/loginSuccess")
            .permitAll()
            .and().rememberMe().key("test.com");

        http.addFilterAt(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(auFilterSecurityInterceptor, FilterSecurityInterceptor.class)
            .exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
            .and()
            .logout()
            .logoutUrl("/logout").logoutSuccessUrl("/logoutSuccess")
            .and().exceptionHandling().accessDeniedPage("/accessDenied")
            .and().csrf().disable();

        //session管理,失效后跳转
        http.sessionManagement().invalidSessionUrl("/login");
        //只允许一个用户登录,如果同一个账户两次登录,那么第一个账户将被踢下线,跳转到登录页面
        http.sessionManagement().maximumSessions(1).expiredUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }

    private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
        usernamePasswordAuthenticationFilter.setPostOnly(true);
        usernamePasswordAuthenticationFilter.setAuthenticationManager(this.authenticationManager());
        usernamePasswordAuthenticationFilter.setUsernameParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
        usernamePasswordAuthenticationFilter.setPasswordParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
        usernamePasswordAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        usernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        usernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        return usernamePasswordAuthenticationFilter;
    }


    /**
     * 验证异常处理器
     *
     * @return SimpleUrlAuthenticationFailureHandler
     */
    private SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/loginFailure");
    }

    /**
     * 登录成功后跳转
     * 如果需要根据不同的角色做不同的跳转处理,那么继承AuthenticationSuccessHandler重写方法
     *
     * @return SimpleUrlAuthenticationSuccessHandler
     */
    private SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/loginSuccess");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
