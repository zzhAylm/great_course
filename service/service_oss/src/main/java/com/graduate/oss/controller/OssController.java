package com.graduate.oss.controller;

import com.graduate.oss.service.OssService;
import com.graduate.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/10/30 18:07
 */
@Api(description = "Oss文件上传")
@CrossOrigin
@RestController
@RequestMapping("/oss/file")
public class OssController {

    @Resource
    private OssService ossService;
//    上传头像的方法
    @PostMapping("/upload")
    public Result uploadOssFile(MultipartFile file){

        String url =ossService.uploadFileAvatar(file);
        return Result.success().message("文件上传成功").data("url",url);
    }
}
