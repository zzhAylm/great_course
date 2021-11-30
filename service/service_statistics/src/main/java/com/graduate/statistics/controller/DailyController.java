package com.graduate.statistics.controller;


import com.graduate.statistics.service.DailyService;
import com.graduate.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-23
 */
@RestController
@RequestMapping("/statistics/daily")
//@CrossOrigin
public class DailyController {

    @Resource
    private DailyService dailyService;
    @PostMapping("/{day}")
    public Result createStatisticsByDate(@PathVariable String day) {
        dailyService.createStatisticsByDay(day);
        return Result.success();
    }

    /**
     * 显示图标的数据,日期和 要显示的数据
     * */
    @GetMapping("/showChart/{begin}/{end}/{type}")
    public Result showChart(@PathVariable String begin,@PathVariable String end,@PathVariable String type){
        Map<String, Object> map = dailyService.getChartData(begin, end, type);
        return Result.success().data(map);
    }

}

