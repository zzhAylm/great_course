package com.graduate.statistics.scheduled;

import com.graduate.statistics.service.DailyService;
import com.graduate.statistics.utils.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/24 0:20
 */
@Component
public class ScheduledTask {

    @Resource
    private DailyService dailyService;
        /**
         * 测试
         * 每天七点到二十三点每五秒执行一次
         */
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void task1() {
//        System.out.println("*********++++++++++++*****执行了");
//    }
        /**
         * 每天凌晨1点执行定时
         */
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
        //获取上一天的日期
        String day = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        dailyService.createStatisticsByDay(day);
    }
}
