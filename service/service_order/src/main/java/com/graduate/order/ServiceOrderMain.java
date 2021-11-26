package com.graduate.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/21 22:44
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ServiceOrderMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderMain.class, args);
    }
}
