package com.graduate.order.service;

import com.graduate.order.pojo.LearnLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张紫韩
 * @since 2022-03-30
 */
public interface LearnLogService extends IService<LearnLog> {

    String create(LearnLog learnLog, String memberId);
}
