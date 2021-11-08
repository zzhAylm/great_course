package com.graduate.edu.service;

import com.graduate.edu.pojo.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.edu.pojo.vo.CourseInfoVo;

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
}
