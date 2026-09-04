package com.baidu.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.FormContentFilter;
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<FormContentFilter> formContentFilter() {
        FilterRegistrationBean<FormContentFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new FormContentFilter());
        registration.addUrlPatterns("/*");
        registration.setName("formContentFilter");
        registration.setOrder(1);
        return registration;
    }
}
