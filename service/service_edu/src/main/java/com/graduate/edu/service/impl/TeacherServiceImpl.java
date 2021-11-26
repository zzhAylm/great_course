package com.graduate.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.edu.mapper.TeacherMapper;
import com.graduate.edu.pojo.Teacher;
import com.graduate.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author 张紫韩
 * @since 2021-10-22
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Override
    public boolean removeById(Serializable id) {
        int result = baseMapper.deleteById(id);
        return result > 0;
    }

    @Override
    public HashMap<String, Object> pageList(Long current, Long limit) {

        Page<Teacher> teacherPage = new Page<>(current,limit);
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        baseMapper.selectPage(teacherPage, wrapper);
        HashMap<String, Object> map = new HashMap<>();

        map.put("items", teacherPage.getRecords());
        map.put("current", teacherPage.getCurrent());
        map.put("pages", teacherPage.getPages());
        map.put("size", teacherPage.getSize());
        map.put("total", teacherPage.getTotal());
        map.put("hasNext", teacherPage.hasNext());
        map.put("hasPrevious", teacherPage.hasPrevious());
        return map;
    }
}
