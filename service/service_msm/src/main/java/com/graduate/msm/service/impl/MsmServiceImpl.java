package com.graduate.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.graduate.msm.service.MsmService;
import com.graduate.msm.utils.ConstantVodUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/15 23:31
 */
@Service
public class MsmServiceImpl implements MsmService {

    /**
     * 发送短信
     */
    @Override
    public boolean send(String PhoneNumbers, String templateCode, Map<String, Object> param) {
        if (StringUtils.isEmpty(PhoneNumbers))
            return false;

        DefaultProfile profile =
//                DefaultProfile.getProfile("default", "LTAI4FvvVEWiTJ3GNJJqJnk7", "9st82dv7EvFk9mTjYO1XXbM632fRbG");
//        DefaultProfile.getProfile("default", "LTAIq6nIPY09VROj", "FQ7UcixT9wEqMv9F35nORPqKr8XkTF");
        DefaultProfile.getProfile("default", "LTAI4GBABS7Sq8MLf2RNwLuu", "ynfsD31FLdcRoQVFSIdHP7AeoKaf4o");
        IAcsClient client = new DefaultAcsClient(profile);

//        设置固定参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

//        设置发送相关参数
        //发送手机号
        request.putQueryParameter("PhoneNumbers", PhoneNumbers);
//        //签名名称
//        request.putQueryParameter("SignName","我的谷粒在线教育网站"); //申请阿里云 签名名称
//        ////        模板CODE
//        request.putQueryParameter("TemplateCode","SMS_180051135"); //申请阿里云 模板code

        request.putQueryParameter("SignName","阿昌日语在线教育网站");//申请阿里云短信服务的【签名名称】
        request.putQueryParameter("TemplateCode","SMS_212711286");//申请阿里云短信服务的【模版中的 模版CODE】
//        验证码，JSONObject.toJSONString(param)：map装json
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}

