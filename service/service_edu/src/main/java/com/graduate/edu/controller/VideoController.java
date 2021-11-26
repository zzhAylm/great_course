package com.graduate.edu.controller;


import com.alibaba.excel.util.StringUtils;
import com.graduate.base.exception.MyException;
import com.graduate.edu.feign.VodClient;
import com.graduate.edu.pojo.Video;
import com.graduate.edu.service.VideoService;
import com.graduate.utils.Result;
import io.swagger.annotations.Api;
import org.apache.poi.util.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-03
 */
@Api(description = "视频管理")
@RestController
@RequestMapping("/edu/video")
@CrossOrigin
public class VideoController {

    @Resource
    private VideoService videoService;
//    查询小结

    @Resource
    private VodClient vodClient;

    @GetMapping("/get/{videoId}")
    public Result getVideo(@PathVariable String videoId){
        Video video = videoService.getById(videoId);
        return Result.success().data("video",video);
    }

//    添加小结
    @PostMapping("/add")
    public Result addVideo(@RequestBody Video video){
        videoService.save(video);
        return Result.success();
    }
//   TODO, 修改小结


//    删除小结
//    TODO，后面这个方法还需要完善，在删除小结的额时候同时把小结的视频删除
    @DeleteMapping("/delete/{id}")
    public Result deleteVideo(@PathVariable String id){
        Video video = videoService.getById(id);
//         调用另一个实现删除视频， 根据视频id删除视频
        String videoSourceId = video.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)){
//            调用微服务，删除视频
            Result result = vodClient.removeVideo(videoSourceId);
            if (result.getCode()==500){
                throw new MyException(500,"删除视频出错，执行了服务熔断");
            }
        }
        videoService.removeById(id);
        return Result.success();
    }


    @PostMapping("/update")
    public Result update(@RequestBody Video video){
        videoService.updateById(video);
        return Result.success();
    }


}

