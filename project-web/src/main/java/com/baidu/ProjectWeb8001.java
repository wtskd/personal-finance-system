package com.baidu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProjectWeb8001 {
    public static void main(String[] args) {
        SpringApplication.run(ProjectWeb8001.class,args);
    }
}
