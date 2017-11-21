package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgRUserModuleCallTrans;
import com.beyond.algm.vo.AlgRUserModuleCallTransVo;

import java.util.List;

public interface AlgRUserModuleCallTransMapper {
    int deleteByPrimaryKey(String umcSn);

    int insert(AlgRUserModuleCallTrans record);

    AlgRUserModuleCallTrans selectByPrimaryKey(String umcSn);

    List<AlgRUserModuleCallTrans> selectAll();

    int updateByPrimaryKey(AlgRUserModuleCallTrans record);

    List<AlgRUserModuleCallTransVo> selectAlgorithmRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo);
}