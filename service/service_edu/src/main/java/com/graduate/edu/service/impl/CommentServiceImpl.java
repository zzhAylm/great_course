package com.graduate.edu.service.impl;

import com.graduate.edu.pojo.Comment;
import com.graduate.edu.mapper.CommentMapper;
import com.graduate.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-21
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
