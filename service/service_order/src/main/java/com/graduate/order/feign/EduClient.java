package com.graduate.order.feign;

import com.graduate.order.pojo.vo.FrontCourseWebVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/22 0:33
 */
@Component
@FeignClient("service-edu")
public interface EduClient {
        //根据课程id查询课程信息
    @GetMapping("/edu/front/course//getDto/{courseId}")
    FrontCourseWebVo getCourseInfoDto(@PathVariable("courseId") String courseId);
}
