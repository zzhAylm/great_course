package com.graduate.edu.enums;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/9 19:58
 */
public enum CourseStatusEnum {
    PUBLISHED("已发布"),UNPUBLISHED("未发布");
    private String status;

    public String getStatus() {
        return status;
    }

    CourseStatusEnum(String status) {
        this.status = status;
    }
}
