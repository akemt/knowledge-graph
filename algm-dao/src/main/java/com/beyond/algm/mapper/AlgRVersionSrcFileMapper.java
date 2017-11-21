package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgRVersionSrcFile;
import java.util.List;

public interface AlgRVersionSrcFileMapper {
    int deleteByPrimaryKey(String sn);

    int insert(AlgRVersionSrcFile record);

    AlgRVersionSrcFile selectByPrimaryKey(String sn);

    List<AlgRVersionSrcFile> selectAll();

    int updateByPrimaryKey(AlgRVersionSrcFile record);
}