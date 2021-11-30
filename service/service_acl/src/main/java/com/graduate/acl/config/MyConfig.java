package com.graduate.acl.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Description:配置类
 * @Author: 张紫韩
 * @Crete 2021/11/14 18:24
 */
@Configuration
@MapperScan("com.graduate.acl.mapper")
@ComponentScan(basePackages = "com.graduate")
public class MyConfig {
}
