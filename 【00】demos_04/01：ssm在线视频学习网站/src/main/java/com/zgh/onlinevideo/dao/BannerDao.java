package com.zgh.onlinevideo.dao;

import com.zgh.onlinevideo.domain.Banner;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerDao {

    int insertBanner(Banner banner);

}
