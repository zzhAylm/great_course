package com.graduate.edu.controller;


import com.graduate.edu.pojo.vo.CourseInfoVo;
import com.graduate.edu.service.CourseService;
import com.graduate.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-03
 */
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class CourseController {
    @Resource
    private CourseService courseService;

    @PostMapping("/add")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        String cid = courseService.saveCourseInfo(courseInfoVo);
        return Result.success().data("courseId", cid);
    }

    @GetMapping("/get/{courseId}")
    public Result getCourseInfo(@PathVariable String courseId){

        CourseInfoVo courseInfoVo=courseService.getCourseInfo(courseId);
        return Result.success().data("courseInfoVo", courseInfoVo);

    }

    @PostMapping("/update")
    public Result update(@RequestBody CourseInfoVo courseInfoVo){

        courseService.updateCourseInfo(courseInfoVo);

        return Result.success();



    }

}

