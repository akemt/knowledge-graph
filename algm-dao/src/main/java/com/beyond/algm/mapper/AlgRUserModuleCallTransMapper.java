package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgRUserModuleCallTrans;
import com.beyond.algm.vo.AlgRUserModuleCallTransVo;
import com.github.pagehelper.Page;
import java.util.List;

public interface AlgRUserModuleCallTransMapper {
    int deleteByPrimaryKey(String umcSn);

    int insert(AlgRUserModuleCallTrans record);

    AlgRUserModuleCallTrans selectByPrimaryKey(String umcSn);

    List<AlgRUserModuleCallTrans> selectAll();

    int updateByPrimaryKey(AlgRUserModuleCallTrans record);

    Page<AlgRUserModuleCallTransVo> selectAlgorithmRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo);

    int insertList(List<AlgRUserModuleCallTrans> recordList);
}