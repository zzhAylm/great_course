package com.graduate.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Component;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/10/30 17:48
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    //地狱节点
    public static String END_POINT;

    //  访问ID
    public static String ACCESS_KEY_ID;

//    访问密钥
    public static String ACCESS_KEY_SECRET;

//    bucket名称
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT=endpoint;
        ACCESS_KEY_ID=keyId;
        ACCESS_KEY_SECRET=keySecret;
        BUCKET_NAME=bucketName;
    }
}
