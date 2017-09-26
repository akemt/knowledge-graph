package com.beyond.algo.dao.mapper;

import com.beyond.algo.dao.model.Paymentinfo;
import java.util.List;

public interface PaymentinfoMapper {
    int deleteByPrimaryKey(String pyisn);

    int insert(Paymentinfo record);

    Paymentinfo selectByPrimaryKey(String pyisn);

    List<Paymentinfo> selectAll();

    int updateByPrimaryKey(Paymentinfo record);
}