package com.zgh.onlinevideo.dao;

import com.zgh.onlinevideo.domain.ToolItem;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface ToolItemDao {

    int insertToolItem(ToolItem toolItem);

    List<ToolItem> findToolItemByCondition(HashMap<String, Object> map);

    List<ToolItem> findToolItemAll();

}
