package com.graduate.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.order.pojo.LearnLog;
import com.graduate.order.pojo.vo.LearnLogForm;
import com.graduate.order.service.LearnLogService;
import com.graduate.utils.JwtUtils;
import com.graduate.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 张紫韩
 * @since 2022-03-30
 */
@RestController
@RequestMapping("/order/learn")
public class LearnLogController {


    @Resource
    private LearnLogService learnLogService;

    @PostMapping("/add")
    public Result add(@RequestBody LearnLog learnLog, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if ("".equals(memberId) || StringUtils.isEmpty(memberId)) {
            return Result.error().code(28004).message("请先登录");
        }
        String leanLogId = learnLogService.create(learnLog, memberId);
        return Result.success().data("leanLogId", leanLogId);
    }

    @GetMapping("/delete/{learnId}")
    public Result delete(@PathVariable String learnId) {
        if (learnLogService.removeById(learnId)) {
            return Result.success();
        }
        return Result.error();
    }

    @PostMapping("/update")
    public Result update(@RequestBody LearnLog learnLog) {
        if (learnLogService.updateById(learnLog)) {
            return Result.success();
        }
        return Result.error();
    }

    @PostMapping("/page")
    public Result pageCondition(@RequestBody LearnLogForm learnLogForm, HttpServletRequest request) {

        QueryWrapper<LearnLog> wrapper = new QueryWrapper<>();
        wrapper.eq("member_id", JwtUtils.getMemberIdByJwtToken(request));
        if (StringUtils.isNotBlank(learnLogForm.getChapterName())) {
            wrapper.eq("chapter_name", learnLogForm.getChapterName());
        }
        if (StringUtils.isNotBlank(learnLogForm.getCourseName())) {
            wrapper.eq("course_name", learnLogForm.getCourseName());
        }
        if (StringUtils.isNotBlank(learnLogForm.getTeacherName())) {
            wrapper.eq("teacher_name", learnLogForm.getTeacherName());
        }
        if (StringUtils.isNotBlank(learnLogForm.getVideoName())) {
            wrapper.eq("video_name", learnLogForm.getVideoName());
        }
        Page<LearnLog> page = new Page<>(learnLogForm.getCurrent(), learnLogForm.getSize());

        IPage<LearnLog> iPage = learnLogService.page(page, wrapper);

        return Result.success().data("item", iPage.getRecords()).data("total", iPage.getTotal());

    }


}

