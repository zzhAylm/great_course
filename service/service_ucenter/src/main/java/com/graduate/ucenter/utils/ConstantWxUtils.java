package com.graduate.ucenter.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:微信工具类
 * @Author: 张紫韩
 * @Crete 2021/11/19 19:54
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx.open")
public class ConstantWxUtils implements InitializingBean {

    /**
     *   微信开放平台 appid
     * */
    private String appid;

    /**
     * 微信开放平台 appsecret
     * */
    private String appsecret;

    /**
     * 微信开放平台 重定向url（guli.shop需要在微信开放平台配置）
     * */
    private String redirecturl;



    public static String WX_OPEN_APP_ID;
    public static String WX_OPEN_APP_SECRET;
    public static String WX_OPEN_REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_OPEN_APP_ID = appid;
        WX_OPEN_APP_SECRET = appsecret;
        WX_OPEN_REDIRECT_URL = redirecturl;
    }

}
