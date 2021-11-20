package com.graduate.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.cms.pojo.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-14
 */
public interface BannerService extends IService<Banner> {

    List<Banner> getAllBanner();
    public void saveBanner(Banner banner);
    public void updateBannerById(Banner banner);
    public void removeBannerById(String id);
    public Banner getBannerById(String id);
    public void pageBanner(Page<Banner> pageParam, Object o);
}
