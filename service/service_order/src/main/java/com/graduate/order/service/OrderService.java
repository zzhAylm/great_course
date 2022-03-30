package com.graduate.order.service;

import com.graduate.order.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-21
 */
public interface OrderService extends IService<Order> {

    String saveOrder(String courseId, String memberIdByJwtToken);

    List<Order> getDayNum(String day);
}
