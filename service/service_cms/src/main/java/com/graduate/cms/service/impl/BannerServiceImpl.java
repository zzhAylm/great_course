package com.graduate.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.cms.pojo.Banner;
import com.graduate.cms.mapper.BannerMapper;
import com.graduate.cms.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-14
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Cacheable(key = "'bannerList'",value = "banner")
    @Override
    public List<Banner> getAllBanner() {

        QueryWrapper<Banner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
//        wrapper.last("limit 2");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public void pageBanner(Page<Banner> pageParam, Object o) {
        baseMapper.selectPage(pageParam,null);
    }
    @Override
    public Banner getBannerById(String id) {
        return baseMapper.selectById(id);
    }

    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void saveBanner(Banner banner) {
        baseMapper.insert(banner);
    }
    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void updateBannerById(Banner banner) {
        baseMapper.updateById(banner);
    }
    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void removeBannerById(String id) {
        baseMapper.deleteById(id);
    }


}
