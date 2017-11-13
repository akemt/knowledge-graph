package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgModelSet;
import com.beyond.algo.vo.AlgModelSetVo;

import java.util.List;

public interface AlgModelSetMapper {
    int deleteByPrimaryKey(String modelSetSn);

    int insert(AlgModelSet record);

    AlgModelSet selectByPrimaryKey(String modelSetSn);

    List<AlgModelSet> selectAll(String usrSn);

    int updateByPrimaryKey(AlgModelSet record);

    List<AlgModelSetVo> queryModelSet(String usrSn);
}