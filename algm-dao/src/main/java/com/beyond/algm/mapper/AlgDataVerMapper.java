package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgDataVer;
import java.util.List;

public interface AlgDataVerMapper {
    int deleteByPrimaryKey(String dataVerSn);

    int insert(AlgDataVer record);

    AlgDataVer selectByPrimaryKey(String dataVerSn);

    List<AlgDataVer> selectAll();

    int updateByPrimaryKey(AlgDataVer record);
}