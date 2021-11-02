package com.graduate.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/10/30 18:07
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
