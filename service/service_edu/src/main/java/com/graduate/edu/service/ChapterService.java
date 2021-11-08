package com.graduate.edu.service;

import com.graduate.edu.pojo.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.edu.pojo.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-03
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    Boolean deleteChapter(String chapterId);
}
