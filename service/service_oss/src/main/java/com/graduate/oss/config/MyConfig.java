package com.graduate.oss.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/10/30 19:22
 */
@Configuration
@ComponentScan(basePackages = {"com.graduate.base"})
@MapperScan("com.graduate.oss.mapper")
public class MyConfig {
}
