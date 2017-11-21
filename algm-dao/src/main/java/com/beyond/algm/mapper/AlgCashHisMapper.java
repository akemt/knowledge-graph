package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgCashHis;
import java.util.List;

public interface AlgCashHisMapper {
    int deleteByPrimaryKey(String traSn);

    int insert(AlgCashHis record);

    AlgCashHis selectByPrimaryKey(String traSn);

    List<AlgCashHis> selectAll();

    int updateByPrimaryKey(AlgCashHis record);

    String selectTotalCash(AlgCashHis  record);
}