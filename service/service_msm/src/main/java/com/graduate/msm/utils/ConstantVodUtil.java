package com.graduate.msm.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/12 0:52
 */
@Component
public class ConstantVodUtil implements InitializingBean {
    @Value("${aliyun.vod.file.keyid}")
    private String keyId;

    @Value("${aliyun.vod.file.keysecret}")
    private String keysecret;

    public static String KEY_ID;
    public static String KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_ID=this.keyId;
        KEY_SECRET=this.keysecret;

    }
}
