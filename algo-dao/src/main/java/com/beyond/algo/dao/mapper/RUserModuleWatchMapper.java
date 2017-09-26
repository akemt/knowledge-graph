package com.beyond.algo.dao.mapper;

import com.beyond.algo.dao.model.RUserModuleWatch;
import java.util.List;

public interface RUserModuleWatchMapper {
    int deleteByPrimaryKey(String wtcsn);

    int insert(RUserModuleWatch record);

    RUserModuleWatch selectByPrimaryKey(String wtcsn);

    List<RUserModuleWatch> selectAll();

    int updateByPrimaryKey(RUserModuleWatch record);
}