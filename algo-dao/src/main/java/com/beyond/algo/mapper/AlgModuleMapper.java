package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgModule;
import java.util.List;

public interface AlgModuleMapper {
    int deleteByPrimaryKey(String modSn);

    int insert(AlgModule record);

    AlgModule selectByPrimaryKey(String modSn);

    List<AlgModule> selectAll();

    int updateByPrimaryKey(AlgModule record);

    List<AlgModule> getRankList(List rankList);
}