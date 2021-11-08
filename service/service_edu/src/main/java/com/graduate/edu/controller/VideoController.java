package com.graduate.edu.controller;


import com.graduate.edu.pojo.Video;
import com.graduate.edu.service.VideoService;
import com.graduate.utils.Result;
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
@RestController
@RequestMapping("/edu/video")
@CrossOrigin
public class VideoController {

    @Resource
    private VideoService videoService;
//    查询小结

//    添加小结
    @PostMapping("/add")
    public Result addVideo(@RequestBody Video video){
        videoService.save(video);
        return Result.success();
    }
//    修改小结

//    删除小结
//    TODO，后面这个方法还需要完善，在删除小结的额时候同时把小结的视频删除
    @DeleteMapping("/delete/{id}")
    public Result deleteVideo(@PathVariable String id){
        videoService.removeById(id);
        return Result.success();
    }

}

