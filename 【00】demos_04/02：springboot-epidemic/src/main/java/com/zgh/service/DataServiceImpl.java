package com.zgh.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgh.bean.DataBean;
import com.zgh.mapper.DataMapper;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl extends ServiceImpl<DataMapper, DataBean> implements DataService{

}
