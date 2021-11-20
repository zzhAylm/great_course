package com.graduate.vod.service;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/12 0:39
 */
public interface VodService {
    String uploadVideo(MultipartFile file);

    void removeVideo(String videoId);

    void removeVideoList(List videoList);
}
