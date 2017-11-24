package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgModuleUsage;
import java.util.List;

public interface AlgModuleUsageMapper {
    int deleteByPrimaryKey(String usgSn);

    int insert(AlgModuleUsage record);

    AlgModuleUsage selectByPrimaryKey(String usgSn);

    List<AlgModuleUsage> selectAll();

    int updateByPrimaryKey(AlgModuleUsage record);
}