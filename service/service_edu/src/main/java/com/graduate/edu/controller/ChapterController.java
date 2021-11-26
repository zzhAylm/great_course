package com.graduate.edu.controller;


import com.graduate.edu.pojo.Chapter;
import com.graduate.edu.pojo.frontvo.FrontCourseWebVo;
import com.graduate.edu.pojo.vo.ChapterVo;
import com.graduate.edu.service.ChapterService;
import com.graduate.utils.JwtUtils;
import com.graduate.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-03
 */
@Api(description = "小节管理控制类")
@RestController
@RequestMapping("/edu/chapter")
@CrossOrigin
public class ChapterController {

    @Resource
    private ChapterService chapterService;



//    得到某个课程的章节和小结
    @GetMapping("/getChapterVideo/{courseId}")
    public Result getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> chapterVoList=chapterService.getChapterVideoByCourseId(courseId);
        System.out.println(chapterVoList.isEmpty());
        return Result.success().data("list", chapterVoList);

    }
//    添加章节
    @PostMapping("/add")
    public Result addChapter(@RequestBody Chapter chapter){
        chapterService.save(chapter);
        return Result.success();

    }
//    根据章节id查询
    @GetMapping("/get/{chapterId}")
    public Result getChapter(@PathVariable String chapterId){
        Chapter chapter = chapterService.getById(chapterId);
        return Result.success().data("chapter",chapter);
    }

//    修改章节
    @PostMapping("/update")
    public Result updateChapter(@RequestBody Chapter chapter){
       chapterService.updateById(chapter);
        return Result.success();
    }

    @DeleteMapping("/delete/{chapterId}")
    public Result deleteChapter(@PathVariable String chapterId){

        if (chapterService.deleteChapter(chapterId)){
            return Result.success();
        }
        return Result.error().message("章节为非空，删除失败");

    }





}

