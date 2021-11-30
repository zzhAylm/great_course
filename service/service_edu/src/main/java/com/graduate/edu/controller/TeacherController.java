package com.graduate.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.edu.pojo.Teacher;
import com.graduate.edu.pojo.vo.TeacherQueryVo;
import com.graduate.edu.service.TeacherService;
import com.graduate.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 张紫韩
 * @since 2021-10-22
 */
//@CrossOrigin
@Api(description = "讲师管理")
@RestController
@EnableTransactionManagement
@RequestMapping("/edu/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping("/queryAll")
    public Result query(){
        List<Teacher> list = teacherService.list(null);

        return Result.success().data("list", list);
    }


    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("/delete/{id}")
    public Result delete(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable("id")String id){

        boolean flag = teacherService.removeById(id);
        if (flag){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @ApiOperation("讲师分页查询")
    @GetMapping("/page/{current}/{limit}")
    public Result pageTeacher(@PathVariable("current")Integer current, @PathVariable("limit")Integer limit){

        Page<Teacher> page = new Page<>(current,limit);
        IPage<Teacher> iPage = teacherService.page(page, null);
        List<Teacher> list = iPage.getRecords();
        long total = iPage.getTotal();
        return Result.success().data("list", list).data("total", total);

    }

    @ApiOperation("讲师模糊带分页查询")
    @PostMapping("/pageCondition/{current}/{limit}")
    public Result pageTeacher(@PathVariable("current")Integer current,
                              @PathVariable("limit")Integer limit,
                              @RequestBody(required = false) TeacherQueryVo teacherQueryVo){
        Page<Teacher> page = new Page<>(current,limit);
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        String name = teacherQueryVo.getName();
        String begin = teacherQueryVo.getBegin();
        String end = teacherQueryVo.getEnd();
        Integer level = teacherQueryVo.getLevel();

        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        wrapper.orderByDesc("gmt_create");
        IPage<Teacher> iPage = teacherService.page(page, wrapper);
        List<Teacher> teacherList = iPage.getRecords();
        long total = iPage.getTotal();
        return Result.success().data("list", teacherList).data("total",total);

    }

    @ApiOperation("讲师添加")
    @PostMapping("/add")
    public Result add(@RequestBody Teacher teacher){

        boolean save = teacherService.save(teacher);
        if (save){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @ApiOperation("讲师获得")
    @GetMapping("/get/{id}")
    public Result getTeacher(@PathVariable("id")String id){
        Teacher teacher = teacherService.getById(id);
        return Result.success().data("teacher", teacher);

    }

    @ApiOperation("讲师修改")
    @PostMapping("/update")
    public Result updateTeacher(@RequestBody Teacher teacher){

        boolean flag = teacherService.updateById(teacher);
        if (flag){
            return Result.success();
        }else {
            return Result.error();
        }


    }

}

