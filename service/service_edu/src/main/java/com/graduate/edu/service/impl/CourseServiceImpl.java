package com.graduate.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.base.exception.MyException;
import com.graduate.edu.pojo.Course;
import com.graduate.edu.mapper.CourseMapper;
import com.graduate.edu.pojo.CourseDescription;
import com.graduate.edu.pojo.frontvo.FrontCourseVo;
import com.graduate.edu.pojo.frontvo.FrontCourseWebVo;
import com.graduate.edu.pojo.vo.CourseInfoVo;
import com.graduate.edu.pojo.vo.CoursePublishVo;
import com.graduate.edu.service.ChapterService;
import com.graduate.edu.service.CourseDescriptionService;
import com.graduate.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.edu.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private VideoService videoService;
    @Resource
    private ChapterService chapterService;


    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
//       1. 添加课程信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo, course);
        int insert = baseMapper.insert(course);
        if (insert == 0) {
            throw new MyException(500, "插入课程失败");
        }

        String cid = course.getId();
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
    public CoursePublishVo getCoursePublishVoById(String id) {
        return baseMapper.selectCoursePublishVoById(id);
    }

    //    删除课程信息
    @Override
    public void removeCourse(String courseId) {
//        删除小节
        videoService.removeVideoByCourseId(courseId);
//        删除章节
        chapterService.removeChapterByCourseId(courseId);
//        删除课程描述
        courseDescriptionService.removeById(courseId);
//        删除课程本身
        int delete = baseMapper.deleteById(courseId);
        if (delete == 0) {
            throw new MyException(500, "课程删除失败");
        }
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo, course);
        int update = baseMapper.updateById(course);
        if (update == 0) {
            throw new MyException(500, "修改课程信息失败");
        }
        CourseDescription description = new CourseDescription();

        description.setDescription(courseInfoVo.getDescription());
        description.setId(courseInfoVo.getId());
        boolean b = courseDescriptionService.updateById(description);
        if (!b) {
            throw new MyException(500, "修改课程描述失败");
        }
    }

    @Override
    public Map<String, Object> pageCondition(Long current, Long limit, FrontCourseVo courseVo) {

        Page<Course> pageParam = new Page<>(current, limit);

        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseVo.getTitle())) {
            queryWrapper.like("title", courseVo.getTitle());
        }
        if (!StringUtils.isEmpty(courseVo.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", courseVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseVo.getSubjectId())) {
            queryWrapper.eq("subject_id", courseVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseVo.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseVo.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(courseVo.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }
        queryWrapper.orderByDesc("gmt_modified");
        baseMapper.selectPage(pageParam, queryWrapper);
        List<Course> records = pageParam.getRecords();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public FrontCourseWebVo selectInfoWebById(String id) {
        this.updatePageViewCount(id);
        return baseMapper.selectInfoWebById(id);
    }

    @Override
    public void updatePageViewCount(String id) {
        Course course = baseMapper.selectById(id);
        course.setViewCount(course.getViewCount() + 1);
        baseMapper.updateById(course);
    }
}
