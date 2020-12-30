package com.zgh.service;

import com.zgh.bean.DataBean;
import com.zgh.handler.JsoupHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {


    @Override
    public List<DataBean> list() {
        return JsoupHandler.getData();
    }

}
