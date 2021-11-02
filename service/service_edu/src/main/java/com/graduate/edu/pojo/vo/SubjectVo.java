package com.graduate.edu.pojo.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/1 17:33
 */
@Data
public class SubjectVo {
    private String id;
    private String title;
//    一个二级分类又多个二级分类
    private List<SubjectVo> children=new ArrayList<>();
}
