package stu.napls.copdmanage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import stu.napls.copdmanage.config.property.Cors;
import stu.napls.copdmanage.core.security.filter.JWTAuthenticationFilter;
import stu.napls.copdmanage.core.security.filter.JWTLoginFilter;
import stu.napls.copdmanage.core.security.handler.CustomAuthenticationEntryPoint;
import stu.napls.copdmanage.core.security.handler.CustomAuthenticationFailureHandler;
import stu.napls.copdmanage.core.security.handler.CustomAuthenticationSuccessHandler;
import stu.napls.copdmanage.core.security.handler.CustomLogoutSuccessHandler;
import stu.napls.copdmanage.core.security.provider.CustomAuthenticationProvider;
import stu.napls.copdmanage.core.security.service.CustomUserDetailsService;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author Yepeng Ding
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private Cors cors;

    private static final String[] AUTH_WHITELIST = {
            "/",
            "/index",
            "/user/create",
            "test/*",
            "/v2/api-docs", "/swagger-resources/configuration/ui", "/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security", "/configuration/security", "/swagger-ui.html", "/webjars/**"
    };

    private static final String LOGIN_URL = "/login";

    private static final String LOGOUT_URL = "/logout";

    @Resource
    private CustomUserDetailsService customUserDetailsService;

    @Resource
    private CustomAuthenticationSuccessHandler successHandler;

    @Resource
    private CustomAuthenticationFailureHandler failureHandler;

    @Resource
    private CustomAuthenticationEntryPoint entryPoint;

    @Resource
    private CustomLogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .formLogin()
                .loginPage(LOGIN_URL)
//                .successHandler(successHandler)
//                .failureHandler(failureHandler)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl(LOGOUT_URL)
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .cors()
                .and()
                .headers().addHeaderWriter(getCorsHeader())
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        auth.authenticationProvider(new CustomAuthenticationProvider(customUserDetailsService, new BCryptPasswordEncoder()));
    }

    private StaticHeadersWriter getCorsHeader() {
        return new StaticHeadersWriter(Arrays.asList(
                new Header("Access-control-Allow-Origin", cors.getFrontAppUrl()),
                new Header("Access-Control-Allow-Credentials", "true"),
                new Header("Access-Control-Allow-Headers", "Authorization,Content-Type,Accept,Origin,User-Agent,DNT,Cache-Control,X-Mx-ReqToken,X-Data-Type,X-Requested-With"),
                new Header("Access-Control-Allow-Headers", "X-Data-Type,X-Auth-Token"),
                new Header("Access-Control-Allow-Methods", "GET,POST,OPTIONS,HEAD,PUT")));
    }

}
