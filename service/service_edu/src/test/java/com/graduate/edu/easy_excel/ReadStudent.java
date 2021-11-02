package com.graduate.edu.easy_excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/1 9:43
 */
@Data
public class ReadStudent {

    @ExcelProperty(value = "学生姓名",index = 0) //对应excel表中的第一列
    private String name;
    @ExcelProperty(value = "学生编号",index = 1) //对应excel表中的第二列
    private String num;
}
