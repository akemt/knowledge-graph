package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgModelSet;
import java.util.List;

public interface AlgModelSetMapper {
    int deleteByPrimaryKey(String modelSetSn);

    int insert(AlgModelSet record);

    AlgModelSet selectByPrimaryKey(String modelSetSn);

    List<AlgModelSet> selectAll();

    int updateByPrimaryKey(AlgModelSet record);
}