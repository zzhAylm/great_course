package com.graduate.edu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/23 0:47
 */

@Component
@FeignClient(value = "service-order")
public interface OrderClient {
        //查询订单信息
    @GetMapping("/order/order/isBuyCourse/{memberid}/{id}")
    boolean isBuyCourse(@PathVariable("memberid") String memberid, @PathVariable("id") String id);
}
