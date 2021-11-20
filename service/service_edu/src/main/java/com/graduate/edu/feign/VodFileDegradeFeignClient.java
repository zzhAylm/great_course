package com.graduate.edu.feign;

import com.graduate.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/13 23:10
 */
@Component
@Slf4j
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public Result removeVideo(String videoId) {
        log.info("执行了服务熔断......................");
        return Result.error().message("删除视频失败");
    }

    @Override
    public Result removeVideo(List<String> videoList) {
        return Result.error().message("删除多个视频失败");
    }
}
