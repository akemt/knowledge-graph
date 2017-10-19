package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgAuthorList;
import java.util.List;

public interface AlgAuthorListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlgAuthorList record);

    AlgAuthorList selectByPrimaryKey(Integer id);

    List<AlgAuthorList> selectAll();

    int updateByPrimaryKey(AlgAuthorList record);
}