package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgModel;
import com.beyond.algm.model.AlgModelSet;
import com.beyond.algm.vo.AlgModelSetVo;
import com.github.pagehelper.Page;

import java.util.List;

public interface AlgModelSetMapper {
    int deleteByPrimaryKey(AlgModel algModel);

    int insert(AlgModelSet record);

    AlgModelSet selectByPrimaryKey(String modelSetSn);

    List<AlgModelSet> selectAll(String usrSn);

    int updateByPrimaryKey(AlgModelSet record);

    Page<AlgModelSetVo> queryModelSet(String usrSn);

    int checkData(AlgModelSet algModelSet);

    String checkMaxOrderby(String usrSn);
}