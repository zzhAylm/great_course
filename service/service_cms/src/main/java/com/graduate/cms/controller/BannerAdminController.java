package com.graduate.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.cms.pojo.Banner;
import com.graduate.cms.service.BannerService;
import com.graduate.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-14
 */
@RestController
@RequestMapping("/crm/banner")
public class BannerAdminController {

    @Resource
    private BannerService bannerService;
    @GetMapping("/page/{current}/{limit}")
    public Result pageBanner(@PathVariable Long current,@PathVariable Long limit){

        Page<Banner> bannerPage=new Page<>(current,limit);

        IPage<Banner> bannerIPage = bannerService.page(bannerPage, null);

        return Result.success().data("bannerList",bannerIPage.getRecords()).data("total", bannerIPage.getTotal());
    }

    @PostMapping("/add")
    public Result addBanner(@RequestBody Banner banner){
        bannerService.save(banner);
        return Result.success();

    }


    @ApiOperation(value = "获取Banner")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        Banner banner = bannerService.getById(id);
        return Result.success().data("banner", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("/update")
    public Result updateById(@RequestBody Banner banner) {
        bannerService.updateById(banner);
        return Result.success();
    }
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("delete/{id}")
    public Result remove(@PathVariable String id) {
        bannerService.removeById(id);
        return Result.success();
    }

}

