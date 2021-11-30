package com.graduate.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduate.edu.pojo.Course;
import com.graduate.edu.pojo.Teacher;
import com.graduate.edu.service.CourseService;
import com.graduate.edu.service.TeacherService;
import com.graduate.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:客户端讲师控制类
 * @Author: 张紫韩
 * @Crete 2021/11/20 17:49
 */
@Api(description = "前端讲师管理")
@RestController
@RequestMapping("/edu/front/teacher")
//@CrossOrigin
public class FrontTeacherController {

    @Resource
    private TeacherService teacherService;
    @Resource
    private CourseService courseService;

    @ApiOperation("分页查询讲师的方法")
    @PostMapping("/page/{current}/{limit}")
    public Result frontTeacherPage(@PathVariable Long current, @PathVariable Long limit) {
        HashMap<String, Object> map = teacherService.pageList(current, limit);
        return Result.success().data(map);
    }

    @ApiOperation("讲师详情查询")
    @GetMapping("/query/{teacherId}")
    public Result frontQueryTeacherById(@PathVariable String teacherId) {
        //1.查询讲师详情
        Teacher teacher = teacherService.getById(teacherId);

//        2.查询讲师所讲课程
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", teacherId);
        List<Course> courseList = courseService.list(wrapper);

        return Result.success().data("teacher", teacher).data("courseList",courseList);

    }


}
