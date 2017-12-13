package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgCashTrans;
import java.util.List;
import com.github.pagehelper.Page;

public interface AlgCashTransMapper {
    int deleteByPrimaryKey(String traSn);

    int insert(AlgCashTrans record);

    AlgCashTrans selectByPrimaryKey(String traSn);

    List<AlgCashTrans> selectAll();

    int updateByPrimaryKey(AlgCashTrans record);

    Page<AlgCashTrans>  payRecord(String usrSn );
}