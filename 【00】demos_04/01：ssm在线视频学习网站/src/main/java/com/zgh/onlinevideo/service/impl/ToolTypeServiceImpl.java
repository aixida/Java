package com.zgh.onlinevideo.service.impl;

import com.zgh.onlinevideo.dao.ToolTypeDao;
import com.zgh.onlinevideo.domain.ToolType;
import com.zgh.onlinevideo.service.ToolTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolTypeServiceImpl implements ToolTypeService {

    @Autowired
    ToolTypeDao toolTypeDao;

    @Override
    public List<ToolType> getToolTypeAll() {
        return toolTypeDao.findToolTypeAll();
    }
}
