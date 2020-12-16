package com.zgh.onlinevideo.service;

import com.github.pagehelper.PageInfo;
import com.zgh.onlinevideo.domain.ToolItem;

public interface ToolItemService {

    PageInfo<ToolItem> getToolItem(int typeId);

    PageInfo<ToolItem> getToolItemAll();
}
