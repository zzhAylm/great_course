package com.graduate.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.edu.pojo.Teacher;

import java.io.Serializable;
import java.util.HashMap;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author 张紫韩
 * @since 2021-10-22
 */
public interface TeacherService extends IService<Teacher> {
    boolean removeById(Serializable id);

    HashMap<String,Object> pageList(Long current,Long limit);

}
