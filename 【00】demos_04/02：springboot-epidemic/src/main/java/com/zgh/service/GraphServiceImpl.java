package com.zgh.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgh.bean.GraphBean;
import com.zgh.mapper.GraphMapper;
import org.springframework.stereotype.Service;

@Service
public class GraphServiceImpl extends ServiceImpl<GraphMapper, GraphBean> implements GraphService{
}
