package com.graduate.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduate.base.exception.MyException;
import com.graduate.edu.mapper.VideoMapper;
import com.graduate.edu.pojo.Chapter;
import com.graduate.edu.mapper.ChapterMapper;
import com.graduate.edu.pojo.Video;
import com.graduate.edu.pojo.vo.ChapterVo;
import com.graduate.edu.pojo.vo.VideoVo;
import com.graduate.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-03
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    @Resource
    private VideoMapper videoMapper;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        ArrayList<ChapterVo> chapterVoList = new ArrayList<>();
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        List<Chapter> chapterList = chapterMapper.selectList(wrapper);

        for (Chapter chapter:chapterList) {

            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);

            System.out.println(chapterVo);

            QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("course_id", courseId);
            queryWrapper.eq("chapter_id",chapter.getId());
            List<Video> videoList = videoMapper.selectList(queryWrapper);

            List<VideoVo> VideoVoList = new ArrayList<>();
            for (Video video:videoList){
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(video, videoVo);
                VideoVoList.add(videoVo);
            }
            chapterVo.setChildren(VideoVoList);
            chapterVoList.add(chapterVo);
        }

        return chapterVoList;
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
       baseMapper.delete(wrapper);
    }

    @Override
    public Boolean deleteChapter(String chapterId) {

//        如果章节中有小结，就不能进行删除，需要删除小结后才能删除章节
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);

        Integer integer = videoMapper.selectCount(wrapper);
        if (integer>0){
            throw new MyException(500,"删除章节失败,章节并非未空");
        }else {
            return baseMapper.deleteById(chapterId) > 0;
        }
    }
}
