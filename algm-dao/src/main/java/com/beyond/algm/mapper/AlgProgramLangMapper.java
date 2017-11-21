package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgProgramLang;
import java.util.List;

public interface AlgProgramLangMapper {
    int deleteByPrimaryKey(String lanSn);

    int insert(AlgProgramLang record);

    AlgProgramLang selectByPrimaryKey(String lanSn);

    List<AlgProgramLang> selectAll();

    int updateByPrimaryKey(AlgProgramLang record);

    AlgProgramLang selectLanSn(String lanName);
}