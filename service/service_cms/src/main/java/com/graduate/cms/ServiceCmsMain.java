package com.graduate.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:Banner轮播图服务启动类
 * @Author: 张紫韩
 * @Crete 2021/11/14 18:06
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceCmsMain {
    public static void main(String[] args) {

        SpringApplication.run(ServiceCmsMain.class, args);
    }
}
