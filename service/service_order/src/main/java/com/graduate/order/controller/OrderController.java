package com.graduate.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduate.order.pojo.Order;
import com.graduate.order.service.OrderService;
import com.graduate.utils.JwtUtils;
import com.graduate.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-21
 */
@RestController
@RequestMapping("/order/order")
@CrossOrigin
public class OrderController {

    @Resource
    private OrderService orderService;

        //根据课程id和用户id创建订单，返回订单id
    @PostMapping("/create/{courseId}")
    public Result save(@PathVariable String courseId, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        if ("".equals(memberId) || StringUtils.isEmpty(memberId)){
            return Result.error().code(28004).message("请先登录");
        }
        String orderId = orderService.saveOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return Result.success().data("orderId", orderId);
    }


    @ApiOperation("根据订单id,查询订单信息")
    @GetMapping("/get/{orderId}")
    public Result get(@PathVariable String orderId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        Order order = orderService.getOne(wrapper);
        return Result.success().data("item", order);
    }


    @GetMapping("/isBuyCourse/{memberid}/{id}")
    public boolean isBuyCourse(@PathVariable String memberid,
                               @PathVariable String id) {
        //订单状态是1表示支付成功
        int count = orderService.count(new QueryWrapper<Order>().eq("member_id", memberid).eq("course_id", id).eq("status", 1));
        return count > 0;
    }

}

