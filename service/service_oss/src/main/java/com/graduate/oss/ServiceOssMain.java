package com.graduate.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:图片服务启动类
 * @Author: 张紫韩
 * @Crete 2021/10/30 17:05
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class ServiceOssMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOssMain.class, args);
    }
}
