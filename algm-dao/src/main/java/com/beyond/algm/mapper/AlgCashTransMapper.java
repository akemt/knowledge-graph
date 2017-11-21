package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgCashTrans;
import java.util.List;

public interface AlgCashTransMapper {
    int deleteByPrimaryKey(String traSn);

    int insert(AlgCashTrans record);

    AlgCashTrans selectByPrimaryKey(String traSn);

    List<AlgCashTrans> selectAll();

    int updateByPrimaryKey(AlgCashTrans record);

    List<AlgCashTrans>  payRecord(String usrSn );
}