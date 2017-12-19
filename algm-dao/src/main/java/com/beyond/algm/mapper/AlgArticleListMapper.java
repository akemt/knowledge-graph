package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgArticleList;
import com.beyond.algm.vo.AlgArticleListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AlgArticleListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlgArticleList record);

    AlgArticleList selectByPrimaryKey(Integer id);

    List<AlgArticleList> selectAll();

    int updateByPrimaryKey(AlgArticleList record);

    List<AlgArticleListVo>searchArticles(AlgArticleListVo algArticleListVo);

    List<Map<String,Object>> queryAlgArticlesListByTitle( String strTitle);
}