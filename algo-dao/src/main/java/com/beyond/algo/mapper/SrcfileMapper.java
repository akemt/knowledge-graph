package com.beyond.algo.mapper;

import com.beyond.algo.model.Srcfile;
import java.util.List;

public interface SrcfileMapper {
    int deleteByPrimaryKey(String sflsn);

    int insert(Srcfile record);

    Srcfile selectByPrimaryKey(String sflsn);

    List<Srcfile> selectAll();

    int updateByPrimaryKey(Srcfile record);
}