package com.graduate.edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduate.edu.pojo.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.edu.pojo.frontvo.FrontCourseVo;
import com.graduate.edu.pojo.frontvo.FrontCourseWebVo;
import com.graduate.edu.pojo.vo.CourseInfoVo;
import com.graduate.edu.pojo.vo.CoursePublishVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-03
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);
    CoursePublishVo getCoursePublishVoById(String id);

    void removeCourse(String courseId);


    Map<String, Object> pageCondition(Long current, Long limit, FrontCourseVo courseVo);


/**
 * 获取课程信息
 * @param id
 * @return
 */
    FrontCourseWebVo selectInfoWebById(String id);
/**
 9
 * 更新课程浏览数
 * @param id
 */
    void updatePageViewCount(String id);
}
