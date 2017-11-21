package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgModel;
import com.beyond.algm.vo.ModelDataVo;

import java.util.List;

public interface AlgModelMapper {
    int deleteByPrimaryKey(String modelSn);

    int insert(AlgModel record);

    AlgModel selectByPrimaryKey(String modelSn);

    List<AlgModel> selectAll(String modelSetSn);

    int updateByPrimaryKey(AlgModel record);

    int deleteByModelSetSn (String modelSetSn);

    List<ModelDataVo> queryModelDataSet(ModelDataVo modelDataVo);

    List<ModelDataVo> queryModel (AlgModel algModel);

    int checkData (AlgModel algModel);
}