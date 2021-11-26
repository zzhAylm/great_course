package com.graduate.statistics.mapper;

import com.graduate.statistics.pojo.Daily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 * 网站统计日数据 Mapper 接口
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-23
 */
@Mapper
public interface DailyMapper extends BaseMapper<Daily> {

}
