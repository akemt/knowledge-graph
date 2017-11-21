package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgTag;
import java.util.List;

public interface AlgTagMapper {
    int deleteByPrimaryKey(String tagSn);

    int insert(AlgTag record);

    AlgTag selectByPrimaryKey(String tagSn);

    List<AlgTag> selectAll();

    int updateByPrimaryKey(AlgTag record);
}