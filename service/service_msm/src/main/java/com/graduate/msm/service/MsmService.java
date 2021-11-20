package com.graduate.msm.service;

import java.util.Map;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/15 23:31
 */
public interface MsmService {
    boolean send(String phone, String templateCode, Map<String, Object> param);
}
