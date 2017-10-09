package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgCashTrans;
import java.util.List;

public interface AlgCashTransMapper {
    int deleteByPrimaryKey(String traSn);

    int insert(AlgCashTrans record);

    AlgCashTrans selectByPrimaryKey(String traSn);

    List<AlgCashTrans> selectAll();

    int updateByPrimaryKey(AlgCashTrans record);
}