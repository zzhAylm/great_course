package com.graduate.edu.easy_excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/1 9:21
 */
@Data
public class WriteStudent {

    @ExcelProperty(value = "学生姓名")
    private String name;
    @ExcelProperty(value = "学生编号")
    private String num;
}
