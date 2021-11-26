package com.graduate.canal;

import com.graduate.canal.client.CanalClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @Description:分库时实现数据统一
 * @Author: 张紫韩
 * @Crete 2021/11/25 1:32
 */
@SpringBootApplication
public class CanalClientMain implements CommandLineRunner {

    @Resource
    private CanalClient canalClient;

    public static void main(String[] args) {
        SpringApplication.run(CanalClientMain.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //项目启动，执行canal客户端监听
        canalClient.run();

    }
}
