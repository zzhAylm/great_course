package com.graduate.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/26 20:33
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceGatewayMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayMain.class, args);
    }
}
