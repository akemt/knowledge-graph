package com.beyond.algo.dao.mapper;

import com.beyond.algo.dao.model.RVersionSrcfile;
import java.util.List;

public interface RVersionSrcfileMapper {
    int deleteByPrimaryKey(String sn);

    int insert(RVersionSrcfile record);

    RVersionSrcfile selectByPrimaryKey(String sn);

    List<RVersionSrcfile> selectAll();

    int updateByPrimaryKey(RVersionSrcfile record);
}