package com.graduate.vod.controller;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.graduate.base.exception.MyException;
import com.graduate.utils.Result;
import com.graduate.vod.service.VodService;
import com.graduate.vod.utils.ConstantVodUtil;
import com.graduate.vod.utils.InitVodClient;
import com.graduate.vod.utils.VideoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(description="阿里云视频点播微服务")
@RestController
@RequestMapping("/vod/video")
//@CrossOrigin
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

    @ApiOperation("获取视频播放凭证")
    @GetMapping("getPlayAuth/{videoId}")
    public Result getVideoPlayAuth(@PathVariable("videoId") String videoId) throws Exception {
        try {
            //获取阿里云存储相关常量
            String accessKeyId = ConstantVodUtil.KEY_ID;
            String accessKeySecret =ConstantVodUtil.KEY_SECRET;
            //初始化
            DefaultAcsClient client = InitVodClient.initVodClient(accessKeyId, accessKeySecret);
            //请求
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(videoId);
            //响应
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            //得到播放凭证
            String playAuth = response.getPlayAuth();
            //返回结果
            return Result.success().message("获取凭证成功").data("playAuth", playAuth);
        } catch (ClientException e) {
            e.printStackTrace();
            return Result.error();
        }
    }

}
