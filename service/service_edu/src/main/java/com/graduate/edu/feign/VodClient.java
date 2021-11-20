package com.graduate.edu.feign;

import com.graduate.utils.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/13 18:10
 */
@Component
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
public interface VodClient {

    @DeleteMapping("/vod/video/delete/{videoId}")
    Result removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                              @PathVariable("videoId") String videoId);

    @DeleteMapping("/vod/video/delete")
    Result removeVideo(@RequestParam("videoList") List<String> videoList);

}
