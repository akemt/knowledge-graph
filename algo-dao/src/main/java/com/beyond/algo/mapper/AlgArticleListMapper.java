package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgArticleList;
import java.util.List;

public interface AlgArticleListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlgArticleList record);

    AlgArticleList selectByPrimaryKey(Integer id);

    List<AlgArticleList> selectAll();

    int updateByPrimaryKey(AlgArticleList record);
}