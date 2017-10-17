package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgDataSet;
import java.util.List;

public interface AlgDataSetMapper {
    int deleteByPrimaryKey(String dataSetSn);

    int insert(AlgDataSet record);

    AlgDataSet selectByPrimaryKey(String dataSetSn);

    List<AlgDataSet> selectAll();

    int updateByPrimaryKey(AlgDataSet record);
}