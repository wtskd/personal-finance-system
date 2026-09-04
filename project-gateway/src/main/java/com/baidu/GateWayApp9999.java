package com.baidu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Gateway 网关启动类
 * 端口：9999
 * 功能：统一入口，负责请求路由转发、负载均衡、跨域处理
 */
@SpringBootApplication
public class GateWayApp9999 {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApp9999.class, args);
    }
}
