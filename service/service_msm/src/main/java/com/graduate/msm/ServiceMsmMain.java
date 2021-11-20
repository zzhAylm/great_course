package com.graduate.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/15 23:27
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)

public class ServiceMsmMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMsmMain.class, args);
    }
}
