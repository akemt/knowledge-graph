package com.beyond.algo.mapper;

import com.beyond.algo.model.RUserModuleWatch;
import java.util.List;

public interface RUserModuleWatchMapper {
    int deleteByPrimaryKey(String wtcsn);

    int insert(RUserModuleWatch record);

    RUserModuleWatch selectByPrimaryKey(String wtcsn);

    List<RUserModuleWatch> selectAll();

    int updateByPrimaryKey(RUserModuleWatch record);
}