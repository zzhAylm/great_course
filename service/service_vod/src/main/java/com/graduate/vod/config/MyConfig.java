package com.graduate.vod.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/10/23 14:08
 */
@Configuration
//@MapperScan("com.graduate.edu.mapper")
//加载配置类，swagger配置类，因为主启动类只会加载本项目中的配置类，
// @ComponentScan(basePackages = {"com.graduate.base"})可以扫描到其他包的配置类，如com.graduate.base.SwaggerConfig 配置类
// 加载其他包中的配置类到容器中
@ComponentScan(basePackages = {"com.graduate.base"})
@MapperScan("com.graduate.vod.mapper")
public class MyConfig {




}
