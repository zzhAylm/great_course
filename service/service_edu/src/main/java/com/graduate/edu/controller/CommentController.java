package com.graduate.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.edu.feign.UcenterClient;
import com.graduate.edu.pojo.Comment;
import com.graduate.edu.pojo.frontvo.UcenterMemberVo;
import com.graduate.edu.service.CommentService;
import com.graduate.utils.JwtUtils;
import com.graduate.utils.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-21
 */
@RestController
@RequestMapping("/edu/comment")
@CrossOrigin
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private UcenterClient ucenterClient;


    //根据课程id查询评论列表
    @ApiOperation(value = "评论分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseId", value = "查询对象", required = false)
                     @RequestParam(required = false) String courseId) {
        Page<Comment> pageParam = new Page<>(page, limit);
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.orderByDesc("gmt_create");
        commentService.page(pageParam, wrapper);
        List<Comment> commentList = pageParam.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());
        return Result.success().data(map);
    }


    @ApiOperation(value = "添加评论")
    @PostMapping("/auth/save")
    public Result save(@RequestBody Comment comment, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        if ("".equals(memberId)||StringUtils.isEmpty(memberId)) {
            return Result.error().code(28004).message("请登录");
        }
        comment.setMemberId(memberId);
        UcenterMemberVo ucenterInfo = ucenterClient.getUcenterMemberVo(memberId);
        comment.setNickname(ucenterInfo.getNickname());
        comment.setAvatar(ucenterInfo.getAvatar());
        commentService.save(comment);
        return Result.success();
    }



}

