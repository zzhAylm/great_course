package com.graduate.utils;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/10/23 19:34
 */
//统一返回值 ，200为成功，500为失败
public enum ResultCode {
      SUCCESS(200),ERROR(500);
      private Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
