package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgModuleVersion;
import java.util.List;

public interface AlgModuleVersionMapper {
    int deleteByPrimaryKey(String verSn);

    int insert(AlgModuleVersion record);

    AlgModuleVersion selectByPrimaryKey(String verSn);

    List<AlgModuleVersion> selectAll();

    int updateByPrimaryKey(AlgModuleVersion record);
}