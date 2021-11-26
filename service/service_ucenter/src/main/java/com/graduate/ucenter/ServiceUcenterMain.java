package com.graduate.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/16 9:52
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceUcenterMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUcenterMain.class, args);
    }
}
