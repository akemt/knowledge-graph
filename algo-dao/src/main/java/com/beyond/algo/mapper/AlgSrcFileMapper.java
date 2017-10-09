package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgSrcFile;
import java.util.List;

public interface AlgSrcFileMapper {
    int deleteByPrimaryKey(String sflSn);

    int insert(AlgSrcFile record);

    AlgSrcFile selectByPrimaryKey(String sflSn);

    List<AlgSrcFile> selectAll();

    int updateByPrimaryKey(AlgSrcFile record);
}