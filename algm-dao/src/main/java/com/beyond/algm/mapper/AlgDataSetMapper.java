package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgDataSet;
import java.util.List;

public interface AlgDataSetMapper {
    int deleteByPrimaryKey(String dataSetSn);

    int insert(AlgDataSet record);

    AlgDataSet selectByPrimaryKey(String dataSetSn);

    List<AlgDataSet> selectAll(String usrSn);

    int updateByPrimaryKey(AlgDataSet record);

    int dataSetCount(String usrSn,String dataSetName);

    String getMaxDataOrderBy(String usrSn);

    List<AlgDataSet>   dataSetId(String usr_sn,String dataSetName );
}