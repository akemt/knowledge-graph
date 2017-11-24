package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgData;
import com.beyond.algm.vo.AlgDifDataListVo;

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

    //我的收藏-不同实现-数据列表
    List<AlgDifDataListVo> findDifDataList(Integer id);

    //数据商城
    List<AlgData> findAlgDataMall(String dataContent);
}