package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgAuthCode;
import java.util.List;

public interface AlgAuthCodeMapper {
    int deleteByPrimaryKey(String acdSn);

    int insert(AlgAuthCode record);

    AlgAuthCode selectByPrimaryKey(String acdSn);

    List<AlgAuthCode> selectAll();

    List<AlgAuthCode> selectByUsrSnKey(String usrSn);

    int updateByPrimaryKeySelective(AlgAuthCode record);
}