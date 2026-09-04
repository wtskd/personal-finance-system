package com.baidu.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class Knife4jConfig {
    //可以针对每个子项目，每只一个对应的Bean
    @Bean
    public GroupedOpenApi commonApi() {
        return GroupedOpenApi.builder()
                .group("测试模块")
                .pathsToMatch("/**")
                .packagesToScan("com.baidu.controller")
                .build();
    }
}