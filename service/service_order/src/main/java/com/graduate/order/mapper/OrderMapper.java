package com.graduate.order.mapper;

import com.graduate.order.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-21
 */
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> selectDayNum(String day);
}
