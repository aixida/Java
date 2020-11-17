package com.zgh.onlinevideo.dao;

import com.zgh.onlinevideo.domain.ToolType;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolTypeDao {

    int insertToolType(ToolType toolType);

}
