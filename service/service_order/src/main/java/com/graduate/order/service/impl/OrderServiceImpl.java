package com.graduate.order.service.impl;

import com.graduate.order.feign.EduClient;
import com.graduate.order.feign.UcenterClient;
import com.graduate.order.pojo.Order;
import com.graduate.order.mapper.OrderMapper;
import com.graduate.order.pojo.vo.FrontCourseWebVo;
import com.graduate.order.pojo.vo.Member;
import com.graduate.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.order.utils.OrderNoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Resource
    private EduClient eduClient;

    @Resource
    private UcenterClient ucenterClient;
        //创建订单
    @Override
    public String saveOrder(String courseId, String memberId) {
        //远程调用课程服务，根据课程id获取课程信息
        FrontCourseWebVo courseDto = eduClient.getCourseInfoDto(courseId);

        //远程调用用户服务，根据用户id获取用户信息
        Member ucenterMember = ucenterClient.getInfo(memberId);
        //创建订单
        Order order = new Order();

        order.setOrderNo(OrderNoUtil.getOrderNo());

        order.setCourseId(courseId);

        order.setCourseTitle(courseDto.getTitle());

        order.setCourseCover(courseDto.getCover());

        order.setTeacherName(courseDto.getTeacherName());

        order.setTotalFee(courseDto.getPrice());

        order.setMemberId(memberId);

        order.setMobile(ucenterMember.getMobile());

        order.setNickname(ucenterMember.getNickname());

        order.setStatus(0);//订单状态

        order.setPayType(1);//支付方式

        baseMapper.insert(order);

        return order.getOrderNo();
    }

    @Override
    public List<Order> getDayNum(String day) {

       return baseMapper.selectDayNum(day);
    }
}
