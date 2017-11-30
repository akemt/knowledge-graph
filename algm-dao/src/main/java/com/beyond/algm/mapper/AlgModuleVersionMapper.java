package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgModuleVersion;
import java.util.List;

public interface AlgModuleVersionMapper {
    int deleteByPrimaryKey(String verSn);

    int insert(AlgModuleVersion record);

    AlgModuleVersion selectByPrimaryKey(String verSn);

    List<AlgModuleVersion> selectAll();

    int updateByPrimaryKey(AlgModuleVersion record);

    AlgModuleVersion queryLatestVersion(String mod_sn);

    int updateLatestCommit( AlgModuleVersion algModuleVersion);
}