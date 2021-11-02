package com.graduate.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduate.edu.pojo.Subject;
import com.graduate.edu.pojo.excel.SubjectExcel;
import com.graduate.edu.service.SubjectService;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/1 10:23
 */
public class SubjectListener extends AnalysisEventListener<SubjectExcel> {

//    因为我们的监听器没有放在容器中所以，无法使用注入的方式使用容器中的对象
    private SubjectService subjectService;

    public SubjectListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public SubjectListener() {
    }

//    一行一行的读取excel中的文件内容
    @Override
    public void invoke(SubjectExcel subjectExcel, AnalysisContext analysisContext) {
//        添加一级分类
        if (!existSubject("0",subjectExcel.getOneSubjectName())){

            Subject oneSubject = new Subject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectExcel.getOneSubjectName());
            subjectService.save(oneSubject);
        }
//        添加二级标题
        String parentId=getParentId(subjectExcel.getOneSubjectName());
        if (!existSubject(parentId,subjectExcel.getTwoSubjectName())){
            Subject subject = new Subject();
            subject.setTitle(subjectExcel.getTwoSubjectName());
            subject.setParentId(parentId);
            subjectService.save(subject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


//    判断subject是否存在
    public Boolean existSubject(String parentId,String title){

        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        wrapper.eq("title", title);
        return subjectService.getOne(wrapper)!=null;
    }

//    的到parent_id
    public String getParentId(String title){

        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",title);
        wrapper.eq("parent_id", "0");
      return subjectService.getOne(wrapper).getId();

    }

}
