package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgPaymentInfo;
import java.util.List;

public interface AlgPaymentInfoMapper {
    int deleteByPrimaryKey(String paySn);

    int insert(AlgPaymentInfo record);

    AlgPaymentInfo selectByPrimaryKey(String paySn);

    List<AlgPaymentInfo> selectAll();

    int updateByPrimaryKey(AlgPaymentInfo record);
}