package com.graduate.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:优课网系统设计与实现启动类
 * @Author: 张紫韩
 * @Crete 2021/10/22 15:55
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceEduMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduMain.class, args);
    }
}
