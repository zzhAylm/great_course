package com.graduate.order.controller;


import com.graduate.order.service.PayLogService;
import com.graduate.utils.Result;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-21
 */
@RestController
@RequestMapping("/order/log")
@CrossOrigin
public class PayLogController {

    @Resource
    private PayLogService payService;

    /**
     * 生成二维码
     *
     * @return
     */
    @GetMapping("/createNative/{orderNo}")
    public Result createNative(@PathVariable String orderNo) {
        Map map = payService.createNative(orderNo);
        return Result.success().data(map);
    }


    @ApiOperation("查询订单状态")
    @GetMapping("/queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo) {
        //调用查询接口
        Map<String, String> map = payService.queryPayStatus(orderNo);
        if (map == null) {//出错
            return Result.error().message("支付出错");
        }
        if (map.get("trade_state").equals("SUCCESS")) {//如果成功
            //更改订单状态
            payService.updateOrderStatus(map);
            return Result.success().message("支付成功");
        }
        return Result.success().code(25000).message("支付中");
    }

}

