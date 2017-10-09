package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgAlgoCategory;
import java.util.List;

public interface AlgAlgoCategoryMapper {
    int deleteByPrimaryKey(String catSn);

    int insert(AlgAlgoCategory record);

    AlgAlgoCategory selectByPrimaryKey(String catSn);

    List<AlgAlgoCategory> selectAll();

    int updateByPrimaryKey(AlgAlgoCategory record);
}