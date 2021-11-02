package com.graduate.edu.service;

import com.graduate.edu.pojo.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.edu.pojo.vo.SubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-01
 */
public interface SubjectService extends IService<Subject> {


    void saveSubject(MultipartFile file,SubjectService subjectService);

    List<SubjectVo> getAllSubjectVo();
}
