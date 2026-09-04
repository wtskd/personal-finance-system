package com.baidu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baidu.dao")
public class ProjectService9001 {
    public static void main(String[] args) {
        SpringApplication.run(ProjectService9001.class, args);
    }
}
