package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgAuthCode;
import java.util.List;
import com.github.pagehelper.Page;

public interface AlgAuthCodeMapper {
    int deleteByPrimaryKey(String acdSn);

    int insert(AlgAuthCode record);

    AlgAuthCode selectByPrimaryKey(String acdSn);

    List<AlgAuthCode> selectAll();

    Page<AlgAuthCode> selectByUsrSnKey(String usrSn);

    int updateByPrimaryKeySelective(AlgAuthCode record);
}