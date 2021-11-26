package com.graduate.statistics.client;

import com.graduate.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/24 0:12
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @GetMapping(value = "/ucenter/member/countregister/{day}")
    Result registerCount(@PathVariable("day") String day);
}
