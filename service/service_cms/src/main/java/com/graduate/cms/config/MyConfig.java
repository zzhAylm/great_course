package com.graduate.cms.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Description:配置类
 * @Author: 张紫韩
 * @Crete 2021/11/14 18:24
 */
@Component
@ComponentScan("com.graduate.base")
@MapperScan("com.graduate.cms.mapper")
public class MyConfig {
}
