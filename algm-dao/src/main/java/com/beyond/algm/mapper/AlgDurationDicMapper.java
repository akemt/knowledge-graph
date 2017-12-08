package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgDurationDic;
import java.util.List;

public interface AlgDurationDicMapper {
    int deleteByPrimaryKey(String dicSn);

    int insert(AlgDurationDic record);

    AlgDurationDic selectByPrimaryKey(String dicSn);

    List<AlgDurationDic> selectAll();

    int updateByPrimaryKey(AlgDurationDic record);
}