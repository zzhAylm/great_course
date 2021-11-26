package com.graduate.ucenter.controller;

import com.google.gson.Gson;
import com.graduate.base.exception.MyException;
import com.graduate.ucenter.pojo.Member;
import com.graduate.ucenter.service.MemberService;
import com.graduate.ucenter.utils.ConstantWxUtils;
import com.graduate.ucenter.utils.HttpClientUtils;
import com.graduate.utils.JwtUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @Description:微信API类
 * @Author: 张紫韩
 * @Crete 2021/11/19 20:09
 */

@CrossOrigin
@Controller//注意这里没有配置 @RestController
@RequestMapping("/ucenter/wx")
public class WxApiController {

    @Resource
    private MemberService memberService;

    @GetMapping("/callback")
    public String callback(String code, String state, HttpSession session) {
        //得到授权临时票据code
//        System.out.println(code);
//
//        System.out.println(state);

        //从redis中将state获取出来，和当前传入的state作比较
        //如果一致则放行，如果不一致则抛出异常：非法访问
        //向认证服务器发送请求换取access_token
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        String accessTokenUrl = String.format(baseAccessTokenUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                ConstantWxUtils.WX_OPEN_APP_SECRET,
                code);
        String result = null;
        try {
            result = HttpClientUtils.get(accessTokenUrl);
//            System.out.println("accessToken=============" + result);
        } catch (Exception e) {
            throw new MyException(500, "获取access_token失败");
        }
        //解析json字符串
        Gson gson = new Gson();
        HashMap map = gson.fromJson(result, HashMap.class);
        String accessToken = (String) map.get("access_token");
        String openid = (String) map.get("openid");
        //查询数据库当前用用户是否曾经使用过微信登录

        Member member = memberService.getByOpenid(openid);

        if (member == null) {
//            System.out.println("新用户注册");
            //访问微信的资源服务器，获取用户信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);
            String resultUserInfo = null;
            try {
                resultUserInfo = HttpClientUtils.get(userInfoUrl);
//                System.out.println("resultUserInfo==========" + resultUserInfo);
                //解析json
                HashMap mapUserInfo = gson.fromJson(resultUserInfo, HashMap.class);
                String nickname = (String) mapUserInfo.get("nickname");
                String headimgurl = (String) mapUserInfo.get("headimgurl");
                //向数据库中插入一条记录
                member = new Member();
                member.setNickname(nickname);
                member.setOpenid(openid);
                member.setAvatar(headimgurl);
                memberService.save(member);
            } catch (Exception e) {
                throw new MyException(500, "获取用户信息失败");
            }
        }
        // 生成jwt
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        //存入cookie
        //CookieUtils.setCookie(request, response, "guli_jwt_token", token);
        //因为端口号不同存在蛞蝓问题，cookie不能跨域，所以这里使用url重写
        return "redirect:http://localhost:3000?token=" + token;

    }

    @GetMapping("/login")
    public String genQrConnect(HttpSession session) {
        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        // 回调地址
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL; //获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (UnsupportedEncodingException e) {
            throw new MyException(500, e.getMessage());
        }
        // 防止csrf攻击（跨站请求伪造攻击）
        //String state = UUID.randomUUID().toString().replaceAll("-", "");//一般情况下会使用一个随机数
        String state = "imhelen";//为了让大家能够使用我搭建的外网的微信回调跳转服务器，这里填写你在ngrok的前置域名
//        System.out.println("state = " + state);
        // 采用redis等进行缓存state 使用sessionId为key 30分钟后过期，可配置
        //键："wechar-open-state-" + httpServletRequest.getSession().getId()
        //值：satte
        //过期时间：30分钟

        //生成qrcodeUrl
        String qrcodeUrl = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                state);
        return "redirect:" + qrcodeUrl;
    }
}
