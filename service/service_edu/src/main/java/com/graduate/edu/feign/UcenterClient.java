package com.graduate.edu.feign;

import com.graduate.edu.pojo.frontvo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/21 3:24
 */
@Component
@FeignClient(name="service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {

        //根据用户id获取用户信息
    @GetMapping("/ucenter/member/getUcenterVo/{memberId}")
    UcenterMemberVo getUcenterMemberVo(@PathVariable("memberId") String memberId);

}
