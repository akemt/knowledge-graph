package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgDic;
import java.util.List;

public interface AlgDicMapper {
    int deleteByPrimaryKey(String dicSn);

    int insert(AlgDic record);

    AlgDic selectByPrimaryKey(String dicSn);

    List<AlgDic> selectAll();

    List<AlgDic> getDictionarylist(String dicCode);

    int updateByPrimaryKey(AlgDic record);

    AlgDic selectKeyAll(String dicValue);

    String selectDicValue(String dicCode,String dicKey);
}