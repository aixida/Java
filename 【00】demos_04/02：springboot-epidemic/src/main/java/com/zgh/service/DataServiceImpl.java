package com.zgh.service;

import com.zgh.handler.DataHandler;
import com.zgh.bean.DataBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {


    @Override
    public List<DataBean> list() {
        return DataHandler.getData();
    }

}
