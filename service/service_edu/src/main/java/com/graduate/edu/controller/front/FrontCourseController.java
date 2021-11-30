package com.graduate.edu.controller.front;

import com.graduate.edu.feign.OrderClient;
import com.graduate.edu.pojo.frontvo.FrontCourseVo;
import com.graduate.edu.pojo.frontvo.FrontCourseWebVo;
import com.graduate.edu.pojo.vo.ChapterVo;
import com.graduate.edu.pojo.vo.CourseInfoVo;
import com.graduate.edu.service.ChapterService;
import com.graduate.edu.service.CourseService;
import com.graduate.utils.JwtUtils;
import com.graduate.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/20 20:49
 */
@Api(description = "客户端课程管理")
@RestController
@RequestMapping("/edu/front/course")
//@CrossOrigin
public class FrontCourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private ChapterService chapterService;

    @Resource
    private OrderClient orderClient;

    //根据id查询课程详情信息
    @GetMapping("/getCourseInfo/{id}")
    public Result getCourseInfo(@PathVariable String id, HttpServletRequest request) {
        //课程查询课程基本信息
        FrontCourseWebVo courseFrontInfo = courseService.selectInfoWebById(id);
        //查询课程里面大纲数据
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(id);

        boolean buyCourse=false;
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (!"".equals(memberId) &&!StringUtils.isEmpty(memberId)){
            //远程调用，判断课程是否被购买
             buyCourse = orderClient.isBuyCourse(JwtUtils.getMemberIdByJwtToken(request), id);
        }
        return Result.success().data("course",courseFrontInfo).data("chapterVoList",chapterVideoList).data("isbuyCourse",buyCourse);
    }


    @PostMapping("/page/{current}/{limit}")
    public Result pageCourse(@PathVariable Long current,
                             @PathVariable Long limit,
                             @RequestBody(required = false) FrontCourseVo courseVo){

//        条件查询带分页
        Map<String,Object> map=courseService.pageCondition(current,limit,courseVo);

        return Result.success().data(map);

    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("/{courseId}")
    public Result getById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId) {
        //查询课程信息和讲师信息
        FrontCourseWebVo courseWebVo = courseService.selectInfoWebById(courseId);
        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.getChapterVideoByCourseId(courseId);
        return Result.success().data("course", courseWebVo).data("chapterVoList", chapterVoList);
    }

    //根据课程id查询课程信息
    @GetMapping("/getDto/{courseId}")
    public FrontCourseWebVo getCourseInfoDto(@PathVariable String courseId) {
        FrontCourseWebVo courseInfoForm = courseService.selectInfoWebById(courseId);
        return courseInfoForm;
    }







}
