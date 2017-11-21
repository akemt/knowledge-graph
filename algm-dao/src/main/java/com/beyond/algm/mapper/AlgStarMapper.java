package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgStar;
import com.beyond.algm.vo.CollectArticlesVo;

import java.util.List;

public interface AlgStarMapper {
    int deleteByPrimaryKey(String starSn);

    int insert(AlgStar record);

    AlgStar selectByPrimaryKey(String starSn);

    List<AlgStar> selectAll();

    int updateByPrimaryKey(AlgStar record);

    List<CollectArticlesVo> selectArticles(CollectArticlesVo collectArticlesVo);
}