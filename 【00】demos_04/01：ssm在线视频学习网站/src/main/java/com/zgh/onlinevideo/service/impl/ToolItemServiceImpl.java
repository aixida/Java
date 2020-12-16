package com.zgh.onlinevideo.service.impl;

import com.github.pagehelper.PageInfo;
import com.zgh.onlinevideo.dao.ToolItemDao;
import com.zgh.onlinevideo.domain.ToolItem;
import com.zgh.onlinevideo.service.ToolItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ToolItemServiceImpl implements ToolItemService {

    @Autowired
    ToolItemDao toolItemDao;

    @Override
    public PageInfo<ToolItem> getToolItem(int toolTypeId) {

        HashMap<String, Object> map = new HashMap<>();
        // 类型ID
        map.put("toolTypeId", toolTypeId);

        List<ToolItem> list = toolItemDao.findToolItemByCondition(map);

        PageInfo<ToolItem> pageInfo = new PageInfo<>(list, 4);

        return pageInfo;
    }

    @Override
    public PageInfo<ToolItem> getToolItemAll() {
        HashMap<String, Object> map = new HashMap<>();

        List<ToolItem> list = toolItemDao.findToolItemAll();

        PageInfo<ToolItem> pageInfo = new PageInfo<>(list, 4);

        return pageInfo;
    }
}
