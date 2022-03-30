package com.graduate.order.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2022/3/28 10:06
 */
@Data
public class OrderPage implements Serializable {

    private Integer current=1;
    private Integer size=10;
    @ApiModelProperty(value = "课程名称")
    private String courseTitle;

    @ApiModelProperty(value = "讲师名称")
    private String teacherName;

    private String memberName;
}
