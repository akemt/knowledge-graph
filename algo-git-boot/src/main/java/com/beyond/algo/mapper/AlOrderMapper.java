package com.beyond.algo.mapper;

import com.beyond.algo.model.AlOrder;
import java.util.List;

public interface AlOrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(AlOrder record);

    AlOrder selectByPrimaryKey(String orderId);

    List<AlOrder> selectAll();

    int updateByPrimaryKey(AlOrder record);
}