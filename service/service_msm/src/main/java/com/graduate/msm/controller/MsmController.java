package com.graduate.msm.controller;

import com.graduate.msm.service.MsmService;
import com.graduate.msm.utils.RandomUtil;
import com.graduate.utils.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/15 23:31
 */
@RestController
@CrossOrigin
@RequestMapping("/msm/message")
public class MsmController {

    @Resource
    private MsmService msmService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static final String TEMPLATE_CODE="SMS_227739696";

    @GetMapping("/send/{phone}")
    public Result code(@PathVariable String phone) {

//       从redis中获取，设置验证码的有效时间
        String code = redisTemplate.opsForValue().get(phone);

        if (!StringUtils.isEmpty(code))
            return Result.success();
//        生成随机值
        code = RandomUtil.getFourBitRandom();

        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        System.out.println("验证码：**************:"+code);

//        TODO,
        boolean isSend = msmService.send(phone, TEMPLATE_CODE, param);
        if (isSend) {
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return Result.success();
        } else {
            return Result.error().message("发送短信失败");
        }
    }
}
