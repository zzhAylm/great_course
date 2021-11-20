package com.graduate.vod.controller;

import com.graduate.base.exception.MyException;
import com.graduate.utils.Result;
import com.graduate.vod.service.VodService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/12 0:38
 */
@RestController
@RequestMapping("/vod/video")
@CrossOrigin
public class VodController {

    @Resource
    private VodService vodService;
//    上传视频到阿里云
    @PostMapping("/upload")
    public Result uploadVideo(MultipartFile file){

//        返回上传视频的id
        String videoId = vodService.uploadVideo(file);

        return Result.success().data("videoId", videoId);

    }


    @DeleteMapping("/delete/{videoId}")
    public Result removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
        @PathVariable String videoId){
        try {
            vodService.removeVideo(videoId);
            return Result.success().message("视频删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(500,"删除视频失败");
        }
    }

//    删除多个阿里云视频

    @DeleteMapping("/delete")
    public Result removeVideo(@RequestParam("videoList")List<String> videoList){

        vodService.removeVideoList(videoList);

        return Result.success();

    }

}
