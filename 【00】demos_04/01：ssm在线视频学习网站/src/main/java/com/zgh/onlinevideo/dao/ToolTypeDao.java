package com.zgh.onlinevideo.dao;

import com.zgh.onlinevideo.domain.ToolType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolTypeDao {

    int insertToolType(ToolType toolType);

    List<ToolType> findToolTypeAll();

}
