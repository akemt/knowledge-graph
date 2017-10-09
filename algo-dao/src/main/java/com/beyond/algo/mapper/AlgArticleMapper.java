package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgArticle;
import java.util.List;

public interface AlgArticleMapper {
    int deleteByPrimaryKey(String atlSn);

    int insert(AlgArticle record);

    AlgArticle selectByPrimaryKey(String atlSn);

    List<AlgArticle> selectAll();

    int updateByPrimaryKey(AlgArticle record);
}