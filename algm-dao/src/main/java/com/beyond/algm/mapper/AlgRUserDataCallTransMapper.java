package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgRUserDataCallTrans;
import java.util.List;

public interface AlgRUserDataCallTransMapper {
    int deleteByPrimaryKey(String umcSn);

    int insert(AlgRUserDataCallTrans record);

    AlgRUserDataCallTrans selectByPrimaryKey(String umcSn);

    List<AlgRUserDataCallTrans> selectAll();

    int updateByPrimaryKey(AlgRUserDataCallTrans record);

    AlgRUserDataCallTrans  selectPayStatus(AlgRUserDataCallTrans record);
}