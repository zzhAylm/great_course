package com.graduate.ucenter.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/16 10:01
 */

@Data
@ApiModel(value="登录对象", description="登录对象")
public class LoginVo implements Serializable {
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;
}
