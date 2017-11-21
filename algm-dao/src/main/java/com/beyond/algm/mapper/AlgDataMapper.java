package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgData;
import java.util.List;

public interface AlgDataMapper {
    int deleteByPrimaryKey(String dataSn);

    int insert(AlgData record);

    AlgData selectByPrimaryKey(String dataSn);

    List<AlgData> queryAlgDatabySet(String dataSetSn);

    int updateByPrimaryKey(AlgData record);

    int checkDataEnName(String usrSn,String dataEnName);

    int dataCount(String dataSetSn);

    List<AlgData> findDataList(String usrSn);
}