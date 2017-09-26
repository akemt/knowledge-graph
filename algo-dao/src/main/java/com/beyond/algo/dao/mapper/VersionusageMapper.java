package com.beyond.algo.dao.mapper;

import com.beyond.algo.dao.model.Versionusage;
import java.util.List;

public interface VersionusageMapper {
    int deleteByPrimaryKey(String usgsn);

    int insert(Versionusage record);

    Versionusage selectByPrimaryKey(String usgsn);

    List<Versionusage> selectAll();

    int updateByPrimaryKey(Versionusage record);
}