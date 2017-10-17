package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgModel;
import java.util.List;

public interface AlgModelMapper {
    int deleteByPrimaryKey(String modelSn);

    int insert(AlgModel record);

    AlgModel selectByPrimaryKey(String modelSn);

    List<AlgModel> selectAll();

    int updateByPrimaryKey(AlgModel record);
}