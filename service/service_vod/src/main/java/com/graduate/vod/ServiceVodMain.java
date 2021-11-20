package com.graduate.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:视频服务启动类
 * @Author: 张紫韩
 * @Crete 2021/11/12 0:35
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class ServiceVodMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVodMain.class, args);
    }
}
