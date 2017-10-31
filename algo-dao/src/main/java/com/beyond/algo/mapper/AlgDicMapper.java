package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgDic;
import java.util.List;

public interface AlgDicMapper {
    int deleteByPrimaryKey(String dicSn);

    int insert(AlgDic record);

    AlgDic selectByPrimaryKey(String dicSn);

    List<AlgDic> selectAll();
    List<AlgDic> getDictionarylist(String code);
    int updateByPrimaryKey(AlgDic record);
}