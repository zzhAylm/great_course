package com.graduate.edu.pojo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/1 10:24
 */

@Data
public class SubjectExcel {

    @ExcelProperty(index = 0) //一级标题
    private String oneSubjectName;
    @ExcelProperty(index = 1) //二级标题
    private String twoSubjectName;
}
