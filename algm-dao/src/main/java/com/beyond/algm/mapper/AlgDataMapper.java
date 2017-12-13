package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgData;
import com.beyond.algm.vo.AlgDataDownLoadVo;
import com.beyond.algm.vo.AlgDataVo;
import com.beyond.algm.vo.AlgDifDataListVo;
import com.github.pagehelper.Page;

import java.util.List;

public interface AlgDataMapper {
    int deleteByPrimaryKey(String dataSn);

    int insert(AlgData record);

    AlgData selectByPrimaryKey(String dataSn);

    List<AlgData> queryAlgDatabySet(String dataSetSn);

    int updateByPrimaryKey(AlgData record);

    int checkDataEnName(String usrSn,String dataEnName);

    int dataCount(String dataSetSn);

    Page<AlgData> findDataList(String usrSn);

    //我的收藏-不同实现-数据列表
    List<AlgDifDataListVo> findDifDataList(Integer id);

    //数据商城
    Page<AlgDataDownLoadVo> findAlgDataMall(String dataContent);

    int checkFileName(AlgData data);

    int update(AlgData record);
    String  dataUrl(AlgData record);
    String dataSn(AlgDataVo algDataVo);
}