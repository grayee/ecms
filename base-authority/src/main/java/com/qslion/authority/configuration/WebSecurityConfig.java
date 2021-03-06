package com.qslion.authority.configuration;

import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

import com.qslion.authority.security.AuAuthenticationFailureHandler;
import com.qslion.authority.security.AuAuthenticationSuccessHandler;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

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

    private static final String[] PERMIT_ALL_URLS = new String[]{"/", "/login/*", "/logout/*", "/login*", "/logout**", "error**", "/swagger**",
            "/swagger**/*",
            "/webjars/**", "/csrf**", "/index", "/v2/*", "/hello*"};
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private Filter ssoFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**").authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(PERMIT_ALL_URLS).permitAll()
                .anyRequest().authenticated()
                //.and().csrf()
                // .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //.csrfTokenRepository(csrfTokenRepository()).and()//.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
                .and().oauth2Login().loginPage("/login").failureUrl("/loginFailure").defaultSuccessUrl("/loginSuccess")
                .permitAll().and()
                //.rememberMe().key("test.com").and()
                .addFilterBefore(ssoFilter, BasicAuthenticationFilter.class)
                //.addFilterAt(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                //.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")).and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and().exceptionHandling().accessDeniedPage("/accessDenied")
                .and().csrf().disable()
                //session管理,失效后跳转
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).invalidSessionUrl("/login")
                //.and()
                //只允许一个用户登录,如果同一个账户两次登录,那么第一个账户将被踢下线,跳转到登录页面
                .sessionManagement().maximumSessions(1).expiredUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
        usernamePasswordAuthenticationFilter.setPostOnly(true);
        usernamePasswordAuthenticationFilter.setAuthenticationManager(this.authenticationManager());
        usernamePasswordAuthenticationFilter.setUsernameParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
        usernamePasswordAuthenticationFilter.setPasswordParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
        usernamePasswordAuthenticationFilter
                .setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        usernamePasswordAuthenticationFilter
                .setAuthenticationFailureHandler(new AuAuthenticationFailureHandler("/loginFailure"));
        usernamePasswordAuthenticationFilter
                .setAuthenticationSuccessHandler(new AuAuthenticationSuccessHandler("/loginSuccess"));
        return usernamePasswordAuthenticationFilter;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private Filter csrfHeaderFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain)
                    throws ServletException, IOException {
                CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
                if (csrf != null) {
                    Cookie cookie = new Cookie("XSRF-TOKEN", csrf.getToken());
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                filterChain.doFilter(request, response);
            }
        };
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }


    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
        System.out.println(new BCryptPasswordEncoder().encode("test"));
        System.out.println(new BCryptPasswordEncoder().encode("wang"));
        System.out.println(new BCryptPasswordEncoder().encode("client_secret_1234567890"));
    }

}
