package com.graduate.base.exception;

import com.graduate.base.exception.MyException;
import com.graduate.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/10/24 13:00
 * 统一全局异常处理类
 */

//将异常信息输出到文件中： 再异常处理类加入 @Slf4j 的注解,
// 再捕获到的异常信息输出到 error.log文件 : log.error(e.getMessage())
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

//    全局，包含所有的异常
    @ExceptionHandler(Exception.class)//指定什么异常会执行这个方法
    @ResponseBody //返回数据
    public Result error(Exception e){
//        log.error(e.getMessage()); //将错误信息输出带文件中
        log.error(ExceptionUtil.getMessage(e)); //将更多的错误信息输出到文件中
        e.printStackTrace();
        return Result.error().message("执行了全局异常处理。。。");
    }

//    ArithmeticException异常处理方法
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return Result.error().message("执行了ArithmeticException异常处理方法异常处理。。");
    }

//    自定义异常处理方法
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result error(MyException e){
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMessage());
    }

}
