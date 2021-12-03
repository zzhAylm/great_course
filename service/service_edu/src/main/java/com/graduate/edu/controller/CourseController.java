package com.graduate.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.edu.enums.CourseStatusEnum;
import com.graduate.edu.pojo.Course;
import com.graduate.edu.pojo.frontvo.FrontCourseWebVo;
import com.graduate.edu.pojo.vo.ChapterVo;
import com.graduate.edu.pojo.vo.CourseInfoVo;
import com.graduate.edu.pojo.vo.CoursePublishVo;
import com.graduate.edu.pojo.vo.CourseQueryVo;
import com.graduate.edu.service.ChapterService;
import com.graduate.edu.service.CourseService;
import com.graduate.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.util.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-03
 */
@Api(description = "课程管理")
@RestController
@RequestMapping("/edu/course")
//@CrossOrigin
public class CourseController {
    @Resource
    private CourseService courseService;

    @GetMapping("/get")
    public Result getCourseList() {

        List<Course> courseList = courseService.list(null);
        return Result.success().data("list", courseList);

    }

    //    分页查询
    @PostMapping("/page/{current}/{limit}")
    public Result getPageCourseList(@PathVariable Integer current,
                                    @PathVariable Integer limit) {
        Page<Course> page = new Page<>(current, limit);
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_modified");
        IPage<Course> courseIPage = courseService.page(page, wrapper);
        return Result.success().data("list", courseIPage.getRecords()).data("total", courseIPage.getTotal());
    }

    //    条件查询带分页
    @PostMapping("/pageCondition/{current}/{limit}")
    public Result getPageCourseList(@PathVariable Integer current,
                                    @PathVariable Integer limit,
                                    @RequestBody(required = false) CourseQueryVo courseQueryVo) {
        Page<Course> page = new Page<>(current, limit);

        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_modified");
        String title = courseQueryVo.getTitle();
        String status = courseQueryVo.getStatus();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }
        IPage<Course> courseIPage = courseService.page(page, wrapper);
        return Result.success().data("list", courseIPage.getRecords()).data("total", courseIPage.getTotal());
    }

    @PostMapping("/add")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {

        String cid = courseService.saveCourseInfo(courseInfoVo);
        return Result.success().data("courseId", cid);
    }

    @GetMapping("/get/{courseId}")
    public Result getCourseInfo(@PathVariable String courseId) {

        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return Result.success().data("courseInfoVo", courseInfoVo);

    }

    @PostMapping("/update")
    public Result update(@RequestBody CourseInfoVo courseInfoVo) {

        courseService.updateCourseInfo(courseInfoVo);

        return Result.success();
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("/coursePublishInfo/{id}")
    public Result getCoursePublishVoById(
//        @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVoById(id);
        return Result.success().data("publishCourse", coursePublishVo);
    }

    //    课程的最终发布，修改课程的状态
    @PostMapping("/publishCourse/{courseId}")
    public Result publishCourse(@PathVariable String courseId) {

        Course course = new Course();
        course.setId(courseId);
        course.setStatus(CourseStatusEnum.PUBLISHED.getStatus());//设置课程为以发布
        courseService.updateById(course);
        return Result.success();

    }

    //    删除课程
    @DeleteMapping("/delete/{courseId}")
    public Result deleteCourse(@PathVariable String courseId) {


        courseService.removeCourse(courseId);

        return Result.success();

    }


}

