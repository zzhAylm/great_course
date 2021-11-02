package com.graduate.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduate.edu.listener.SubjectListener;
import com.graduate.edu.pojo.Subject;
import com.graduate.edu.mapper.SubjectMapper;
import com.graduate.edu.pojo.excel.SubjectExcel;
import com.graduate.edu.pojo.vo.SubjectVo;
import com.graduate.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-01
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Resource
    private SubjectMapper subjectMapper;

    @Override
    public void saveSubject(MultipartFile file, SubjectService subjectService) {

        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectExcel.class, new SubjectListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SubjectVo> getAllSubjectVo() {

//        得到一级标题的所有Vo，和二级标题并封装到Vo中返回
       return getChildrenVo("0");

    }
    //   找到所有的课程分类及其子类，并封装成Vo并返回其子类
    public List<SubjectVo> getChildrenVo(String parentId) {
        List<Subject> subjectList = getChildren(parentId);
        ArrayList<SubjectVo> subjectVoList = new ArrayList<>();
        for (Subject subject : subjectList) {
            SubjectVo subjectVo = new SubjectVo();
            BeanUtils.copyProperties(subject, subjectVo);
//            查找封装所有的子类
            addChildrenVo(subjectVo);
            subjectVoList.add(subjectVo);
        }
        return subjectVoList;
    }

//    根据父类对象Vo找到所有的子类对象，并将子类对象封装到其属性中
    public void addChildrenVo(SubjectVo subjectParentVo) {
        List<Subject> subjectList = getChildren(subjectParentVo.getId());
        ArrayList<SubjectVo> childrenVoList = new ArrayList<>();
        for (Subject subject : subjectList) {
            SubjectVo subjectVo = new SubjectVo();
            BeanUtils.copyProperties(subject, subjectVo);
            childrenVoList.add(subjectVo);
        }
        subjectParentVo.setChildren(childrenVoList);
    }

//    查询所有的子类,
    public List<Subject> getChildren(String parentId){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        return subjectMapper.selectList(wrapper);
    }





////    根据parentId得到所有的子类subject和其子类的子类，并封装为Vo
//    public List<SubjectVo> getChildrenVo(String parentId){
//        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
//        wrapper.eq("parent_id", parentId);
//        List<Subject> subjectList = subjectMapper.selectList(wrapper);
//        ArrayList<SubjectVo> subjectVoList = new ArrayList<>();
//        for (Subject subject:subjectList){
//            SubjectVo subjectVo = new SubjectVo();
//            BeanUtils.copyProperties(subject, subjectVo);
//            addChildren(subjectVo);
//            subjectVoList.add(subjectVo);
//        }
//        return subjectVoList;
//    }

}
