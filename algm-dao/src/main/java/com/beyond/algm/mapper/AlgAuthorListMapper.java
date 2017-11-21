package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgAuthorList;
import java.util.List;

public interface AlgAuthorListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlgAuthorList record);

    AlgAuthorList selectByPrimaryKey(Integer id);

    List<AlgAuthorList> selectAll();

    int updateByPrimaryKey(AlgAuthorList record);
}