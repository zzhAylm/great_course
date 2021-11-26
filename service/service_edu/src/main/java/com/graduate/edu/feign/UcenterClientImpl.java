package com.graduate.edu.feign;

import com.graduate.edu.pojo.frontvo.UcenterMemberVo;
import org.springframework.stereotype.Component;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/21 3:25
 */
@Component
public class UcenterClientImpl implements UcenterClient {

    @Override
    public UcenterMemberVo getUcenterMemberVo(String memberId) {
        return null;
    }
}
