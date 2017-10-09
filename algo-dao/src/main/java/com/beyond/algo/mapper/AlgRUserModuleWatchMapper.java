package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgRUserModuleWatch;
import java.util.List;

public interface AlgRUserModuleWatchMapper {
    int deleteByPrimaryKey(String wtcSn);

    int insert(AlgRUserModuleWatch record);

    AlgRUserModuleWatch selectByPrimaryKey(String wtcSn);

    List<AlgRUserModuleWatch> selectAll();

    int updateByPrimaryKey(AlgRUserModuleWatch record);
}