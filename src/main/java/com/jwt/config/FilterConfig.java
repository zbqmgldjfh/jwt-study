package com.jwt.config;

import com.jwt.filter.TestTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean filter1() {
//        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new TestTokenFilter());
//        registrationBean.setOrder(0);
//        registrationBean.addUrlPatterns("/*");
//        return  registrationBean;
//    }
}
