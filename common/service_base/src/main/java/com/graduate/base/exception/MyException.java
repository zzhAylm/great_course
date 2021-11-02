package com.graduate.base.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/10/24 13:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyException extends RuntimeException{

    private Integer code; //状态码
    private String message;//异常信息


}
