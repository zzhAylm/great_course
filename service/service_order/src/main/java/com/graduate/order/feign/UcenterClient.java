package com.graduate.order.feign;

import com.graduate.order.pojo.vo.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/22 0:36
 */

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //根据课程id查询课程信息
    @PostMapping("/ucenter/member/getInfoUc/{id}")
    public Member getInfo(@PathVariable("id") String id);
}

