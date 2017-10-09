package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgAuthCode;
import java.util.List;

public interface AlgAuthCodeMapper {
    int deleteByPrimaryKey(String acdSn);

    int insert(AlgAuthCode record);

    AlgAuthCode selectByPrimaryKey(String acdSn);

    List<AlgAuthCode> selectAll();

    int updateByPrimaryKey(AlgAuthCode record);
}