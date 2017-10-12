package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgRUserModuleCallTrans;
import com.beyond.algo.vo.AlgRUserModuleCallTransVo;

import java.util.List;

public interface AlgRUserModuleCallTransMapper {
    int deleteByPrimaryKey(String umcSn);

    int insert(AlgRUserModuleCallTrans record);

    AlgRUserModuleCallTrans selectByPrimaryKey(String umcSn);

    List<AlgRUserModuleCallTrans> selectAll();

    int updateByPrimaryKey(AlgRUserModuleCallTrans record);

    List<AlgRUserModuleCallTransVo> selectAlgorithmRecord(String callUsrSn);
}