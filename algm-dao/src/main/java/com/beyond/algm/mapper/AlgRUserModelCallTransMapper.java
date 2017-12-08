package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgRUserModelCallTrans;
import java.util.List;

public interface AlgRUserModelCallTransMapper {
    int deleteByPrimaryKey(String umcSn);

    int insert(AlgRUserModelCallTrans record);

    AlgRUserModelCallTrans selectByPrimaryKey(String umcSn);

    List<AlgRUserModelCallTrans> selectAll();

    int updateByPrimaryKey(AlgRUserModelCallTrans record);
}