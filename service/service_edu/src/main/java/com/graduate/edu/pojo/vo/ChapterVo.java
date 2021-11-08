package com.graduate.edu.pojo.vo;

import lombok.Data;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/7 16:03
 */
//章节实体类Vo
@Data
public class ChapterVo {
    private String id;
    private String title;
//    表示小节
    private List<VideoVo> children=new ArrayList<>();
}
