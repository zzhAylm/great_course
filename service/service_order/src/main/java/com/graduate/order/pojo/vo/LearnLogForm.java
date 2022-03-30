package com.graduate.order.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2022/3/30 13:35
 */
@Data
public class LearnLogForm implements Serializable {

    private Integer current=1;
    private Integer size=10;
    //章名称
    private String chapterName;
    //小节名称
    private String videoName;
    private String courseName;
    private String teacherName;
}
