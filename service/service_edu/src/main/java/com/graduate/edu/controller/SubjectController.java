package com.graduate.edu.controller;


import com.graduate.edu.pojo.vo.SubjectVo;
import com.graduate.edu.service.SubjectService;
import com.graduate.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-01
 */
@Api(description = "课程分类管理")
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
//分类管理
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    @ApiOperation("excel格式课程数据导入库中")
    @PostMapping("/add")
    public Result addSubject(MultipartFile file) {
//        上传文件
        subjectService.saveSubject(file, subjectService);
        return Result.success();
    }

    @ApiOperation("课程分类列表")
    @GetMapping("/getAll")
    public Result getAll() {
        List<SubjectVo> subjectVoList = subjectService.getAllSubjectVo();

        return Result.success().data("list",subjectVoList);
    }


}

