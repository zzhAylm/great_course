package com.graduate.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduate.base.exception.MyException;
import com.graduate.edu.feign.VodClient;
import com.graduate.edu.pojo.Video;
import com.graduate.edu.mapper.VideoMapper;
import com.graduate.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private VodClient vodClient;

//    TODO，删除小节的时候还需要删除视频
    @Override
    public void removeVideoByCourseId(String courseId) {

//        根据课程id查到所有的视频id
        QueryWrapper<Video> wrapper1 = new QueryWrapper<>();

        wrapper1.eq("course_id", courseId);
        wrapper1.select("video_source_id");
        List<Video> videoList = baseMapper.selectList(wrapper1);

        ArrayList<String> idList = new ArrayList<>();
        for (Video video:videoList){
            idList.add(video.getVideoSourceId());
        }
        if (!idList.isEmpty()) {
            Result result = vodClient.removeVideo(idList);
            if (result.getCode()==500){
                throw new MyException(500,"删除视频失败,服务熔断....");
            }
        }
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
