package com.graduate.edu.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/10/23 14:08
 */
@Configuration
@MapperScan("com.graduate.edu.mapper")
//加载配置类，swagger配置类，因为主启动类只会加载本项目中的配置类，
// @ComponentScan(basePackages = {"com.graduate.base"})可以扫描到其他包的配置类，如com.graduate.base.SwaggerConfig 配置类
// 加载其他包中的配置类到容器中
@ComponentScan(basePackages = {"com.graduate.base"})
public class MyConfig {



    /**
     * SQL 执行性能分析插件
     * 开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长
     */
//    @Bean
//    @Profile({"dev","test"})// 设置 dev test 环境开启
//    public PerformanceInterceptor performanceInterceptor() {
//        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//        performanceInterceptor.setMaxTime(1000);//ms，超过此处设置的ms则sql不执行
//        performanceInterceptor.setFormat(true);
//        return performanceInterceptor;
//    }


//    mybatis-plus的分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
       return new PaginationInterceptor();
    }
}
