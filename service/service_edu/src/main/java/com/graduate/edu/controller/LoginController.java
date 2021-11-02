package com.graduate.edu.controller;

import com.graduate.utils.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/10/28 11:37
 */
@Api(description = "登录管理")
@RestController
@RequestMapping("/edu/user")
@CrossOrigin  //解决跨域问题的注解
@Slf4j
public class LoginController {


    @PostMapping("/login")
    public Result login(){
        log.info("登录请求**************");
        return Result.success().data("token","admin");
    }

    @GetMapping("/info")
    public Result info(){
        return Result.success().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }


}
