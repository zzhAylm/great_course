package com.graduate.edu.mapper;

import com.graduate.edu.pojo.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.edu.pojo.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-03
 */
public interface CourseMapper extends BaseMapper<Course> {
    CoursePublishVo selectCoursePublishVoById(String id);

}
