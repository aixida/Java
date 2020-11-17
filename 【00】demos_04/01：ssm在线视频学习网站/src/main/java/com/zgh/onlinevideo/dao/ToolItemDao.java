package com.zgh.onlinevideo.dao;

import com.zgh.onlinevideo.domain.ToolItem;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolItemDao {

    int insertToolItem(ToolItem toolItem);

}
