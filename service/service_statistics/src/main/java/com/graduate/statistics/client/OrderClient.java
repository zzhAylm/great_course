package com.graduate.statistics.client;

import com.graduate.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2022/3/30 15:51
 */
@Component
@FeignClient("service-order")
public interface OrderClient {
    @GetMapping("/order/order/num/{day}")
    public Result orderNum(@PathVariable("day") String day);
}
