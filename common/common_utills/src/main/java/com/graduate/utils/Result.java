package com.graduate.utils;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/10/23 19:43
 */
//统一返回结构类
@Data
public class Result {

    @ApiModelProperty("返回的状态码")
    private Integer code;
    @ApiModelProperty("是否成功")
    private Boolean flag;
    @ApiModelProperty("返回的消息")
    private String message;
    @ApiModelProperty("返回的数据")
    private Map<String,Object> data=new HashMap<>();

    private Result(){};

    public static Result success(){

        Result result=new Result();
        result.setFlag(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage("成功");
        return  result;
    }

    public static Result error(){
        Result result=new Result();
        result.setFlag(false);
        result.setCode(ResultCode.ERROR.getCode());
        result.setMessage("失败");
        return  result;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result flag(Boolean flag){
        this.setFlag(flag);
        return this;
    }
    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    public Result data(Map<String,Object> map){
        this.setData(map);
        return this;
    }
}

