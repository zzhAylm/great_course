package com.graduate.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduate.edu.pojo.Course;
import com.graduate.edu.pojo.Teacher;
import com.graduate.edu.service.CourseService;
import com.graduate.edu.service.TeacherService;
import com.graduate.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/14 19:09
 */
@RestController
@CrossOrigin
@RequestMapping("/edu/front")
public class FrontIndexController {

    @Resource
    private CourseService courseService;

    @Resource
    private TeacherService teacherService;

    //获取到前八的热门课程，和前四名老师
    @GetMapping("/index")
    public Result index(){

        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<Course> courseList = courseService.list(wrapper);


        QueryWrapper<Teacher> teacherWrapper = new QueryWrapper<>();
        teacherWrapper.orderByDesc("id");
        teacherWrapper.last("limit 4");
        List<Teacher> teacherList = teacherService.list(teacherWrapper);

        return Result.success().data("courseList", courseList).data("teacherList", teacherList);


    }
}
