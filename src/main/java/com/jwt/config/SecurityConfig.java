package com.jwt.config;

import com.jwt.config.jwt.JwtAuthenticationFilter;
import com.jwt.config.jwt.JwtAuthorizationFilter;
import com.jwt.filter.TestTokenFilter;
import com.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.addFilterBefore(new TestTokenFilter(), SecurityContextPersistenceFilter.class);
        http
                .addFilter(corsFilter) // 인증기능이 있을때는 필터에 등록시켜줘야 한다.
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session을 사용하지 않겠다.
                .and()
                .formLogin().disable() // form 로그인을 하지 않겠다.
                .httpBasic().disable() // 기본적인 http 로그인 방식을 사용하지 않는다.
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_MANAGER')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_MANAGER')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_MANAGER')");
    }
}
