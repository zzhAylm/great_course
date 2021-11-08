package com.graduate.edu.service.impl;

import com.graduate.edu.pojo.Video;
import com.graduate.edu.mapper.VideoMapper;
import com.graduate.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-03
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
