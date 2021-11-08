package com.graduate.edu.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.base.exception.MyException;
import com.graduate.edu.pojo.Course;
import com.graduate.edu.mapper.CourseMapper;
import com.graduate.edu.pojo.CourseDescription;
import com.graduate.edu.pojo.vo.CourseInfoVo;
import com.graduate.edu.service.CourseDescriptionService;
import com.graduate.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-03
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {


    @Resource
    private CourseDescriptionService courseDescriptionService;
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
//       1. 添加课程信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo, course);
        int insert = baseMapper.insert(course);
        if (insert==0){
            throw new MyException(500,"插入课程失败");
        }

        String cid=course.getId();
//      2.  向课程添加课程简介
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;

    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        Course course = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course, courseInfoVo);

        CourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo, course);
        int update = baseMapper.updateById(course);
        if (update==0){
            throw new MyException(500,"修改课程信息失败");
        }
        CourseDescription description = new CourseDescription();

        description.setDescription(courseInfoVo.getDescription());
        description.setId(courseInfoVo.getId());
        boolean b = courseDescriptionService.updateById(description);
        if (!b){
            throw new MyException(500,"修改课程描述失败");
        }
    }
}
