package com.graduate.ucenter.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/16 1:28
 */
@Component
@ComponentScan("com.graduate.base")
@MapperScan("com.graduate.ucenter.mapper")
public class MyConfig {
}