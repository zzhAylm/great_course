package com.graduate.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.cms.pojo.Banner;
import com.graduate.cms.service.BannerService;
import com.graduate.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-14
 */
@RestController
@RequestMapping("/cms/banner")
//@CrossOrigin
public class BannerFrontController {

    @Resource
    private BannerService bannerService;


    @ApiOperation(value = "获取首页banner")
    @GetMapping("/getAll")
    public Result index() {
        List<Banner> list = bannerService.getAllBanner();
        return Result.success().data("bannerList", list);
    }



}

