package com.zgh.onlinevideo.service.impl;

import com.zgh.onlinevideo.dao.BannerDao;
import com.zgh.onlinevideo.domain.Banner;
import com.zgh.onlinevideo.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {


    @Autowired
    BannerDao bannerDao;

    @Override
    public List<Banner> getIndexBanner() {
        return bannerDao.findBannerAll();
    }
}
