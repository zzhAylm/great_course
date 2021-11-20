package com.graduate.ucenter.controller;


import com.graduate.base.exception.MyException;
import com.graduate.ucenter.pojo.vo.LoginInfoVo;
import com.graduate.ucenter.pojo.vo.LoginVo;
import com.graduate.ucenter.pojo.vo.RegisterVo;
import com.graduate.ucenter.service.MemberService;
import com.graduate.utils.JwtUtils;
import com.graduate.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-16
 */
@RestController
@RequestMapping("/ucenter/member")
@CrossOrigin
public class MemberController {
    @Resource
    private MemberService memberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {
        System.out.println(loginVo);
        String token = memberService.login(loginVo);
        return Result.success().data("token",token);
    }
    @ApiOperation(value = "会员注册")
    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return Result.success();
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("/auth/getLoginInfo")
    public Result getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            LoginInfoVo loginInfoVo = memberService.getLoginInfo(memberId);
            return Result.success().data("loginInfo", loginInfoVo);
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(500,"error");
        }
    }
}

