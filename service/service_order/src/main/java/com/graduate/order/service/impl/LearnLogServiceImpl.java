package com.graduate.order.service.impl;

import com.graduate.order.feign.EduClient;
import com.graduate.order.feign.UcenterClient;
import com.graduate.order.pojo.LearnLog;
import com.graduate.order.mapper.LearnLogMapper;
import com.graduate.order.pojo.vo.FrontCourseWebVo;
import com.graduate.order.pojo.vo.Member;
import com.graduate.order.service.LearnLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张紫韩
 * @since 2022-03-30
 */
@Service
public class LearnLogServiceImpl extends ServiceImpl<LearnLogMapper, LearnLog> implements LearnLogService {



    @Resource
    private UcenterClient ucenterClient;

    @Override
    public String create(LearnLog learnLog, String memberId) {
        //远程调用用户服务，根据用户id获取用户信息
        Member ucenterMember = ucenterClient.getInfo(memberId);
        learnLog.setMemberId(memberId).setMemberName(ucenterMember.getNickname());
        this.save(learnLog);
        return learnLog.getId();
    }
}
